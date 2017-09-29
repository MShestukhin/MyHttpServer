
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by MShestukhin on 27.09.2017.
 */
public class GetHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String absPath="C://MyServer//webapps/";
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
    }
}

class DownloadFrame extends JFrame {
        DownloadFrame(){
            JFileChooser dialog=new JFileChooser();
            dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            dialog.showSaveDialog(this);
            dialog.setVisible(true);
        }
    }
