package com.xkcoding.rpc.nio;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 * 动态代理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 12:36
 */
@AllArgsConstructor
public class RemoteInvocationHandler implements InvocationHandler {

	private String host;

	private int port;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("需要动态代理生成请求对象");
		// 构建 RpcRequest
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setClassName(method.getDeclaringClass().getName());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setParams(args);

		// 远程调用
		RpcTransport rpcTransport = new RpcTransport(host, port);
		Object result = rpcTransport.call(rpcRequest);
		return result;
	}

}
