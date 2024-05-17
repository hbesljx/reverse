package tools;

import pojo.Node;

import java.util.ArrayList;
import java.util.List;

public class RandomWalk {
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
}
