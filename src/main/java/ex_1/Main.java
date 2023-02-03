package ex_1;

public class Main {
    public static void main(String[] args) {

        ThreadSymbol threadSymbol = new ThreadSymbol();

        SymbolA symbolA = new SymbolA(threadSymbol);
        SymbolB symbolB = new SymbolB(threadSymbol);
        SymbolC symbolC = new SymbolC(threadSymbol);

        new Thread(symbolA).start();
        new Thread(symbolB).start();
        new Thread(symbolC).start();

    }

}

class ThreadSymbol {
    private int sleep = 100;
    private int count = 0;

    public synchronized void outputSymbolA(String symbol) {
        while (count != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print(symbol);
        notifyAll();
        count++;
    }

    public synchronized void outputSymbolB(String symbol) {
        while (count != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print(symbol);
        notifyAll();
        count--;
        count--;
    }

    public synchronized void outputSymbolC(String symbol) {
        while (count != -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print(symbol + " ");
        notifyAll();
        count++;
    }
}

class SymbolA implements Runnable {

    private final ThreadSymbol threadSymbol;

    public SymbolA(ThreadSymbol threadSymbol) {
        this.threadSymbol = threadSymbol;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 5; i++) {
            threadSymbol.outputSymbolA("A");
        }

    }

}

class SymbolB implements Runnable {

    private final ThreadSymbol threadSymbol;

    public SymbolB(ThreadSymbol threadSymbol) {
        this.threadSymbol = threadSymbol;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 5; i++) {
            threadSymbol.outputSymbolB("B");
        }

    }

}

class SymbolC implements Runnable {

    private final ThreadSymbol threadSymbol;

    public SymbolC(ThreadSymbol threadSymbol) {
        this.threadSymbol = threadSymbol;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 5; i++) {
            threadSymbol.outputSymbolC("C");
        }

    }

}