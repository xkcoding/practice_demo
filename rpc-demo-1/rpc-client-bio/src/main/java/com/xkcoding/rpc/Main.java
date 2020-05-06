package com.xkcoding.rpc;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 12:48
 */
public class Main {

	public static void main(String[] args) {
		RpcClient rpcClient = new RpcClient();
		IHelloService helloService = rpcClient.proxy(IHelloService.class, "0.0.0.0", 8080);
		String content = helloService.sayHello("rpc simple demo");
		System.out.println("content = " + content);
	}

}
