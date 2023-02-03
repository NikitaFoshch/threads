package ex_0.stopThread.stop_1;

public class App {

    public static void main(String[] args) {
        System.out.println("Start main...");

        MyThread thread = new MyThread();
        new Thread(thread).start();
        new Thread(thread).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.stopIsActive();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        поток остановлен
//        thread.runIsActive();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println("Finish main...");
    }

}

class MyThread implements Runnable {

    private boolean isActive = true;

    public void stopIsActive() {
        isActive = false;
    }

    public void runIsActive() {
        isActive = true;
    }

    @Override
    public void run() {
        System.out.println("Start");
        int count = 1;
        while (isActive) {
            System.out.println(Thread.currentThread().getName() + ": " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
        System.out.println("Finish");
    }

}
