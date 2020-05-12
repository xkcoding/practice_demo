package com.xkcoding.rpc.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AllArgsConstructor;

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
		Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup eventGroup = new NioEventLoopGroup();

		RpcRequestClientHandler rpcRequestClientHandler = new RpcRequestClientHandler();
		bootstrap.group(eventGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				socketChannel.pipeline()
						.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
						.addLast(new ObjectEncoder())
                        // 添加自定义处理器
                        .addLast(rpcRequestClientHandler);
			}
		}).option(ChannelOption.TCP_NODELAY, true);

		try {
			ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
			// 发送 RPC 请求参数
			channelFuture.channel().writeAndFlush(rpcRequest).sync();
			// 等待连接关闭
			channelFuture.channel().closeFuture().sync();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			eventGroup.shutdownGracefully();
		}

		return rpcRequestClientHandler.getResult();
	}

}
