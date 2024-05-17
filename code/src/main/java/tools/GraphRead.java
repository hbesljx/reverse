package tools;

import pojo.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GraphRead {
    //将有向图节点集合保存到文件中，方便graphviz读取并且生成有向图
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
}
