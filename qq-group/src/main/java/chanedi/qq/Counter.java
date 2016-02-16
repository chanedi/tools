package chanedi.qq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chanedi on 2014/12/12.
 */
public class Counter {

    private List<String> ignoreList;
    private String[] fileNames;
    private static final String REGEX = "^(\\d+)\\t[男|女|未知]\\t\\d+年\\t.+";
    private Map<String, Integer> countMap;

    public Counter(String fileNames) {
        this.fileNames = fileNames.split(" ");
        countMap = new HashMap<String, Integer>();
        ignoreList = new ArrayList<String>();
        ignoreList.add("314154083");
        ignoreList.add("89916538");
        ignoreList.add("51569166");
        ignoreList.add("834803271");
        ignoreList.add("762591404");
    }

    public void count(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Pattern pattern = Pattern.compile(REGEX);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            Matcher matcher = pattern.matcher(readLine);
            if (matcher.find()) {
                String qqNumber = matcher.group(1);
                Integer count = countMap.get(qqNumber);
                if (count == null) {
                    count = 1;
                } else {
                    count++;
                }
                countMap.put(qqNumber, count);
            }
        }

    }

    public void count(String fileName) throws IOException {
        URL url = Counter.class.getResource("/" + fileName + ".txt");
        File file = new File(url.getPath());
        count(file);
    }

    public void count() throws IOException {
        for (String fileName : fileNames) {
            count(fileName);
        }
    }

    public void printOverOneTimes() {
        printOverTimes(1);
    }

    public void printOverTimes(int times) {
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            Integer count = entry.getValue();
            if (count > times) {
                if (ignoreList.contains(entry.getKey())) {
                    continue;
                }
                System.out.println(entry.getKey() + ":\t" + count);
            }
        }
    }

    public void queryTimes(String qqNumber) {
        Integer count = countMap.get(qqNumber);
        System.out.println(count);
    }

}