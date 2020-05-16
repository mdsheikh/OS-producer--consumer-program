
import java.util.Random;

public class Consumer extends Thread {

    private int k;
    private int n;
    private int t;
    private Random rand = new Random();
    private int next_out = 0;
    private int[] buffer;

    public Consumer(int n, int k, int t, int[] buffer) {
        this.k = k;
        this.n = n;
        this.t = t;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            int t1 = rand.nextInt(t);
            try {
                Thread.sleep(t1 * 1000);
                
                int k2 = rand.nextInt(k);
                for (int i = 0; i < k2 - 1; i++) {
                    int data = buffer[(next_out + i) % n];
                    if (data > 1) {
                        System.out.println("Race Condition Occured");
                        System.exit(0);
                    }
                    buffer[(next_out + i) % n] = 0;
                   
                }

                next_out = (next_out + k2) % n;
                 System.out.println("Consumer executed: " +  displayBuffer());
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
