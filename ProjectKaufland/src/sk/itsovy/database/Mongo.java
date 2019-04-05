package sk.itsovy.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;
import org.bson.types.ObjectId;
import sk.itsovy.bill.Bill;
import sk.itsovy.items.Item;
import sk.itsovy.items.Pcsinterface;
import sk.itsovy.items.drink.DraftInterface;
import sk.itsovy.items.food.Fruit;
import sk.itsovy.main.Globals;

import java.text.SimpleDateFormat;

public class Mongo {

    private static Mongo mongo = new Mongo();
    private Mongo(){}

    public static Mongo getInstanceMongo(){
        return mongo;
    }

    public MongoDatabase getMongoConn(){
        try {
            MongoClient mongoClient = new MongoClient(Globals.host, Globals.port );
            MongoCredential credential = MongoCredential.createCredential(Globals.usernameMongo, Globals.databaseMongo,
                    Globals.passwordMongo.toCharArray());
            System.out.println("connected to mongodb");
            MongoDatabase database = mongoClient.getDatabase(Globals.databaseMongo);
            for (String name: database.listCollectionNames()){
                System.out.println("Collection: "+name);
            }
            return database;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public MongoCollection<Document> accessToBill(){

        MongoDatabase database = getMongoConn();

        try {
            MongoCollection<Document>  collection = database.getCollection("bill");
            System.out.println("Collection bill selected");
            return collection;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public MongoCollection<Document> accessToItems(){
        MongoDatabase database = getMongoConn();

        try {
            MongoCollection<Document>  collection = database.getCollection("items");
            System.out.println("Collection items selected");
            return collection;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void addBillToMongo(Bill bill){
        MongoCollection<Document> collectionBill = accessToBill();
        MongoCollection<Document> collectionItem = accessToItems();


        SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
        String time = formattime.format(bill.getDatetime());

        SimpleDateFormat formatdate = new SimpleDateFormat("dd:MM:yyyy");
        String date = formatdate.format(bill.getDatetime());

        Document documentBill = new Document("date", date)
                .append("time", time)
                .append("totalPrice", bill.getFinalPrice());
        collectionBill.insertOne(documentBill);
        System.out.println("Document Bill inserted succesfully");
        ObjectId id = documentBill.getObjectId("_id");

        for (Item item:bill.getList()) {
            Document documentItem = new Document("name",item.getName())
                    .append("idBill",id)
                    .append("price",item.getPrice());
            if (item instanceof DraftInterface){
                documentItem.append("count",((DraftInterface) item).getVolume());
                documentItem.append("unit", "l");

            }else if(item instanceof Fruit){
                documentItem.append("count",((Fruit) item).getWeight());
                documentItem.append("unit", "kg");

            }else if(item instanceof Pcsinterface){
                documentItem.append("count",((Pcsinterface) item).getAmount());
                documentItem.append("unit", "pcs");
            }
            collectionItem.insertOne(documentItem);
            System.out.println("Document bill inserted");
        }
    }




}