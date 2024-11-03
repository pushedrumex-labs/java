package design_pattern.커맨드패턴;

public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl(); // 인보커

        Light light = new Light(); // 리시버
        Command lightOnCommand = new LightOnCommand(light); // 커맨드
        Command lightOffCommand = new LightOffCommand(light); // 커맨드

        remote.setCommand(0, lightOnCommand, lightOffCommand);

        remote.onButtonWasPressed(0); // 실행 - 조명 키기
        remote.offButtonWasPressed(0); // 실행 - 조명 끄기
    }

}
