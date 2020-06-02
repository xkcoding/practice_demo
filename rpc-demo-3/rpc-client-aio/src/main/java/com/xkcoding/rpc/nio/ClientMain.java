package com.xkcoding.rpc.nio;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 12:48
 */
public class ClientMain {

	public static void main(String[] args) {
		RpcClient rpcClient = new RpcClient();
		IHelloService helloService = rpcClient.proxy(IHelloService.class, "0.0.0.0", 8080);
		String content = helloService.sayHello("rpc aio demo");
		System.out.println("content = " + content);
	}

}
