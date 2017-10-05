package com.croc.myhttpserver;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

/**
 * Created by MShestukhin on 27.09.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        HttpServer server= HttpServer.create();
        server.bind(new InetSocketAddress(3333),0);
        HttpContext context=server.createContext("/", new IndexHandler());
        server.start();
    }
}
