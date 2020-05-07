package consumer;

import framework.Invocation;
import protocol.http.HttpClient;
import provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost",
                        8080,
                        new Invocation(HelloService.class.getName(),"sayHello",new Class[]{String.class},new Object[]{"孙慧明"}));
        System.out.println(result);

        //但是以上写法很麻烦，现在希望简化调用代码
        //将以上逻辑移至ProxyFactory中

    }
}
