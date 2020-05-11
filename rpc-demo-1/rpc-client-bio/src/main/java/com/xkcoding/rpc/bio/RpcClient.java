package com.xkcoding.rpc.bio;

import java.lang.reflect.Proxy;

/**
 * <p>
 * RPC 客户端
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 12:32
 */
public class RpcClient {

	/**
	 * 动态代理
	 * @param interfaceClass 接口类
	 * @param host IP
	 * @param port 端口
	 * @param <T> 代理对象类型
	 * @return 代理对象
	 */
	public <T> T proxy(Class<T> interfaceClass, String host, int port) {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
				new RemoteInvocationHandler(host, port));
	}

}
