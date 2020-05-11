package com.xkcoding.rpc.bio;

/**
 * <p>
 * RPC 接口实现
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 11:44
 */
public class HelloServiceImpl implements IHelloService {

	@Override
	public String sayHello(String content) {
		System.out.println("request is coming: " + content);
		return "hello " + content;
	}

}
