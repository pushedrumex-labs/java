package design_pattern.옵저버패턴;

public class BObserver implements Observer {

    private int data1;
    private int data2;
    private Subject subject;

    public BObserver(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    public void update(int data1, int data2) {
        this.data1 = data1;
        this.data2 = data2;
        System.out.println("BObserver data1 : " + data1 + ", " + "data2 : " + data2);
    }
}
