package com.mayizt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * 用户：Administrator
 * 描述：${desc}
 */
public class Main {
    public static void main(String[] args) throws Exception {




        System.out.println("Hello world!");
        //生成选择器和初始通道
        Selector tor = Selector.open();
        ServerSocketChannel cn = ServerSocketChannel.open();
        //绑定端口，设置非异步。
        cn.bind(new InetSocketAddress(9999));
        cn.configureBlocking(false);
        cn.register(tor,SelectionKey.OP_ACCEPT);

        while(tor.select()>0){
            System.out.println("Yes...");
            Iterator<SelectionKey> it =tor.selectedKeys().iterator();
            while(it.hasNext()){

                SelectionKey sj=it.next();
                if(sj.isAcceptable()){
                    System.out.println("注册事件");
                    SocketChannel ncn=cn.accept();
                    ncn.configureBlocking(false);
                    ncn.register(tor,SelectionKey.OP_READ);
                }
                if(sj.isReadable()){
                  System.out.println("读取事件");
                    SocketChannel sjcn = (SocketChannel) sj.channel();
                  ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len=0;
                    System.out.println(111);
                      /*
                       while((len=sjcn.read(buf))>0){

                        buf.flip();
                        System.out.println(
                                new String(buf.array(),0,len)
                        );
                        buf.clear();
                    }*/
                }
                it.remove();
            }


        }

    }
}