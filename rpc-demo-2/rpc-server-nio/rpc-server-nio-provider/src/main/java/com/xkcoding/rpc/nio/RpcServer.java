package com.xkcoding.rpc.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

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
		ServerBootstrap serverBootstrap = new ServerBootstrap();

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						socketChannel.pipeline()
								.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
								.addLast(new ObjectEncoder())
								// 自定义处理器
								.addLast(new RpcRequestServerHandler(service));
					}
				});

		try {
			serverBootstrap.bind(port).sync();
			System.out.println(service + " 服务发布在 " + port + " 端口");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
