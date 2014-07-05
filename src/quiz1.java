/**
 * Created by Lauri on 30.06.2014.
 */

/*
(seed = 960013)
        Give the id[] array that results from the following sequence of 6 union
        operations on a set of 10 items using the quick-find algorithm.

        5-4 2-9 5-9 2-1 3-0 2-3

        Recall: our quick-find convention for the union operation p-q is to change id[p]
        (and perhaps some other entries) but not id[q].
*/

public class quiz1 {
    public static void main(String[] args) {
        UFTest qf = new UFTest(10);
        // 5-4 2-9 5-9 2-1 3-0 2-3
        qf.union(5,4);
        qf.union(2,9);
        qf.union(5,9);
        qf.union(2,1);
        qf.union(3,0);
        qf.union(2,3);
        StdOut.println(qf.count() + " components");
        qf.printID();
    }
}

