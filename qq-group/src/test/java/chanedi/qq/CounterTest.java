package chanedi.qq;

import junit.framework.TestCase;

/**
 * Created by test on 2016/2/16.
 */
public class CounterTest extends TestCase {

    public void testQueryTimes() throws Exception {
        Counter counter = new Counter("1 2 3 4");
        counter.count();
        counter.queryTimes("51964987");
    }

    public void testPrintOverOneTimes() throws Exception {
        Counter counter = new Counter("1 2 3 4");
        counter.count();
        counter.printOverOneTimes();
    }
}