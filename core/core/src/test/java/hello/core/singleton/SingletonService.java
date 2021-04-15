package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instace = new SingletonService();

    public static SingletonService getInstace(){
        return instace;
    }

    private SingletonService()
    {
        //생성자를 private로 만들어준다 외부에서 호출할 수 없도록
    }

    public  void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
