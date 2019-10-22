package com.yyj.jantent.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import com.yyj.jantent.client.codec.HttpJsonRequestEncoder;
import com.yyj.jantent.client.codec.HttpJsonResponseDecoder;
import com.yyj.jantent.client.handler.ClientHandler;
import com.yyj.jantent.modal.vo.UserVo;


public class HttpClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("http-decoder",new HttpResponseDecoder());
        ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
        ch.pipeline().addLast("json-decoder",new HttpJsonResponseDecoder(UserVo.class));

        ch.pipeline().addLast("http-encoder",new HttpRequestEncoder());
        ch.pipeline().addLast("json-encoder",new HttpJsonRequestEncoder());
        ch.pipeline().addLast("handler",new ClientHandler());
    }
}
