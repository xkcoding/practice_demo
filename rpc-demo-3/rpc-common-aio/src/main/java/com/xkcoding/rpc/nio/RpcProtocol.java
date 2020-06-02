package com.xkcoding.rpc.nio;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

import java.nio.ByteBuffer;

/**
 * <p>
 * RPC 协议解析，通信协议我们采用最简单的length+data模式
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-02 14:58
 */
public class RpcProtocol implements Protocol<byte[]> {
    private static final int INTEGER_BYTES = Integer.SIZE / Byte.SIZE;

    /**
     * 对于从Socket流中获取到的数据采用当前Protocol的实现类协议进行解析。
     *
     * @param readBuffer 待处理的读buffer
     * @param session    本次需要解码的session
     * @return 本次解码成功后封装的业务消息对象, 返回null则表示解码未完成
     */
    @Override
    public byte[] decode(ByteBuffer readBuffer, AioSession<byte[]> session) {
        int remaining = readBuffer.remaining();
        if (remaining < INTEGER_BYTES) {
            return null;
        }
        int messageSize = readBuffer.getInt(readBuffer.position());
        if (messageSize > remaining) {
            return null;
        }
        byte[] data = new byte[messageSize - INTEGER_BYTES];
        readBuffer.getInt();
        readBuffer.get(data);
        return data;
    }
}
