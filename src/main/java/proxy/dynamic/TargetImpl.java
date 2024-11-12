package proxy.dynamic;

public class TargetImpl implements TargetInterface {

    @Override
    public void print() {
        System.out.println("target : Hello");
    }
}
