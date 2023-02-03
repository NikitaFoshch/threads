package ex_3;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        DataList dataList = new DataList();


        final ListNumbers listNumbers = new ListNumbers(dataList);
        new Thread(listNumbers).start();
        new Thread(listNumbers).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Size list: " + dataList.list.size());

    }

}

class DataList {
    List<Integer> list = new ArrayList<>();
}

class ListNumbers implements Runnable {

    final DataList dataList;

    public ListNumbers(DataList dataList) {
        this.dataList = dataList;
    }


    @Override
    public void run() {
        synchronized (dataList) {
            for (int i = 0; i < 100; i++) {
                dataList.list.add(i);
            }
        }
    }

}
