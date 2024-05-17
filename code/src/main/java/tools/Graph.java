package tools;

import pojo.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private static int maxInt=1000;
    private static List<Node> nodes=new ArrayList<>();
    public static List<Node> showDirectedGraph(String filePath) throws IOException {//获取生成的有向图节点集合
        List<String> list0 = txtToList.read(filePath);//将txt文件转为List集合
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
//        for (Node node : nodes) {
//            System.out.print(node.getS());
//            System.out.println(" ");
//            List<Integer> ids = node.getIds();
//            for (Integer id : ids) {
//                System.out.print(id);
//                System.out.print("-");
//            }
//            System.out.println("\n");
//        }
        return nodes;
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
}
