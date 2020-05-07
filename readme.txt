包名：
consumer 消费者
framework
protocol.dubbo
protocol.http
provider.api  业务接口
provider.impl 业务实现

1

提供API、实现API


2 provider
1.本地注册

2.远程注册

3.暴露服务，启动tomat
	HttpServer server = new HttpServer()
	server.start("localhost","8080");