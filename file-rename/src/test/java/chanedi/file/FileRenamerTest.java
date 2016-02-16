package chanedi.file;

import junit.framework.TestCase;

/**
 * Created by test on 2016/2/16.
 */
public class FileRenamerTest extends TestCase {

    private static final String regex1 = "^.*\\[(\\d+)\\].*(\\.[^.]+$)";
    private static final String regex2 = "^.* - (\\d+) .*(\\.[^.]+$)";

    public void testRename() throws Exception {
        String dirPath = "E:\\Chanedi\\Downloads\\完成\\[SumiSora&CASO][Steins_Gate][01-24][GB_Big5][720p]";
        FileRenamer renamer = new FileRenamer(dirPath, regex1);
        renamer.rename();
    }
}