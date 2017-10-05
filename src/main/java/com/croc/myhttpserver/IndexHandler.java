package com.croc.myhttpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by MShestukhin on 02.10.2017.
 */
public class IndexHandler implements HttpHandler {

    private void readChatFile(String path, HttpExchange exchange) throws IOException {
        File file=new File(getClass().getClassLoader().getResource(path).getFile());
        FileInputStream fileReader = new FileInputStream(file);
        byte[] buff = new byte[(int) file.length()];
        OutputStream os=null;
        int i = 0;
        try {
            while ((i = fileReader.read(buff)) != -1) {
            }
            exchange.sendResponseHeaders(200, buff.length);
            os = exchange.getResponseBody();
            os.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String strPath = exchange.getRequestURI().toString().substring(1);
        if (strPath.isEmpty()) {
            readChatFile("index.html", exchange);
        }
        else {
            this.readChatFile(strPath,exchange);
        }
    }
}

