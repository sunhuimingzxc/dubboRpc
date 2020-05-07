package register;

import framework.URL;

import java.io.*;
import java.util.*;

public class RemoteMapRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<>(); //远程注册表

    /**
     *
     * @param interfaceName 服务接口名
     * @param url 当前机器的地址和端口
     */
    public static void regist(String interfaceName, URL url){
        List<URL> urls = Collections.singletonList(url);
        REGISTER.put(interfaceName, urls);
    }

    /**
     * 负载均衡策略-随机
     * @param interfaceName
     * @return
     */
    public static URL random(String interfaceName){
        List<URL> list = REGISTER.get(interfaceName);
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }


}
