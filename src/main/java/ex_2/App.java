package ex_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class App {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Igor");
        list.add("Anton");
        list.add("Luke");
        list.add("Lia");
        list.add("Anna");
        int count;

        System.out.println("List people:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter number: ");
            count = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Demon demon = new Demon();
        MyThread myThread = new MyThread(count, list, demon);
        Thread thread = new Thread(demon);
        thread.setDaemon(true);
        thread.start();
        System.out.println(myThread.call());


    }


}

class MyThread implements Callable<String> {

    private final int number;
    private final List<String> list;
    private final Demon demon;

    public MyThread(int number, List<String> list, Demon demon) {
        this.number = number;
        this.list = list;
        this.demon = demon;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(1000);

            demon.setActive();
            return ("\n" + "\n" + list.get(number - 1));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class Demon implements Runnable {

    private boolean isActive = true;

    public void setActive() {
        isActive = false;
    }

    @Override
    public void run() {
        System.out.println();
        while (isActive) {
            try {
                Thread.sleep(50);
                System.out.print('.');
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

