/**
 * Created by Lauri on 5.07.2014.
 *
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
*/

public class Percolation {
    private int[][] sites;
    private int size;
    private WeightedQuickUnionUF uf;
//    private String[] sequence;
//    private int sequenceIterator;
    private boolean percolated;

    public Percolation(int N) {              // create N-by-N grid, with all sites blocked
        if (N < 1) throw new IllegalArgumentException("grid size N too small");
        size = N;
        sites = new int[size][size];
//        sequence = new String[size*size];
//        sequenceIterator = 0;
        // +2 because we pick 0 to be the "top" node and 1 to be the "bottom" node
        // so we can connect the first row to "top" and last row to "bottom" and use
        // weighted quick union to test for connectivity of first row to last, e.g. percolation
        uf = new WeightedQuickUnionUF(size*size + 2);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                sites[i-1][j-1] = 0;
            }
        }
    }

    public void open(int i, int j) {        // open site (row i, column j) if it is not already
        checkIndices(i, j);
        int midPoint, destination;
        if (!isOpen(i, j)) {
            sites[i-1][j-1] = 1;
//            sequence[sequenceIterator++] = String.format("%d %d", i, j);
            midPoint = xyTo1D(i, j);
            if (i == 1) uf.union(0, midPoint); // have first row elements point to 0 (the "top")
            if (i == size && !percolated) uf.union(1, midPoint); // have last row elements point to 1 (the "bottom")
            // connect to top
            if (i > 1 && isOpen(i - 1, j)) {
                destination = xyTo1D(i - 1, j);
                if (!uf.connected(midPoint, destination)) {
                    uf.union(destination, midPoint);
                }
            }
            // connect to bottom
            if (i < size && isOpen(i + 1, j)) {
                destination = xyTo1D(i + 1, j);
                if (!uf.connected(midPoint, destination)) {
                    uf.union(destination, midPoint);
                }
            }
            // connect to left
            if (j > 1 && isOpen(i, j - 1)) {
                destination = xyTo1D(i, j - 1);
                if (!uf.connected(midPoint, destination)) {
                    uf.union(destination, midPoint);
                }
            }
            // connect to right
            if (j < size && isOpen(i, j + 1)) {
                destination = xyTo1D(i, j + 1);
                if (!uf.connected(midPoint, destination)) {
                    uf.union(destination, midPoint);
                }
            }
        }
    }

    public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
        checkIndices(i, j);
        return sites[i-1][j-1] == 1;
    }

    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        checkIndices(i, j);
        return uf.connected(0, xyTo1D(i, j));
    }

    public boolean percolates() {           // does the system percolate?
        if (percolated) {
            return true;
        }
        else {
            percolated = uf.connected(0, 1); // 0 is "top" node, 1 is "bottom" node
            return percolated;
        }
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
        return (i-1)*size + j + 1;
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
/*
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Out out;

        N = 7;
        Percolation percolation = new Percolation(N);
        percolation.printMatrix();
        percolation.open(6, 1);
        percolation.open(7, 1);
        percolation.open(7, 2);
        percolation.open(7, 4);
        percolation.open(1, 1);
        percolation.open(1, 5);
        percolation.open(2, 5);
        percolation.open(3, 5);
        percolation.open(4, 5);
        percolation.open(5, 5);
        percolation.open(6, 5);
        percolation.open(7, 5);
/*
        6 1
        7 1
        7 2
        7 4
        1 1
        1 5
        2 5
        3 5
        4 5
        5 5
        6 5
        7 5
        2 1
        4 1
        5 1
        3 1

        percolation.printMatrix();
        System.out.println(percolation.percolates());

        // write to a file
        out = new Out("test.txt");
        out.println(N);
        for (int i = 0; i < percolation.sequenceIterator; i++) {
            out.println(percolation.sequence[i]);
        }
        out.close();
    }
*/
}