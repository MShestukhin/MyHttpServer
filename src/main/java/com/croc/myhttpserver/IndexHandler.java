package com.croc.myhttpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by MShestukhin on 02.10.2017.
 */
public class IndexHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
       System.out.print(String.valueOf(exchange.getRequestURI().getPath().toString().indexOf("/")));
        File file= new File(getClass().getClassLoader().getResource("images/social.png").getFile());
        System.out.print(String.valueOf(file.exists()));
        //System.out.print(file.exists());
        OutputStream os = exchange.getResponseBody();
        //StringBuilder str = null;
       /* FileReader fileReader= new FileReader(getClass().getClassLoader().getResource("index.html").getFile());
        char[] buff=new char[1024];
        int i=0;
        while ((i=file.read(buff))!=-1){
            String str=new String(buff);
            // System.out.print(str.toString());
            exchange.sendResponseHeaders(1024, str.length());
            os.write(str.getBytes());
            System.out.print(str);
        }
        os.close();*/
    }
}
