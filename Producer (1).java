
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    private int k;
    private int n;
    private int t;
    private Random rand = new Random();
    private int next_in = 0;
    private int[] buffer;

    public Producer(int n, int k, int t, int[] buffer) {
        this.k = k;
        this.n = n;
        this.t = t;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int k1 = rand.nextInt(k);
                for (int i = 0; i < k1 - 1; i++) {
                    buffer[(next_in + i) % n] += 1;
                }
                next_in = (next_in + k1) % n;

                int t1 = rand.nextInt(t);

                Thread.sleep(t1 * 1000);
                System.out.println("Producer executed: " + displayBuffer());
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String displayBuffer() {
        String out = "[ ";
        for (int i = 0; i < buffer.length; i++) {
            out += buffer[i] + " ";
        }

        out += "] ";

        return out;
    }
}
