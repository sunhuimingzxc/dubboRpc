package framework;

import protocol.dubbo.NettyClient;
import protocol.http.HttpClient;
import register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {


    //抽象出协议工厂类ProtocolFactory
    //通过指定运行参数-DprotocolName=dubbo，来指定consummer与privider的通讯协议
    public static<T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                //通过注册中心获取privider地址，以地址随机的方式调用privider
                URL url = RemoteMapRegister.random(interfaceClass.getName());
                Invocation invocation = new Invocation(interfaceClass.getName(),method.getName(), method.getParameterTypes(), args);

                Protocol protocol = ProtocolFactory.getProtocol();
                String result = protocol.send(url, invocation);

                return result;
            }
        });
    }


}
