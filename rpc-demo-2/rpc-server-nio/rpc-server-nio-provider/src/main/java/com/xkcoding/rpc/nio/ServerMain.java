package com.xkcoding.rpc.nio;

import java.io.IOException;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 12:29
 */
public class ServerMain {

	public static void main(String[] args) throws IOException {
		IHelloService helloService = new HelloServiceImpl();

		RpcServer rpcServer = new RpcServer();
		rpcServer.start(helloService, 8080);
	}

}
