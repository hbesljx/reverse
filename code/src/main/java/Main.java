import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static int maxInt=1000;
    private static List<Node> nodes=new ArrayList<>();
    private static String[] works = null;
    private static int n=0;
    private static int MAXN=100;
    private static int[][] pathmatirx=new int[MAXN][MAXN];//记录对应点的最小路径的前驱点，例如p(1,3) = 2 说明顶点1到顶点3的最小路径要经过2
    private static int[][] shortPath=new int[MAXN][MAXN];//记录顶点间的最小路径值
    public static class Node {
        private Integer id;
        private String s;//节点内容
        private int a;//节点出现次数
        private List<Integer> ids;//节点的子节点集合
        private List<Integer> weights;//定义边的权重
        public Node(){}

        public Node(Integer id, String s, int a, List<Integer> ids,List<Integer> weights) {
            this.id = id;
            this.s = s;
            this.a = a;
            this.ids = ids;
            this.weights=weights;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public List<Integer> getIds() {
            return ids;
        }

        public void setIds(List<Integer> ids) {
            this.ids = ids;
        }
        public List<Integer> getWeights() {
            return weights;
        }

        public void setWeights(List<Integer> weights) {
            this.weights = weights;
        }
    }
    public static class Graph {
        private char[] vexs=new char[100];
        private int vexnum;//顶点数
        private int edgnum;//边数
        private int[][] matirx=new int[100][100];//邻接矩阵

        public Graph(){}
        public Graph(char[] vexs, int vexnum, int edgnum, int[][] matirx) {
            this.vexs = vexs;
            this.vexnum = vexnum;
            this.edgnum = edgnum;
            this.matirx = matirx;
        }

        public char[] getVexs() {
            return vexs;
        }

        public void setVexs(char[] vexs) {
            this.vexs = vexs;
        }

        public int getVexnum() {
            return vexnum;
        }

        public void setVexnum(int vexnum) {
            this.vexnum = vexnum;
        }

        public int getEdgnum() {
            return edgnum;
        }

        public void setEdgnum(int edgnum) {
            this.edgnum = edgnum;
        }

        public int[][] getMatirx() {
            return matirx;
        }

        public void setMatirx(int[][] matirx) {
            this.matirx = matirx;
        }
    }
    public static int[][] matrix(List<Node> nodes){
            //创建邻接矩阵
            int length=nodes.size();
            int[][] matrix=new int[length][length];
            for(int i=0;i<length;i++){//初始化矩阵
                for(int j=0;j<length;j++){
                    if(i==j){
                        matrix[i][j]=0;
                    }
                    else{
                        matrix[i][j]=maxInt;
                    }
                }
            }
            for (Node node : nodes) {
                for(int i=0;i<node.getIds().size();i++){
                    matrix[node.getId()][node.getIds().get(i)]=node.getWeights().get(i);
                }
            }
            return matrix;
        }
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
    public static void graphRead(List<Node> nodes) throws IOException {
        String s="digraph abc{";
        for (Node node : nodes) {
            List<Integer> ids = node.getIds();
            if(ids!=null){
                for(int i=0;i<ids.size();i++){
                    s=s+node.getS()+"->"+nodes.get(ids.get(i)).getS()+"[label=\""+node.getWeights().get(i)+"\""+"]"+";";
                }
            }
        }
        s=s+"}";
        FileWriter fw = new FileWriter("E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\graph.gv");
        fw.write(s);
        fw.close();
    }
    public static List<Node> showDirectedGraph(String filePath) throws IOException {//获取生成的有向图节点集合
        List<String> list0 = read(filePath);//将txt文件转为List集合
//        System.out.println(list0);
        Set<String> middleHashSet = new HashSet<String>(list0);//转hashSet去重
        List<String> list1 = new ArrayList<String>(middleHashSet);
//        System.out.println(list1);
        for(int i=0;i< list1.size();i++){
            int k=0;
            List<Integer> ids=new ArrayList<>();
            List<Integer> weights=new ArrayList<>();
            for(int j=0;j< list0.size();j++){
                if(list0.get(j).equals(list1.get(i))){
                    k++;
                    if(j!= list0.size()-1){
                        ids.add(list1.indexOf(list0.get(j+1)));
                    }
                }
            }
            Node node=new Node();
            node.setId(i);
            node.setS(list1.get(i));
            node.setA(k);
//            ids.add(i);
            Set<Integer> IdsMiddleHashSet = new HashSet<Integer>(ids);//转hashSet去重
            List<Integer> ids1 = new ArrayList<Integer>(IdsMiddleHashSet);
            for(int p=0;p< ids1.size();p++){
                int w=0;
                for(int q=0;q<ids.size();q++){
                    if(ids.get(q)==ids.get(p)){
                        w++;
                    }
                }
                weights.add(w);
            }
            node.setIds(ids1);
            node.setWeights(weights);
            nodes.add(node);
        }
        return nodes;
    }
    //查询桥接词
    public static String queryBridgeWords(List<Node> nodes, String word1, String word2){
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
    public static List<String> query(List<Node> nodes, String word1, String word2){
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
    public static void calcShortestPath(List<Node> nodes, String word1, String word2){
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
        G.setMatirx(matrix(nodes));
        short_path_floyd(nodes,G, pathmatirx, shortPath,a,b);
    }
    public  static void short_path_floyd(List<Node> nodes,Graph G, int[][] P, int[][] D, int a, int b){
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

    public static void randomWalk(List<Node> nodes){
        int k=0;
        int randomKey=(int)(Math.random()*(nodes.size()));//生成一个0到nodes.size()-1的随机数
        List<List<Integer>> roads=new ArrayList<>();//存储已经走过的边的两个顶点
        String s=nodes.get(randomKey).getS();
        int a=nodes.get(randomKey).getId();//记录当前遍历到哪个id了
        int idsLength=nodes.get(randomKey).getIds().size();
        while(true){
            boolean flag=false;
            if(idsLength==0){
                break;
            }
            int randomId=nodes.get(a).getIds().get((int)(Math.random()*(idsLength)));//生成一个随机数，选取下一个点
            List<Integer> road=new ArrayList<>();
            road.add(a);
            road.add(randomId);
            if(k!=0){
                for (List<Integer> list : roads) {
                    if(list.get(0).equals(road.get(0))&&list.get(1).equals(road.get(1))){
                        flag=true;
                    }
                }
            }
            roads.add(road);
            if(flag==true){
                break;
            }
            s=s+"->"+nodes.get(randomId).getS();
            a=randomId;
            idsLength=nodes.get(a).getIds().size();
            k++;
        }
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("==================showDirectedGraph()==================");
        String filePath="E:\\课程学习\\9_软件工程\\lab01\\src\\main\\resources\\a.txt";
        List<Node> nodes = showDirectedGraph(filePath);
        System.out.println("文件已保存!");
        System.out.println("========================================================");
        System.out.println(" ");

        System.out.println("===================queryBridgeWords()===================");
        String word1="people";
        String word2="stand";
        String queryBridgeWords = queryBridgeWords(nodes, word1, word2);
        System.out.println(queryBridgeWords);
        System.out.println("========================================================");
        System.out.println(" ");

        System.out.println("===================generateNewText()===================");
        String s="If someone you their life,they will room for you.";
        String res= generateNewText(s,nodes);
        System.out.println(res);
        System.out.println("========================================================");
        System.out.println(" ");

        System.out.println("===================calcShortestPath()===================");
        String word11="life";
        String word22="friends";
        calcShortestPath(nodes,word11,word22);
        System.out.println("========================================================");
        System.out.println(" ");

        System.out.println("======================randomWalk()======================");
        randomWalk(nodes);
        System.out.println("========================================================");
    }
}
