package design_pattern.전략패턴;

public class MiniDuckSimulator {

    public static void main(String[] args) {
        MallarDuck mallarDuck = new MallarDuck();
        mallarDuck.display();
        mallarDuck.performFly();
        mallarDuck.performQuack();
        mallarDuck.setFlyBehavior(new FlyNoWay());
        mallarDuck.performFly();
    }

}
