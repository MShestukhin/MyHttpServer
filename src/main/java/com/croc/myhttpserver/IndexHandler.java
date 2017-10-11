package com.croc.myhttpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.activation.MimeType;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;

/**
 * Created by MShestukhin on 02.10.2017.
 */
public class IndexHandler implements HttpHandler {
    
    private void readChatFile(String path, HttpExchange exchange) throws IOException {
        System.out.print(path+"\n");
        File file=new File(getClass().getClassLoader().getResource(path).getFile());
        FileInputStream fileReader = new FileInputStream(file);
        Headers header=exchange.getResponseHeaders();
        header.add("Component-type", Files.probeContentType(file.toPath()));
        byte[] buff = new byte[(int) file.length()];
        OutputStream os=null;
        int i = 0;
        int j=0;
        try {
            while ((i = fileReader.read(buff)) != -1) {
            }
            exchange.sendResponseHeaders(0, buff.length);
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

