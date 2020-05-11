package com.xkcoding.rpc.nio;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * RPC 请求参数
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 11:43
 */
@Data
public class RpcRequest implements Serializable {

	private String className;

	private String methodName;

	private Object[] params;

}
