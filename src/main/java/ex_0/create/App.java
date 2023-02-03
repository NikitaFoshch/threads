package ex_0.create;

public class App {
    public static void main(String[] args) {

        Runnable runnable =() ->{
            System.out.println("Thread start: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Tread finish: " + Thread.currentThread().getName());
        };

        new MainThread().start();
        new MainThread().start();
        new MainThread().start();
        new MainThread().start();

        Thread thread = new Thread(runnable,"Thread_11");
        thread.start();

        Thread thread1 = new Thread(new RunnableThread("Thread_00"));
        thread1.start();

        Thread thread2 = new Thread(runnable,"Demon");
        thread2.setDaemon(true);
        thread2.start();


    }
}

class MainThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread start: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tread finish: " + Thread.currentThread().getName());
    }
}

class RunnableThread implements Runnable{

    private final String name;

    public RunnableThread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Thread start: " + getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tread finish: " + getName());
    }
}
