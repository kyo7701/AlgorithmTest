package com.github.kyo7701.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

/**
 * Author:Mr.Cris
 * Date:2020-05-20 14:04
 *
 * @description
 */
public class Client {

    //定义发送缓冲区
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    //接收缓冲区
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;
    private InetSocketAddress server;

    Client(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        server = new InetSocketAddress(InetAddress.getLocalHost(),port);
        socketChannel.connect(server);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                try {
                    handle(selectionKey);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    void handle(SelectionKey selectionKey) throws IOException {
        //如果连接已就绪
        if (selectionKey.isConnectable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            sendBuffer.clear();
                            Scanner scanner = new Scanner(System.in);
                            String sendText = scanner.nextLine();
                            System.out.println("待发送的消息:" + sendText);
                            sendBuffer.put(charset.encode(sendText));
                            sendBuffer.flip();
                            try {
                                socketChannel.write(sendBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

            }
            socketChannel.register(selector,SelectionKey.OP_READ);

        } else if (selectionKey.isReadable()) { // 可读事件就绪，代表有从服务端发送过来的消息
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            receiveBuffer.clear();
            int bytes = socketChannel.read(receiveBuffer);
            if (bytes > 0 ) {
                receiveBuffer.flip();
                String receivedMessage = String.valueOf(charset.decode(receiveBuffer));
                System.out.println("收到来自服务端的消息："+ receivedMessage);
            }
        }

    }

    public static void main(String[] args) {
        try {
            Client client = new Client(57048);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
