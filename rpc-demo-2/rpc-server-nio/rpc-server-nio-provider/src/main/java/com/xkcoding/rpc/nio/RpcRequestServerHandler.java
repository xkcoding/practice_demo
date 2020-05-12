package com.xkcoding.rpc.nio;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * RPC 请求服务端处理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 11:59
 */
@AllArgsConstructor
public class RpcRequestServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

	private Object service;

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
		// 反射调用
		Object invoke = invoke(rpcRequest);
		channelHandlerContext.writeAndFlush(invoke).addListener(ChannelFutureListener.CLOSE);
	}

	private Object invoke(RpcRequest rpcRequest)
			throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// 请求参数
		Object[] params = rpcRequest.getParams();
		// 请求参数类型
		Class<?>[] paramTypes = new Class[params.length];

		for (int i = 0; i < params.length; i++) {
			paramTypes[i] = params[i].getClass();
		}

		// 获取请求的类名
		Class<?> clazz = Class.forName(rpcRequest.getClassName());
		// 获取请求的方法名
		Method method = clazz.getMethod(rpcRequest.getMethodName(), paramTypes);

		// 调用
		Object result = method.invoke(service, params);
		return result;
	}

}
