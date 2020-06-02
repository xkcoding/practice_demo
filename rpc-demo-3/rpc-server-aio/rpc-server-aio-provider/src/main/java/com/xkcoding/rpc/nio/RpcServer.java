package com.xkcoding.rpc.nio;

import org.smartboot.socket.transport.AioQuickServer;

import java.io.IOException;

/**
 * <p>
 * RPC 服务提供
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-05-06 11:51
 */
public class RpcServer {

    public void start(Object service, int port) {
        RpcServerMessageProcessor messageProcessor = new RpcServerMessageProcessor(service);
        AioQuickServer<byte[]> server = new AioQuickServer<>(port, new RpcProtocol(), messageProcessor);

        try {
            server.start();
            System.out.println(service + " 服务发布在 " + port + " 端口");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
