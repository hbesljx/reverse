package pojo;

import java.util.List;

public class Node {
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
