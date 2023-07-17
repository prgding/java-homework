public class Main {
    private int j = 0;

    private class DecrementThread extends Thread {
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (Main.this) {
                    j--;
                    System.out.println("DecrementThread: " + j);
                }
            }
        }
    }

    private class IncrementThread extends Thread {
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (Main.this) {
                    j++;
                    System.out.println("IncrementThread: " + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        DecrementThread dt1 = main.new DecrementThread();
        DecrementThread dt2 = main.new DecrementThread();
        IncrementThread it1 = main.new IncrementThread();
        IncrementThread it2 = main.new IncrementThread();

        dt1.start();
        dt2.start();
        it1.start();
        it2.start();
    }
}
