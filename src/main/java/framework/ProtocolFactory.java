package framework;

import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ProtocolFactory {

    public static Protocol getProtocol(){

        //javaSPI机制实现协议切换
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();
/*
        //工厂模式
        String name = System.getProperty("protocolName");
        if(name == null || name.equals("")){
            name = "http";
        }
        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }
        return new HttpProtocol();
*/

    }
}
