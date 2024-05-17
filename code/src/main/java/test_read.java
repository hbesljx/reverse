
import org.testng.annotations.Test;
import pojo.Node;
import tools.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class test_read {
    @Test
    public void testRead() throws IOException {
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<String> res= txtToList.read(filePath);
    }

    @Test
    public void test01() throws IOException {
        //测试Graph的showDirectedGraph()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
    }
    @Test
    public void test02(){
        String s="a*b c\nd|e";
        System.out.println(s);
        String[] strings = s.split("\"|\\+|-|\\*|/|,|<|>|\\.|\\?|;|:|'|\\[|\\]|\\{|}|`|~|!|@|#|\\$|%|\\^|&|\\*|\\(|\\)|-|\\_|=|\\\\|\\||\n| ");
        for (String string : strings) {
            System.out.print(string);
        }
        System.out.println("");
        String s1="digraph abc{";
        System.out.println(s1);
    }
    @Test
    public void test03() throws IOException {
        //测试GraphRead的graphRead方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        GraphRead.graphRead(nodes);
    }
    @Test
    public void test04() throws IOException {
        //测试QueryWords的queryBridgeWords()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        String word1="people";
        String word2="stand";
        String word3="your";
        String word4="life";
        String queryBridgeWords = QueryWords.queryBridgeWords(nodes, word1, word2);
        System.out.println(queryBridgeWords);
    }
    @Test
    public void test_list(){
        List<String> list=new ArrayList<>();
        String s1="abc";
        String s2="def";
        list.add(s1);
        list.add(s2);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
    @Test
    public void test05() throws IOException {
        //测试QueryWords的query()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        String word1="people";
        String word2="stand";
        List<String> list = QueryWords.query(nodes, word1, word2);
        System.out.println(list);
    }
    @Test
    public void test06() throws IOException {
        //测试QueryWords的generateNewText()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        String s="If someone you their life,they will room for you.";
        String res=QueryWords.generateNewText(s,nodes);
        System.out.println(res);
    }
    @Test
    public void test07() throws IOException {
        //测试Graph的matrix()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        int[][] matrix = Graph.matrix(nodes);
        for (Node node : nodes) {
            System.out.print(node.getS()+",");
        }
        System.out.println(nodes.size());
        System.out.println("\n");
        System.out.println(nodes.get(0).getS());
        System.out.println(nodes.get(49).getS());
        System.out.println(matrix[0][49]);
        for(int i=0;i< nodes.size();i++){
            for(int j=0;j<nodes.size();j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("\n");
        }
        System.out.println(nodes.get(26).getS()+" "+nodes.get(12).getS()+" "+nodes.get(30).getS()+" "+nodes.get(49).getS()+" "+nodes.get(47).getS()+" "+nodes.get(3).getS()+" "+nodes.get(33).getS()+" "+nodes.get(1).getS()+nodes.get(20).getS()+nodes.get(15).getS()+".");
    }
    @Test
    public void test08() throws IOException {
        //测试QueryWords的calcShortestPath()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        String word1="life";
        String word2="friends";
        String word3="aaa";
        QueryWords.calcShortestPath(nodes,word2,word1);
    }
    @Test
    public void test09() throws IOException {
        //测试RandomWalk的randomWalk()方法
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = Graph.showDirectedGraph(filePath);
        RandomWalk.randomWalk(nodes);
    }
    @Test
    public void random(){
        int a=(int) Math.random()*50;//始终为0
        System.out.println(a);
        int b=(int)(Math.random()*50);
        System.out.println(b);
    }
}

