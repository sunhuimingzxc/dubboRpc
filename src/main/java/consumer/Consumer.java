package consumer;

import framework.Invocation;
import framework.ProxyFactory;
import protocol.http.HttpClient;
import provider.api.HelloService;

/**
 * consumer 消费调用方
 0*/
public class Consumer {
    /**
     * Main 方法
     */
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayHello("孙慧明"));
    }
}
