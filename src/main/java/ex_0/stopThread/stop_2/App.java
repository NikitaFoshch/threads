package ex_0.stopThread.stop_2;

public class App {

    public static void main(String[] args) {
        System.out.println("Start main...");

        MyThread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(1000);

            myThread.interrupt();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Finish main...");

    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Start...");
        int count = 1;
        while (!isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + " " + count);
            count++;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + Thread.currentThread().getName());
                System.out.println(isInterrupted());
                interrupt();
            }
        }

        System.out.println("Finish...");
    }

}
