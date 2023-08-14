package com.mayizt;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 用户：Administrator
 * 描述：客户端
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //生成
        Selector tor = Selector.open();
        SocketChannel cn=SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        //初始化
        cn.configureBlocking(false);
        cn.register(tor, SelectionKey.OP_READ);
        //发送数据给服务端
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("请说：");
            String msg=scanner.nextLine();
            buf.put(("波仔："+msg).getBytes());
            buf.flip();
            cn.write(buf);
            buf.clear();
        }

    }






}
