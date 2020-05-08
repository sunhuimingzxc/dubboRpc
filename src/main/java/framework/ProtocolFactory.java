package framework;

import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

public class ProtocolFactory {

    //工厂模式
    public static Protocol getProtocol(){
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
    }
}
