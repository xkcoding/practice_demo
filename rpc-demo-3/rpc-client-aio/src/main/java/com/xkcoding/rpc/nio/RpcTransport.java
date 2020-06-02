package com.xkcoding.rpc.nio;

import lombok.AllArgsConstructor;
import org.smartboot.socket.transport.AioQuickClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
        // 通过 CompletableFuture 阻塞获取返回结果
        CompletableFuture<Object> waitForResult = new CompletableFuture<>();
        RpcClientMessageProcessor messageProcessor = new RpcClientMessageProcessor(waitForResult);
        AioQuickClient<byte[]> consumer = new AioQuickClient<>(host, port, new RpcProtocol(), messageProcessor);

        try {
            consumer.start();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(rpcRequest);
            byte[] data = byteArrayOutputStream.toByteArray();
            messageProcessor.getAioSession().writeBuffer().writeInt(data.length + 4);
            messageProcessor.getAioSession().writeBuffer().write(data);
            messageProcessor.getAioSession().writeBuffer().flush();
            // 阻塞 3s 获取返回结果
            return waitForResult.get(3, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            consumer.shutdown();
        }
        return null;
    }

}
