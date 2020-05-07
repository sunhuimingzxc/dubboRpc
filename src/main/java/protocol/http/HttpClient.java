package protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * consummer端网络处理类
 */
public class HttpClient {

    /**
     * 客户端发送请求
     * @param hostName
     * @param port
     * @param invocation 需要发送的数据
     * @return
     */
    public String send(String hostName, Integer port, Invocation invocation){
        try {
            //向privider发送数据
            URL url = new URL("http",hostName, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream oos =  new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            //拿到privider端响应的结果
            InputStream inputStream = connection.getInputStream();
            String result = IOUtils.toString(inputStream, "utf-8");
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
