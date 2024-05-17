package pojo;

public class Graph {
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
