package thread_assignment;

public class DisplayTimer implements Runnable {

    public void run() {
        while (true) {
            try {
                for (int i = 0; i <= 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(i);
                }
                System.out.println("Thread Sleeping for 10s");
                Thread.sleep(10000);
                System.out.println("Timer Refereshed");
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                break;
            }
        }
    }

    public static void main(String[] args) {
        DisplayTimer dS = new DisplayTimer();
        Thread t1 = new Thread(dS, "t1");
        t1.start();
    }
}