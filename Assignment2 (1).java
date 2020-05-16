// 100 22 30 for race condition

public class Assignment2 {

    public static int[] buffer;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            System.out.print("please provide value of n, k and t.");
            System.exit(1);
        }

        try {
            int n = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]);
            int t = Integer.parseInt(args[2]);
            buffer = new int[n];

            for (int i = 0; i < n; i++) {
                buffer[i] = 0;
            }

            Producer pro = new Producer(n, k, t, buffer);
            Consumer con = new Consumer(n, k, t, buffer);
            pro.start();
            con.start();

            pro.join();

            con.join();
        } catch (IllegalArgumentException IAE) {
            System.out.println("Please provide valid values for n, k and t");
        }
    }

}
