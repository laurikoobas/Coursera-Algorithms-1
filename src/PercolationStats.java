/**
 * Created by Lauri on 6.07.2014.
 */
public class PercolationStats {
    private double[] threshold;
    private int times;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("N or T below 0");

        times = T;
        Percolation percolation;
        int i, j; // coordinates for the grid
        double thresholdCounter; // count the number of opened sites in a single grid

        threshold = new double[T];
        for (int t = 0; t < T; t++) {
            thresholdCounter = 0;
            percolation = new Percolation(N);
            while (!percolation.percolates()) {
                i = StdRandom.uniform(N) + 1;
                j = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    ++thresholdCounter;
                }
            }
            threshold[t] = thresholdCounter / (N*N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        if (times > 1) {
            return StdStats.stddev(threshold);
        } else {
            return Double.NaN;
        }
    }
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(times);
    }
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(times);
    }
    // test client, described below
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(N, T);
//        for (int i = 0; i < percolationStats.threshold.length; i++) {
//            System.out.println(percolationStats.threshold[i]);
//        }
        System.out.printf("mean                    = %f%n", percolationStats.mean());
        System.out.printf("stddev                  = %f%n", percolationStats.stddev());
        System.out.printf("95%% confidence interval = %f, %f", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
