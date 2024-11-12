package design_pattern.전략패턴;

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< 조용 >>");
    }
}
