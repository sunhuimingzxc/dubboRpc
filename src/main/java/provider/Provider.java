package provider;

import framework.Protocol;
import framework.ProtocolFactory;
import framework.URL;
import protocol.dubbo.NettyServer;
import protocol.http.HttpClient;
import protocol.http.HttpServer;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.RemoteMapRegister;

public class Provider {

    public static void main(String[] args) {
        //1.本地注册  存储格式：{暴露出去的服务名, 实现类}
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //2.远程注册
        //｛服务名, List<URL>｝
        URL url = new URL("localhost",8080);
        RemoteMapRegister.regist(HelloService.class.getName(), url);

        //3.暴露服务，启动tomcat
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
