package consumer;

import framework.Invocation;
import framework.ProxyFactory;
import protocol.http.HttpClient;
import provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
        //但是以上写法很麻烦，现在希望简化调用代码
        //将以上逻辑移至ProxyFactory中
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayHello("孙慧明"));
    }
}
