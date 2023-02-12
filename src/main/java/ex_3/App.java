package ex_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        final ListNumbers listNumbers = new ListNumbers(list);
        new Thread(listNumbers).start();
        new Thread(listNumbers).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Size list: " + list.size());

    }

}

class ListNumbers implements Runnable {

    final List<Integer> list;

    public ListNumbers(List<Integer> list) {
        this.list = list;
    }


    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

    }

}
