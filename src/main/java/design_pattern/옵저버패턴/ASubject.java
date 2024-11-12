package design_pattern.옵저버패턴;

import java.util.ArrayList;
import java.util.List;

public class ASubject implements Subject {

    private List<Observer> observers;
    private int data1;
    private int data2;

    public ASubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(data1, data2);
        }
    }

    public void changed() {
        notifyObservers();
    }

    public void setData(int data1, int data2) {
        this.data1 = data1;
        this.data2 = data2;
        changed();
    }
}
