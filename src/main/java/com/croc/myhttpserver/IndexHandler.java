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
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;

/**
 * Created by MShestukhin on 02.10.2017.
 */
public class IndexHandler implements HttpHandler {

    private String chooseMime(String path){
        switch (path.substring(path.indexOf(".")+1))
        {
            case "htm":
            case "html":
            case "log":
            case "cmd":
            case "css":
            case "csv":
            case "javascript (Obsolete):":
            case "plain:":
            case "php:":
            case "xml:":
            case "markdown:":
            case "txt":
                return "text/"+path;
            case "gif:":
            case "jpeg:":
            case "pjpeg:":
            case "svg+xml":
            case "tiff":
            case "vnd.microsoft.icon":
            case "vnd.wap.wbmp":
            case "webp":
            case  "png":
                return "image/"+path;
            case "pdf":
            case "zip":
            case "xml":
            case "json:":
                return "application/"+path;

            default:
                return "application/octet-stream";
        }
    }
    private void readChatFile(String path, HttpExchange exchange) throws IOException {
        File file=new File(getClass().getClassLoader().getResource(path).getFile());
        FileInputStream fileReader = new FileInputStream(file);
        Headers header=exchange.getResponseHeaders();
        header.add("Component-type", this.chooseMime(path));
        System.out.print(path.substring(path.indexOf(".")+1));
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
        System.out.print("handler");
        String strPath = exchange.getRequestURI().toString().substring(1);
        if (strPath.isEmpty()) {
            readChatFile("index.html", exchange);
        }
        else {
            this.readChatFile(strPath,exchange);
        }
    }
}

