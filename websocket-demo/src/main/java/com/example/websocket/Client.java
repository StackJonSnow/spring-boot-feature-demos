package com.example.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * @author Txm
 * @description Websocket客户端
 * @date 2021/3/11
 */
public class Client {

    public static void main(String[] args) throws Exception {
        String wsUrl = "ws://****";
        URI uri = URI.create(wsUrl);
        WebSocketClient webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("open...");
            }

            @Override
            public void onMessage(String s) {
                System.out.println("message:" + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("closes...");
            }

            @Override
            public void onError(Exception e) {
                System.out.println("error...");
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                System.out.println("收到响应:" + new String(bytes.array()));
                super.onMessage(bytes);
            }
        };

        boolean connecSuccess = webSocketClient.connectBlocking();
        if (connecSuccess) {
            System.out.println("连接建立成功");
        }

        webSocketClient.send("@013qmvmcwdgcwEVZjPNcasGlmNyHXZI5rZS5ucgDXEVerzksBWynVLOagZ4RuPrTasxsXv7cj6yphxyergtew9nqwOPsBWyZZEdnPzOZZTsTn2nanEOZuny4aNybrEBerCypysVERbyKtvAcwESbr5PZjPNcasGlaNybwON5jPNcrP6sBWylRMNlob3syNybYzADrzksB904wV=");

        System.out.println("消息发送成功");

    }
}
