package chanedi.file;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by test on 2016/2/16.
 */
public class FileRenamer {

    private String dirPath;
    private String regex;

    public FileRenamer(String dirPath, String regex) {
        this.dirPath = dirPath;
        this.regex = regex;
    }

    public void rename() {
        File dir = new File(dirPath);
        Pattern pattern = Pattern.compile(regex);

        File[] files = dir.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            Matcher matcher = pattern.matcher(fileName);

            if (!matcher.find()) {
                ignoreFile(file);
                continue;
            }
            if (matcher.groupCount() != 2) {
                ignoreFile(file);
                continue;
            }

            File destFile = new File(dirPath + "/" + matcher.group(1) + matcher.group(2));
            file.renameTo(destFile);
        }
    }

    private void ignoreFile(File file) {
        System.out.println("Ignore file: " + file.getName());
    }

}
