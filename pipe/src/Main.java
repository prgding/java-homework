import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            PipedOutputStream out = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(out);

            new Thread(new Producer(out)).start();
            new Thread(new Consumer(in)).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Consumer implements Runnable {
    private final PipedInputStream in;

    public Consumer(PipedInputStream in) {
        this.in = in;
    }

    @Override
    public synchronized void run() {
        // 消费数据
        try {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) != -1) {
                String data = new String(buffer, 0, length);//从管道流读取数据
                System.out.println("Consumed: " + data);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {
    private final PipedOutputStream out;

    public Producer(PipedOutputStream out) {
        this.out = out;
    }


    @Override
    public synchronized void run() {
        // 生产数据
        try {
            for (int i = 0; i < 5; i++) {
                String data = "Data " + i;
                out.write(data.getBytes());//写入管道流
                System.out.println("Produced: " + data);
                Thread.sleep(1000);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
