package tools;

import pojo.Graph;
import pojo.Node;

import java.util.ArrayList;
import java.util.List;

public class QueryWords {
    private static int MAXN=100;
    private static int[][] pathmatirx=new int[MAXN][MAXN];//记录对应点的最小路径的前驱点，例如p(1,3) = 2 说明顶点1到顶点3的最小路径要经过2
    private static int[][] shortPath=new int[MAXN][MAXN];//记录顶点间的最小路径值
    //查询桥接词
    public static String queryBridgeWords(List<Node> nodes,String word1, String word2){
        String res="";
        List<String> list = query(nodes, word1, word2);
        if(list.get(2)=="0"){
            res= "No \""+word1+"\" or \""+word2+"\" in the graph!";
        }
        if(list.get(2)=="1"){
            res= "No bridge words from \""+word1+"\" to \""+word2+"\"!";
        }
        if(list.get(2)=="2"){
            res= "The bridge words from \""+word1+"\" to \""+word2+"\" are:"+list.get(3);
        }
        else if(list.get(2)=="3"){
            String s="The bridge words from \""+word1+"\" to \""+word2+"\" are:";
            for(int i=3;i<list.size()-1;i++){
                s=s+list.get(i)+",";
            }
            s=s+list.get(list.size()-1)+".";
            res= s;
        }
        return res;
    }
    public static String generateNewText(String s,List<Node> nodes){
        String res="";
        String[] works = null;
        works=s.split("\"|\\+|-|\\*|/|,|<|>|\\.|\\?|;|:|'|\\[|\\]|\\{|}|`|~|!|@|#|\\$|%|\\^|&|\\*|\\(|\\)|-|\\_|=|\\\\|\\||\n| ");
        for(int i=0;i<works.length-2;i++){
            res=res+works[i]+" ";
            List<String> list=query(nodes,works[i],works[i+1]);
            if(list.get(2)=="2"){
                res=res+list.get(3)+" ";
            }
            if(list.get(2)=="3"){
                int max=list.size()-1;
                int min=3;
                int f = (int)Math.random() * (max - min + 1) + min;
                res=res+list.get(f);
            }
        }
        res=res+works[works.length-1];
        return res;
    }
    public static List<String> query(List<Node> nodes,String word1,String word2){
        List<String> res=new ArrayList<>();
        List<Integer> tids=new ArrayList<>();//存储查询到的结果集
        res.add(word1);
        res.add(word2);
        int flag1=0;
        int flag2=0;
        for (Node node : nodes) {
            if(node.getS().equals(word1)){
                flag1++;
            }
            if(node.getS().equals(word2)){
                flag2++;
            }
        }
        if(flag1==0||flag2==0){
            res.add("0");//设置状态码
            return res;
//            return "No \""+word1+"\" or \""+word2+"\" in the graph!";
        }
        for (Node node : nodes) {
            if(node.getS().equals(word1)){
                for (Integer aid : node.getIds()) {
                    for (Integer bid : nodes.get(aid).getIds()) {
                        if(nodes.get(bid).getS().equals(word2)){
                            tids.add(aid);
                        }
                    }
                }
            }
        }
        if(tids.size()==0){
            res.add("1");
            return res;
//            return "No bridge words from \""+word1+"\" to \""+word2+"\"!";
        }
        else{
//            res="The bridge words from \""+word1+"\" to \""+word2+"\" are:";
            if (tids.size()==1){
                res.add("2");
                res.add(nodes.get(tids.get(0)).getS());
                return res;
//                res=res+nodes.get(tids.get(0)).getS()+".";
            }else{
                res.add("3");
                for(int i=0;i<tids.size();i++){
                    res.add(nodes.get(tids.get(i)).getS());
//                    res=res+nodes.get(tids.get(i)).getS()+",";
                }
                return res;
//                res=res+nodes.get(tids.get(tids.size()-1)).getS()+".";
            }
        }
    }
    public static void calcShortestPath(List<Node> nodes,String word1,String word2){
        int a=0;
        int b=0;
        for (Node node : nodes) {
            if(node.getS().equals(word1)){
                a=node.getId();
            }
            if(node.getS().equals(word2)){
                b=node.getId();
            }
        }
        if(a==0||b==0){
            System.out.println("No words like "+word1+" or "+word2+"!");
            return;
        }
        Graph G=new Graph();
        G.setVexnum(nodes.size());//顶点数
        G.setMatirx(tools.Graph.matrix(nodes));
        short_path_floyd(nodes,G, pathmatirx, shortPath,a,b);
    }
    public static void short_path_floyd(List<Node> nodes,Graph G, int[][] P, int[][] D,int a,int b){
        int v, w, k;
        //初始化floyd算法的两个矩阵
        for(v = 0; v < G.getVexnum(); v++){
            for(w = 0; w < G.getVexnum(); w++){
                D[v][w] = G.getMatirx()[v][w];
                P[v][w] = w;
            }
        }

        //这里是弗洛伊德算法的核心部分
        //k为中间点
        for(k = 0; k < G.getVexnum(); k++){
            //v为起点
            for(v = 0 ; v < G.getVexnum(); v++){
                //w为终点
                for(w =0; w < G.getVexnum(); w++){
                    if(D[v][w] > (D[v][k] + D[k][w])){
                        D[v][w] = D[v][k] + D[k][w];//更新最小路径
                        P[v][w] = P[v][k];//更新最小路径中间顶点
                    }
                }
            }
        }
        v=a;
        w=b;
//        v = 26;
//        w = 15;
        //求 26 到 15的最小路径
        if(D[v][w]>MAXN){
            System.out.println("No roads from \""+nodes.get(v).getS()+"\" to \""+nodes.get(w).getS()+"\"!");
            return;
        }
        System.out.print("\n"+nodes.get(v).getS()+"->"+nodes.get(w).getS()+"的最小路径为:"+D[v][w]+"\n");
        k = P[v][w];
        System.out.print("path::"+nodes.get(v).getS());//打印起点
        while(k != w){
            System.out.print("->"+nodes.get(k).getS());//打印中间点
            k = P[k][w];
        }
        System.out.print("->"+nodes.get(w).getS()+"\n");
    }
}
