package design_pattern.데코레이터패턴;

public class DecoratorSimulator {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        beverage = new Milk(beverage); // 우유 토핑 추가
        beverage = new Whip(beverage); // 휘핑 크림 추가

        System.out.println("가격 : " + beverage.cost());
    }

}
