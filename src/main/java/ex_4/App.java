package ex_4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class App {

    static final CountDownLatch START = new CountDownLatch(13);
    static final int SIMPLE_ROAD = 50000;
    static final int TUNNEL = 5000;
    static final PlaceCar place = new PlaceCar();
    static final Semaphore semaphore = new Semaphore(3);


    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Car(place, i, (int) (Math.random() * (120 - 100) + 100))).start();
        }

        while (START.getCount() > 3) {
            try {
                System.out.println("Ready!");
                START.countDown();
                Thread.sleep(1000);
                System.out.println("Steady!");
                START.countDown();
                Thread.sleep(1000);
                System.out.println("Go!");
                START.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class PlaceCar {
    int placeRace = 1;
}

class Car implements Runnable {
    private final PlaceCar place;
    private final int number;
    private int speed;

    private long timeBefore;
    private long timeAfter;

    public Car(PlaceCar place, int number, int speed) {
        this.place = place;
        this.number = number;
        this.speed = speed;
    }

    @Override
    public void run() {
        timeBefore = System.currentTimeMillis();
        try {
            App.START.countDown();
            App.START.await();
            Thread.sleep(App.SIMPLE_ROAD / speed);
            App.semaphore.acquire();
            System.out.println("Went into the tunnel - Car number: " + number);
            this.speed = (int) (Math.random() * (120 - 100) + 100);
            Thread.sleep(App.TUNNEL / speed);
            this.speed += 30;
            App.semaphore.release();
            Thread.sleep(App.SIMPLE_ROAD / speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        timeAfter = System.currentTimeMillis();
        synchronized (place) {

            System.out.println("Place: " + place.placeRace +
                    " - Car number: " + number + " Car speed: " + speed +
                    " - Time: " + (timeAfter - timeBefore));
            place.placeRace++;
        }


    }
}
