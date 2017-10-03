package com.croc.myhttpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by MShestukhin on 27.09.2017.
 */
public class GetHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        // System.out.print(getClass().getResource(httpExchange.getRequestURI().getPath()));

        //Headers headers = exchange.getRequestHeaders();


        //byte[] bytes = builder.toString().getBytes();
        //ClassLoader.getSystemClassLoader().getResource(exchange.getRequestURI().getPath());
        //File file=new File(String.valueOf(getClass().getResource(exchange.getRequestURI().getPath())));
        OutputStream os = exchange.getResponseBody();
        File file= new File(getClass().getClassLoader().getResource("index.html").getFile());
        System.out.print(file.length());
        String str="<h1> hello</h1>";
        exchange.sendResponseHeaders(40000, file.length());
        //os.write(str.getBytes());
        os.write((int) file.length());
        os.close();
    }
        /*String absPath="C://MyServer//webapps/";
        String path=absPath+httpExchange.getHttpContext().getPath();
        System.out.print(path+"\n");
        try(FileReader reader=new FileReader(path)){
            int c;
            reader.read();
            while ((c=reader.read())!=-1){
                System.out.print((char)c);
            }
        }
        catch (IOException e ){
            System.out.print("not found");
        }
        new DownloadFrame();
    }*/
}

