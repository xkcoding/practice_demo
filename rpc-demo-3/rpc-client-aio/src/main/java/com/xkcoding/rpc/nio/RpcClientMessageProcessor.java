package com.xkcoding.rpc.nio;

import lombok.Getter;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * RPC 请求客户端处理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-11 23:49
 */
@Getter
public class RpcClientMessageProcessor implements MessageProcessor<byte[]> {

    private AioSession<byte[]> aioSession;
    private CompletableFuture<Object> waitForResult;

    public RpcClientMessageProcessor(CompletableFuture<Object> waitForResult) {
        this.waitForResult = waitForResult;
    }


    /**
     * 处理接收到的消息
     *
     * @param session 通信会话
     * @param msg     待处理的业务消息
     */
    @Override
    public void process(AioSession<byte[]> session, byte[] msg) {
        ObjectInput objectInput = null;
        try {
            objectInput = new ObjectInputStream(new ByteArrayInputStream(msg));
            // 获取到结果，并通知主线程获取结果
            waitForResult.complete(objectInput.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInput != null) {
                try {
                    objectInput.close();
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
        switch (stateMachineEnum) {
            case NEW_SESSION:
                this.aioSession = session;
                break;
        }
    }
}
