public class Data {
    private String packet;
    private boolean transfer = true; //true if receiver should wait, false if sender should wait

    public synchronized void send(String packet) {
        while(!transfer){
            try {
                wait();
            } catch (InterruptedException e) { //tu je ina exception
                e.printStackTrace();
            }
        }

        transfer =false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while(transfer){
            try {
                wait();
            } catch (InterruptedException e) { //tu je ina exception
                e.printStackTrace();
            }
        }

        transfer = true;

        notifyAll();
        return packet;
    }


}
