package framework;

import java.io.Serializable;

/**
 * 客户端需要发送的数据，封装成一个对象；需要序列化
 */
public class Invocation implements Serializable{
    //接口名
    private String interfaceName;
    //方法名
    private String methodName;
    //方法参数类型
    private Class[] paramTypes;
    //方法参数
    private Object[] params;

    public Invocation(String interfaceName, String methodName, Class[] paramTypes, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public Object[] getParams() {
        return params;
    }
}
