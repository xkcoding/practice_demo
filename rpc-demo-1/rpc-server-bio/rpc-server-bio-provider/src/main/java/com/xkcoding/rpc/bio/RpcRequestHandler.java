package com.xkcoding.rpc.bio;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * <p>
 * RPC 请求处理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 11:59
 */
@AllArgsConstructor
public class RpcRequestHandler implements Runnable {

	private Socket socket;

	private Object service;

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			// 根据输入流拿到 RpcRequest
			RpcRequest rpcRequest = (RpcRequest) ois.readObject();

			// 反射调用本地服务
			Object result = invoke(rpcRequest);

			oos = new ObjectOutputStream(socket.getOutputStream());
			// 通过输出流输出结果
			oos.writeObject(result);
			oos.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		finally {
			if (ois != null) {
				try {
					ois.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
