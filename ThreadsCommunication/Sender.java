import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable{
    private Data data;

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String packets[] = generateNumbers();

        for(String packet : packets){
            data.send(packet);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,5000));
            } catch (InterruptedException e) {
                e.printStackTrace(); //ina exception
            }
        }
    }

    public String[] generateNumbers(){
        String[] pole = new String[11];
        //tu kod
        int[] pole2 = new int[10];
        Random random = new Random();

        for(int i = 0; i<pole2.length; i++){
            pole2[i]=i+1;
            pole[i] = String.valueOf(pole2[i]);
        }

        int i = 0;
        while(i != 30) {
            int cislo =random.nextInt(10);
            int cislo2 =random.nextInt(10);
            String temp = pole[cislo];
            pole[cislo] = pole[cislo2];
            pole[cislo2] = temp;
            i++;
        }

        pole[10] = "End";
        return pole;
    }
}
