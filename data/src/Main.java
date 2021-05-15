package com.execute;

// My Class
import com.window.WindowsData;
import com.data.DataBase;
import com.data.Client;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DataBase.set_paths(); // Set the paths
                DataBase.restoreObjects();
                try {
                    WindowsData home = new WindowsData();
                    home.homeWindow();
                } catch (java.lang.ArrayIndexOutOfBoundsException arer) {
                    // Check if exist a copy database if exist replace the database and try
                    resete();
                } catch (java.lang.NullPointerException ne) {
                    // Check if exist a copy database if exist replace the database and try
                    resete();
                }
            }
        });
    }

    // Check if exist a copy database if exist replace the database and try
    public static void resete() {
        // Copdy secure DataBase to DataBase
        if ( DataBase.Exists(DataBase.folderData_secure) ) {
            // Delete DataBase
            //DataBase.delete(new File(DataBase.folderData));
            // Copy loacl database
            File sourceFolder = new File(DataBase.folderData_secure);
            File destinationFolder = new File(DataBase.folderData);
            DataBase.copyFolder(sourceFolder,destinationFolder);
        }
        // Open Home Window
        try {
            DataBase.set_paths(); // Set the paths
            WindowsData home = new WindowsData();
            home.homeWindow();
        } catch (java.lang.ArrayIndexOutOfBoundsException arer) {
            new WindowsData().show(
                    "Can't find the file: <br>" +
                            DataBase.file_path + "<br>" +
                            "You must delete this client."
            );
        } catch (java.lang.NullPointerException e) {
            new WindowsData().show(
                    "Can't find the: <br>" + DataBase.folderData + " folder."
            );
        }
    }

}
