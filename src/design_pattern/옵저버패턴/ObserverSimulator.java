package design_pattern.옵저버패턴;

public class ObserverSimulator {

    public static void main(String[] args) {
        ASubject aSubject = new ASubject();
        AObserver aObserver = new AObserver(aSubject);
        BObserver bObserver = new BObserver(aSubject);

        aSubject.setData(1, 2);
        aSubject.setData(10, 20);
    }

}
