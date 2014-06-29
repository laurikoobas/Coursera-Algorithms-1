/**
 * Created by Lauri on 30.06.2014.
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
