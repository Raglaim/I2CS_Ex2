package assignments.Ex2.MainFiles.Classes;

import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;

public class PixelsContainer {

    private Pixel2D[] LIST = {};

    public PixelsContainer() {}

    public PixelsContainer(int size) {this.LIST = new Pixel2D[size];}

    public PixelsContainer(Pixel2D[] list) {this.LIST = list;}

    public Pixel2D[] getList() {return LIST;}

    public void enqueue(Pixel2D p) {
        Pixel2D[] tempList = new Pixel2D[this.LIST.length];
        System.arraycopy(this.LIST, 0, tempList, 0, this.LIST.length);
        this.LIST = new Pixel2D[tempList.length + 1];
        System.arraycopy(tempList, 0, this.LIST, 0, tempList.length);
        this.LIST[this.LIST.length-1] = p;
    }

    public Pixel2D dequeue() {
        Pixel2D[] tempList = new Pixel2D[this.LIST.length];
        Pixel2D p = new Index2D(this.LIST[0]);
        System.arraycopy(this.LIST, 0, tempList, 0, this.LIST.length);
        this.LIST = new Pixel2D[tempList.length -1];
        System.arraycopy(tempList, 1, this.LIST, 0, this.LIST.length);
        return p;
    }

    public Boolean isEmpty() {return this.LIST.length == 0;}

    public void reverse(){
        for (int i = 0; i < this.LIST.length / 2; i+=1) {
            Pixel2D temp = this.LIST[i];
            this.LIST[i] = this.LIST[this.LIST.length - (i+1)];
            this.LIST[this.LIST.length - (i+1)] = temp;
        }
    }

    public String toString(){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.LIST.length; i+=1) {
            ans.append(this.LIST[i].toString());
        }
        return ans.toString();
    }

    public static void main() {
        PixelsContainer pc = new PixelsContainer();
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(2,3);
        pc.enqueue(p1);
        pc.enqueue(p2);
        System.out.println(pc.toString());
        pc.reverse();
        System.out.println(pc.toString());
    }
}
