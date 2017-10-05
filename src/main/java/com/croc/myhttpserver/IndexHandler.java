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
        FileReader fileReader = new FileReader(file);
        char[] buff = new char[(int) file.length()];
        OutputStream os=null;
        int i = 0;
        try {
            while ((i = fileReader.read(buff)) != -1) {
                String str = new String(buff);
                try {
                    exchange.sendResponseHeaders(200, str.length());
                    os = exchange.getResponseBody();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                os.write(str.getBytes());
            }
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
        if(strPath.contains(".png")&&strPath.contains(".ico")){
            System.out.print(strPath);
            File filE= new File(getClass().getClassLoader().getResource("images/bg.png").getFile());
            FileInputStream file = new FileInputStream(filE);
            int c = file.available();
            byte[] bytes = new byte[c];
            int i = 0;
            while ((i=file.read(bytes))!=-1){};
            exchange.sendResponseHeaders(1024,bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
        else {
            System.out.print(strPath);
            this.readChatFile(strPath,exchange);
        }
    }
}

