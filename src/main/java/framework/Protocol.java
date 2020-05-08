package framework;

/**
 * 协议抽象接口
 * 对于相同的东西，最先想到的应该是给它抽象出来
 */
public interface Protocol {

    void start(URL url);
    String send(URL url, Invocation invocation);

}
