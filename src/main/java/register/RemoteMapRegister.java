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
        saveFile();//将注册列表存入文件，供consumer使用；dubbo使用的消息队列，而我们就用这种方式简化模拟一下
    }

    /**
     * 负载均衡策略-随机
     * @param interfaceName
     * @return
     */
    public static URL random(String interfaceName){
        REGISTER = getFile();
        List<URL> list = REGISTER.get(interfaceName);
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }

    private static void saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream("temp.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(REGISTER);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile(){
        try {
            FileInputStream fis = new FileInputStream("temp.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, List<URL>> map = (Map<String, List<URL>>) ois.readObject();
            ois.close();
            fis.close();
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
