package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class txtToList {
    private static String[] works = null;
    private static int n=0;
    public static List<String> read(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader rea = new BufferedReader(fr);
        String line = null;
        List<String> res=new ArrayList<>();
        while((line=rea.readLine())!=null) {//先读取一行字符
            works=line.split("\"|\\+|-|\\*|/|,|<|>|\\.|\\?|;|:|'|\\[|\\]|\\{|}|`|~|!|@|#|\\$|%|\\^|&|\\*|\\(|\\)|-|\\_|=|\\\\|\\||\n| ");//然后以空格作为间隔读单个单词
            n=0;
            while(works[n]!=null) {
                works[n]=works[n].toLowerCase();//转为小写
                res.add(works[n]);
                n++;
                if (n >= works.length) break;//这里是为了防止数组访问超过其大小
            }
        }
        return res;
    }

}
