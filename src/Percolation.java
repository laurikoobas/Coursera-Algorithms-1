/**
 * Created by Lauri on 5.07.2014.
 *
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
*/

public class Percolation {
    private int[][] sites;
    private int size;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {              // create N-by-N grid, with all sites blocked
        if (N < 1) throw new IndexOutOfBoundsException("grid size N too small");
        size = N;
        sites = new int[size][size];
        // +2 because we pick 0 to be the "top" node and 1 to be the "bottom" node
        // so we can connect the first row to "top" and last row to "bottom" and use
        // weighted quick union to test for connectivity of first row to last, e.g. percolation
        uf = new WeightedQuickUnionUF(size*size + 2);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i == 1) uf.union(0, xyTo1D(i, j)+1); // have all first row point to 0
                if (i == size) uf.union(1, xyTo1D(i, j)+1); // have all last row point to 1
                sites[i-1][j-1] = 1;
            }
        }
    }

    public void open(int i, int j) {        // open site (row i, column j) if it is not already
        checkIndices(i, j);
        if (!isOpen(i, j)) {
            sites[i-1][j-1] = 0;
        }
    }

    public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
        checkIndices(i, j);
        return sites[i-1][j-1] == 0;
    }

    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        checkIndices(i, j);
        return sites[i-1][j-1] == 2;
    }
    public boolean percolates() {           // does the system percolate?
        return uf.connected(0, 1); // 0 is "top" node, 1 is "bottom" node
    }

    private void checkIndices(int i, int j) {
        if (i < 1 || i > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j < 1 || j > size) throw new IndexOutOfBoundsException("row index j out of bounds");
    }

    /*
     * Maps a 2-coordinate sites index to 1-coordinate array index
     */
    private int xyTo1D(int i, int j) {
        checkIndices(i, j);
        return (i-1)*size + j;
    }

    // print the sites variable in somewhat readable way
    private void printMatrix() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                System.out.printf("%3d", sites[i-1][j-1]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Percolation percolation = new Percolation(3);
        percolation.printMatrix();
        percolation.open(1,1);
//        percolation.printMatrix();
        System.out.println(percolation.percolates());
    }
}