package com.xkcoding.rpc.nio;

import lombok.AllArgsConstructor;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

import java.io.*;
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
public class RpcServerMessageProcessor implements MessageProcessor<byte[]> {

    private Object service;

    /**
     * 处理接收到的消息
     *
     * @param session 通信会话
     * @param msg     待处理的业务消息
     */
    @Override
    public void process(AioSession<byte[]> session, byte[] msg) {
        ObjectInput objectInput = null;
        ObjectOutput objectOutput = null;
        try {
            objectInput = new ObjectInputStream(new ByteArrayInputStream(msg));
            RpcRequest rpcRequest = (RpcRequest) objectInput.readObject();
            // 反射调用
            Object invoke = invoke(rpcRequest);

            // 写回客户端
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(invoke);
            byte[] data = byteArrayOutputStream.toByteArray();
            session.writeBuffer().writeInt(data.length + 4);
            session.writeBuffer().write(data);
            session.writeBuffer().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInput != null) {
                try {
                    objectInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutput != null) {
                try {
                    objectOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 状态机事件,当枚举事件发生时由框架触发该方法
     *
     * @param session          本次触发状态机的AioSession对象
     * @param stateMachineEnum 状态枚举
     * @param throwable        异常对象，如果存在的话
     * @see StateMachineEnum
     */
    @Override
    public void stateEvent(AioSession<byte[]> session, StateMachineEnum stateMachineEnum, Throwable throwable) {

    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
