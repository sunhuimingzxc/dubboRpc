package provider;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册
 *  存储格式：{暴露出去的服务名, 实现类}
 *  所以用HashMap来承装
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>(); //本地注册表

    public static void regist(String interfaceName, Class implClass){
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
