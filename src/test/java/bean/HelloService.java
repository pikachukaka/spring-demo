package bean;

public class HelloService {
    private String name;

    public HelloService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HelloService{" +
                "name='" + name + '\'' +
                '}';
    }

    public String sayHello(){
        System.out.println("hello"+name);
        return "hello";
    }
}
