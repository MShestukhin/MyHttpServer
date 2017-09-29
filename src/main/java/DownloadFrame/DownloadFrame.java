package DownloadFrame;

import javax.swing.*;

/**
 * Created by MShestukhin on 27.09.2017.
 */
public class DownloadFrame extends JFrame{
    DownloadFrame(){
        JFileChooser dialog=new JFileChooser();
        dialog.showOpenDialog(this);
        dialog.setVisible(true);
    }

}
