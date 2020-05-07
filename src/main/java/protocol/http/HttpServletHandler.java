package protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;
import provider.LocalRegister;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

public class HttpServletHandler {

    //处理请求
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        InputStream inputStream = null;
        try {
            //接收consumer发来的数据
            inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation)ois.readObject();

            //从本地注册表中拿到所要执行的实现类
            Class implClass = LocalRegister.get(invocation.getInterfaceName());
            //构造所要执行的方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            //执行方法
            String result = (String)method.invoke(implClass.newInstance(), invocation.getParams());

            //方法执行结果返回给consumer
            IOUtils.write(result, resp.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
