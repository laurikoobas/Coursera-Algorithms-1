/*
(seed = 460637)
        Give the id[] array that results from the following sequence of 9 union
        operations on a set of 10 items using the weighted quick-union algorithm from lecture.

        6-5 9-5 7-0 1-6 2-3 5-8 0-3 2-5 8-4

        Recall: when joining two trees of equal size, our weighted quick union convention is to
        make the root of the second tree point to the root of the first tree. Also, our weighted
        quick union algorithm uses union by size (number of nodes), not union by height.
*/

public class quiz1_2 {
    public static void main(String[] args) {
        WeightedQuickUnionTest qf = new WeightedQuickUnionTest(10);
        qf.union(6,5);
        qf.union(9,5);
        qf.union(7,0);
        qf.union(1,6);
        qf.union(2,3);
        qf.union(5,8);
        qf.union(0,3);
        qf.union(2,5);
        qf.union(8,4);
        StdOut.println(qf.count() + " components");
        qf.printID();
    }
}
