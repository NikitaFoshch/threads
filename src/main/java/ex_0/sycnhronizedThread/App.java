package ex_0.sycnhronizedThread;

public class App {

    public static void main(String[] args) {
        Resources resources = new Resources();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyThread(resources));
            thread.start();
        }

    }

}

class Resources{int x = 0;}

class MyThread implements Runnable{
    private final Resources resources;
    public MyThread(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        synchronized (resources){
            resources.x=1;
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " " + resources.x);
                resources.x++;
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
