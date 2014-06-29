/**
 * Created by Lauri on 30.06.2014.
 */

public class UFTest {
    public static void main(String[] args) {

        In in = new In("algs4_book/src/test/resources/tinyUF.txt");
        int N = in.readInt();
        UF uf = new UF(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}