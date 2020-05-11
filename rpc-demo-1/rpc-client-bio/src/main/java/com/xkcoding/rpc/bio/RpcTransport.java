package com.xkcoding.rpc.bio;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>
 * 远程调用
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 12:40
 */
@AllArgsConstructor
public class RpcTransport {

	private String host;

	private int port;

	public Object call(RpcRequest rpcRequest) {
		Object result = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try (Socket socket = new Socket(host, port);) {
			oos = new ObjectOutputStream(socket.getOutputStream());
			// 序列化
			oos.writeObject(rpcRequest);
			oos.flush();

			ois = new ObjectInputStream(socket.getInputStream());
			// 获取服务端返回结果
			result = ois.readObject();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
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
		return result;
	}

}
