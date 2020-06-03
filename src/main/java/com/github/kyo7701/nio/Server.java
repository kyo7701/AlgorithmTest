package com.github.kyo7701.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:Mr.Cris
 * Date:2020-05-20 11:03
 *
 * @description
 */
public class Server {

    private static ThreadPoolExecutor executor;

    public InetSocketAddress host;
    //用于发送数据的缓冲区
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    //用于接收数据的缓冲区
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    public static final int port = 57048;
    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;
    private HashMap<String,SocketChannel> clientMap = new HashMap<>();

    static {
        executor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20));

    }


    Server() {
        initialize();
    }

    void initialize() {

        ServerSocketChannel serverSocketChannel = null;
        try {

            serverSocketChannel = ServerSocketChannel.open();
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            host = new InetSocketAddress(port);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(host);

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server启动,监听" + port + "端口");

    }

    void listen() throws IOException {

        while (true) {
            System.out.println("当前线程: " +Thread.currentThread().getName());
            selector.select();
            //获取当前selector下面注册的所有选择键(所有事件)
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                //如果有任意一个接收事件已经就绪,即客户端发起了连接
                if (selectionKey.isAcceptable()) {
                    //获取客户端连接
                    SocketChannel client = ((ServerSocketChannel) selectionKey.channel()).accept();
                    client.configureBlocking(false);
                    //将客户端注册到selector上面
                    client.register(selector, SelectionKey.OP_READ);
                    clientMap.put(client.getLocalAddress().toString(),client);
                } else if (selectionKey.isReadable()) { //读事件就绪,即客户端发送了消息
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    //清空接受数据缓冲区
                    try {
                        dispatch(client);
                    } catch (IOException e) {
                        selectionKey.cancel();
                        client.socket().close();
                        client.close();
                        clientMap.remove(client.getLocalAddress().toString());
                    }
                }


            }
            //清理已处理的事件
//            selectionKeys.clear();
        }
    }

    public void dispatch(SocketChannel client) throws IOException {
        //清空接受数据缓冲区
        receiveBuffer.clear();
        System.out.println("dispatch-当前线程: " +Thread.currentThread().getName());
        int bytes = client.read(receiveBuffer);
        if (bytes > 0) {
            receiveBuffer.flip();
            String receivedMessage = String.valueOf(charset.decode(receiveBuffer));
            System.out.println("收到来自客户端的消息" + receivedMessage);
            String serverMessage = "服务端广播" + receivedMessage;
            for (SocketChannel socketChannel : clientMap.values()) {
                sendBuffer.clear();
                sendBuffer.put(charset.encode(serverMessage));
                sendBuffer.flip();
                socketChannel.write(sendBuffer);
            }
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
