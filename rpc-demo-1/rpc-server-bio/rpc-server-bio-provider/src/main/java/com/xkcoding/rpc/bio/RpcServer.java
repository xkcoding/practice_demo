package com.xkcoding.rpc.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * RPC 服务提供
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 11:51
 */
public class RpcServer {

	// 阿里 P3C 不建议直接调用 Executors，此处为了偷懒
	private final ExecutorService executorService = Executors.newCachedThreadPool();

	public void start(Object service, int port) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println(service + " 服务发布在 " + port + " 端口");
			while (true) {
				// 不断阻塞，等待请求
				Socket socket = serverSocket.accept();
				// 通过线程池异步处理，提升性能
				executorService.execute(new RpcRequestHandler(socket, service));
			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
