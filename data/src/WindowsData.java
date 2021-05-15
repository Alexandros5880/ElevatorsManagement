package com.window;

// My class
import com.data.DataBase;
import com.data.Client;
import com.printer.Printer;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;
import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.table.DefaultTableCellRenderer;

// Threading Class
//import java.lang.Runnable;
import java.lang.Thread;

import java.io.File;

import java.util.Vector;
import java.util.Collections;
import java.util.Arrays;

// Close window after clicking outside
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.awt.KeyboardFocusManager;


// Class with the clients page
public class WindowsData {

    public Color color = new Color(27, 14, 14);
    public Color textColor = Color.GRAY;
    public Color clientDataColor = textColor;
    public Color colo_scros_home = Color.WHITE;
    public Color colo_scros_client_page = Color.WHITE;
    public static Font textSize = new Font("Arial", Font.BOLD, 13);
    final public static Font table_columns_font = new Font("Arial", Font.BOLD, 20);
    final public static Font table_rows_font = new Font("Arial", Font.BOLD, 13);
    final public static Font imformations_font = new Font("Arial", Font.BOLD, 13);
    public static int table_rows_height = 25;

    // Variables for JText in settings window
    public static String get_database_path = "";

    // Home Window jtable columns sizes
    public static int table_streets_column_width = 0;
    public static int table_locations_column_width = 0;
    public static int table_types_column_width = 0;
    public static int table_phones_column_width = 0;
    public static int table_codes_column_width = 0;
    public static int table_id_column_width = 0;

    // Maintence jtable columns sizes
    public static int maintence_table_dates_column_width = 0;
    public static int maintence_table_workers_column_width = 0;
    public static int maintence_table_comments_column_width = 0;

    // Repairs jtable columns sizes
    public static int repairs_table_dates_column_width = 0;
    public static int repairs_table_workers_column_width = 0;
    public static int repairs_table_RepairsTypes_column_width = 0;

    // Battaries jtable columns sizes
    public static int battaries_table_dates_column_width = 0;
    public static int battaries_table_type1_column_width = 0;
    public static int battaries_table_type2_column_width = 0;
    public static int battaries_table_type3_column_width = 0;

    // DocNum jtable columns sizes
    public static int DocNum_table_dates_column_width = 0;
    public static int DocNum_table_DocNum_column_width = 0;
    public static int DocNum_table_Work_column_width = 0;
    public static int DocNum_table_Price_column_width = 0;
    public static int DocNum_table_Repayment_column_width = 0;

    // Damages jtable columns sizes
    public static int damages_table_dates_column_width = 0;
    public static int damages_table_workers_column_width = 0;
    public static int damages_table_DamageTypes_column_width = 0;
    public static int damages_table_FixTypes_column_width = 0;

    // Constructor read edit data and remake window object
    public WindowsData() {
        // Set text size
        String textSize = DataBase.read(DataBase.text_size);
        if ( textSize.indexOf("20") > -1 ) {
            this.textSize = new Font("Arial", Font.PLAIN, 20);
        } else if ( textSize.indexOf("30") > -1 ) {
            this.textSize = new Font("Arial", Font.PLAIN, 30);
        } else if ( textSize.indexOf("38") > -1 ) {
            this.textSize = new Font("Arial", Font.PLAIN, 38);
        } else if (textSize.indexOf("preferred") > -1) {
            this.textSize = new Font("Arial", Font.PLAIN, 23);
        }
        // Set text color
        String textColor = DataBase.read(DataBase.text_color);
        if ( textColor.indexOf("white") > -1 ) {
            this.textColor = Color.WHITE;
            this.clientDataColor = Color.WHITE;
        } else if ( textColor.indexOf("gray") > -1 ) {
            this.textColor = Color.GRAY;
            this.clientDataColor = Color.GRAY;
        } else if ( textColor.indexOf("black") > -1 ) {
            this.textColor = Color.BLACK;
            this.clientDataColor = Color.BLACK;
        } else if (textColor.indexOf("preferred") > -1) {
            this.textColor = Color.GRAY;
        }
        // Set background color
        String backColor = DataBase.read(DataBase.back_color);
        if ( backColor.indexOf("white") > -1 ) {
            this.color = Color.WHITE;
        } else if ( backColor.indexOf("blue") > -1 ) {
            this.color = new Color(0 , 0 , 73); 
        } else if ( backColor.indexOf("black") > -1 ) {
            this.color = Color.BLACK;
        } else if (backColor.indexOf("preferred") > -1) {
            this.color = new Color(27, 14, 14);
        }
    }

    // Settings Window
    public void settings() {
        JFrame frame = new JFrame("Settings");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(color);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
            }
        });

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels size
        int labelsWidth = (frameWidth/11);
        int labelsHeight = (frameHeight/33);

        // Buttons X space
        int space_buttons_X = ((labelsWidth/9)/2);
        // Check boxes heigt space
        int heightSpace = (labelsHeight/4);

        // Settings window place and size
        int windowWidth = frameWidth - (frameWidth/7);
        int windowheight = frameHeight/2;//frameHeight - (frameHeight/7);//frameHeight - (frameHeight/2);
        int windowX = (frameWidth / 2) - (windowWidth / 2);
        int windowY = (frameHeight / 2) - (windowheight / 2);

        // Color for all Text basics
        Color text_labels_color = Color.ORANGE;//new Color(255,140,0);
        // Check Boxs Color
        Color text_check_box = new Color(255,140,0);
        // Main Text Size
        Font text_labels_size = new Font("Arial", Font.PLAIN, 17);

        // PATH DATABASE SETTINGS //
        // DataBase path label
        int path_label_X = frameWidth/37;
        int path_label_Y = frameHeight/33;
        int path_label_width = labelsWidth;
        int path_label_height = labelsHeight;
        JLabel path_label = new JLabel("[DATA PATH]: ");
        path_label.setFont (text_labels_size);
        path_label.setForeground(text_labels_color);
        path_label.setBounds(path_label_X,path_label_Y,path_label_width,path_label_height);
        frame.add(path_label);

        // DataBase textField
        int path_text_X = (path_label_X+path_label_width) + space_buttons_X;
        int path_text_Y = path_label_Y+(path_label_Y/12);
        int path_text_width = (labelsWidth*7)-(labelsWidth*4)+(labelsWidth);
        int path_text_height = labelsHeight;
        JTextField path_text = new JTextField(DataBase.folderData);
        path_text.setFont (text_labels_size);
        path_text.setBackground(Color.GRAY);
        path_text.setBounds(path_text_X,path_text_Y,path_text_width,path_text_height);
        frame.add(path_text);

        // DataBase button tree file
        int treeFile_path_X = (path_text_X+path_text_width) + space_buttons_X;
        int treeFile_path_Y = path_text_Y;
        int treeFile_path_WIDTH = labelsWidth/4;
        int treeFile_path_HEIGHT = labelsHeight;
        JButton treeFile_path = new JButton("...");
        treeFile_path.setBounds(treeFile_path_X,treeFile_path_Y,treeFile_path_WIDTH,treeFile_path_HEIGHT);
        // Action Button
        treeFile_path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for downloading
                Thread thread = new Thread(){
                    public void run(){
                        Find_File_Tree(frame,"putDATABASE");
                    }
                };
                // Start thread
                thread.start();
            }
        });
        frame.add(treeFile_path);

        // Button set the path
        int path_button_X = (treeFile_path_X+treeFile_path_WIDTH) + space_buttons_X;
        int path_button_Y = path_text_Y;
        int path_button_width = labelsWidth - (labelsWidth/4);
        int path_button_height = labelsHeight;
        JButton path_button = new JButton("[ APPLY ]");
        // Action Button
        path_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String my_path = "";
                if ( !path_text.getText().contains(":") ) {
                    my_path = System.getProperty("user.home") + File.separator + path_text.getText();
                } else {
                    my_path = path_text.getText();
                }
                Vector<String> path_data = new Vector<String>(2);
                path_data.add(my_path); // The new path
                path_data.add("true"); // True
                DataBase.write(DataBase.path_file, path_data);
                DataBase.restart();
            }
        });
        path_button.setBounds(path_button_X,path_button_Y,path_button_width,path_button_height);
        frame.add(path_button);
        // PATH DATABASE SETTINGS OVER //

        // COPY DATABASE //
        // Copy DataBase to my DataBase
        int copy_Data_X = path_label_X;
        int copy_Data_Y = (path_label_Y+path_label_height)+heightSpace +(heightSpace/3);
        int copy_Data_Width = labelsWidth+(labelsWidth/3);
        int copy_Data_Height = labelsHeight;
        JLabel copy_Data_label = new JLabel("[GET DATABASE]: ");
        copy_Data_label.setForeground(text_labels_color);
        copy_Data_label.setFont (text_labels_size);
        copy_Data_label.setBounds(copy_Data_X,copy_Data_Y,copy_Data_Width,copy_Data_Height);
        frame.add(copy_Data_label);

        // Copy DataBase textField for Path  copy_Data_text
        int copy_Data_text_X = (copy_Data_X+copy_Data_Width) + space_buttons_X;
        int copy_Data_text_Y = copy_Data_Y;
        int copy_Data_text_Width = (labelsWidth*7)-(labelsWidth/3)-(labelsWidth*4)+(labelsWidth);
        int copy_Data_text_Height = labelsHeight;
        JTextField copy_Data_text = new JTextField(WindowsData.get_database_path);
        copy_Data_text.setBounds(copy_Data_text_X,copy_Data_text_Y, copy_Data_text_Width, copy_Data_text_Height);
        copy_Data_text.setFont (new Font("Arial", Font.PLAIN, 13));
        copy_Data_text.setBackground(Color.GRAY);
        frame.add(copy_Data_text);

        // MAKE Jtree BUTTON
        int treeFile_copy_Data_Width = treeFile_path_WIDTH;
        int treeFile_copy_Data_Height = treeFile_path_HEIGHT;
        int treeFile_copy_Data_X = (copy_Data_text_X+copy_Data_text_Width) + space_buttons_X;
        int treeFile_copy_Data_Y = copy_Data_text_Y;
        JButton treeFile_copy_Data = new JButton("...");
        treeFile_copy_Data.setBounds(treeFile_copy_Data_X,treeFile_copy_Data_Y,treeFile_copy_Data_Width,treeFile_copy_Data_Height);
        // Action Button
        treeFile_copy_Data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for downloading
                Thread thread = new Thread(){
                    public void run(){
                        Find_File_Tree(frame,"getDATABASE");
                    }
                };
                // Start thread
                thread.start();
            }
        });
        frame.add(treeFile_copy_Data);

        // BUTTON COPY DATABASE
        int button_copy_Data_Width = path_button_width;
        int button_copy_Data_Height = labelsHeight;
        int button_copy_Data_X = (treeFile_copy_Data_X+treeFile_copy_Data_Width) + space_buttons_X;
        int button_copy_Data_Y = treeFile_copy_Data_Y;
        JButton button_copy_Data = new JButton("[ GET ]");
        button_copy_Data.setBounds(button_copy_Data_X,button_copy_Data_Y,button_copy_Data_Width,button_copy_Data_Height);
        // Action Button
        button_copy_Data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for uploading
                Thread thread = new Thread(){
                    public void run(){
                        new WindowsData().Show("COPING...");
                        String my_path = "";
                        if ( !copy_Data_text.getText().contains(":") ) {
                            my_path = System.getProperty("user.home") + File.separator + copy_Data_text.getText();
                        } else {
                            my_path = copy_Data_text.getText();
                        }
                        if ( DataBase.getDataBase(my_path) ) {
                            WindowsData.Show_Close();
                        } else {
                            WindowsData.Show_Close();
                            new WindowsData().Show("[ERROR COPING..]");
                            try { sleep(3000); } catch (InterruptedException ie) { }
                            WindowsData.Show_Close();
                        }

                        window.dispose();
                        frame.dispose();
                        homeWindow();
                    }
                };
                // Start thread
                thread.start();
            }
        });
        frame.add(button_copy_Data);
        // COPY DATABSE OVER //

        //  RENAME DATABASE //
        // Rename DataBase
        int rename_Data_X = copy_Data_X;
        int rename_Data_Y = (copy_Data_Y+copy_Data_Height)+heightSpace +(heightSpace/7);
        int rename_Data_Width = copy_Data_Width+(copy_Data_Width/4);
        int rename_Data_Height = copy_Data_Height;
        JLabel rename_Data_label = new JLabel("[RENAME DATABASE]: ");
        rename_Data_label.setForeground(text_labels_color);
        rename_Data_label.setFont (text_labels_size);
        rename_Data_label.setBounds(rename_Data_X,rename_Data_Y,rename_Data_Width,rename_Data_Height);
        frame.add(rename_Data_label);

        // Rename DataBase textField name  rename_Data_text
        int rename_Data_text_X = (rename_Data_X+rename_Data_Width) + space_buttons_X;
        int rename_Data_text_Y = rename_Data_Y;
        int rename_Data_text_Width = (labelsWidth*7)-(labelsWidth/2)-(labelsWidth/7)-((labelsWidth/55))-(labelsWidth*4)+(labelsWidth);
        int rename_Data_text_Height = copy_Data_text_Height;
        JTextField rename_Data_text = new JTextField( DataBase.name_database );
        rename_Data_text.setBounds(rename_Data_text_X,rename_Data_text_Y,rename_Data_text_Width,rename_Data_text_Height);
        rename_Data_text.setFont (new Font("Arial", Font.PLAIN, 13));
        rename_Data_text.setBackground(Color.GRAY);
        frame.add(rename_Data_text);

        // BUTTON COPY DATABASE
        int button_rename_Data_Width = path_button_width + treeFile_copy_Data_Width + space_buttons_X;
        int button_rename_Data_Height = path_button_height;
        int button_rename_Data_X = (rename_Data_text_X+rename_Data_text_Width)+space_buttons_X;
        int button_rename_Data_Y = rename_Data_text_Y;
        JButton button_rename_Data = new JButton("[ RENAME ]");
        button_rename_Data.setBounds(button_rename_Data_X,button_rename_Data_Y,button_rename_Data_Width,button_rename_Data_Height);
        // Action Button
        button_rename_Data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for uploading
                Thread thread = new Thread(){
                    public void run(){
                        // RENAME
                        // Rename Directory of Database                                     IF TH NAME IS THE SAME ITH WITH BIG OR SMALL LETTERS HAS PROBELM
                        String old_database_path = DataBase.folderData;
                        String new_database_path = DataBase.myDirectory + File.separator + rename_Data_text.getText();
                        File old_database = new File(old_database_path);
                        File new_database = new File(new_database_path);
                        if ( !old_database.equals(new_database) ) {
                            // Rename the FTP DATABASE
                            new WindowsData().Show("RENAMING...");
                            if ( DataBase.renameDirFtp(DataBase.name_database, rename_Data_text.getText()) ) {
                                DataBase.copyFolder(old_database, new_database); // Make new directory and copy files
                                DataBase.delete(old_database); // Delete old DataBase
                                // Write to file the name o new DataBase
                                DataBase.write2(DataBase.path_name_database_file, rename_Data_text.getText());
                                frame.dispose();
                                DataBase.set_paths();
                                homeWindow();
                            }
                            WindowsData.Show_Close();
                            if ( !DataBase.renameDirFtp(DataBase.name_database, rename_Data_text.getText()) ) {
                                new WindowsData().Show("[CAN'T RENAME DATABSE]");
                                try { sleep(3000); } catch (InterruptedException ie) { }
                                WindowsData.Show_Close();
                            }
                        } else {
                            new WindowsData().Show("[TRY OTHER NAME]");
                            try { sleep(3000); } catch (InterruptedException ie) { }
                            WindowsData.Show_Close();
                        }
                    }
                };
                // Start thread
                thread.start();
            }
        });
        frame.add(button_rename_Data);
        // RENAME DATABASE OVER //

        // COPY DATABASE //
        // Label Copy DataBase
        int copy_DataBase_X = rename_Data_X;
        int copy_DataBase_Y = (rename_Data_Y+copy_Data_Height)+heightSpace +(heightSpace/7);
        int copy_DataBase_Width = copy_Data_Width+(rename_Data_Width/5)+(rename_Data_Width/33);
        int copy_DataBase_Height = rename_Data_Height;
        JLabel copy_DataBase_label = new JLabel("[COPY DATABASE TO]: ");
        copy_DataBase_label.setForeground(text_labels_color);
        copy_DataBase_label.setFont (text_labels_size);
        copy_DataBase_label.setBounds(copy_DataBase_X,copy_DataBase_Y,copy_DataBase_Width,copy_DataBase_Height);
        frame.add(copy_DataBase_label);

        // Copy DataBase textField PATH  copy_DataBase_path_text
        int copy_DataBase_path_text_X = (copy_DataBase_X+copy_DataBase_Width) + space_buttons_X;
        int copy_DataBase_path_text_Y = copy_DataBase_Y;
        int copy_DataBase_path_text_Width = (labelsWidth*7)-(labelsWidth/2)-(labelsWidth/7)-(labelsWidth/15)-(labelsWidth*4)+(labelsWidth);
        int copy_DataBase_path_text_Height = copy_DataBase_Height;
        JTextField copy_DataBase_path_text = new JTextField(DataBase.copy_database_path);
        copy_DataBase_path_text.setBounds(copy_DataBase_path_text_X,copy_DataBase_path_text_Y,copy_DataBase_path_text_Width,copy_DataBase_path_text_Height);
        copy_DataBase_path_text.setFont (new Font("Arial", Font.PLAIN, 13));
        copy_DataBase_path_text.setBackground(Color.GRAY);
        frame.add(copy_DataBase_path_text);

        // MAKE Jtree BUTTON
        int treeFile_copy_DataBase_Width = treeFile_path_WIDTH;
        int treeFile_copy_DataBase_Height = treeFile_path_HEIGHT;
        int treeFile_copy_DataBase_X = (copy_DataBase_path_text_X+copy_DataBase_path_text_Width) + space_buttons_X;
        int treeFile_copy_DataBase_Y = copy_DataBase_path_text_Y;
        JButton treeFile_copy_DataBase = new JButton("...");
        treeFile_copy_DataBase.setBounds(treeFile_copy_DataBase_X,treeFile_copy_DataBase_Y,treeFile_copy_DataBase_Width,treeFile_copy_DataBase_Height);
        // Action Button
        treeFile_copy_DataBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for downloading
                Thread thread = new Thread(){
                    public void run(){
                        Find_File_Tree(frame,"copyDATABASE");
                    }
                };
                // Start thread
                thread.start();
            }
        });
        frame.add(treeFile_copy_DataBase);

        // Button set the path
        int copy_DataBase_button_X = (treeFile_copy_DataBase_X+treeFile_copy_DataBase_Width) + space_buttons_X;
        int copy_DataBase_button_Y = treeFile_copy_DataBase_Y;
        int copy_DataBase_button_width = path_button_width;
        int copy_DataBase_button_height = treeFile_copy_DataBase_Height;
        JButton copy_DataBase_button = new JButton("[ COPY ]");
        // Action Button
        copy_DataBase_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Copy DataBase
                // Make a sub class thread for uploading
                Thread thread = new Thread(){
                    public void run(){
                        new WindowsData().Show("COPING...");
                        // Get the Paths
                        String old_path_to_copy_database = DataBase.folderData;
                        String new_path_to_copy_database = "";
                        if ( !new_path_to_copy_database.contains(":") ) {
                            new_path_to_copy_database = System.getProperty("user.home") + File.separator + copy_DataBase_path_text.getText() + File.separator + DataBase.name_database;
                        } else {
                            new_path_to_copy_database = copy_DataBase_path_text.getText() + File.separator + DataBase.name_database;
                        }
                        System.out.println("[old_path_to_copy_database] " + old_path_to_copy_database);
                        System.out.println("[new_path_to_copy_database] " + new_path_to_copy_database);
                        // Copy loacal database
                        File sourceFolder = new File(old_path_to_copy_database);
                        File destinationFolder = new File(new_path_to_copy_database);
                        DataBase.copyFolder(sourceFolder, destinationFolder);
                        WindowsData.Show_Close();
                    }
                };
                // Start thread
                thread.start();
            }
        });
        copy_DataBase_button.setBounds(copy_DataBase_button_X,copy_DataBase_button_Y,copy_DataBase_button_width,copy_DataBase_button_height);
        frame.add(copy_DataBase_button);
        // COPY DATABASE OVER //

        // FTP SETTINGS //
        Color ftp_color_text = Color.DARK_GRAY;//this.color;
        Color ftp_color_back = Color.GRAY;//Color.DARK_GRAY;
        int ftp_X = (path_button_X+path_button_width) + (space_buttons_X*5);
        int ftp_Y = path_label_Y;//-(path_label_Y/3);
        int ftp_Width = (labelsWidth*2)+(labelsWidth/3);
        int ftp_Height = (labelsHeight*2);
        JLabel ftp_label = new JLabel("[ FTP IMFORMATIONS ]");
        //ftp_label.setForeground(text_labels_color); // textColor
        ftp_label.setForeground(new Color(255,140,0)); // textColor
        ftp_label.setFont (new Font("Arial", Font.PLAIN, 25));
        ftp_label.setBounds(ftp_X,ftp_Y,ftp_Width,ftp_Height);
        JPanel panel_ftp_label = new JPanel();
        panel_ftp_label.add(ftp_label);
        panel_ftp_label.setBackground(Color.DARK_GRAY); // color
        panel_ftp_label.setBounds(ftp_X,ftp_Y,ftp_Width,ftp_Height);
        frame.add(panel_ftp_label);

        // FTP URL LABEL
        int url_ftp_X =  ftp_X;
        int url_ftp_Y = (ftp_Y+ftp_Height)+heightSpace;
        int url_ftp_width = labelsWidth-(labelsWidth/5);
        int url_ftp_height = labelsHeight;
        JLabel url_ftp_label = new JLabel("URL: ");
        url_ftp_label.setFont(new Font("Arial", Font.PLAIN, 13));
        url_ftp_label.setForeground(ftp_color_text); // textColor
        JPanel url_ftp_panel = new JPanel();
        url_ftp_panel.add(url_ftp_label);
        url_ftp_panel.setBackground(ftp_color_back); // color
        url_ftp_label.setBounds(url_ftp_X,url_ftp_Y,url_ftp_width,url_ftp_height);
        url_ftp_panel.setBounds(url_ftp_X,url_ftp_Y,url_ftp_width,url_ftp_height);
        frame.add(url_ftp_panel);

        // FTP URL TEXTFIELD     url_ftp_text
        int url_ftp_text_X = (ftp_X+url_ftp_width)+space_buttons_X;
        int url_ftp_text_Y = url_ftp_Y;
        int url_ftp_text_Width = (ftp_Width-url_ftp_width)-space_buttons_X;
        int url_ftp_text_Height = url_ftp_height;
        JTextField url_ftp_text = new JTextField( DataBase.getURL() );
        url_ftp_text.setBounds(url_ftp_text_X,url_ftp_text_Y, url_ftp_text_Width, url_ftp_text_Height);
        url_ftp_text.setFont (new Font("Arial", Font.PLAIN, 13));
        url_ftp_text.setBackground(Color.GRAY);
        frame.add(url_ftp_text);

        // FTP PORT LABEL
        int port_ftp_X =  ftp_X;
        int port_ftp_Y = (url_ftp_Y+url_ftp_height)+heightSpace;
        int port_ftp_width = url_ftp_width;
        int port_ftp_height = url_ftp_height;
        JLabel port_ftp_label = new JLabel("PORT: ");
        port_ftp_label.setFont(new Font("Arial", Font.PLAIN, 13));
        port_ftp_label.setForeground(ftp_color_text);// textColor
        JPanel port_ftp_panel = new JPanel();
        port_ftp_panel.add(port_ftp_label);
        port_ftp_panel.setBackground(ftp_color_back);// color
        port_ftp_label.setBounds(port_ftp_X,port_ftp_Y,port_ftp_width,port_ftp_height);
        port_ftp_panel.setBounds(port_ftp_X,port_ftp_Y,port_ftp_width,port_ftp_height);
        frame.add(port_ftp_panel);

        // FTP PORT TEXTFIELD    port_ftp_text
        int port_ftp_text_X = (port_ftp_X+port_ftp_width)+space_buttons_X;
        int port_ftp_text_Y = port_ftp_Y;
        int port_ftp_text_Width = (ftp_Width-port_ftp_width)-space_buttons_X;
        int port_ftp_text_Height = url_ftp_height;
        JTextField port_ftp_text = new JTextField( DataBase.getPORT() );
        port_ftp_text.setBounds(port_ftp_text_X,port_ftp_text_Y, port_ftp_text_Width, port_ftp_text_Height);
        port_ftp_text.setFont (new Font("Arial", Font.PLAIN, 13));
        port_ftp_text.setBackground(Color.GRAY);
        frame.add(port_ftp_text);

        // FTP USER NAME LABEL
        int user_name_ftp_X =  ftp_X;
        int user_name_ftp_Y = (port_ftp_Y+port_ftp_height)+heightSpace;
        int user_name_ftp_width = url_ftp_width;
        int user_name_ftp_height = url_ftp_height;
        JLabel user_name_ftp_label = new JLabel("USER NAME: ");
        user_name_ftp_label.setFont(new Font("Arial", Font.PLAIN, 13));
        user_name_ftp_label.setForeground(ftp_color_text);// textColor
        JPanel user_name_ftp_panel = new JPanel();
        user_name_ftp_panel.add(user_name_ftp_label);
        user_name_ftp_panel.setBackground(ftp_color_back);// color
        user_name_ftp_label.setBounds(user_name_ftp_X,user_name_ftp_Y,user_name_ftp_width,user_name_ftp_height);
        user_name_ftp_panel.setBounds(user_name_ftp_X,user_name_ftp_Y,user_name_ftp_width,user_name_ftp_height);
        frame.add(user_name_ftp_panel);

        // FTP USER NAME TEXTFIELD     user_name_ftp_text
        int user_name_ftp_text_X = (user_name_ftp_X+user_name_ftp_width)+space_buttons_X;
        int user_name_ftp_text_Y = user_name_ftp_Y;
        int user_name_ftp_text_Width = (ftp_Width-user_name_ftp_width)-space_buttons_X;
        int user_name_ftp_text_Height = url_ftp_height;
        JTextField user_name_ftp_text = new JTextField( DataBase.getUSERNAME() );
        user_name_ftp_text.setBounds(user_name_ftp_text_X,user_name_ftp_text_Y, user_name_ftp_text_Width, user_name_ftp_text_Height);
        user_name_ftp_text.setFont (new Font("Arial", Font.PLAIN, 13));
        user_name_ftp_text.setBackground(Color.GRAY);
        frame.add(user_name_ftp_text);

        // FTP PASSWORD LABEL
        int password_ftp_X =  ftp_X;
        int password_ftp_Y = (user_name_ftp_Y+user_name_ftp_height)+heightSpace;
        int password_ftp_width = url_ftp_width;
        int password_ftp_height = url_ftp_height;
        JLabel password_ftp_label = new JLabel("PASSWORD: ");
        password_ftp_label.setFont(new Font("Arial", Font.PLAIN, 13));
        password_ftp_label.setForeground(ftp_color_text);// textColor
        JPanel password_ftp_panel = new JPanel();
        password_ftp_panel.add(password_ftp_label);
        password_ftp_panel.setBackground(ftp_color_back);// color
        password_ftp_label.setBounds(password_ftp_X,password_ftp_Y,password_ftp_width,password_ftp_height);
        password_ftp_panel.setBounds(password_ftp_X,password_ftp_Y,password_ftp_width,password_ftp_height);
        frame.add(password_ftp_panel);

        // FTP PASSWORD TEXTFIELD   password_ftp_text
        int password_ftp_text_X = (password_ftp_X+password_ftp_width)+space_buttons_X;
        int password_ftp_text_Y = password_ftp_Y;
        int password_ftp_text_Width = (ftp_Width-password_ftp_width)-space_buttons_X;
        int password_ftp_text_Height = url_ftp_height;
        JTextField password_ftp_text = new JTextField( DataBase.getPASSWORD() );
        password_ftp_text.setBounds(password_ftp_text_X,password_ftp_text_Y, password_ftp_text_Width, password_ftp_text_Height);
        password_ftp_text.setFont (new Font("Arial", Font.PLAIN, 13));
        password_ftp_text.setBackground(Color.GRAY);
        frame.add(password_ftp_text);

        // FTP PATH IN FTP LABEL
        int path_ftp_X =  ftp_X;
        int path_ftp_Y = (password_ftp_Y+password_ftp_height)+heightSpace;
        int path_ftp_width = url_ftp_width;
        int path_ftp_height = url_ftp_height;
        JLabel path_ftp_label = new JLabel("PATH IN FTP: ");
        path_ftp_label.setFont(new Font("Arial", Font.PLAIN, 13));
        path_ftp_label.setForeground(ftp_color_text);// textColor
        JPanel path_ftp_panel = new JPanel();
        path_ftp_panel.add(path_ftp_label);
        path_ftp_panel.setBackground(ftp_color_back);// color
        path_ftp_label.setBounds(path_ftp_X,path_ftp_Y,path_ftp_width,path_ftp_height);
        path_ftp_panel.setBounds(path_ftp_X,path_ftp_Y,path_ftp_width,path_ftp_height);
        frame.add(path_ftp_panel);

        // FTP PATH IN FTP TEXTFIELD    path_ftp_text
        int path_ftp_text_X = (path_ftp_X+path_ftp_width)+space_buttons_X;
        int path_ftp_text_Y = path_ftp_Y;
        int path_ftp_text_Width = (ftp_Width-path_ftp_width)-space_buttons_X;
        int path_ftp_text_Height = url_ftp_height;
        JTextField path_ftp_text = new JTextField( DataBase.getPATH() );
        path_ftp_text.setBounds(path_ftp_text_X,path_ftp_text_Y, path_ftp_text_Width, path_ftp_text_Height);
        path_ftp_text.setFont (new Font("Arial", Font.PLAIN, 13));
        path_ftp_text.setBackground(Color.GRAY);
        frame.add(path_ftp_text);

        // BUTTON FTP SET
        int button_set_Width = ftp_Width;//url_ftp_width;
        int button_set_Height = url_ftp_height;
        int button_set_X = (ftp_X+ftp_Width)-button_set_Width;
        int button_set_Y = (path_ftp_Y+path_ftp_height)+heightSpace;
        JButton button_Set = new JButton("[ SET ]");
        button_Set.setBounds(button_set_X,button_set_Y,button_set_Width,button_set_Height);
        // Action Button
        boolean check = true;
        button_Set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for downloading
                Thread thread = new Thread(){
                    public void run(){
                        String addres = url_ftp_text.getText();
                        int port = -1;
                        if ( !port_ftp_text.getText().isEmpty() ) {
                            port = Integer.parseInt(port_ftp_text.getText());
                        }
                        String user_name = user_name_ftp_text.getText();
                        String password = password_ftp_text.getText();
                        String ftp_data_dir = path_ftp_text.getText();
                        if ( addres.isEmpty() || user_name.isEmpty() || password.isEmpty() ) {
                            boolean check = false;
                        }
                        if (check) {
                            // Set acount FTP and save data to ftp_data file
                            DataBase.setAccountFTP(addres, port, user_name, password, ftp_data_dir);
                            // Test Connections
                            WindowsData.Show("[ CONNECTING... ]");
                            if ( DataBase.checkConnectionFTP() ) {
                                WindowsData.Show_Close();
                                WindowsData.Show("[ CONNECTION SUCCESS ]");
                                try { this.sleep(3000); } catch(InterruptedException ie) { }
                                WindowsData.Show_Close();
                            } else {
                                WindowsData.Show_Close();
                                WindowsData.Show("[ CONNECTION FAILURE ]");
                                try { this.sleep(3000); } catch(InterruptedException ie) { }
                                WindowsData.Show_Close();
                            }
                        } else {
                            WindowsData.Show("[ CONNECTION FAILURE ]");
                            try { this.sleep(3000); } catch(InterruptedException ie) { }
                            WindowsData.Show_Close();
                        }
                    }
                };
                // Start thread
                thread.start();
            }
        });
        frame.add(button_Set);
        //  FTP SETTING OVER //

        // CHECK BOXS SETTINGS //
        // Text Size Label
        int textSizeX = (frameWidth/11);
        int textSizeY = copy_DataBase_Y + (heightSpace*5);
        int textSizeWidth = (labelsWidth/2)+(labelsWidth/11);
        int textSizeHeight = (labelsHeight)+(labelsHeight/2);
        JLabel labelCollor = new JLabel("Text Size:");
        labelCollor.setFont(text_labels_size);
        labelCollor.setForeground(text_check_box);
        JPanel text_size_panel = new JPanel();
        text_size_panel.setBackground(color);
        //text_size_panel.setBackground(Color.GRAY);
        text_size_panel.add(labelCollor);
        labelCollor.setBounds(textSizeX,textSizeY,labelsWidth,labelsHeight);
        text_size_panel.setBounds(textSizeX,textSizeY,textSizeWidth,textSizeHeight);
        frame.add(text_size_panel);

        // Text Size small
        int checkX1 = (textSizeX+textSizeWidth) + space_buttons_X;
        int checkY1 = textSizeY + heightSpace;
        int widthCheck1 = frameWidth/17;
        int heightCheck1 = frameHeight/34;
        JCheckBox tseckBoxTextSize1 = new JCheckBox("SMALL   ");
        tseckBoxTextSize1.setBackground(color);
        tseckBoxTextSize1.setOpaque(false);
        tseckBoxTextSize1.setForeground(textColor);
        tseckBoxTextSize1.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxTextSize1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());
                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    textSize = new Font("Arial", Font.PLAIN, 20);
                    DataBase.write2(DataBase.text_size, "20"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckTextSize1 = new JPanel();
        mainPanelTseckTextSize1.setBounds(checkX1, checkY1, widthCheck1, heightCheck1);
        mainPanelTseckTextSize1.setBackground(color);
        mainPanelTseckTextSize1.add(tseckBoxTextSize1); 
        frame.add(mainPanelTseckTextSize1);

        // Text Size MEDIUM
        int checkX2 = checkX1;
        int checkY2 = (checkY1+heightCheck1) + (heightSpace/2);
        int widthCheck2 = widthCheck1;
        int heightCheck2 = heightCheck1;
        JCheckBox tseckBoxTextSize2 = new JCheckBox("MEDIUM");
        tseckBoxTextSize2.setBackground(color);
        tseckBoxTextSize2.setOpaque(false);
        tseckBoxTextSize2.setForeground(textColor);
        tseckBoxTextSize2.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxTextSize2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());
                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    textSize = new Font("Arial", Font.PLAIN, 30);
                    DataBase.write2(DataBase.text_size, "30"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckTextSize2 = new JPanel();
        mainPanelTseckTextSize2.setBounds(checkX2, checkY2, widthCheck2, heightCheck2);
        mainPanelTseckTextSize2.setBackground(color);
        mainPanelTseckTextSize2.add(tseckBoxTextSize2); 
        frame.add(mainPanelTseckTextSize2);

        // Text Size LARGE
        int checkX3 = checkX2;
        int checkY3 = (checkY2+heightCheck2) + (heightSpace/2);
        int widthCheck3 = widthCheck2;
        int heightCheck3 = heightCheck1;
        JCheckBox tseckBoxTextSize3 = new JCheckBox("LARGE  ");
        tseckBoxTextSize3.setBackground(color);
        tseckBoxTextSize3.setOpaque(false);
        tseckBoxTextSize3.setForeground(textColor);
        tseckBoxTextSize3.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxTextSize3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());
                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    textSize = new Font("Arial", Font.PLAIN, 38);
                    DataBase.write2(DataBase.text_size, "38"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckTextSize3 = new JPanel();
        mainPanelTseckTextSize3.setBounds(checkX3, checkY3, widthCheck3, heightCheck3);
        mainPanelTseckTextSize3.setBackground(color);
        mainPanelTseckTextSize3.add(tseckBoxTextSize3); 
        frame.add(mainPanelTseckTextSize3);

        //  Check Box  Text Color
        // Collor Label
        int labelCollorTX = (checkX1+widthCheck1) + (space_buttons_X);
        int labelCollorTY = textSizeY;
        int textColorWidth = (textSizeWidth+(textSizeWidth/5));
        int textColorHeight = textSizeHeight;
        JLabel labelCollorT = new JLabel("Text Collor:");
        labelCollorT.setFont(text_labels_size);
        labelCollorT.setForeground(text_check_box);
        JPanel color_T = new JPanel();
        color_T.add(labelCollorT);
        color_T.setBackground(color);
        labelCollorT.setBounds(labelCollorTX,labelCollorTY,textColorWidth,textColorHeight);
        color_T.setBounds(labelCollorTX,labelCollorTY,textColorWidth,textColorHeight);
        frame.add(color_T);

        // Black
        int checkXT1 = (labelCollorTX +textColorWidth) + space_buttons_X;
        int checkYT1 = labelCollorTY + heightSpace;
        int widthCheckT1 = widthCheck1;
        int heightCheckT1 = heightCheck1;
        JCheckBox tseckBox1 = new JCheckBox("BLACK  ");
        tseckBox1.setBackground(color);
        tseckBox1.setOpaque(false);
        tseckBox1.setForeground(textColor);
        tseckBox1.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());
                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    textColor = Color.BLACK;
                    DataBase.write2(DataBase.text_color, "black"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseck1 = new JPanel();
        mainPanelTseck1.setBounds(checkXT1, checkYT1, widthCheckT1, heightCheckT1);
        mainPanelTseck1.setBackground(color);
        mainPanelTseck1.add(tseckBox1); 
        frame.add(mainPanelTseck1);

        // Gray
        //  Check Box gia to Color
        int checkXT2 = checkXT1;
        int checkYT2 = (checkYT1+heightCheckT1) + (heightSpace/2);
        int widthCheckT2 = widthCheckT1;
        int heightCheckT2 = heightCheckT1;
        JCheckBox tseckBox2 = new JCheckBox("GRAY     ");
        tseckBox2.setBackground(color);
        tseckBox2.setOpaque(false);
        tseckBox2.setForeground(textColor);
        tseckBox2.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());
                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    textColor = Color.DARK_GRAY;
                    DataBase.write2(DataBase.text_color, "gray"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseck2 = new JPanel();
        mainPanelTseck2.setBounds(checkXT2, checkYT2, widthCheckT2, heightCheckT2);
        mainPanelTseck2.setBackground(color);
        mainPanelTseck2.add(tseckBox2); 
        frame.add(mainPanelTseck2);

        // White
        //  Check Box gia to Color
        int checkXT3 = checkXT2;
        int checkYT3 = (checkYT2+heightCheckT2) + (heightSpace/2);
        int widthCheckT3 = widthCheckT2;
        int heightCheckT3 = heightCheckT2;
        JCheckBox tseckBox3 = new JCheckBox("WHITE    ");
        tseckBox3.setBackground(color);
        tseckBox3.setOpaque(false);
        tseckBox3.setForeground(textColor);
        tseckBox3.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());

                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    textColor = Color.WHITE;
                    DataBase.write2(DataBase.text_color, "white"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseck3 = new JPanel();
        mainPanelTseck3.setBounds(checkXT3, checkYT3, widthCheckT3, heightCheckT3);
        mainPanelTseck3.setBackground(color);
        mainPanelTseck3.add(tseckBox3); 
        frame.add(mainPanelTseck3);

        // Check Box  Background Color
        int labelCollorBX = (checkXT3+widthCheckT3) + (space_buttons_X);
        int labelCollorBY = labelCollorTY;
        int labelColor_B_Width = textColorWidth+(textColorWidth/11);
        int labelColor_B_Height = textColorHeight;
        JLabel labelCollorB = new JLabel("Buckground:");
        labelCollorB.setFont(text_labels_size);
        labelCollorB.setForeground(text_check_box);
        JPanel color_B = new JPanel();
        color_B.setBackground(color);
        color_B.add(labelCollorB);
        labelCollorB.setBounds(labelCollorBX,labelCollorBY,labelColor_B_Width,labelColor_B_Height);
        color_B.setBounds(labelCollorBX,labelCollorBY,labelColor_B_Width,labelColor_B_Height);
        frame.add(color_B);

        // Black
        int checkXB1 = (labelCollorBX+labelColor_B_Width) + space_buttons_X;
        int checkYB1 = labelCollorBY + heightSpace;
        int widthCheckB1 = widthCheckT1;
        int heightCheckB1 = heightCheckT1;
        JCheckBox tseckBoxBack1 = new JCheckBox("BLACK    ");
        tseckBoxBack1.setBackground(color);
        tseckBoxBack1.setOpaque(false);
        tseckBoxBack1.setForeground(textColor);
        tseckBoxBack1.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxBack1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());

                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    color = Color.BLACK;
                    textColor = Color.WHITE;
                    DataBase.write2(DataBase.back_color, "black"); // Write data to database
                    DataBase.write2(DataBase.text_color, "white");
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckBack1 = new JPanel();
        mainPanelTseckBack1.setBounds(checkXB1,checkYB1, widthCheckB1, heightCheckB1);
        mainPanelTseckBack1.setBackground(color);
        mainPanelTseckBack1.add(tseckBoxBack1); 
        frame.add(mainPanelTseckBack1);

        // Gray
        //  Check Box gia to Color
        int checkXB2 = checkXB1;
        int checkYB2 = (checkYB1+heightCheckB1) + (heightSpace/2);
        int widthCheckB2 = widthCheckB1;
        int heightCheckB2 = heightCheckB1;
        JCheckBox tseckBoxBack2 = new JCheckBox("BLUE       ");
        tseckBoxBack2.setBackground(color);
        tseckBoxBack2.setOpaque(false);
        tseckBoxBack2.setForeground(textColor);
        tseckBoxBack2.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxBack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());

                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    color = new Color(0 , 0 , 73);
                    DataBase.write2(DataBase.back_color, "blue"); // Write data to database
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckBack2 = new JPanel();
        mainPanelTseckBack2.setBounds(checkXB2,checkYB2, widthCheckB2, heightCheckB2);
        mainPanelTseckBack2.setBackground(color);
        mainPanelTseckBack2.add(tseckBoxBack2); 
        frame.add(mainPanelTseckBack2);

        // White
        //  Check Box gia to Color
        int checkXB3 = checkXB2;
        int checkYB3 = (checkYB2+heightCheckB2) + (heightSpace/2);
        int widthCheckB3 = widthCheckB2;
        int heightCheckB3 = heightCheckB2;
        JCheckBox tseckBoxBack3 = new JCheckBox("WHITE      ");
        tseckBoxBack3.setBackground(color);
        tseckBoxBack3.setOpaque(false);
        tseckBoxBack3.setForeground(textColor);
        tseckBoxBack3.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxBack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());

                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    color = Color.WHITE;
                    textColor = Color.BLACK;
                    DataBase.write2(DataBase.back_color, "white"); // Write data to database
                    DataBase.write2(DataBase.text_color, "black");
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckBack3 = new JPanel();
        mainPanelTseckBack3.setBounds(checkXB3,checkYB3, widthCheckB3, heightCheckB3);
        mainPanelTseckBack3.setBackground(color);
        mainPanelTseckBack3.add(tseckBoxBack3); 
        frame.add(mainPanelTseckBack3);

        // Preferred
        //  Check Box gia to Color
        int checkXB4 = checkXB3 + (widthCheckB3/2);
        int checkYB4 = (checkYB3+heightCheckB3) + (heightSpace/2);
        int widthCheckB4 = widthCheckB3;
        int heightCheckB4 = heightCheckB3;
        JCheckBox tseckBoxBack4 = new JCheckBox("Preferred");
        tseckBoxBack4.setBackground(color);
        tseckBoxBack4.setOpaque(false);
        tseckBoxBack4.setForeground(textColor);
        tseckBoxBack4.setFocusPainted( false );
        //checkBox.setEnabled(false);
        // Action Button
        tseckBoxBack4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                JOptionPane.showMessageDialog(frame,((JCheckBox)source).getText() + ": " + ((JCheckBox)source).isSelected());

                // HERE i have Function As Parametre  UP a ihave helper function
                if ( ((JCheckBox)source).isSelected() ) { // if ( checked )
                    color = new Color(27, 14, 14);
                    textColor = Color.GRAY;
                    clientDataColor = Color.GREEN;
                    textSize = new Font("Arial", Font.PLAIN, 23);
                    DataBase.write2(DataBase.text_size, "preferred"); // Write data to database
                    DataBase.write2(DataBase.text_color, "preferred"); // Write data to database
                    DataBase.write2(DataBase.back_color, "preferred"); // Write data to database

                    // Reset the Data D path
                    Vector<String> path_data = new Vector<String>(2);
                    String D_path = DataBase.myDirectory + File.separator + DataBase.name_database + File.separator;
                    path_data.add(D_path); // The new path
                    path_data.add("false"); // True
                    DataBase.write(DataBase.path_file, path_data);
               
                    DataBase.restart();
                }
            }
        });
        JPanel mainPanelTseckBack4 = new JPanel();
        mainPanelTseckBack4.setBounds(checkXB4,checkYB4, widthCheckB4, heightCheckB4);
        mainPanelTseckBack4.setBackground(color);
        mainPanelTseckBack4.add(tseckBoxBack4); 
        frame.add(mainPanelTseckBack4);
        // CHECK BOXS SETTINGS OVER //


        // Pack
        frame.pack();
        // Set place ans size
        frame.setBounds(windowX, windowY, windowWidth, windowheight);
        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    //////////    Hellp Window     ///////////////////////////////////////////////////////////////////////////////////////////

    // Help Window
    public void hellp() {

        JFrame frame = new JFrame("Help");
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(color);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
            }
        });

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Settings window place and size
        int windowWidth = frameWidth/2 + ((frameWidth/2)/2);
        int windowheight = frameHeight/2 + ((frameHeight/2)/2);
        int windowX = (frameWidth / 2) - (windowWidth / 2);
        int windowY = (frameHeight / 2) - (windowheight / 2);

        String html =   "<html>" +
                            "<br><h2> Home Page: </h2>" + "<br>" +
                            "<p>a). Add Client button: Open a page and write the base details for every client." + "<br>" +
                            "b). Refresh button: Will show you new clients in the screen." + "<br>" +
                            "c). Search button: Show you what clients data has tha value of your text." + "<br>" +
                            "d). Menu: Change the Text size of the program, change the background color and text color.</p>" + "<br>" +
                            "<br>" +
                            "<br><h2> Client Page: </h2>" + "<br>" +
                            "<p>a) In every collumn has a button to add a new value. " + "<br>" +
                            "b). If you double click the value open a txt label for your data write and press apply." + "<br>" +
                            "c). With right click in value you can rename the value or delete the value." + "<br>" +
                            "d). Edit button: is if you wont to changes clients data." + "<br>" +
                            "e). Delete button: is to delete the client. </p>" + "<br>" + 
                        "</html>";
        int hellptextX = frameWidth/15;
        int hellptextY = frameHeight/15;
        int hellptextWidth = frameWidth/2;
        int hellptextHeight = frameHeight/2;
        JLabel hellptext = new JLabel(html);
        hellptext.setForeground(textColor);
        JPanel hellp_panel = new JPanel();
        hellp_panel.setBackground(color);
        hellp_panel.add(hellptext);
        hellptext.setBounds(hellptextX,hellptextY,hellptextWidth,hellptextHeight);
        hellp_panel.setBounds(hellptextX,hellptextY,hellptextWidth,hellptextHeight);
        frame.add(hellp_panel);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(windowX, windowY, windowWidth, windowheight);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Tree File window
    public static void Find_File_Tree(JFrame window, String category) {
        JFrame frame = new JFrame("[ FILES ]");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        // if you exit refrace
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
            }
        });
        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addVetoableChangeListener("focusedWindow",
                    new VetoableChangeListener() {
                        private boolean gained = false;
                        @Override
                        public void vetoableChange(PropertyChangeEvent evt)
                                throws PropertyVetoException {
                            if (evt.getNewValue() == frame) {
                                gained = true;
                            }
                            if (gained && evt.getNewValue() != frame) {
                                frame.dispose();
                            }
                        }
                    });
        // Frame size
        int frameWidth = new WindowsData().screenWidth();
        int frameHeight = new WindowsData().screenHeight();
        // Settings window place and size
        int windowWidth = frameWidth/3;
        int windowheight = frameHeight/3;
        int windowX = (frameWidth / 2) - (windowWidth / 2);
        int windowY = (frameHeight / 2) - (windowheight / 2);

        // Make a tree list with all the nodes, and make it a JTree
        //String node_path = "C:\\Users\\Alexandros.DESKTOP-D8M3V0N\\";
        String node_path = System.getProperty("user.home") + File.separator + "Desktop";
        System.out.println("[USER PATH] " + node_path);
        JTree tree = new JTree(addNodes(null, new File(node_path)));
        tree.setShowsRootHandles(true);

        // Add a listener
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) { // Click 1
                        String file_path = createFilePath(selPath);
                        try {
                            if ( file_path.substring(0,2).indexOf("\\.") != -1 ) {
                                file_path = file_path.substring(2,file_path.length());
                            }
                        } catch (java.util.regex.PatternSyntaxException pe) {
                            System.err.println("[ERROR] " + pe);
                        }
                        if ( category.equals("getDATABASE") ) {
                            WindowsData.get_database_path = file_path;
                        } else if ( category.equals("putDATABASE") ) {
                            DataBase.folderData = file_path;
                        } else if ( category.equals("copyDATABASE") ) {
                            DataBase.copy_database_path = file_path;
                        }
                    }
                    else if(e.getClickCount() == 2) { // Click 2
                    }
                    else if(e.getClickCount() == 3) { // Click 3
                    }
                }
            }
        };
        tree.addMouseListener(ml);
        //scrollpane.setBackground(Color.DARK_GRAY);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setBackgroundNonSelectionColor(Color.WHITE);
        renderer.setTextNonSelectionColor(Color.BLACK);
        renderer.setTextSelectionColor(Color.BLUE);
        renderer.setBackgroundSelectionColor(Color.WHITE);
        renderer.setBorderSelectionColor(Color.WHITE);
        tree.setBackground(Color.WHITE);
        // Lastly, put the JTree into a JScrollPane.
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.getViewport().add(tree);
        scrollpane.setBounds(0, 0, windowWidth-13, windowheight-(windowheight/4));
        frame.add(scrollpane);

        // Button set Path
        int width_button_SET = windowWidth/4;
        int height_button_SET = windowheight/13;
        int x_button_SET = windowWidth - (width_button_SET) - (windowWidth/13) ;
        int y_button_SET = (windowheight-(windowheight/4)+(windowheight/53));
        JButton buttonSET = new JButton("[ SET ]");
        buttonSET.setBounds(x_button_SET,y_button_SET,width_button_SET,height_button_SET);
        // Action Button
        buttonSET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                window.dispose();
                new WindowsData().settings();
            }
        });
        frame.add(buttonSET);

        frame.pack();
        frame.setBounds(windowX, windowY, windowWidth, windowheight);
        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Take TreePath return String path
    public static String createFilePath(TreePath treePath) {
        StringBuilder sb = new StringBuilder();
        Object[] nodes = treePath.getPath();
        for( int i=0; i<nodes.length; i++ ) {
            sb.append(File.separatorChar).append(nodes[i].toString());
        }
        return sb.toString();
    }

    // Add Node for JTree
    public static DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode( curPath.substring(curPath.lastIndexOf(File.separator)+1,curPath.length()) );
        // In the first time curTop mast be null
        if (curTop != null) {
            curTop.add(curDir);
        }
        // Make list with name of inside files
        String[] my_files = dir.list();
        Vector<String> ol = new Vector<String>();
        for (String t : my_files) {
            ol.addElement(t);
        }
        // Sort the files
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        // Make a vector array onli with the files
        Vector<String> files = new Vector<String>();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (String file : ol) {
            String thisObject = file;
            String newPath;
            if ( new File(curPath).isFile() ) { // == File
                newPath = thisObject;
            } else { // folder
                newPath = curPath + File.separator + thisObject;
            }
            // If file == directory call again this function
            if ( new File(newPath).isDirectory() ) {
                addNodes(curDir, new File(newPath));
            } else if ( !new File(newPath).isDirectory() ) { // Files vector array add the file
                files.addElement(thisObject);
            } else {

            }
        }
        // Make the defasult multiple tree mode
        for (String fnum : files) {
            curDir.add(new DefaultMutableTreeNode(fnum));
        }
        return curDir;
    }


    // Edit Client page
    public void editClient(Client client) {

        JFrame frame = new JFrame("Edit");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // if you exit refrace
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                homeWindow();
            }
        });

        frame.getContentPane().setBackground(color);

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        int x = frameWidth/5;
        int LabelsY = frameHeight/15;

        //int labelsNumber = 4;
        int labelsWidth = frameWidth/6;
        int labelsHeight = frameHeight/20;

        // New Client Label
        int labelAddClientX = x;
        int labelAddClientY = frameHeight/7;
        int labelAddClientWidth = frameWidth/3;
        int labelAddClientHeight = frameHeight/10;
        JLabel labelAddClient = new JLabel("Edit Client:");
        labelAddClient.setFont (new Font("Arial", Font.PLAIN, 70));
        labelAddClient.setForeground(textColor);
        labelAddClient.setBounds(labelAddClientX,labelAddClientY,labelAddClientWidth,labelAddClientHeight);                  
        frame.add(labelAddClient);        
    
        // Street Label
        int labelStreetX = x;
        int labelStreetY = labelAddClientY + (frameHeight/5);
        JLabel labelStreet = new JLabel("Addres & Number:");
        labelStreet.setFont (textSize);
        labelStreet.setForeground(textColor);
        labelStreet.setBounds(labelStreetX,labelStreetY,labelsWidth,labelsHeight);                          
        frame.add(labelStreet);

        // Name Label
        int labelLocationX = x;
        int labelLocationY = labelStreetY + LabelsY; 
        JLabel labelLocation = new JLabel("Location:");
        labelLocation.setFont (textSize);
        labelLocation.setForeground(textColor);
        labelLocation.setBounds(labelLocationX,labelLocationY,labelsWidth,labelsHeight);                     
        frame.add(labelLocation);

        // Mobile Phone Label
        int labelTypeX = x;
        int labelTypeY = labelLocationY + LabelsY;
        JLabel labelType = new JLabel("Type:");
        labelType.setFont (textSize);
        labelType.setForeground(textColor);
        labelType.setBounds(labelTypeX,labelTypeY,labelsWidth,labelsHeight);            
        frame.add(labelType);

        // House Phone Label
        int labelPhoneX = x;
        int labelPhoneY = labelTypeY + LabelsY;
        JLabel labelPhone = new JLabel("Phone:");
        labelPhone.setFont (textSize);
        labelPhone.setForeground(textColor);
        labelPhone.setBounds(labelPhoneX,labelPhoneY,labelsWidth,labelsHeight);
        frame.add(labelPhone);

        // Postal Code Label
        int labelPostalCodeX = x;
        int labelPostalCodeY = labelPhoneY + LabelsY;
        JLabel labelPostalCode = new JLabel("Postal Code:");
        labelPostalCode.setFont (textSize);
        labelPostalCode.setForeground(textColor);
        labelPostalCode.setBounds(labelPostalCodeX,labelPostalCodeY,labelsWidth,labelsHeight);
        frame.add(labelPostalCode);

        /////   TextFields    ////
        int textFieldsWidth = frameWidth/2;
        // Text Field Variable positions
        int textFieldStreetX = x + labelsWidth;
        int textFieldsStreetY = labelStreetY + (labelsHeight/4);
        int textFieldStreetHeight = labelsHeight/2;
        int textFieldStreetWidth = textFieldsWidth;

        int textFieldLocationX = x + labelsWidth;
        int textFieldsLocationY = textFieldsStreetY + LabelsY;
        int textFieldLocationHeight = labelsHeight/2;
        int textFieldLocationWidth = textFieldsWidth;

        int textFieldTypeX = x + labelsWidth;
        int textFieldsTypeY = textFieldsLocationY + LabelsY;
        int textFieldTypeHeight = labelsHeight/2;
        int textFieldTypeWidth = textFieldsWidth;   

        int textFieldPhoneX = x + labelsWidth;
        int textFieldsPhoneY = textFieldsTypeY + LabelsY;
        int textFieldPhoneHeight = labelsHeight/2;
        int textFieldPhoneWidth = textFieldsWidth;               

        int textFieldPostalCodeX = x + labelsWidth;
        int textFieldsPostalCodeY = textFieldsPhoneY + LabelsY;
        int textFieldPostalCodeHeight = labelsHeight/2;
        int textFieldPostalCodeWidth = textFieldsWidth;

        // Make the text fields
        JTextField jtextStreet;
        JTextField jtextLocation;
        JTextField jtextType;
        JTextField jtextPhone;
        JTextField jtextPostallCode;
        if (!client.street.equals("-")) {
            jtextStreet = new JTextField(client.street);
        } else {
            jtextStreet = new JTextField();
        }
        if (!client.location.equals("-")) {
            jtextLocation = new JTextField(client.location);
        } else {
            jtextLocation = new JTextField();
        }
        if (!client.type.equals("-")) {
            jtextType = new JTextField(client.type);
        } else {
            jtextType = new JTextField();
        }
        if (!client.phone.equals("-")) {
            jtextPhone = new JTextField(client.phone);
        } else {
            jtextPhone = new JTextField();
        }
        if (!client.postallCode.equals("-")) {
            jtextPostallCode = new JTextField(client.postallCode);
        } else {
            jtextPostallCode = new JTextField();
        }

        // Position and size of textFields
        jtextStreet.setBounds(textFieldStreetX,textFieldsStreetY,textFieldStreetWidth,textFieldStreetHeight);
        jtextLocation.setBounds(textFieldLocationX,textFieldsLocationY,textFieldLocationWidth,textFieldLocationHeight);
        jtextType.setBounds(textFieldTypeX,textFieldsTypeY,textFieldTypeWidth,textFieldTypeHeight);
        jtextPhone.setBounds(textFieldPhoneX,textFieldsPhoneY,textFieldPhoneWidth,textFieldPhoneHeight);
        jtextPostallCode.setBounds(textFieldPostalCodeX,textFieldsPostalCodeY,textFieldPostalCodeWidth,textFieldPostalCodeHeight);
        // Add text fields in frame window
        frame.add(jtextStreet);
        frame.add(jtextLocation);
        frame.add(jtextType);
        frame.add(jtextPhone);
        frame.add(jtextPostallCode);

        // Button Apply Client
        int widthButtonApplyClient = frameWidth/11;
        int heightButtonApplyClient = frameHeight/27;
        int xBA = (frameWidth/2) + (frameWidth/7);
        int yBA = (frameHeight/2) + (frameHeight/4) + (frameHeight/20);

        JButton button = new JButton("Apply:");
        button.setBounds(xBA,yBA,widthButtonApplyClient,heightButtonApplyClient);
        // Action Button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String street = jtextStreet.getText();
                String location = jtextLocation.getText();
                String type = jtextType.getText();
                String phone = jtextPhone.getText();
                String postalCode = jtextPostallCode.getText();
                if ( street.equals("") ) {
                    street = "-";
                }
                if ( location.equals("") ) {
                    location = "-";
                }
                if ( type.equals("") ) {
                    type = "-";
                }
                if ( phone.equals("") ) {
                    phone = "-";
                }
                if ( postalCode.equals("") ) {
                    postalCode = "-";
                }
                if (postalCode.equals("")) {
                    postalCode = "-";
                }
                if (street.indexOf("&") != -1) {
                    street = street.replace("&"," AND ");
                }
                if (location.indexOf("&") != -1) {
                    location = location.replace("&"," AND ");
                }
                if (type.indexOf("&") != -1) {
                    type = type.replace("&"," AND ");
                }
                if (phone.indexOf("&") != -1) {
                    phone = phone.replace("&"," AND ");
                }
                if (postalCode.indexOf("&") != -1) {
                    postalCode = postalCode.replace("&"," AND ");
                }
                Vector<String> now = new Vector<String>();
                now.add(street);
                now.add(location);
                now.add(type);
                now.add(phone);
                now.add(postalCode);

                DataBase.editClient(now, client);
                
                frame.dispose();
                //homeWindow();
                characteristicsWin(client);

            }
        });
        frame.add(button);

        // Button Delete Client
        int widthButtonDeleteClient = widthButtonApplyClient;
        int heightButtonDeleteClient = heightButtonApplyClient;
        int x_DEL = (frameWidth/2) + (frameWidth/4);
        int y_DEL = (frameHeight/2) + (frameHeight/4) + (frameHeight/20);
        JButton buttonDEL = new JButton("Delete Client:");
        buttonDEL.setBounds(x_DEL,y_DEL,widthButtonDeleteClient,heightButtonDeleteClient);
        // Action Button
        buttonDEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //frame.dispose();
                deleteClient(client, frame);
            }
        });
        frame.add(buttonDEL);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Delete Client Window
    public void deleteClient(Client client, JFrame w) {

        JFrame frame = new JFrame("Delete");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addVetoableChangeListener("focusedWindow",
                new VetoableChangeListener() {
                    private boolean gained = false;
                    @Override
                    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                        if (evt.getNewValue() == frame) {
                            gained = true;
                        }
                        if (gained && evt.getNewValue() != frame) {
                            frame.dispose();
                        }
                    }
                });

        // if you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
            }
        });

        frame.getContentPane().setBackground(color);

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        int x = frameWidth/5;
        int LabelsY = frameHeight/15;

        //int labelsNumber = 4;
        int labelsWidth = frameWidth/6;
        int labelsHeight = frameHeight/20;

        // Window Size and Place
        int windowWidth = frameWidth/3;
        int windowHeight = frameHeight/3;
        int window_X = (frameWidth/2)-(windowWidth/2);
        int window_Y = (frameHeight/2)-(windowHeight/2);

        // Label
        int labelWidth = frameWidth/5;
        int labelHeight = frameHeight/17;
        int labelX = window_X - (windowWidth/2) - (windowWidth/3);
        int labelY = window_Y - (windowHeight/2) - (windowHeight/4);
        JLabel label = new JLabel("Are you sure?");
        label.setForeground(textColor);
        label.setFont(new Font("Arial", Font.BOLD, 33));
        label.setBounds(labelX,labelY,labelWidth,labelHeight);
        frame.add(label);

        // Button Delete Client
        int widthButtonDeleteClient = frameWidth/11;
        int heightButtonDeleteClient = frameHeight/27;
        int x_DEL = window_X - (windowWidth/2) + (windowWidth/17);
        int y_DEL = window_Y - (windowHeight/2) + (windowHeight/17);
        JButton buttonDEL = new JButton("Delete Client:");
        buttonDEL.setBounds(x_DEL,y_DEL,widthButtonDeleteClient,heightButtonDeleteClient);
        // Action Button
        buttonDEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Delete from DataBase
                DataBase.deleteClient(client);
                frame.dispose();
                w.dispose();
                homeWindow();
            }
        });
        frame.add(buttonDEL);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(window_X,window_Y,windowWidth,windowHeight);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

///////////     Add  Somthing page      ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addDataPage(Client client, String nameWindow, Vector<String> list, String listName, String type, JFrame w1) {

        JFrame frame = new JFrame(nameWindow);
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(color);

        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addVetoableChangeListener("focusedWindow",
            new VetoableChangeListener() {
                private boolean gained = false;
                @Override
                public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                    if (evt.getNewValue() == frame) {
                        gained = true;
                    }
                    if (gained && evt.getNewValue() != frame) {
                        frame.dispose();
                    }
                }
            });

        // if you exit refrace
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
            }
        });

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels size
        int labelsWidth = (frameWidth/7);
        int labelsHeight = frameHeight/15;

        int windowWidth = frameWidth/2;
        int windowheight = frameHeight/2;
        int windowX = (frameWidth / 2) - (windowWidth / 2);
        int windowY = (frameHeight / 2) - (windowheight / 2);

        // Ask name Label
        int panelX = frameWidth/17;
        int panelY = frameHeight/7;
        int panel_width = (labelsWidth/2)-(labelsWidth/7);
        int panel_height = labelsHeight-(labelsHeight/3);
        JLabel labelStreet = new JLabel(type);
        labelStreet.setFont (textSize);
        labelStreet.setForeground(textColor);
        JPanel street_panel = new JPanel();
        street_panel.add(labelStreet);
        street_panel.setBackground(color);
        street_panel.setBounds(panelX , panelY, panel_width, panel_height);
        frame.add(street_panel);

        // TextField
        int text_width = labelsWidth*2+(labelsWidth/17);
        int text_height = panel_height;
        int textX = panelX + (labelsWidth/2);    
        int textY = panelY;   
        JTextField jtext = new JTextField();
        jtext.setBounds(textX,textY,text_width,text_height);
        frame.add(jtext);

        // Button Add
        int button_add_width = labelsWidth/2;
        int button_add_height = labelsHeight-(labelsHeight/3);
        int bX = ((frameWidth/3) + (button_add_width/3)) - (button_add_width/14);
        int bY = frameHeight/5;  
        JButton button = new JButton("Add");
        button.setBounds(bX,bY,button_add_width,button_add_height);
        // Action Button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Thread thread = new Thread() {
                    public void run() {
                        // If the list not contain the date
                        String nameList = jtext.getText();
                        boolean check = true;
                        for (String l : list) {
                            if ( l.equals(nameList) ) {
                                check = false;
                            }
                        }
                        if ( check ) { // Date not Exist
                            boolean check2 = false;
                            try {
                                DataBase.addData(client, list, nameList, listName);
                                check2 = true;
                            } catch (ArrayIndexOutOfBoundsException aie) {
                                new WindowsData().Show("[IT'S NOT DATE]");
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ie) {
                                }
                                WindowsData.Show_Close();
                                check2 = false;
                            }
                            // If add the date ok reopen the window
                            if (check2) { // If the date == 00-00-00
                                frame.dispose();
                                w1.dispose();
                                if (listName.equals("Damage")) {
                                    frame.dispose();
                                    w1.dispose();
                                    DamageWin(client);
                                } else if (listName.equals("Repairs")) {
                                    frame.dispose();
                                    w1.dispose();
                                    RepairsWin(client);
                                } else if (listName.equals("maintence")) {
                                    frame.dispose();
                                    w1.dispose();
                                    maintenceWin(client);
                                } else if (listName.equals("Battaries")) {
                                    frame.dispose();
                                    w1.dispose();
                                    BattariesWin(client);
                                } else if (listName.equals("DocNum")) {
                                    frame.dispose();
                                    w1.dispose();
                                    DocNumWin(client);
                                } else {
                                    frame.dispose();
                                    w1.dispose();
                                    homeWindow();
                                }
                            }
                        } else { // Date exist
                            frame.dispose();
                            new WindowsData().Show("[DATE EXIST]");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ie) {
                            }
                            WindowsData.Show_Close();
                        }
                    }
                };
                thread.start();
            }
        });
        frame.add(button);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(windowX, windowY, windowWidth, windowheight);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Window Battaries
    public void DocNumWin(Client client) {
        JFrame frame = new JFrame("Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        //frame.setResizable(false);
        frame.getContentPane().setBackground(color);

        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels Size
        int labels_left_width = frameWidth/13;
        int labels_right_width = frameWidth/4;
        int labels_height = frameHeight/37;

        // Labes text Size
        Font txt_size = imformations_font;

        // Addres labels
        int x_addres_label = frameWidth/33;
        int y_addres_label = frameHeight/7;
        int width_addres_label = labels_left_width;
        int hight_addres_label = labels_height;
        JLabel addres_label = new JLabel("Addres&Num:");
        addres_label.setBounds(x_addres_label, y_addres_label, width_addres_label, hight_addres_label);
        addres_label.setFont( txt_size );
        addres_label.setForeground(textColor);
        frame.add(addres_label);
        // Addres labels
        int x_addres_label_2 = x_addres_label + width_addres_label - (width_addres_label/7);
        int y_addres_label_2 = y_addres_label;
        int width_addres_label_2 = labels_right_width;
        int hight_addres_label_2 = labels_height;
        JLabel addres_label_2 = new JLabel(client.street);
        addres_label_2.setBounds(x_addres_label_2, y_addres_label_2, width_addres_label_2, hight_addres_label_2);
        addres_label_2.setFont( txt_size );
        addres_label_2.setForeground(clientDataColor);
        frame.add(addres_label_2);

        // Location labels
        int x_location_label = x_addres_label;
        int y_location_label = y_addres_label + (labels_height*2);
        int width_location_label = labels_left_width;
        int hight_location_label = labels_height;
        JLabel location_label = new JLabel("Location:");
        location_label.setBounds(x_location_label, y_location_label, width_location_label, hight_location_label);
        location_label.setFont( txt_size );
        location_label.setForeground(textColor);
        frame.add(location_label);
        // Location labels
        int x_location_label_2 = x_addres_label_2;
        int y_location_label_2 = y_location_label;
        int width_location_label_2 = labels_right_width;
        int hight_location_label_2 = labels_height;
        JLabel location_label_2 = new JLabel(client.location);
        location_label_2.setBounds(x_location_label_2, y_location_label_2, width_location_label_2, hight_location_label_2);
        location_label_2.setFont( txt_size );
        location_label_2.setForeground(clientDataColor);
        frame.add(location_label_2);

        // Type labels
        int x_type_label = x_location_label;
        int y_type_label = y_location_label + (labels_height*2);
        int width_type_label = labels_left_width;
        int hight_type_label = labels_height;
        JLabel type_label = new JLabel("Type:");
        type_label.setBounds(x_type_label, y_type_label, width_type_label, hight_type_label);
        type_label.setFont( txt_size );
        type_label.setForeground(textColor);
        frame.add(type_label);
        // Type labels
        int x_type_label_2 = x_addres_label_2;
        int y_type_label_2 = y_type_label;
        int width_type_label_2 = labels_right_width;
        int hight_type_label_2 = labels_height;
        JLabel type_label_2 = new JLabel(client.type);
        type_label_2.setBounds(x_type_label_2, y_type_label_2, width_type_label_2, hight_type_label_2);
        type_label_2.setFont( txt_size );
        type_label_2.setForeground(clientDataColor);
        frame.add(type_label_2);

        // Phone labels
        int x_phone_label = x_type_label;
        int y_phone_label = y_type_label + (labels_height*2);
        int width_phone_label = labels_left_width;
        int hight_phone_label = labels_height;
        JLabel phone_label = new JLabel("Phone:");
        phone_label.setBounds(x_phone_label, y_phone_label, width_phone_label, hight_phone_label);
        phone_label.setFont( txt_size );
        phone_label.setForeground(textColor);
        frame.add(phone_label);
        // Phone labels
        int x_phone_label_2 = x_addres_label_2;
        int y_phone_label_2 = y_phone_label;
        int width_phone_label_2 = labels_right_width;
        int hight_phone_label_2 = labels_height;
        JLabel phone_label_2 = new JLabel(client.phone);
        phone_label_2.setBounds(x_phone_label_2, y_phone_label_2, width_phone_label_2, hight_phone_label_2);
        phone_label_2.setFont( txt_size );
        phone_label_2.setForeground(clientDataColor);
        frame.add(phone_label_2);

        // Postal Code labels
        int x_postal_label = x_phone_label;
        int y_postal_label = y_phone_label + (labels_height*2);
        int width_postal_label = labels_left_width;
        int hight_postal_label = labels_height;
        JLabel postal_label = new JLabel("P.Code:");
        postal_label.setBounds(x_postal_label, y_postal_label, width_postal_label, hight_postal_label);
        postal_label.setFont( txt_size );
        postal_label.setForeground(textColor);
        frame.add(postal_label);
        // Postal Code labels
        int x_postal_label_2 = x_addres_label_2;
        int y_postal_label_2 = y_postal_label;
        int width_postal_label_2 = labels_right_width;
        int hight_postal_label_2 = labels_height;
        JLabel postal_label_2 = new JLabel(client.postallCode);
        postal_label_2.setBounds(x_postal_label_2, y_postal_label_2, width_postal_label_2, hight_postal_label_2);
        postal_label_2.setFont( txt_size );
        postal_label_2.setForeground(clientDataColor);
        postal_label_2.setBackground(Color.GRAY);
        frame.add(postal_label_2);

        // Id labels
        int x_id_label = x_phone_label;
        int y_id_label = y_postal_label + (labels_height*2);
        int width_id_label = labels_left_width;
        int hight_id_label = labels_height;
        JLabel id_label = new JLabel("ID:");
        id_label.setBounds(x_id_label, y_id_label, width_id_label, hight_id_label);
        id_label.setFont( txt_size );
        id_label.setForeground(textColor);
        frame.add(id_label);
        // Id labels
        int x_id_label_2 = x_addres_label_2;
        int y_id_label_2 = y_id_label;
        int width_id_label_2 = labels_right_width;
        int hight_id_label_2 = labels_height;
        JLabel id_label_2 = new JLabel(client.my_id);
        id_label_2.setBounds(x_id_label_2, y_id_label_2, width_id_label_2, hight_id_label_2);
        id_label_2.setFont( txt_size );
        id_label_2.setForeground(clientDataColor);
        id_label_2.setBackground(Color.GRAY);
        frame.add(id_label_2);

        // Button Add list
        int widthButtonAdd = frameWidth/12;
        int heightButtonAdd = frameHeight/20;
        int XButtonAdd = (x_addres_label*3) + (frameWidth/33);
        int YButtonAdd = y_postal_label + (frameHeight/7);
        // Button Y Space
        int button_y_space =  + heightButtonAdd + (heightButtonAdd/11);
        // Button Print
        int widthButtonPrint = frameWidth/12;
        int heightButtonPrint = heightButtonAdd;
        int XButtonPrint = (x_addres_label*3) + (frameWidth/33);
        int YButtonPrint = YButtonAdd + button_y_space;
        // Buttons size
        int widthButtonOne= widthButtonAdd;
        int heightButtonOne = heightButtonAdd;
        int XButtonOne = x_addres_label;
        int YButtonOne = YButtonAdd;
        int widthButtonTow= widthButtonOne;
        int heightButtonTow = heightButtonOne;
        int XButtonTow = x_addres_label;
        int YButtonTow = YButtonOne + button_y_space;
        int widthButtonFour = widthButtonOne;
        int heightButtonFour = heightButtonOne;
        int XButtonFour = x_addres_label;
        int YButtonFour = YButtonTow + button_y_space;
        int widthButtonThree = widthButtonOne;
        int heightButtonThree = heightButtonOne;
        int XButtonThree = x_addres_label;
        int YButtonThree = YButtonFour + button_y_space;
        int widthButtonBattaries = widthButtonOne;
        int heightButtonBattaries = heightButtonOne;
        int XButtonBattaries = x_addres_label;
        int YButtonBattaries = YButtonThree + button_y_space;
        int widthButtonDocNum = widthButtonOne;
        int heightButtonDocNum = heightButtonOne;
        int XButtonDocNum = x_addres_label;
        int YButtonDocNum = YButtonBattaries + button_y_space;

        // Scrolls Size
        int x_scroll = (frameWidth/4) + (frameWidth/17);
        int y_scroll = (frameHeight/9);// + (frameHeight/43);
        int width_scroll = (frameWidth/2) + (frameWidth/7);
        int height_scroll = frameHeight - (frameHeight/3) + (frameHeight/9);
        // Labels Size
        int width_tittle_label = frameWidth/7;
        int hight_tittle_label = frameHeight/23;
        int x_tittle_label = x_scroll + (width_scroll/2) - (width_tittle_label/2);
        int y_tittle_label = frameHeight/20;
        // Button Edit Client
        int widthButtonEdit = (frameWidth/6)+(frameWidth/110);
        int heightButtonEdit = frameHeight/20;
        int XButtonEdit = XButtonFour;
        int YButtonEdit = YButtonDocNum + (button_y_space+(button_y_space/2));

        // Button Add list
        JButton buttonAdd = new JButton("Add:");
        buttonAdd.setBounds(XButtonAdd,YButtonAdd,widthButtonAdd,heightButtonAdd);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addDataPage(client, "Add DocNum", client.DocNum_dates, "DocNum", "Date:", frame);
            }
        });
        frame.add(buttonAdd);

        // Tittle Category label
        JLabel tittle_label = new JLabel("Costing:");
        tittle_label.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        tittle_label.setFont(textSize);
        tittle_label.setForeground(textColor);
        JPanel tittle_panel = new JPanel();
        tittle_panel.add(tittle_label);
        tittle_panel.setBackground(color);
        tittle_panel.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        frame.add(tittle_panel);

        // Find the biger Array Size
        int largest_size = Collections.max(  Arrays.asList( client.DocNum_dates.size(),
                client.DocNum_docnum.size(),
                client.DocNum_Work.size(),
                client.DocNum_Price.size(),
                client.DocNum_Repayment.size()
        ) );

        // Make the 2D array for the table
        String[] columns = { "Date:", "Costing:", "Work:", "Price:", "Repayment:"};
        String[][] data = new String[largest_size][columns.length];

        // Set date in boxe's
        for (int r = 0; r < client.DocNum_dates.size(); r++) {
            data[r][0] = client.DocNum_dates.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.DocNum_docnum.size(); r++) {
            data[r][1] = client.DocNum_docnum.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.DocNum_Work.size(); r++) {
            data[r][2] = client.DocNum_Work.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.DocNum_Price.size(); r++) {
            data[r][3] = client.DocNum_Price.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.DocNum_Repayment.size(); r++) {
            data[r][3] = client.DocNum_Repayment.get(r).replaceAll(DataBase.save_type_of_file, "");
        }

        // Set the row name with text index if exist
        for (int i = 0; i < data.length; i++) {
            // Read DockNum
            String my_path_DockNum = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + data[i][0] + "-DocNum";
            String file_data_DockNum = DataBase.read(my_path_DockNum);
            if (!file_data_DockNum.isEmpty()) {
                String lines = file_data_DockNum;//.replaceAll("\n", " - ");
                data[i][1] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][1] = "null";
            }
            // Read Work
            String my_path_Work = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + data[i][0] + "-Work";
            String file_data_Work = DataBase.read(my_path_Work);
            if (!file_data_Work.isEmpty()) {
                String lines = file_data_Work;//.replaceAll("\n", " - ");
                data[i][2] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][2] = "null";
            }
            // Read Price
            String my_path_Price = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + data[i][0] + "-Price";
            String file_data_Price = DataBase.read(my_path_Price);
            if (!file_data_Price.isEmpty()) {
                String lines = file_data_Price;//.replaceAll("\n", " - ");
                data[i][3] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][3] = "null";
            }
            // Read Repayment
            String my_path_Repayment = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + data[i][0] + "-Repayment";
            String file_data_Repayment = DataBase.read(my_path_Repayment);
            if (!file_data_Repayment.isEmpty()) {
                String lines = file_data_Repayment;//.replaceAll("\n", " - ");
                data[i][4] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][4] = "null";
            }
        }

        // Set the date, Type1, Type2, Type3, list withn the order of the bigest date
        DataBase.setOrder(data);

        // Set Bataries list with data[][] order
        DataBase.setLists(data,"DocNumWin",client);

        // Get the now values of data
        final Vector<String> last_date = new Vector<String>();
        final Vector<String> last_DocNum = new Vector<String>();
        final Vector<String> last_Work = new Vector<String>();
        final Vector<String> last_Price = new Vector<String>();
        final Vector<String> last_Repayment = new Vector<String>();
        for (int r = 0; r < client.DocNum_dates.size(); r++) {
            last_date.add(client.DocNum_dates.get(r));
        }
        for (int r = 0; r < client.DocNum_docnum.size(); r++) {
            last_DocNum.add(client.DocNum_docnum.get(r));
        }
        for (int r = 0; r < client.DocNum_Work.size(); r++) {
            last_Work.add(client.DocNum_Work.get(r));
        }
        for (int r = 0; r < client.DocNum_Price.size(); r++) {
            last_Price.add(client.DocNum_Price.get(r));
        }
        for (int r = 0; r < client.DocNum_Repayment.size(); r++) {
            last_Repayment.add(client.DocNum_Repayment.get(r));
        }

        // JTable sels not or rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Make the JTable object
        JTable jt = new JTable(model);
        // Set columns width
        jt.getColumnModel().getColumn(0).setPreferredWidth(DocNum_table_dates_column_width);
        jt.getColumnModel().getColumn(1).setPreferredWidth(DocNum_table_DocNum_column_width);
        jt.getColumnModel().getColumn(2).setPreferredWidth(DocNum_table_Work_column_width);
        jt.getColumnModel().getColumn(3).setPreferredWidth(DocNum_table_Price_column_width);
        jt.getColumnModel().getColumn(4).setPreferredWidth(DocNum_table_Repayment_column_width);
        // Set columns font
        JTableHeader header = jt.getTableHeader();
        header.setFont(table_columns_font);
        // Set te value of the rows in center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Set Rows height
        for (int r = 0; r < data.length; r++) {
            jt.setRowHeight(r, table_rows_height);
        }

        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    JTable table = (JTable)evt.getSource();
                    int row = table.getSelectedRow(); // get row
                    int column = table.getSelectedColumn(); // get colum
                    String value = (String)jt.getValueAt(row, column); // get value of cell
                    String date = (String)jt.getValueAt(row, 0);

                    // Right click
                    if (SwingUtilities.isRightMouseButton(evt) == true) {
                        if ( column == 0 ) {
                            //rightClickDeleteWindow(client, client.battaries_dates, row, "Battaries", value, "-Type1", "-Type2", "-Type3", frame, row);
                            rightClickWindow(client, client.DocNum_dates, row, "DocNum", value, "-DocNum", "-Work", "-Price", "-Repayment", frame, row);
                        } else {
                            if (column == 1) { // DocNum
                                String path = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + client.DocNum_dates.get(row) + "-DocNum";
                                readDataClient(path,frame,"DocNumWin",client);
                            }
                            if (column == 2) { // Work
                                String path = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + client.DocNum_dates.get(row) + "-Work";
                                readDataClient(path,frame,"DocNumWin",client);
                            }
                            if (column == 3) { // Price
                                String path = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + client.DocNum_dates.get(row) + "-Price";
                                readDataClient(path,frame,"DocNumWin",client);
                            }
                            if (column == 4) { // Repayment
                                String path = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + client.DocNum_dates.get(row) + "-Repayment";
                                readDataClient(path,frame,"DocNumWin",client);
                            }
                        }
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    frame.dispose();
                    DocNumWin(client);
                }
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        jt.setFont(table_rows_font);
        jt.setForeground(Color.BLACK);
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.setBounds( x_scroll, y_scroll, width_scroll, height_scroll);
        tablePane.add(sp, BorderLayout.CENTER);
        frame.add(tablePane);

        // Button Print
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);
        // Base imformation for print
        String str =  "  ADDRES: " + client.street +
                "\n  LOCATION: " + client.location +
                "\n  TYPE: " + client.type +
                "\n  PHONE: " + client.phone +
                "\n  POSTAL CODE: " + client.postallCode;
        str += "\n\n BATTARIES:\n";
        // Set to str string all data
        for (int i = 0; i < client.DocNum_dates.size(); i++) {
            str += client.DocNum_dates.get(i)
                    + "     DOC_NUM: " + client.DocNum_docnum.get(i)
                    + "     WORK: " + client.DocNum_Work.get(i)
                    + "     PRICE: " + client.DocNum_Price.get(i)
                    + "     REPAYMENT: " + client.DocNum_Repayment.get(i)
                    + "\n";
        }

        final String str_2 = str;
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(str_2);
            }
        });
        frame.add(buttonPrint);

        // Button Change Category
        JButton buttonOne = new JButton("Characteristics:");
        buttonOne.setBounds(XButtonOne, YButtonOne, widthButtonOne,heightButtonOne);
        // Action Button
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                    int table_docnum_column_width_new = column_docnum.getWidth();
                    TableColumn column_work = jt.getColumnModel().getColumn(2);
                    int table_eork_column_width_new = column_work.getWidth();
                    TableColumn column_price = jt.getColumnModel().getColumn(3);
                    int table_price_column_width_new = column_price.getWidth();
                    TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                    int table_repayment_column_width_new = column_repayment.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                        DocNum_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                        DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                        check = true;
                    }
                    if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                        DocNum_table_Work_column_width = table_eork_column_width_new;
                        check = true;
                    }
                    if (table_price_column_width_new != DocNum_table_Price_column_width) {
                        DocNum_table_Price_column_width = table_price_column_width_new;
                        check = true;
                    }
                    if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                        DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_DocNum_Table_Cels();
                    }
                    characteristicsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    characteristicsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonOne);
        // Button Change Category
        JButton buttonTow = new JButton("Maintence:");
        buttonTow.setBounds(XButtonTow, YButtonTow, widthButtonTow, heightButtonTow);
        // Action Button
        buttonTow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                    int table_docnum_column_width_new = column_docnum.getWidth();
                    TableColumn column_work = jt.getColumnModel().getColumn(2);
                    int table_eork_column_width_new = column_work.getWidth();
                    TableColumn column_price = jt.getColumnModel().getColumn(3);
                    int table_price_column_width_new = column_price.getWidth();
                    TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                    int table_repayment_column_width_new = column_repayment.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                        DocNum_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                        DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                        check = true;
                    }
                    if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                        DocNum_table_Work_column_width = table_eork_column_width_new;
                        check = true;
                    }
                    if (table_price_column_width_new != DocNum_table_Price_column_width) {
                        DocNum_table_Price_column_width = table_price_column_width_new;
                        check = true;
                    }
                    if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                        DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_DocNum_Table_Cels();
                    }
                    maintenceWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    maintenceWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonTow);
        // Button Change Category
        JButton buttonThree = new JButton("Repears:");
        buttonThree.setBounds(XButtonThree, YButtonThree, widthButtonThree, heightButtonThree);
        // Action Button
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                    int table_docnum_column_width_new = column_docnum.getWidth();
                    TableColumn column_work = jt.getColumnModel().getColumn(2);
                    int table_eork_column_width_new = column_work.getWidth();
                    TableColumn column_price = jt.getColumnModel().getColumn(3);
                    int table_price_column_width_new = column_price.getWidth();
                    TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                    int table_repayment_column_width_new = column_repayment.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                        DocNum_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                        DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                        check = true;
                    }
                    if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                        DocNum_table_Work_column_width = table_eork_column_width_new;
                        check = true;
                    }
                    if (table_price_column_width_new != DocNum_table_Price_column_width) {
                        DocNum_table_Price_column_width = table_price_column_width_new;
                        check = true;
                    }
                    if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                        DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_DocNum_Table_Cels();
                    }
                    RepairsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    RepairsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonThree);

        // Button Change Category Batteries
        JButton buttonBatteries = new JButton("Batteries:");
        buttonBatteries.setBounds(XButtonBattaries,YButtonBattaries,widthButtonBattaries,heightButtonBattaries);
        // Action Button
        buttonBatteries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                    int table_docnum_column_width_new = column_docnum.getWidth();
                    TableColumn column_work = jt.getColumnModel().getColumn(2);
                    int table_eork_column_width_new = column_work.getWidth();
                    TableColumn column_price = jt.getColumnModel().getColumn(3);
                    int table_price_column_width_new = column_price.getWidth();
                    TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                    int table_repayment_column_width_new = column_repayment.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                        DocNum_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                        DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                        check = true;
                    }
                    if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                        DocNum_table_Work_column_width = table_eork_column_width_new;
                        check = true;
                    }
                    if (table_price_column_width_new != DocNum_table_Price_column_width) {
                        DocNum_table_Price_column_width = table_price_column_width_new;
                        check = true;
                    }
                    if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                        DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_DocNum_Table_Cels();
                    }
                    BattariesWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    BattariesWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonBatteries);

        // Button Change Category DocNum
        JButton buttonDocNum = new JButton("Costing:");
        buttonDocNum.setBounds(XButtonDocNum,YButtonDocNum,widthButtonDocNum,heightButtonDocNum);
        // Action Button
        buttonDocNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                    int table_docnum_column_width_new = column_docnum.getWidth();
                    TableColumn column_work = jt.getColumnModel().getColumn(2);
                    int table_eork_column_width_new = column_work.getWidth();
                    TableColumn column_price = jt.getColumnModel().getColumn(3);
                    int table_price_column_width_new = column_price.getWidth();
                    TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                    int table_repayment_column_width_new = column_repayment.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                        DocNum_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                        DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                        check = true;
                    }
                    if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                        DocNum_table_Work_column_width = table_eork_column_width_new;
                        check = true;
                    }
                    if (table_price_column_width_new != DocNum_table_Price_column_width) {
                        DocNum_table_Price_column_width = table_price_column_width_new;
                        check = true;
                    }
                    if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                        DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_DocNum_Table_Cels();
                    }
                    DocNumWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DocNumWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonDocNum);

        // Button Change Category
        JButton buttonFour = new JButton("Damages:");
        buttonFour.setBounds(XButtonFour, YButtonFour, widthButtonFour, heightButtonFour);
        // Action Button
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                    int table_docnum_column_width_new = column_docnum.getWidth();
                    TableColumn column_work = jt.getColumnModel().getColumn(2);
                    int table_eork_column_width_new = column_work.getWidth();
                    TableColumn column_price = jt.getColumnModel().getColumn(3);
                    int table_price_column_width_new = column_price.getWidth();
                    TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                    int table_repayment_column_width_new = column_repayment.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                        DocNum_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                        DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                        check = true;
                    }
                    if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                        DocNum_table_Work_column_width = table_eork_column_width_new;
                        check = true;
                    }
                    if (table_price_column_width_new != DocNum_table_Price_column_width) {
                        DocNum_table_Price_column_width = table_price_column_width_new;
                        check = true;
                    }
                    if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                        DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_DocNum_Table_Cels();
                    }
                    DamageWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DamageWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonFour);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                int table_docnum_column_width_new = column_docnum.getWidth();
                TableColumn column_work = jt.getColumnModel().getColumn(2);
                int table_eork_column_width_new = column_work.getWidth();
                TableColumn column_price = jt.getColumnModel().getColumn(3);
                int table_price_column_width_new = column_price.getWidth();
                TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                int table_repayment_column_width_new = column_repayment.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                    DocNum_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                    DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                    check = true;
                }
                if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                    DocNum_table_Work_column_width = table_eork_column_width_new;
                    check = true;
                }
                if (table_price_column_width_new != DocNum_table_Price_column_width) {
                    DocNum_table_Price_column_width = table_price_column_width_new;
                    check = true;
                }
                if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                    DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_DocNum_Table_Cels();
                }
                frame.dispose();
                homeWindow();
            }
        });

        // Button Save
        int widthButtonSave = widthButtonPrint;
        int heightButtonSave = heightButtonPrint;
        int XButtonSave = XButtonPrint;
        int YButtonSave = YButtonPrint + button_y_space;
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(XButtonSave,YButtonSave,widthButtonSave,heightButtonSave);
        // Action Button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Save Dates Files
                int rows = jt.getRowCount();
                if (rows <= client.DocNum_dates.size()) {
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String docnum = (String) jt.getValueAt(i, 1);
                        String work = (String) jt.getValueAt(i, 2);
                        String price = (String) jt.getValueAt(i, 3);
                        String repayment = (String) jt.getValueAt(i, 4);
                        try {
                            client.DocNum_dates.set(i, date);
                            client.DocNum_docnum.set(i, docnum);
                            client.DocNum_Work.set(i, work);
                            client.DocNum_Price.set(i, price);
                            client.DocNum_Repayment.set(i, repayment);
                        } catch (java.lang.ArrayIndexOutOfBoundsException ai) {

                        }
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date;
                        Vector<String> lines = new Vector<String>(5);
                        lines.add(date);
                        lines.add(docnum);
                        lines.add(work);
                        lines.add(price);
                        lines.add(repayment);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_DocNum = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-DocNum";
                            String path_DocNum = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-DocNum";
                            DataBase.renameFile(last_path_DocNum, path_DocNum);
                            String last_path_Work = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-Work";
                            String path_Work = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-Work";
                            DataBase.renameFile(last_path_Work, path_Work);
                            String last_path_Price = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-Price";
                            String path_Price = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-Price";
                            DataBase.renameFile(last_path_Price, path_Price);
                            String last_path_Repayment = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-Repayment";
                            String path_Repayment = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-Repayment";
                            DataBase.renameFile(last_path_Repayment, path_Repayment);
                        }
                    }
                } else {
                    client.DocNum_dates.clear();
                    client.DocNum_docnum.clear();
                    client.DocNum_Work.clear();
                    client.DocNum_Price.clear();
                    client.DocNum_Repayment.clear();
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String docnum = (String) jt.getValueAt(i, 1);
                        String Work = (String) jt.getValueAt(i, 2);
                        String Price = (String) jt.getValueAt(i, 3);
                        String Repayment = (String) jt.getValueAt(i, 4);
                        client.DocNum_dates.add(date);
                        client.DocNum_docnum.add(docnum);
                        client.DocNum_Work.add(Work);
                        client.DocNum_Price.add(Price);
                        client.DocNum_Repayment.add(Repayment);
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(docnum);
                        lines.add(Work);
                        lines.add(Price);
                        lines.add(Repayment);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_DocNum = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-DocNum";
                            String path_DocNum = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-DocNum";
                            DataBase.renameFile(last_path_DocNum, path_DocNum);
                            String last_path_Work = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-Work";
                            String path_Work = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-Work";
                            DataBase.renameFile(last_path_Work, path_Work);
                            String last_path_Price = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-Price";
                            String path_Price = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-Price";
                            DataBase.renameFile(last_path_Price, path_Price);
                            String last_path_Repayment = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + last_date.get(i) + "-Repayment";
                            String path_Repayment = DataBase.folderData + client.my_id + File.separator + "DocNum" + File.separator + date + "-Repayment";
                            DataBase.renameFile(last_path_Repayment, path_Repayment);
                        }
                    }
                }

                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_docnum = jt.getColumnModel().getColumn(1);
                int table_docnum_column_width_new = column_docnum.getWidth();
                TableColumn column_work = jt.getColumnModel().getColumn(2);
                int table_eork_column_width_new = column_work.getWidth();
                TableColumn column_price = jt.getColumnModel().getColumn(3);
                int table_price_column_width_new = column_price.getWidth();
                TableColumn column_repayment = jt.getColumnModel().getColumn(4);
                int table_repayment_column_width_new = column_repayment.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != DocNum_table_dates_column_width) {
                    DocNum_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_docnum_column_width_new != DocNum_table_DocNum_column_width) {
                    DocNum_table_DocNum_column_width = table_docnum_column_width_new;
                    check = true;
                }
                if (table_eork_column_width_new != DocNum_table_Work_column_width) {
                    DocNum_table_Work_column_width = table_eork_column_width_new;
                    check = true;
                }
                if (table_price_column_width_new != DocNum_table_Price_column_width) {
                    DocNum_table_Price_column_width = table_price_column_width_new;
                    check = true;
                }
                if (table_repayment_column_width_new != DocNum_table_Repayment_column_width) {
                    DocNum_table_Repayment_column_width = table_repayment_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_DocNum_Table_Cels();
                }
                frame.dispose();
                DocNumWin(client);
            }
        });
        frame.add(buttonSave);

        // Button Edit Client
        JButton buttonED = new JButton("Edit Client:");
        buttonED.setBounds(XButtonEdit,YButtonEdit,widthButtonEdit,heightButtonEdit);
        // Action Button
        buttonED.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editClient(client);
                frame.dispose();
            }
        });
        frame.add(buttonED);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Window Battaries
    public void BattariesWin(Client client) {
        JFrame frame = new JFrame("Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        //frame.setResizable(false);
        frame.getContentPane().setBackground(color);

        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels Size
        int labels_left_width = frameWidth/13;
        int labels_right_width = frameWidth/4;
        int labels_height = frameHeight/37;

        // Labes text Size
        Font txt_size = imformations_font;

        // Addres labels
        int x_addres_label = frameWidth/33;
        int y_addres_label = frameHeight/7;
        int width_addres_label = labels_left_width;
        int hight_addres_label = labels_height;
        JLabel addres_label = new JLabel("Addres&Num:");
        addres_label.setBounds(x_addres_label, y_addres_label, width_addres_label, hight_addres_label);
        addres_label.setFont( txt_size );
        addres_label.setForeground(textColor);
        frame.add(addres_label);
        // Addres labels
        int x_addres_label_2 = x_addres_label + width_addres_label - (width_addres_label/7);
        int y_addres_label_2 = y_addres_label;
        int width_addres_label_2 = labels_right_width;
        int hight_addres_label_2 = labels_height;
        JLabel addres_label_2 = new JLabel(client.street);
        addres_label_2.setBounds(x_addres_label_2, y_addres_label_2, width_addres_label_2, hight_addres_label_2);
        addres_label_2.setFont( txt_size );
        addres_label_2.setForeground(clientDataColor);
        frame.add(addres_label_2);

        // Location labels
        int x_location_label = x_addres_label;
        int y_location_label = y_addres_label + (labels_height*2);
        int width_location_label = labels_left_width;
        int hight_location_label = labels_height;
        JLabel location_label = new JLabel("Location:");
        location_label.setBounds(x_location_label, y_location_label, width_location_label, hight_location_label);
        location_label.setFont( txt_size );
        location_label.setForeground(textColor);
        frame.add(location_label);
        // Location labels
        int x_location_label_2 = x_addres_label_2;
        int y_location_label_2 = y_location_label;
        int width_location_label_2 = labels_right_width;
        int hight_location_label_2 = labels_height;
        JLabel location_label_2 = new JLabel(client.location);
        location_label_2.setBounds(x_location_label_2, y_location_label_2, width_location_label_2, hight_location_label_2);
        location_label_2.setFont( txt_size );
        location_label_2.setForeground(clientDataColor);
        frame.add(location_label_2);

        // Type labels
        int x_type_label = x_location_label;
        int y_type_label = y_location_label + (labels_height*2);
        int width_type_label = labels_left_width;
        int hight_type_label = labels_height;
        JLabel type_label = new JLabel("Type:");
        type_label.setBounds(x_type_label, y_type_label, width_type_label, hight_type_label);
        type_label.setFont( txt_size );
        type_label.setForeground(textColor);
        frame.add(type_label);
        // Type labels
        int x_type_label_2 = x_addres_label_2;
        int y_type_label_2 = y_type_label;
        int width_type_label_2 = labels_right_width;
        int hight_type_label_2 = labels_height;
        JLabel type_label_2 = new JLabel(client.type);
        type_label_2.setBounds(x_type_label_2, y_type_label_2, width_type_label_2, hight_type_label_2);
        type_label_2.setFont( txt_size );
        type_label_2.setForeground(clientDataColor);
        frame.add(type_label_2);

        // Phone labels
        int x_phone_label = x_type_label;
        int y_phone_label = y_type_label + (labels_height*2);
        int width_phone_label = labels_left_width;
        int hight_phone_label = labels_height;
        JLabel phone_label = new JLabel("Phone:");
        phone_label.setBounds(x_phone_label, y_phone_label, width_phone_label, hight_phone_label);
        phone_label.setFont( txt_size );
        phone_label.setForeground(textColor);
        frame.add(phone_label);
        // Phone labels
        int x_phone_label_2 = x_addres_label_2;
        int y_phone_label_2 = y_phone_label;
        int width_phone_label_2 = labels_right_width;
        int hight_phone_label_2 = labels_height;
        JLabel phone_label_2 = new JLabel(client.phone);
        phone_label_2.setBounds(x_phone_label_2, y_phone_label_2, width_phone_label_2, hight_phone_label_2);
        phone_label_2.setFont( txt_size );
        phone_label_2.setForeground(clientDataColor);
        frame.add(phone_label_2);

        // Postal Code labels
        int x_postal_label = x_phone_label;
        int y_postal_label = y_phone_label + (labels_height*2);
        int width_postal_label = labels_left_width;
        int hight_postal_label = labels_height;
        JLabel postal_label = new JLabel("P.Code:");
        postal_label.setBounds(x_postal_label, y_postal_label, width_postal_label, hight_postal_label);
        postal_label.setFont( txt_size );
        postal_label.setForeground(textColor);
        frame.add(postal_label);
        // Postal Code labels
        int x_postal_label_2 = x_addres_label_2;
        int y_postal_label_2 = y_postal_label;
        int width_postal_label_2 = labels_right_width;
        int hight_postal_label_2 = labels_height;
        JLabel postal_label_2 = new JLabel(client.postallCode);
        postal_label_2.setBounds(x_postal_label_2, y_postal_label_2, width_postal_label_2, hight_postal_label_2);
        postal_label_2.setFont( txt_size );
        postal_label_2.setForeground(clientDataColor);
        postal_label_2.setBackground(Color.GRAY);
        frame.add(postal_label_2);

        // Id labels
        int x_id_label = x_phone_label;
        int y_id_label = y_postal_label + (labels_height*2);
        int width_id_label = labels_left_width;
        int hight_id_label = labels_height;
        JLabel id_label = new JLabel("ID:");
        id_label.setBounds(x_id_label, y_id_label, width_id_label, hight_id_label);
        id_label.setFont( txt_size );
        id_label.setForeground(textColor);
        frame.add(id_label);
        // Id labels
        int x_id_label_2 = x_addres_label_2;
        int y_id_label_2 = y_id_label;
        int width_id_label_2 = labels_right_width;
        int hight_id_label_2 = labels_height;
        JLabel id_label_2 = new JLabel(client.my_id);
        id_label_2.setBounds(x_id_label_2, y_id_label_2, width_id_label_2, hight_id_label_2);
        id_label_2.setFont( txt_size );
        id_label_2.setForeground(clientDataColor);
        id_label_2.setBackground(Color.GRAY);
        frame.add(id_label_2);

        // Button Add list
        int widthButtonAdd = frameWidth/12;
        int heightButtonAdd = frameHeight/20;
        int XButtonAdd = (x_addres_label*3) + (frameWidth/33);
        int YButtonAdd = y_postal_label + (frameHeight/7);
        // Button Y Space
        int button_y_space =  + heightButtonAdd + (heightButtonAdd/11);
        // Button Print
        int widthButtonPrint = frameWidth/12;
        int heightButtonPrint = heightButtonAdd;
        int XButtonPrint = (x_addres_label*3) + (frameWidth/33);
        int YButtonPrint = YButtonAdd + button_y_space;
        // Buttons size
        int widthButtonOne= widthButtonAdd;
        int heightButtonOne = heightButtonAdd;
        int XButtonOne = x_addres_label;
        int YButtonOne = YButtonAdd;
        int widthButtonTow= widthButtonOne;
        int heightButtonTow = heightButtonOne;
        int XButtonTow = x_addres_label;
        int YButtonTow = YButtonOne + button_y_space;
        int widthButtonFour = widthButtonOne;
        int heightButtonFour = heightButtonOne;
        int XButtonFour = x_addres_label;
        int YButtonFour = YButtonTow + button_y_space;
        int widthButtonThree = widthButtonOne;
        int heightButtonThree = heightButtonOne;
        int XButtonThree = x_addres_label;
        int YButtonThree = YButtonFour + button_y_space;
        int widthButtonBattaries = widthButtonOne;
        int heightButtonBattaries = heightButtonOne;
        int XButtonBattaries = x_addres_label;
        int YButtonBattaries = YButtonThree + button_y_space;
        int widthButtonDocNum = widthButtonOne;
        int heightButtonDocNum = heightButtonOne;
        int XButtonDocNum = x_addres_label;
        int YButtonDocNum = YButtonBattaries + button_y_space;

        // Scrolls Size
        int x_scroll = (frameWidth/4) + (frameWidth/17);
        int y_scroll = (frameHeight/9);// + (frameHeight/43);
        int width_scroll = (frameWidth/2) + (frameWidth/7);
        int height_scroll = frameHeight - (frameHeight/3) + (frameHeight/9);
        // Labels Size
        int width_tittle_label = frameWidth/7;
        int hight_tittle_label = frameHeight/23;
        int x_tittle_label = x_scroll + (width_scroll/2) - (width_tittle_label/2);
        int y_tittle_label = frameHeight/20;
        // Button Edit Client
        int widthButtonEdit = (frameWidth/6)+(frameWidth/110);
        int heightButtonEdit = frameHeight/20;
        int XButtonEdit = XButtonFour;
        int YButtonEdit = YButtonDocNum + (button_y_space+(button_y_space/2));

        // Button Add list
        JButton buttonAdd = new JButton("Add:");
        buttonAdd.setBounds(XButtonAdd,YButtonAdd,widthButtonAdd,heightButtonAdd);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addDataPage(client, "Add Battary", client.battaries_dates, "Battaries", "Date:", frame);
            }
        });
        frame.add(buttonAdd);

        // Tittle Category label
        JLabel tittle_label = new JLabel("Battaries:");
        tittle_label.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        tittle_label.setFont(textSize);
        tittle_label.setForeground(textColor);
        JPanel tittle_panel = new JPanel();
        tittle_panel.add(tittle_label);
        tittle_panel.setBackground(color);
        tittle_panel.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        frame.add(tittle_panel);

        // Find the biger Array Size
        int largest_size = Collections.max(  Arrays.asList( client.battaries_dates.size(),
                client.battaries_type1.size(),
                client.battaries_type2.size(),
                client.battaries_type3.size()
        ) );

        // Make the 2D array for the table
        String[] columns = { "Date:", "Type1:", "Type2:", "Type3:"};
        String[][] data = new String[largest_size][columns.length];

        // Set date in boxe's
        for (int r = 0; r < client.battaries_dates.size(); r++) {
            data[r][0] = client.battaries_dates.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.battaries_type1.size(); r++) {
            data[r][1] = client.battaries_type1.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.battaries_type2.size(); r++) {
            data[r][2] = client.battaries_type2.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.battaries_type3.size(); r++) {
            data[r][3] = client.battaries_type3.get(r).replaceAll(DataBase.save_type_of_file, "");
        }

        // Set the row name with text index if exist
        for (int i = 0; i < data.length; i++) {
            // Read Type1
            String my_path_Type1 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + data[i][0] + "-Type1";
            String file_data_Type1 = DataBase.read(my_path_Type1);
            if (!file_data_Type1.isEmpty()) {
                String lines = file_data_Type1;//.replaceAll("\n", " - ");
                data[i][1] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][1] = "null";
            }
            // Read Type2
            String my_path_Type2 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + data[i][0] + "-Type2";
            String file_data_Type2 = DataBase.read(my_path_Type2);
            if (!file_data_Type2.isEmpty()) {
                String lines = file_data_Type2;//.replaceAll("\n", " - ");
                data[i][2] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][2] = "null";
            }
            // Read Type3
            String my_path_Type3 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + data[i][0] + "-Type3";
            String file_data_Type3 = DataBase.read(my_path_Type3);
            if (!file_data_Type3.isEmpty()) {
                String lines = file_data_Type3;//.replaceAll("\n", " - ");
                data[i][3] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][3] = "null";
            }
        }

        // Set the date, Type1, Type2, Type3, list withn the order of the bigest date
        DataBase.setOrder(data);

        // Set Bataries list with data[][] order
        DataBase.setLists(data,"BattariesWin",client);

        // Get the now values of data
        final Vector<String> last_date = new Vector<String>();
        final Vector<String> last_Type1 = new Vector<String>();
        final Vector<String> last_Type2 = new Vector<String>();
        final Vector<String> last_Type3 = new Vector<String>();
        for (int r = 0; r < client.battaries_dates.size(); r++) {
            last_date.add(client.battaries_dates.get(r));
        }
        for (int r = 0; r < client.battaries_type1.size(); r++) {
            last_Type1.add(client.battaries_type1.get(r));
        }
        for (int r = 0; r < client.battaries_type2.size(); r++) {
            last_Type2.add(client.battaries_type2.get(r));
        }
        for (int r = 0; r < client.battaries_type3.size(); r++) {
            last_Type3.add(client.battaries_type3.get(r));
        }

        // JTable sels not or rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Make the JTable object
        JTable jt = new JTable(model);
        // Set columns width
        jt.getColumnModel().getColumn(0).setPreferredWidth(battaries_table_dates_column_width);
        jt.getColumnModel().getColumn(1).setPreferredWidth(battaries_table_type1_column_width);
        jt.getColumnModel().getColumn(2).setPreferredWidth(battaries_table_type2_column_width);
        jt.getColumnModel().getColumn(3).setPreferredWidth(battaries_table_type3_column_width);
        // Set columns font
        JTableHeader header = jt.getTableHeader();
        header.setFont(table_columns_font);
        // Set te value of the rows in center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Set Rows height
        for (int r = 0; r < data.length; r++) {
            jt.setRowHeight(r, table_rows_height);
        }

        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    JTable table = (JTable)evt.getSource();
                    int row = table.getSelectedRow(); // get row
                    int column = table.getSelectedColumn(); // get colum
                    String value = (String)jt.getValueAt(row, column); // get value of cell
                    String date = (String)jt.getValueAt(row, 0);

                    // Right click
                    if (SwingUtilities.isRightMouseButton(evt) == true) {
                        if ( column == 0 ) {
                            //rightClickDeleteWindow(client, client.battaries_dates, row, "Battaries", value, "-Type1", "-Type2", "-Type3", frame, row);
                            rightClickWindow(client, client.battaries_dates, row, "Battaries", value, "-Type1", "-Type2", "-Type3", null, frame, row);
                        } else {
                            if (column == 1) { // Type1
                                String path = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + client.battaries_dates.get(row) + "-Type1";
                                readDataClient(path,frame,"BattariesWin",client);
                            }
                            if (column == 2) { // Type2
                                String path = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + client.battaries_dates.get(row) + "-Type2";
                                readDataClient(path,frame,"BattariesWin",client);
                            }
                            if (column == 3) { // Type3
                                String path = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + client.battaries_dates.get(row) + "-Type3";
                                readDataClient(path,frame,"BattariesWin",client);
                            }
                        }
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    frame.dispose();
                    BattariesWin(client);
                }
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        jt.setFont(table_rows_font);
        jt.setForeground(Color.BLACK);
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.setBounds( x_scroll, y_scroll, width_scroll, height_scroll);
        tablePane.add(sp, BorderLayout.CENTER);
        frame.add(tablePane);

        // Button Print
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);
        // Base imformation for print
        String str =  "  ADDRES: " + client.street +
                "\n  LOCATION: " + client.location +
                "\n  TYPE: " + client.type +
                "\n  PHONE: " + client.phone +
                "\n  POSTAL CODE: " + client.postallCode;
        str += "\n\n BATTARIES:\n";
        // Set to str string all data
        for (int i = 0; i < client.battaries_dates.size(); i++) {
            str += client.battaries_dates.get(i)
                    + "     TYPE_1: " + client.battaries_type1.get(i)
                    + "     TYPE_2: " + client.battaries_type2.get(i)
                    + "     TYPE_3: " + client.battaries_type3.get(i)
                    + "\n";
        }

        final String str_2 = str;
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(str_2);
            }
        });
        frame.add(buttonPrint);

        // Button Change Category
        JButton buttonOne = new JButton("Characteristics:");
        buttonOne.setBounds(XButtonOne, YButtonOne, widthButtonOne,heightButtonOne);
        // Action Button
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                    int table_type1_column_width_new = column_type1.getWidth();
                    TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                    int table_type2_column_width_new = column_type2.getWidth();
                    TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                    int table_type3_column_width_new = column_typ3.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != battaries_table_dates_column_width) {
                        battaries_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_type1_column_width_new != battaries_table_type1_column_width) {
                        battaries_table_type1_column_width = table_type1_column_width_new;
                        check = true;
                    }
                    if (table_type2_column_width_new != battaries_table_type2_column_width) {
                        battaries_table_type2_column_width = table_type2_column_width_new;
                        check = true;
                    }
                    if (table_type3_column_width_new != battaries_table_type3_column_width) {
                        battaries_table_type3_column_width = table_type3_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Battaries_Table_Cels();
                    }
                    characteristicsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    characteristicsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonOne);
        // Button Change Category
        JButton buttonTow = new JButton("Maintence:");
        buttonTow.setBounds(XButtonTow, YButtonTow, widthButtonTow, heightButtonTow);
        // Action Button
        buttonTow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                    int table_type1_column_width_new = column_type1.getWidth();
                    TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                    int table_type2_column_width_new = column_type2.getWidth();
                    TableColumn column_type3 = jt.getColumnModel().getColumn(3);
                    int table_type3_column_width_new = column_type3.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != battaries_table_dates_column_width) {
                        battaries_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_type1_column_width_new != battaries_table_type1_column_width) {
                        battaries_table_type1_column_width = table_type1_column_width_new;
                        check = true;
                    }
                    if (table_type2_column_width_new != battaries_table_type2_column_width) {
                        battaries_table_type2_column_width = table_type2_column_width_new;
                        check = true;
                    }
                    if (table_type3_column_width_new != battaries_table_type3_column_width) {
                        battaries_table_type3_column_width = table_type3_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Battaries_Table_Cels();
                    }
                    maintenceWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    maintenceWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonTow);
        // Button Change Category
        JButton buttonThree = new JButton("Repears:");
        buttonThree.setBounds(XButtonThree, YButtonThree, widthButtonThree, heightButtonThree);
        // Action Button
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                    int table_type1_column_width_new = column_type1.getWidth();
                    TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                    int table_type2_column_width_new = column_type2.getWidth();
                    TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                    int table_type3_column_width_new = column_typ3.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != battaries_table_dates_column_width) {
                        battaries_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_type1_column_width_new != battaries_table_type1_column_width) {
                        battaries_table_type1_column_width = table_type1_column_width_new;
                        check = true;
                    }
                    if (table_type2_column_width_new != battaries_table_type2_column_width) {
                        battaries_table_type2_column_width = table_type2_column_width_new;
                        check = true;
                    }
                    if (table_type3_column_width_new != battaries_table_type3_column_width) {
                        battaries_table_type3_column_width = table_type3_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Battaries_Table_Cels();
                    }
                    RepairsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    RepairsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonThree);

        // Button Change Category Batteries
        JButton buttonBatteries = new JButton("Batteries:");
        buttonBatteries.setBounds(XButtonBattaries,YButtonBattaries,widthButtonBattaries,heightButtonBattaries);
        // Action Button
        buttonBatteries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                    int table_type1_column_width_new = column_type1.getWidth();
                    TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                    int table_type2_column_width_new = column_type2.getWidth();
                    TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                    int table_type3_column_width_new = column_typ3.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != battaries_table_dates_column_width) {
                        battaries_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_type1_column_width_new != battaries_table_type1_column_width) {
                        battaries_table_type1_column_width = table_type1_column_width_new;
                        check = true;
                    }
                    if (table_type2_column_width_new != battaries_table_type2_column_width) {
                        battaries_table_type2_column_width = table_type2_column_width_new;
                        check = true;
                    }
                    if (table_type3_column_width_new != battaries_table_type3_column_width) {
                        battaries_table_type3_column_width = table_type3_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Battaries_Table_Cels();
                    }
                    BattariesWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    BattariesWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonBatteries);

        // Button Change Category DocNum
        JButton buttonDocNum = new JButton("Costing:");
        buttonDocNum.setBounds(XButtonDocNum,YButtonDocNum,widthButtonDocNum,heightButtonDocNum);
        // Action Button
        buttonDocNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                    int table_type1_column_width_new = column_type1.getWidth();
                    TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                    int table_type2_column_width_new = column_type2.getWidth();
                    TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                    int table_type3_column_width_new = column_typ3.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != battaries_table_dates_column_width) {
                        battaries_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_type1_column_width_new != battaries_table_type1_column_width) {
                        battaries_table_type1_column_width = table_type1_column_width_new;
                        check = true;
                    }
                    if (table_type2_column_width_new != battaries_table_type2_column_width) {
                        battaries_table_type2_column_width = table_type2_column_width_new;
                        check = true;
                    }
                    if (table_type3_column_width_new != battaries_table_type3_column_width) {
                        battaries_table_type3_column_width = table_type3_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Battaries_Table_Cels();
                    }
                    DocNumWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DocNumWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonDocNum);

        // Button Change Category
        JButton buttonFour = new JButton("Damages:");
        buttonFour.setBounds(XButtonFour, YButtonFour, widthButtonFour, heightButtonFour);
        // Action Button
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                    int table_type1_column_width_new = column_type1.getWidth();
                    TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                    int table_type2_column_width_new = column_type2.getWidth();
                    TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                    int table_type3_column_width_new = column_typ3.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != battaries_table_dates_column_width) {
                        battaries_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_type1_column_width_new != battaries_table_type1_column_width) {
                        battaries_table_type1_column_width = table_type1_column_width_new;
                        check = true;
                    }
                    if (table_type2_column_width_new != battaries_table_type2_column_width) {
                        battaries_table_type2_column_width = table_type2_column_width_new;
                        check = true;
                    }
                    if (table_type3_column_width_new != battaries_table_type3_column_width) {
                        battaries_table_type3_column_width = table_type3_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Battaries_Table_Cels();
                    }
                    DamageWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DamageWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonFour);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                int table_type1_column_width_new = column_type1.getWidth();
                TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                int table_type2_column_width_new = column_type2.getWidth();
                TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                int table_type3_column_width_new = column_typ3.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != battaries_table_dates_column_width) {
                    battaries_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_type1_column_width_new != battaries_table_type1_column_width) {
                    battaries_table_type1_column_width = table_type1_column_width_new;
                    check = true;
                }
                if (table_type2_column_width_new != battaries_table_type2_column_width) {
                    battaries_table_type2_column_width = table_type2_column_width_new;
                    check = true;
                }
                if (table_type3_column_width_new != battaries_table_type3_column_width) {
                    battaries_table_type3_column_width = table_type3_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Battaries_Table_Cels();
                }
                frame.dispose();
                homeWindow();
            }
        });

        // Button Save
        int widthButtonSave = widthButtonPrint;
        int heightButtonSave = heightButtonPrint;
        int XButtonSave = XButtonPrint;
        int YButtonSave = YButtonPrint + button_y_space;
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(XButtonSave,YButtonSave,widthButtonSave,heightButtonSave);
        // Action Button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Save Dates Files
                int rows = jt.getRowCount();
                if (rows <= client.battaries_dates.size()) {
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String type1 = (String) jt.getValueAt(i, 1);
                        String type2 = (String) jt.getValueAt(i, 2);
                        String type3 = (String) jt.getValueAt(i, 3);
                        try {
                            client.battaries_dates.set(i, date);
                            client.battaries_type1.set(i, type1);
                            client.battaries_type2.set(i, type2);
                            client.battaries_type3.set(i, type3);
                        } catch (java.lang.ArrayIndexOutOfBoundsException ai) {

                        }
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(type1);
                        lines.add(type2);
                        lines.add(type3);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {

                            String last_path = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);

                            String last_path_Type1 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i) + "-Type1";
                            String path_Type1 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date + "-Type1";
                            DataBase.renameFile(last_path_Type1, path_Type1);

                            String last_path_Type2 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i) + "-Type2";
                            String path_Type2 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date + "-Type2";
                            DataBase.renameFile(last_path_Type1, path_Type2);

                            String last_path_Type3 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i) + "-Type3";
                            String path_Type3 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date + "-Type3";
                            DataBase.renameFile(last_path_Type3, path_Type3);

                        }
                    }
                } else {
                    client.battaries_dates.clear();
                    client.battaries_type1.clear();
                    client.battaries_type2.clear();
                    client.battaries_type3.clear();
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String type1 = (String) jt.getValueAt(i, 1);
                        String type2 = (String) jt.getValueAt(i, 2);
                        String type3 = (String) jt.getValueAt(i, 3);
                        client.battaries_dates.add(date);
                        client.battaries_type1.add(type1);
                        client.battaries_type2.add(type2);
                        client.battaries_type3.add(type3);
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(type1);
                        lines.add(type2);
                        lines.add(type3);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_Type1 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i) + "-Type1";
                            String path_Type1 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date + "-Type1";
                            DataBase.renameFile(last_path_Type1, path_Type1);
                            String last_path_Type2 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i) + "-Type2";
                            String path_Type2 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date + "-Type2";
                            DataBase.renameFile(last_path_Type1, path_Type2);
                            String last_path_Type3 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + last_date.get(i) + "-Type3";
                            String path_Type3 = DataBase.folderData + client.my_id + File.separator + "Battaries" + File.separator + date + "-Type3";
                            DataBase.renameFile(last_path_Type3, path_Type3);
                        }
                    }
                }

                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_type1 = jt.getColumnModel().getColumn(1);
                int table_type1_column_width_new = column_type1.getWidth();
                TableColumn column_type2 = jt.getColumnModel().getColumn(2);
                int table_type2_column_width_new = column_type2.getWidth();
                TableColumn column_typ3 = jt.getColumnModel().getColumn(3);
                int table_type3_column_width_new = column_typ3.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != battaries_table_dates_column_width) {
                    battaries_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_type1_column_width_new != battaries_table_type1_column_width) {
                    battaries_table_type1_column_width = table_type1_column_width_new;
                    check = true;
                }
                if (table_type2_column_width_new != battaries_table_type2_column_width) {
                    battaries_table_type2_column_width = table_type2_column_width_new;
                    check = true;
                }
                if (table_type3_column_width_new != battaries_table_type3_column_width) {
                    battaries_table_type3_column_width = table_type3_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Battaries_Table_Cels();
                }
                frame.dispose();
                BattariesWin(client);
            }
        });
        frame.add(buttonSave);

        // Button Edit Client
        JButton buttonED = new JButton("Edit Client:");
        buttonED.setBounds(XButtonEdit,YButtonEdit,widthButtonEdit,heightButtonEdit);
        // Action Button
        buttonED.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editClient(client);
                frame.dispose();
            }
        });
        frame.add(buttonED);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Window Damage
    public void DamageWin(Client client) {
    
        JFrame frame = new JFrame("Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        //frame.setResizable(false);
        frame.getContentPane().setBackground(color);
        
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels Size
        int labels_left_width = frameWidth/13;
        int labels_right_width = frameWidth/4;
        int labels_height = frameHeight/37;

        // Labes text Size
        Font txt_size = imformations_font;

        // Addres labels
        int x_addres_label = frameWidth/33;
        int y_addres_label = frameHeight/7;
        int width_addres_label = labels_left_width;
        int hight_addres_label = labels_height;
        JLabel addres_label = new JLabel("Addres&Num:");
        addres_label.setBounds(x_addres_label, y_addres_label, width_addres_label, hight_addres_label);
        addres_label.setFont( txt_size );
        addres_label.setForeground(textColor);
        frame.add(addres_label);
        // Addres labels
        int x_addres_label_2 = x_addres_label + width_addres_label - (width_addres_label/7);
        int y_addres_label_2 = y_addres_label;
        int width_addres_label_2 = labels_right_width;
        int hight_addres_label_2 = labels_height;
        JLabel addres_label_2 = new JLabel(client.street);
        addres_label_2.setBounds(x_addres_label_2, y_addres_label_2, width_addres_label_2, hight_addres_label_2);
        addres_label_2.setFont( txt_size );
        addres_label_2.setForeground(clientDataColor);
        frame.add(addres_label_2);

        // Location labels
        int x_location_label = x_addres_label;
        int y_location_label = y_addres_label + (labels_height*2);
        int width_location_label = labels_left_width;
        int hight_location_label = labels_height;
        JLabel location_label = new JLabel("Location:");
        location_label.setBounds(x_location_label, y_location_label, width_location_label, hight_location_label);
        location_label.setFont( txt_size );
        location_label.setForeground(textColor);         
        frame.add(location_label);
        // Location labels
        int x_location_label_2 = x_addres_label_2;
        int y_location_label_2 = y_location_label;
        int width_location_label_2 = labels_right_width;
        int hight_location_label_2 = labels_height;
        JLabel location_label_2 = new JLabel(client.location);
        location_label_2.setBounds(x_location_label_2, y_location_label_2, width_location_label_2, hight_location_label_2);
        location_label_2.setFont( txt_size );
        location_label_2.setForeground(clientDataColor);
        frame.add(location_label_2);

        // Type labels
        int x_type_label = x_location_label;
        int y_type_label = y_location_label + (labels_height*2);
        int width_type_label = labels_left_width;
        int hight_type_label = labels_height;
        JLabel type_label = new JLabel("Type:");
        type_label.setBounds(x_type_label, y_type_label, width_type_label, hight_type_label);
        type_label.setFont( txt_size );
        type_label.setForeground(textColor);         
        frame.add(type_label);
        // Type labels
        int x_type_label_2 = x_addres_label_2;
        int y_type_label_2 = y_type_label;
        int width_type_label_2 = labels_right_width;
        int hight_type_label_2 = labels_height;
        JLabel type_label_2 = new JLabel(client.type);
        type_label_2.setBounds(x_type_label_2, y_type_label_2, width_type_label_2, hight_type_label_2);
        type_label_2.setFont( txt_size );
        type_label_2.setForeground(clientDataColor);    
        frame.add(type_label_2);

        // Phone labels
        int x_phone_label = x_type_label;
        int y_phone_label = y_type_label + (labels_height*2);
        int width_phone_label = labels_left_width;
        int hight_phone_label = labels_height;
        JLabel phone_label = new JLabel("Phone:");
        phone_label.setBounds(x_phone_label, y_phone_label, width_phone_label, hight_phone_label);
        phone_label.setFont( txt_size );
        phone_label.setForeground(textColor);              
        frame.add(phone_label);
        // Phone labels
        int x_phone_label_2 = x_addres_label_2;
        int y_phone_label_2 = y_phone_label;
        int width_phone_label_2 = labels_right_width;
        int hight_phone_label_2 = labels_height;
        JLabel phone_label_2 = new JLabel(client.phone);
        phone_label_2.setBounds(x_phone_label_2, y_phone_label_2, width_phone_label_2, hight_phone_label_2);
        phone_label_2.setFont( txt_size );
        phone_label_2.setForeground(clientDataColor);   
        frame.add(phone_label_2);

        // Postal Code labels
        int x_postal_label = x_phone_label;
        int y_postal_label = y_phone_label + (labels_height*2);
        int width_postal_label = labels_left_width;
        int hight_postal_label = labels_height;
        JLabel postal_label = new JLabel("P.Code:");
        postal_label.setBounds(x_postal_label, y_postal_label, width_postal_label, hight_postal_label);
        postal_label.setFont( txt_size );
        postal_label.setForeground(textColor);            
        frame.add(postal_label);
        // Postal Code labels
        int x_postal_label_2 = x_addres_label_2;
        int y_postal_label_2 = y_postal_label;
        int width_postal_label_2 = labels_right_width;
        int hight_postal_label_2 = labels_height;
        JLabel postal_label_2 = new JLabel(client.postallCode);
        postal_label_2.setBounds(x_postal_label_2, y_postal_label_2, width_postal_label_2, hight_postal_label_2);
        postal_label_2.setFont( txt_size );
        postal_label_2.setForeground(clientDataColor); 
        postal_label_2.setBackground(Color.GRAY);
        frame.add(postal_label_2);

        // Id labels
        int x_id_label = x_phone_label;
        int y_id_label = y_postal_label + (labels_height*2);
        int width_id_label = labels_left_width;
        int hight_id_label = labels_height;
        JLabel id_label = new JLabel("ID:");
        id_label.setBounds(x_id_label, y_id_label, width_id_label, hight_id_label);
        id_label.setFont( txt_size );
        id_label.setForeground(textColor);
        frame.add(id_label);
        // Id labels
        int x_id_label_2 = x_addres_label_2;
        int y_id_label_2 = y_id_label;
        int width_id_label_2 = labels_right_width;
        int hight_id_label_2 = labels_height;
        JLabel id_label_2 = new JLabel(client.my_id);
        id_label_2.setBounds(x_id_label_2, y_id_label_2, width_id_label_2, hight_id_label_2);
        id_label_2.setFont( txt_size );
        id_label_2.setForeground(clientDataColor);
        id_label_2.setBackground(Color.GRAY);
        frame.add(id_label_2);

        // Button Add list
        int widthButtonAdd = frameWidth/12;
        int heightButtonAdd = frameHeight/20;
        int XButtonAdd = (x_addres_label*3) + (frameWidth/33);
        int YButtonAdd = y_postal_label + (frameHeight/7);
        // Button Y Space
        int button_y_space =  + heightButtonAdd + (heightButtonAdd/11);
        // Button Print
        int widthButtonPrint = frameWidth/12;
        int heightButtonPrint = heightButtonAdd;
        int XButtonPrint = (x_addres_label*3) + (frameWidth/33);
        int YButtonPrint = YButtonAdd + button_y_space;
        // Buttons size
        int widthButtonOne= widthButtonAdd;
        int heightButtonOne = heightButtonAdd;
        int XButtonOne = x_addres_label;
        int YButtonOne = YButtonAdd;
        int widthButtonTow= widthButtonOne;
        int heightButtonTow = heightButtonOne;
        int XButtonTow = x_addres_label;
        int YButtonTow = YButtonOne + button_y_space;
        int widthButtonFour = widthButtonOne;
        int heightButtonFour = heightButtonOne;
        int XButtonFour = x_addres_label;
        int YButtonFour = YButtonTow + button_y_space;
        int widthButtonThree = widthButtonOne;
        int heightButtonThree = heightButtonOne;
        int XButtonThree = x_addres_label;
        int YButtonThree = YButtonFour + button_y_space;
        int widthButtonBattaries = widthButtonOne;
        int heightButtonBattaries = heightButtonOne;
        int XButtonBattaries = x_addres_label;
        int YButtonBattaries = YButtonThree + button_y_space;
        int widthButtonDocNum = widthButtonOne;
        int heightButtonDocNum = heightButtonOne;
        int XButtonDocNum = x_addres_label;
        int YButtonDocNum = YButtonBattaries + button_y_space;

        // Scrolls Size
        int x_scroll = (frameWidth/4) + (frameWidth/17);
        int y_scroll = (frameHeight/9);// + (frameHeight/43);
        int width_scroll = (frameWidth/2) + (frameWidth/7);
        int height_scroll = frameHeight - (frameHeight/3) + (frameHeight/9);
        // Labels Size
        int width_tittle_label = frameWidth/7;
        int hight_tittle_label = frameHeight/23;
        int x_tittle_label = x_scroll + (width_scroll/2) - (width_tittle_label/2);
        int y_tittle_label = frameHeight/20;
        // Button Edit Client
        int widthButtonEdit = (frameWidth/6)+(frameWidth/110);
        int heightButtonEdit = frameHeight/20;
        int XButtonEdit = XButtonFour;
        int YButtonEdit = YButtonDocNum + (button_y_space+(button_y_space/2));

        // Button Add list
        JButton buttonAdd = new JButton("Add:");
        buttonAdd.setBounds(XButtonAdd,YButtonAdd,widthButtonAdd,heightButtonAdd);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addDataPage(client, "Add Damage", client.damage_dates, "Damage", "Date:", frame);
            }
        });
        frame.add(buttonAdd);

        // Tittle Category label
        JLabel tittle_label = new JLabel("Damages:");
        tittle_label.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        tittle_label.setFont(textSize);
        tittle_label.setForeground(textColor);
        JPanel tittle_panel = new JPanel();
        tittle_panel.add(tittle_label);
        tittle_panel.setBackground(color);
        tittle_panel.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);            
        frame.add(tittle_panel); 

        // Find the biger Array Size
        int largest_size = Collections.max(  Arrays.asList( client.damage_dates.size(),
        client.damage_worker.size(), 
        client.damage_damage_type.size(), 
        client.damage_fix_type.size()
        ) );
        
        // Make the 2D array for the table
        String[] columns = { "Date:", "Worker:", "Damage Type:", "Fix Type:"};    
        String[][] data = new String[largest_size][columns.length];

        // Set date in boxe's
        for (int r = 0; r < client.damage_dates.size(); r++) {
            data[r][0] = client.damage_dates.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.damage_worker.size(); r++) {
            data[r][1] = client.damage_worker.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.damage_damage_type.size(); r++) {
            data[r][2] = client.damage_damage_type.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.damage_fix_type.size(); r++) {
            data[r][3] = client.damage_fix_type.get(r).replaceAll(DataBase.save_type_of_file, "");
        }

        // Set the row name with text index if exist 
        for (int i = 0; i < data.length; i++) {
            // Read Worker
            String my_path_Worker = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + data[i][0] + "-Worker";
            String file_data_Worker = DataBase.read(my_path_Worker);
            if (!file_data_Worker.isEmpty()) {
                String lines = file_data_Worker;//.replaceAll("\n", " - ");
                data[i][1] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][1] = "null";
            }
            // Read DamegeType
            String my_path_DamageType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + data[i][0] + "-DamageType";
            String file_data_DamageType = DataBase.read(my_path_DamageType);
            if (!file_data_DamageType.isEmpty()) {
                String lines = file_data_DamageType;//.replaceAll("\n", " - ");
                data[i][2] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][2] = "null";
            }
            // Read FixType
            String my_path_FixTpye = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + data[i][0] + "-FixType";
            String file_data_FixTpye = DataBase.read(my_path_FixTpye);
            if (!file_data_FixTpye.isEmpty()) {
                String lines = file_data_FixTpye;//.replaceAll("\n", " - ");
                data[i][3] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][3] = "null";
            }
        }

        // Set the date, Worker, DamageType, FixTypwe, list withn the order of the bigest date
        DataBase.setOrder(data);

        // Set Bataries list with data[][] order
        DataBase.setLists(data,"DamageWin",client);

        // Get the now values of data
        final Vector<String> last_date = new Vector<String>();
        final Vector<String> last_Worker = new Vector<String>();
        final Vector<String> last_DamageType = new Vector<String>();
        final Vector<String> last_FixType = new Vector<String>();
        for (int r = 0; r < client.damage_dates.size(); r++) {
            last_date.add(client.damage_dates.get(r));
        }
        for (int r = 0; r < client.damage_worker.size(); r++) {
            last_Worker.add(client.damage_worker.get(r));
        }
        for (int r = 0; r < client.damage_damage_type.size(); r++) {
            last_DamageType.add(client.damage_damage_type.get(r));
        }
        for (int r = 0; r < client.damage_fix_type.size(); r++) {
            last_FixType.add(client.damage_fix_type.get(r));
        }

        // JTable sels not or rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Make the JTable object
        JTable jt = new JTable(model);
        // Set columns width
        jt.getColumnModel().getColumn(0).setPreferredWidth(damages_table_dates_column_width);
        jt.getColumnModel().getColumn(1).setPreferredWidth(damages_table_workers_column_width);
        jt.getColumnModel().getColumn(2).setPreferredWidth(damages_table_DamageTypes_column_width);
        jt.getColumnModel().getColumn(3).setPreferredWidth(damages_table_FixTypes_column_width);
        // Set columns font
        JTableHeader header = jt.getTableHeader();
        header.setFont(table_columns_font);
        // Set te value of the rows in center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Set Rows height
        for (int r = 0; r < data.length; r++) {
            jt.setRowHeight(r, table_rows_height);
        }

        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    JTable table = (JTable)evt.getSource();
                    int row = table.getSelectedRow(); // get row
                    int column = table.getSelectedColumn(); // get colum
                    String value = (String)jt.getValueAt(row, column); // get value of cell
                    String date = (String)jt.getValueAt(row, 0);

                    // Right click
                    if (SwingUtilities.isRightMouseButton(evt) == true) {
                        if (column == 0) {
                            //rightClickDeleteWindow(client, client.damage_dates, row, "Damage", value, "-DamageType", "-FixType", null,frame, row);
                            rightClickWindow(client, client.damage_dates, row, "Damage", value, "-Worker", "-DamageType", "-FixType", null, frame, row);
                        } else {
                            if (column == 1) { // Worker
                                String path = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + client.damage_dates.get(row) + "-Worker";
                                readDataClient(path,frame,"DamageWin",client);
                            }
                            if (column == 2) { // Damage Type
                                String path = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + client.damage_dates.get(row) + "-DamageType";
                                readDataClient(path,frame,"DamageWin",client);
                            }
                            if (column == 3) { // Fix Type
                                String path = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + client.damage_dates.get(row) + "-FixType";
                                readDataClient(path,frame,"DamageWin",client);
                            }
                        }
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    frame.dispose();
                    DamageWin(client);
                }
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        jt.setFont(table_rows_font);
        jt.setForeground(Color.BLACK);
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.setBounds( x_scroll, y_scroll, width_scroll, height_scroll);
        tablePane.add(sp, BorderLayout.CENTER);
        frame.add(tablePane);

        // Button Print
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);
        // Base imformation for print
        String str =  "  ADDRES: " + client.street + 
                      "\n  LOCATION: " + client.location +
                      "\n  TYPE: " + client.type +
                      "\n  PHONE: " + client.phone +
                      "\n  POSTAL CODE: " + client.postallCode; 
        str += "\n\n DAMAGES:\n";
        // Set to str string all data
        for (int i = 0; i < client.damage_dates.size(); i++) {
            str += client.damage_dates.get(i) 
                    + "     WORKER: " + client.damage_worker.get(i) 
                    + "     DAMAGE TYPE: " + client.damage_damage_type.get(i) 
                    + "     FIX TYPE: " + client.damage_fix_type.get(i)
                    + "\n";
        }

        final String str_2 = str;
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(str_2);
            }
        });
        frame.add(buttonPrint);

        // Button Change Category
        JButton buttonOne = new JButton("Characteristics:");
        buttonOne.setBounds(XButtonOne, YButtonOne, widthButtonOne,heightButtonOne);
        // Action Button
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    characteristicsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    characteristicsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonOne);
        // Button Change Category
        JButton buttonTow = new JButton("Maintence:");
        buttonTow.setBounds(XButtonTow, YButtonTow, widthButtonTow, heightButtonTow);
        // Action Button
        buttonTow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    maintenceWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    maintenceWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonTow);
        // Button Change Category
        JButton buttonThree = new JButton("Repears:");
        buttonThree.setBounds(XButtonThree, YButtonThree, widthButtonThree, heightButtonThree);
        // Action Button
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    RepairsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    RepairsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonThree);

        // Button Change Category Batteries
        JButton buttonBatteries = new JButton("Batteries:");
        buttonBatteries.setBounds(XButtonBattaries,YButtonBattaries,widthButtonBattaries,heightButtonBattaries);
        // Action Button
        buttonBatteries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    BattariesWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    BattariesWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonBatteries);

        // Button Change Category DocNum
        JButton buttonDocNum = new JButton("Costing:");
        buttonDocNum.setBounds(XButtonDocNum,YButtonDocNum,widthButtonDocNum,heightButtonDocNum);
        // Action Button
        buttonDocNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    DocNumWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DocNumWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonDocNum);

        // Button Change Category
        JButton buttonFour = new JButton("Damages:");
        buttonFour.setBounds(XButtonFour, YButtonFour, widthButtonFour, heightButtonFour);
        // Action Button
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    DamageWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DamageWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonFour);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_workers = jt.getColumnModel().getColumn(1);
                int table_workers_column_width_new = column_workers.getWidth();
                TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != damages_table_dates_column_width) {
                    damages_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_workers_column_width_new != damages_table_workers_column_width) {
                    damages_table_workers_column_width = table_workers_column_width_new;
                    check = true;
                }
                if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                    damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                    check = true;
                }
                if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                    damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Damages_Table_Cels();
                }
                frame.dispose();
                homeWindow();
            }
        });

        // Button Save
        int widthButtonSave = widthButtonPrint;
        int heightButtonSave = heightButtonPrint;
        int XButtonSave = XButtonPrint;
        int YButtonSave = YButtonPrint + button_y_space;
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(XButtonSave,YButtonSave,widthButtonSave,heightButtonSave);
        // Action Button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // Save Dates Files
                int rows = jt.getRowCount();
                if (rows <= client.damage_dates.size()) {
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String Worker = (String) jt.getValueAt(i, 1);
                        String DamageType = (String) jt.getValueAt(i, 2);
                        String FixType = (String) jt.getValueAt(i, 3);
                        try {
                            client.damage_dates.set(i, date);
                            client.damage_worker.set(i, Worker);
                            client.damage_damage_type.set(i, DamageType);
                            client.damage_fix_type.set(i, FixType);
                        } catch (java.lang.ArrayIndexOutOfBoundsException ai) {

                        }
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(Worker);
                        lines.add(DamageType);
                        lines.add(FixType);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_DamageType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + last_date.get(i) + "-DamageType";
                            String path_DamageType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + date + "-DamageType";
                            DataBase.renameFile(last_path_DamageType, path_DamageType);
                            String last_path_FixType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + last_date.get(i) + "-FixType";
                            String path_FixType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + date + "-FixType";
                            DataBase.renameFile(last_path_FixType, path_FixType);
                        }
                    }
                } else {
                    client.damage_dates.clear();
                    client.damage_worker.clear();
                    client.damage_damage_type.clear();
                    client.damage_fix_type.clear();
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String Worker = (String) jt.getValueAt(i, 1);
                        String DamageType = (String) jt.getValueAt(i, 2);
                        String FixType = (String) jt.getValueAt(i, 3);
                        client.damage_dates.add(date);
                        client.damage_worker.add(Worker);
                        client.damage_damage_type.add(DamageType);
                        client.damage_fix_type.add(FixType);
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(Worker);
                        lines.add(DamageType);
                        lines.add(FixType);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_DamageType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + last_date.get(i) + "-DamageType";
                            String path_DamageType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + date + "-DamageType";
                            DataBase.renameFile(last_path_DamageType, path_DamageType);
                            String last_path_FixType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + last_date.get(i) + "-FixType";
                            String path_FixType = DataBase.folderData + client.my_id + File.separator + "Damage" + File.separator + date + "-FixType";
                            DataBase.renameFile(last_path_FixType, path_FixType);
                        }
                    }
                }

                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_workers = jt.getColumnModel().getColumn(1);
                int table_workers_column_width_new = column_workers.getWidth();
                TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != damages_table_dates_column_width) {
                    damages_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_workers_column_width_new != damages_table_workers_column_width) {
                    damages_table_workers_column_width = table_workers_column_width_new;
                    check = true;
                }
                if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                    damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                    check = true;
                }
                if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                    damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Damages_Table_Cels();
                }
                frame.dispose();
                DamageWin(client);             
            }
        });
        frame.add(buttonSave);

        // Button Edit Client
        JButton buttonED = new JButton("Edit Client:");
        buttonED.setBounds(XButtonEdit,YButtonEdit,widthButtonEdit,heightButtonEdit);
        // Action Button
        buttonED.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editClient(client);
                frame.dispose();
            }
        });
        frame.add(buttonED);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Window Repairs
    public void RepairsWin(Client client) {

        JFrame frame = new JFrame("Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        //frame.setResizable(false);
        frame.getContentPane().setBackground(color);
        
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels Size
        int labels_left_width = frameWidth/13;
        int labels_right_width = frameWidth/4;
        int labels_height = frameHeight/37;

        // Labes text Size
        Font txt_size = imformations_font;

        // Addres labels
        int x_addres_label = frameWidth/33;
        int y_addres_label = frameHeight/7;
        int width_addres_label = labels_left_width;
        int hight_addres_label = labels_height;
        JLabel addres_label = new JLabel("Addres&Num:");
        addres_label.setBounds(x_addres_label, y_addres_label, width_addres_label, hight_addres_label);
        addres_label.setFont( txt_size );
        addres_label.setForeground(textColor);
        frame.add(addres_label);
        // Addres labels
        int x_addres_label_2 = x_addres_label + width_addres_label - (width_addres_label/7);
        int y_addres_label_2 = y_addres_label;
        int width_addres_label_2 = labels_right_width;
        int hight_addres_label_2 = labels_height;
        JLabel addres_label_2 = new JLabel(client.street);
        addres_label_2.setBounds(x_addres_label_2, y_addres_label_2, width_addres_label_2, hight_addres_label_2);
        addres_label_2.setFont( txt_size );
        addres_label_2.setForeground(clientDataColor);
        frame.add(addres_label_2);

        // Location labels
        int x_location_label = x_addres_label;
        int y_location_label = y_addres_label + (labels_height*2);
        int width_location_label = labels_left_width;
        int hight_location_label = labels_height;
        JLabel location_label = new JLabel("Location:");
        location_label.setBounds(x_location_label, y_location_label, width_location_label, hight_location_label);
        location_label.setFont( txt_size );
        location_label.setForeground(textColor);
        frame.add(location_label);
        // Location labels
        int x_location_label_2 = x_addres_label_2;
        int y_location_label_2 = y_location_label;
        int width_location_label_2 = labels_right_width;
        int hight_location_label_2 = labels_height;
        JLabel location_label_2 = new JLabel(client.location);
        location_label_2.setBounds(x_location_label_2, y_location_label_2, width_location_label_2, hight_location_label_2);
        location_label_2.setFont( txt_size );
        location_label_2.setForeground(clientDataColor);
        frame.add(location_label_2);

        // Type labels
        int x_type_label = x_location_label;
        int y_type_label = y_location_label + (labels_height*2);
        int width_type_label = labels_left_width;
        int hight_type_label = labels_height;
        JLabel type_label = new JLabel("Type:");
        type_label.setBounds(x_type_label, y_type_label, width_type_label, hight_type_label);
        type_label.setFont( txt_size );
        type_label.setForeground(textColor);
        frame.add(type_label);
        // Type labels
        int x_type_label_2 = x_addres_label_2;
        int y_type_label_2 = y_type_label;
        int width_type_label_2 = labels_right_width;
        int hight_type_label_2 = labels_height;
        JLabel type_label_2 = new JLabel(client.type);
        type_label_2.setBounds(x_type_label_2, y_type_label_2, width_type_label_2, hight_type_label_2);
        type_label_2.setFont( txt_size );
        type_label_2.setForeground(clientDataColor);
        frame.add(type_label_2);

        // Phone labels
        int x_phone_label = x_type_label;
        int y_phone_label = y_type_label + (labels_height*2);
        int width_phone_label = labels_left_width;
        int hight_phone_label = labels_height;
        JLabel phone_label = new JLabel("Phone:");
        phone_label.setBounds(x_phone_label, y_phone_label, width_phone_label, hight_phone_label);
        phone_label.setFont( txt_size );
        phone_label.setForeground(textColor);
        frame.add(phone_label);
        // Phone labels
        int x_phone_label_2 = x_addres_label_2;
        int y_phone_label_2 = y_phone_label;
        int width_phone_label_2 = labels_right_width;
        int hight_phone_label_2 = labels_height;
        JLabel phone_label_2 = new JLabel(client.phone);
        phone_label_2.setBounds(x_phone_label_2, y_phone_label_2, width_phone_label_2, hight_phone_label_2);
        phone_label_2.setFont( txt_size );
        phone_label_2.setForeground(clientDataColor);
        frame.add(phone_label_2);

        // Postal Code labels
        int x_postal_label = x_phone_label;
        int y_postal_label = y_phone_label + (labels_height*2);
        int width_postal_label = labels_left_width;
        int hight_postal_label = labels_height;
        JLabel postal_label = new JLabel("P.Code:");
        postal_label.setBounds(x_postal_label, y_postal_label, width_postal_label, hight_postal_label);
        postal_label.setFont( txt_size );
        postal_label.setForeground(textColor);
        frame.add(postal_label);
        // Postal Code labels
        int x_postal_label_2 = x_addres_label_2;
        int y_postal_label_2 = y_postal_label;
        int width_postal_label_2 = labels_right_width;
        int hight_postal_label_2 = labels_height;
        JLabel postal_label_2 = new JLabel(client.postallCode);
        postal_label_2.setBounds(x_postal_label_2, y_postal_label_2, width_postal_label_2, hight_postal_label_2);
        postal_label_2.setFont( txt_size );
        postal_label_2.setForeground(clientDataColor);
        postal_label_2.setBackground(Color.GRAY);
        frame.add(postal_label_2);

        // Id labels
        int x_id_label = x_phone_label;
        int y_id_label = y_postal_label + (labels_height*2);
        int width_id_label = labels_left_width;
        int hight_id_label = labels_height;
        JLabel id_label = new JLabel("ID:");
        id_label.setBounds(x_id_label, y_id_label, width_id_label, hight_id_label);
        id_label.setFont( txt_size );
        id_label.setForeground(textColor);
        frame.add(id_label);
        // Id labels
        int x_id_label_2 = x_addres_label_2;
        int y_id_label_2 = y_id_label;
        int width_id_label_2 = labels_right_width;
        int hight_id_label_2 = labels_height;
        JLabel id_label_2 = new JLabel(client.my_id);
        id_label_2.setBounds(x_id_label_2, y_id_label_2, width_id_label_2, hight_id_label_2);
        id_label_2.setFont( txt_size );
        id_label_2.setForeground(clientDataColor);
        id_label_2.setBackground(Color.GRAY);
        frame.add(id_label_2);

        // Button Add list
        int widthButtonAdd = frameWidth/12;
        int heightButtonAdd = frameHeight/20;
        int XButtonAdd = (x_addres_label*3) + (frameWidth/33);
        int YButtonAdd = y_postal_label + (frameHeight/7);
        // Button Y Space
        int button_y_space =  + heightButtonAdd + (heightButtonAdd/11);
        // Button Print
        int widthButtonPrint = frameWidth/12;
        int heightButtonPrint = heightButtonAdd;
        int XButtonPrint = (x_addres_label*3) + (frameWidth/33);
        int YButtonPrint = YButtonAdd + button_y_space;
        // Buttons size
        int widthButtonOne= widthButtonAdd;
        int heightButtonOne = heightButtonAdd;
        int XButtonOne = x_addres_label;
        int YButtonOne = YButtonAdd;
        int widthButtonTow= widthButtonOne;
        int heightButtonTow = heightButtonOne;
        int XButtonTow = x_addres_label;
        int YButtonTow = YButtonOne + button_y_space;
        int widthButtonFour = widthButtonOne;
        int heightButtonFour = heightButtonOne;
        int XButtonFour = x_addres_label;
        int YButtonFour = YButtonTow + button_y_space;
        int widthButtonThree = widthButtonOne;
        int heightButtonThree = heightButtonOne;
        int XButtonThree = x_addres_label;
        int YButtonThree = YButtonFour + button_y_space;
        int widthButtonBattaries = widthButtonOne;
        int heightButtonBattaries = heightButtonOne;
        int XButtonBattaries = x_addres_label;
        int YButtonBattaries = YButtonThree + button_y_space;
        int widthButtonDocNum = widthButtonOne;
        int heightButtonDocNum = heightButtonOne;
        int XButtonDocNum = x_addres_label;
        int YButtonDocNum = YButtonBattaries + button_y_space;

        // Scrolls Size
        int x_scroll = (frameWidth/4) + (frameWidth/17);
        int y_scroll = (frameHeight/9);// + (frameHeight/43);
        int width_scroll = (frameWidth/2) + (frameWidth/7);
        int height_scroll = frameHeight - (frameHeight/3) + (frameHeight/9);
        // Labels Size
        int width_tittle_label = frameWidth/7;
        int hight_tittle_label = frameHeight/23; 
        int x_tittle_label = x_scroll + (width_scroll/2) - (width_tittle_label/2);
        int y_tittle_label = frameHeight/20;
        // Button Edit Client
        int widthButtonEdit = (frameWidth/6)+(frameWidth/110);
        int heightButtonEdit = frameHeight/20;
        int XButtonEdit = XButtonFour;
        int YButtonEdit = YButtonDocNum + (button_y_space+(button_y_space/2));

        // Button Add list
        JButton buttonAdd = new JButton("Add:");
        buttonAdd.setBounds(XButtonAdd,YButtonAdd,widthButtonAdd,heightButtonAdd);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addDataPage(client, "Add Repairs", client.repairs_dates, "Repairs", "Date:", frame);
            }
        });
        frame.add(buttonAdd);

        // Button Print
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);
        // Find the biger Array Size
        int largest_size = Collections.max(  Arrays.asList( client.repairs_dates.size(),
                                                            client.repairs_worker.size(), 
                                                            client.repairs_repair_type.size()
                                                            ) );
        String str =  "\n    ADDRES: " + client.street
                      + "\n    LOCATION: " + client.location
                      + "\n    TYPE: " + client.type
                      + "\n    PHONE: " + client.phone
                      + "\n    POSTAL CODE: " + client.postallCode;

        str += "\n\n REPAIRS:\n";
        // Set to str string all data
        for (int i = 0; i < client.repairs_dates.size(); i++) {
            str += client.repairs_dates.get(i) 
            + "     WORKER: " + client.repairs_worker.get(i) 
            + "     REPAIR TYPE: " + client.repairs_repair_type.get(i)
            + "\n";
        }

        final String str_2 = str;
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(str_2);
            }
        });
        frame.add(buttonPrint);


        // Tittle Category label
        JLabel tittle_label = new JLabel("Repairs:");
        tittle_label.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        tittle_label.setFont(textSize);
        tittle_label.setForeground(textColor);
        JPanel tittle_panel = new JPanel(); 
        tittle_panel.add(tittle_label);
        tittle_panel.setBackground(color);
        tittle_panel.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);               
        frame.add(tittle_panel);                
        
        // Make the 2D array for the table
        String[] columns = { "Date:", "Worker:", "Repair Type:"};
        String[][] data = new String[largest_size][columns.length];

        // Set date in boxe's
        for (int r = 0; r < client.repairs_dates.size(); r++) {
            data[r][0] = client.repairs_dates.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.repairs_worker.size(); r++) {
            data[r][1] = client.repairs_worker.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.repairs_repair_type.size(); r++) {
            data[r][2] = client.repairs_repair_type.get(r).replaceAll(DataBase.save_type_of_file, "");
        }

        // Set the row name with text index if exist
        for (int i = 0; i < data.length; i++) {
            // Read Worker
            String my_path_Worker = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + data[i][0] + "-Worker";
            String file_data_Worker = DataBase.read(my_path_Worker);
            if (!file_data_Worker.isEmpty()) {
                String lines = file_data_Worker;//.replaceAll("\n", " - ");
                data[i][1] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][1] = "null";
            }
            // Read RepearType
            String my_path_RepairType = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + data[i][0] + "-RepairsType";
            String file_data_RepairType = DataBase.read(my_path_RepairType);
            if (!file_data_RepairType.isEmpty()) {
                String lines = file_data_RepairType;//.replaceAll("\n", " - ");
                data[i][2] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][2] = "null";
            }
        }

        // Set the date, "Worker, RepairType, list withn the order of the bigest date
        DataBase.setOrder(data);

        // Set Bataries list with data[][] order
        DataBase.setLists(data,"RepairsWin",client);

        // Get the values of date now
        final Vector<String> last_date = new Vector<String>();
        final Vector<String> last_Worker = new Vector<String>();
        final Vector<String> last_RepairType = new Vector<String>();
        for (int r = 0; r < client.repairs_dates.size(); r++) {
            last_date.add(client.repairs_dates.get(r));
        }
        for (int r = 0; r < client.repairs_worker.size(); r++) {
            last_Worker.add(client.repairs_worker.get(r));
        }
        for (int r = 0; r < client.repairs_repair_type.size(); r++) {
            last_RepairType.add(client.repairs_repair_type.get(r));
        }

        // JTable sels not or rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable jt = new JTable(model);
        // Set columns width
        jt.getColumnModel().getColumn(0).setPreferredWidth(repairs_table_dates_column_width);
        jt.getColumnModel().getColumn(1).setPreferredWidth(repairs_table_workers_column_width);
        jt.getColumnModel().getColumn(2).setPreferredWidth(repairs_table_RepairsTypes_column_width);
        // Set columns font
        JTableHeader header = jt.getTableHeader();
        header.setFont(table_columns_font);
        // Set te value of the rows in center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Set Rows height
        for (int r = 0; r < data.length; r++) {
            jt.setRowHeight(r, table_rows_height);
        }

        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    JTable table = (JTable)evt.getSource();
                    int row = table.getSelectedRow(); // get row
                    int column = table.getSelectedColumn(); // get colum
                    String value = (String)jt.getValueAt(row, column); // get value of cell

                    if (SwingUtilities.isRightMouseButton(evt) == true) { // Right click
                        if (column == 0) {
                            //rightClickDeleteWindow(client, client.repairs_dates, row, "Repairs", value, "-RepairsType", null, null, frame, row);
                            rightClickWindow(client, client.repairs_dates, row, "Repairs", value, "-Worker", "-RepairsType", null, null, frame, row);
                        } else {
                            if (column == 1) { // Worker
                                String path = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + client.repairs_dates.get(row) + "-Worker";
                                readDataClient(path,frame,"RepairsWin",client);
                            }
                            if (column == 2) { // Damage Type
                                String path = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + client.repairs_dates.get(row) + "-RepairsType";
                                readDataClient(path,frame,"RepairsWin",client);
                            }
                        }
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    frame.dispose();
                    RepairsWin(client);
                }
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        jt.setFont(table_rows_font);
        jt.setForeground(Color.BLACK);
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.setBounds( x_scroll, y_scroll, width_scroll, height_scroll);
        tablePane.add(sp, BorderLayout.CENTER);
        frame.add(tablePane);

        // Button Change Category
        JButton buttonOne = new JButton("Characteristics:");
        buttonOne.setBounds(XButtonOne, YButtonOne, widthButtonOne,heightButtonOne);
        // Action Button
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                    int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != repairs_table_dates_column_width) {
                        repairs_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != repairs_table_workers_column_width) {
                        repairs_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                        repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Repairs_Table_Cels();
                    }
                    characteristicsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    characteristicsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonOne);

        // Button Change Category
        JButton buttonTow = new JButton("Maintence:");
        buttonTow.setBounds(XButtonTow, YButtonTow, widthButtonTow, heightButtonTow);
        // Action Button
        buttonTow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                    int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != repairs_table_dates_column_width) {
                        repairs_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != repairs_table_workers_column_width) {
                        repairs_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                        repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Repairs_Table_Cels();
                    }
                    maintenceWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    maintenceWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonTow);

        // Button Change Category
        JButton buttonFour = new JButton("Damages:");
        buttonFour.setBounds(XButtonFour, YButtonFour, widthButtonFour, heightButtonFour);
        // Action Button
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                    int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != repairs_table_dates_column_width) {
                        repairs_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != repairs_table_workers_column_width) {
                        repairs_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                        repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Repairs_Table_Cels();
                    }
                    DamageWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    DamageWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonFour);

        // Button Change Category
        JButton buttonThree = new JButton("Repears:");
        buttonThree.setBounds(XButtonThree, YButtonThree, widthButtonThree, heightButtonThree);
        // Action Button
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    RepairsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    RepairsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonThree);

        // Button Change Category Batteries
        JButton buttonBatteries = new JButton("Batteries:");
        buttonBatteries.setBounds(XButtonBattaries,YButtonBattaries,widthButtonBattaries,heightButtonBattaries);
        // Action Button
        buttonBatteries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                    int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != repairs_table_dates_column_width) {
                        repairs_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != repairs_table_workers_column_width) {
                        repairs_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                        repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Repairs_Table_Cels();
                    }
                    BattariesWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    BattariesWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonBatteries);

        // Button Change Category DocNum
        JButton buttonDocNum = new JButton("Costing:");
        buttonDocNum.setBounds(XButtonDocNum,YButtonDocNum,widthButtonDocNum,heightButtonDocNum);
        // Action Button
        buttonDocNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                    int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != repairs_table_dates_column_width) {
                        repairs_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != repairs_table_workers_column_width) {
                        repairs_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                        repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Repairs_Table_Cels();
                    }
                    DocNumWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DocNumWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonDocNum);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_workers = jt.getColumnModel().getColumn(1);
                int table_workers_column_width_new = column_workers.getWidth();
                TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != repairs_table_dates_column_width) {
                    repairs_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_workers_column_width_new != repairs_table_workers_column_width) {
                    repairs_table_workers_column_width = table_workers_column_width_new;
                    check = true;
                }
                if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                    repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Repairs_Table_Cels();
                }
                frame.dispose();
                homeWindow();
            }
        });

        // Button Save
        int widthButtonSave = widthButtonPrint;
        int heightButtonSave = heightButtonPrint;
        int XButtonSave = XButtonPrint;
        int YButtonSave = YButtonPrint + button_y_space;
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(XButtonSave,YButtonSave,widthButtonSave,heightButtonSave);
        // Action Button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // Save Dates Files
                int rows = jt.getRowCount();
                if (rows <= client.repairs_dates.size()) {
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String Worker = (String) jt.getValueAt(i, 1);
                        String RepairsType = (String) jt.getValueAt(i, 2);
                        try {
                            client.repairs_dates.set(i, date);
                            client.repairs_worker.set(i, Worker);
                            client.repairs_repair_type.set(i, RepairsType);
                        } catch (java.lang.ArrayIndexOutOfBoundsException ai) {

                        }
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(Worker);
                        lines.add(RepairsType);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_RepairsType = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + last_date.get(i) + "-RepairsType";
                            String path_RepairsType = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + date + "-RepairsType";
                            DataBase.renameFile(last_path_RepairsType, path_RepairsType);
                        }
                    }
                } else {
                    client.repairs_dates.clear();
                    client.repairs_worker.clear();
                    client.repairs_repair_type.clear();
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String Worker = (String) jt.getValueAt(i, 1);
                        String RepairsType = (String) jt.getValueAt(i, 2);
                        client.repairs_dates.add(date);
                        client.repairs_worker.add(Worker);
                        client.repairs_repair_type.add(RepairsType);
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(Worker);
                        lines.add(RepairsType);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            System.out.println("Defrent path date in Repairs");
                            String last_path = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_RepairsType = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + last_date.get(i) + "-RepairsType";
                            String path_RepairsType = DataBase.folderData + client.my_id + File.separator + "Repairs" + File.separator + date + "-RepairsType";
                            DataBase.renameFile(last_path_RepairsType, path_RepairsType);
                        }
                    }
                }

                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_workers = jt.getColumnModel().getColumn(1);
                int table_workers_column_width_new = column_workers.getWidth();
                TableColumn column_repairsTypes = jt.getColumnModel().getColumn(2);
                int table_repairsTypes_column_width_new = column_repairsTypes.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != repairs_table_dates_column_width) {
                    repairs_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_workers_column_width_new != repairs_table_workers_column_width) {
                    repairs_table_workers_column_width = table_workers_column_width_new;
                    check = true;
                }
                if (table_repairsTypes_column_width_new != repairs_table_RepairsTypes_column_width) {
                    repairs_table_RepairsTypes_column_width = table_repairsTypes_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Repairs_Table_Cels();
                }
                frame.dispose();
                RepairsWin(client);
            }
        });
        frame.add(buttonSave);

        // Button Edit Client
        JButton buttonED = new JButton("Edit Client:");
        buttonED.setBounds(XButtonEdit,YButtonEdit,widthButtonEdit,heightButtonEdit);
        // Action Button
        buttonED.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editClient(client);
                frame.dispose();
            }
        });
        frame.add(buttonED);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Windows Maintence
    public void maintenceWin(Client client) {
 
        JFrame frame = new JFrame("Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        //frame.setResizable(false);
        frame.getContentPane().setBackground(color);
        
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels Size
        int labels_left_width = frameWidth/13;
        int labels_right_width = frameWidth/4;
        int labels_height = frameHeight/37;

        // Labes text Size
        Font txt_size = imformations_font;

        // Addres labels
        int x_addres_label = frameWidth/33;
        int y_addres_label = frameHeight/7;
        int width_addres_label = labels_left_width;
        int hight_addres_label = labels_height;
        JLabel addres_label = new JLabel("Addres&Num:");
        addres_label.setBounds(x_addres_label, y_addres_label, width_addres_label, hight_addres_label);
        addres_label.setFont( txt_size );
        addres_label.setForeground(textColor);
        frame.add(addres_label);
        // Addres labels
        int x_addres_label_2 = x_addres_label + width_addres_label - (width_addres_label/7);
        int y_addres_label_2 = y_addres_label;
        int width_addres_label_2 = labels_right_width;
        int hight_addres_label_2 = labels_height;
        JLabel addres_label_2 = new JLabel(client.street);
        addres_label_2.setBounds(x_addres_label_2, y_addres_label_2, width_addres_label_2, hight_addres_label_2);
        addres_label_2.setFont( txt_size );
        addres_label_2.setForeground(clientDataColor);
        frame.add(addres_label_2);

        // Location labels
        int x_location_label = x_addres_label;
        int y_location_label = y_addres_label + (labels_height*2);
        int width_location_label = labels_left_width;
        int hight_location_label = labels_height;
        JLabel location_label = new JLabel("Location:");
        location_label.setBounds(x_location_label, y_location_label, width_location_label, hight_location_label);
        location_label.setFont( txt_size );
        location_label.setForeground(textColor);
        frame.add(location_label);
        // Location labels
        int x_location_label_2 = x_addres_label_2;
        int y_location_label_2 = y_location_label;
        int width_location_label_2 = labels_right_width;
        int hight_location_label_2 = labels_height;
        JLabel location_label_2 = new JLabel(client.location);
        location_label_2.setBounds(x_location_label_2, y_location_label_2, width_location_label_2, hight_location_label_2);
        location_label_2.setFont( txt_size );
        location_label_2.setForeground(clientDataColor);
        frame.add(location_label_2);

        // Type labels
        int x_type_label = x_location_label;
        int y_type_label = y_location_label + (labels_height*2);
        int width_type_label = labels_left_width;
        int hight_type_label = labels_height;
        JLabel type_label = new JLabel("Type:");
        type_label.setBounds(x_type_label, y_type_label, width_type_label, hight_type_label);
        type_label.setFont( txt_size );
        type_label.setForeground(textColor);
        frame.add(type_label);
        // Type labels
        int x_type_label_2 = x_addres_label_2;
        int y_type_label_2 = y_type_label;
        int width_type_label_2 = labels_right_width;
        int hight_type_label_2 = labels_height;
        JLabel type_label_2 = new JLabel(client.type);
        type_label_2.setBounds(x_type_label_2, y_type_label_2, width_type_label_2, hight_type_label_2);
        type_label_2.setFont( txt_size );
        type_label_2.setForeground(clientDataColor);
        frame.add(type_label_2);

        // Phone labels
        int x_phone_label = x_type_label;
        int y_phone_label = y_type_label + (labels_height*2);
        int width_phone_label = labels_left_width;
        int hight_phone_label = labels_height;
        JLabel phone_label = new JLabel("Phone:");
        phone_label.setBounds(x_phone_label, y_phone_label, width_phone_label, hight_phone_label);
        phone_label.setFont( txt_size );
        phone_label.setForeground(textColor);
        frame.add(phone_label);
        // Phone labels
        int x_phone_label_2 = x_addres_label_2;
        int y_phone_label_2 = y_phone_label;
        int width_phone_label_2 = labels_right_width;
        int hight_phone_label_2 = labels_height;
        JLabel phone_label_2 = new JLabel(client.phone);
        phone_label_2.setBounds(x_phone_label_2, y_phone_label_2, width_phone_label_2, hight_phone_label_2);
        phone_label_2.setFont( txt_size );
        phone_label_2.setForeground(clientDataColor);
        frame.add(phone_label_2);

        // Postal Code labels
        int x_postal_label = x_phone_label;
        int y_postal_label = y_phone_label + (labels_height*2);
        int width_postal_label = labels_left_width;
        int hight_postal_label = labels_height;
        JLabel postal_label = new JLabel("P.Code:");
        postal_label.setBounds(x_postal_label, y_postal_label, width_postal_label, hight_postal_label);
        postal_label.setFont( txt_size );
        postal_label.setForeground(textColor);
        frame.add(postal_label);
        // Postal Code labels
        int x_postal_label_2 = x_addres_label_2;
        int y_postal_label_2 = y_postal_label;
        int width_postal_label_2 = labels_right_width;
        int hight_postal_label_2 = labels_height;
        JLabel postal_label_2 = new JLabel(client.postallCode);
        postal_label_2.setBounds(x_postal_label_2, y_postal_label_2, width_postal_label_2, hight_postal_label_2);
        postal_label_2.setFont( txt_size );
        postal_label_2.setForeground(clientDataColor);
        postal_label_2.setBackground(Color.GRAY);
        frame.add(postal_label_2);

        // Id labels
        int x_id_label = x_phone_label;
        int y_id_label = y_postal_label + (labels_height*2);
        int width_id_label = labels_left_width;
        int hight_id_label = labels_height;
        JLabel id_label = new JLabel("ID:");
        id_label.setBounds(x_id_label, y_id_label, width_id_label, hight_id_label);
        id_label.setFont( txt_size );
        id_label.setForeground(textColor);
        frame.add(id_label);
        // Id labels
        int x_id_label_2 = x_addres_label_2;
        int y_id_label_2 = y_id_label;
        int width_id_label_2 = labels_right_width;
        int hight_id_label_2 = labels_height;
        JLabel id_label_2 = new JLabel(client.my_id);
        id_label_2.setBounds(x_id_label_2, y_id_label_2, width_id_label_2, hight_id_label_2);
        id_label_2.setFont( txt_size );
        id_label_2.setForeground(clientDataColor);
        id_label_2.setBackground(Color.GRAY);
        frame.add(id_label_2);

        // Button Add list
        int widthButtonAdd = frameWidth/12;
        int heightButtonAdd = frameHeight/20;
        int XButtonAdd = (x_addres_label*3) + (frameWidth/33);
        int YButtonAdd = y_postal_label + (frameHeight/7);
        // Button Y Space
        int button_y_space =  + heightButtonAdd + (heightButtonAdd/11);
        // Button Print
        int widthButtonPrint = frameWidth/12;
        int heightButtonPrint = heightButtonAdd;
        int XButtonPrint = (x_addres_label*3) + (frameWidth/33);
        int YButtonPrint = YButtonAdd + button_y_space;
        // Buttons size
        int widthButtonOne= widthButtonAdd;
        int heightButtonOne = heightButtonAdd;
        int XButtonOne = x_addres_label;
        int YButtonOne = YButtonAdd;
        int widthButtonTow= widthButtonOne;
        int heightButtonTow = heightButtonOne;
        int XButtonTow = x_addres_label;
        int YButtonTow = YButtonOne + button_y_space;
        int widthButtonFour = widthButtonOne;
        int heightButtonFour = heightButtonOne;
        int XButtonFour = x_addres_label;
        int YButtonFour = YButtonTow + button_y_space;
        int widthButtonThree = widthButtonOne;
        int heightButtonThree = heightButtonOne;
        int XButtonThree = x_addres_label;
        int YButtonThree = YButtonFour + button_y_space;
        int widthButtonBattaries = widthButtonOne;
        int heightButtonBattaries = heightButtonOne;
        int XButtonBattaries = x_addres_label;
        int YButtonBattaries = YButtonThree + button_y_space;
        int widthButtonDocNum = widthButtonOne;
        int heightButtonDocNum = heightButtonOne;
        int XButtonDocNum = x_addres_label;
        int YButtonDocNum = YButtonBattaries + button_y_space;

        // Scrolls Size
        int x_scroll = (frameWidth/4) + (frameWidth/17);
        int y_scroll = (frameHeight/9);// + (frameHeight/43);
        int width_scroll = (frameWidth/2) + (frameWidth/7);
        int height_scroll = frameHeight - (frameHeight/3) + (frameHeight/9);
        // Labels Size
        int width_tittle_label = frameWidth/7;
        int hight_tittle_label = frameHeight/23; 
        int x_tittle_label = x_scroll + (width_scroll/2) - (width_tittle_label/2);
        int y_tittle_label = frameHeight/20;
        // Button Edit Client
        int widthButtonEdit = (frameWidth/6)+(frameWidth/110);
        int heightButtonEdit = frameHeight/20;
        int XButtonEdit = XButtonFour;
        int YButtonEdit = YButtonDocNum + (button_y_space+(button_y_space/2));

        // Button Add list
        JButton buttonAdd = new JButton("Add:");
        buttonAdd.setBounds(XButtonAdd,YButtonAdd,widthButtonAdd,heightButtonAdd);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addDataPage(client, "Add Maintenance", client.maintence_dates, "maintence", "Date:", frame);
            }
        });
        frame.add(buttonAdd);

        // Button Print
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);
        // Find the biger Array Size
        int largest_size = Collections.max(  Arrays.asList( client.maintence_dates.size(),
                                                            client.maintence_worker.size(), 
                                                            client.maintence_comments.size()
                                                            ) );

        String str =  "\n    ADDRES: " + client.street
                      + "\n    LOCATION: " + client.location
                      + "\n    TYPE: " + client.type
                      + "\n    PHONE: " + client.phone
                      + "\n    POSTAL CODE: " + client.postallCode;

        str += "\n\n MAINTENCE:\n";
        // Set to str string all data
        for (int i = 0; i < client.maintence_dates.size(); i++) {
            str += client.maintence_dates.get(i)
                    + "     WORKER: " + client.maintence_worker.get(i)
                    + "     COMMENTS: " + client.maintence_comments.get(i)
                    + "\n";
        }

        final String str_2 = str;
        
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(str_2);
            }
        });
        frame.add(buttonPrint);


        // Tittle Category label
        JLabel tittle_label = new JLabel("Maintence:");
        tittle_label.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        tittle_label.setFont(textSize);
        tittle_label.setForeground(textColor);
        JPanel tittle_panel = new JPanel();
        tittle_panel.add(tittle_label);
        tittle_panel.setBackground(color);
        tittle_panel.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);            
        frame.add(tittle_panel);

        // Make the 2D array for the table 
        String[] columns = {"Date:", "Worker:", "Comments:"};    
        String[][] data = new String[largest_size][columns.length];

        // Set date in boxe's
        for (int r = 0; r < client.maintence_dates.size(); r++) {
            data[r][0] = client.maintence_dates.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.maintence_worker.size(); r++) {
            data[r][1] = client.maintence_worker.get(r).replaceAll(DataBase.save_type_of_file, "");
        }
        for (int r = 0; r < client.maintence_comments.size(); r++) {
            data[r][2] = client.maintence_comments.get(r).replaceAll(DataBase.save_type_of_file, "");
        }

        // Set the date, Worker, DamageType, FixTypwe, list withn the order of the bigest date
        DataBase.setOrder(data);

        // Set Bataries list with data[][] order
        DataBase.setLists(data,"maintenceWin",client);

        // Get the now values od data
        final Vector<String> last_date = new Vector<String>();
        final Vector<String> last_Worker = new Vector<String>();
        final Vector<String> last_Comments = new Vector<String>();
        for (int r = 0; r < client.maintence_dates.size(); r++) {
            last_date.add(client.maintence_dates.get(r));
        }
        for (int r = 0; r < client.maintence_worker.size(); r++) {
            last_Worker.add(client.maintence_worker.get(r));
        }
        for (int r = 0; r < client.maintence_comments.size(); r++) {
            last_Comments.add(client.maintence_comments.get(r));
        }

        // Set the row name with text index if exist
        for (int i = 0; i < data.length; i++) {
            // Read Workers
            String my_path_Workers = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + data[i][0] + "-Worker";
            String file_data_Workers = DataBase.read(my_path_Workers);
            if (!file_data_Workers.isEmpty()) {
                String lines = file_data_Workers;
                data[i][1] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][1] = "null";
            }
            // Read Comments
            String my_path_Comments = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + data[i][0] + "-Comments";
            String file_data_Comments = DataBase.read(my_path_Comments);
            if (!file_data_Comments.isEmpty()) {
                String lines = file_data_Comments;
                data[i][2] = lines.replaceAll(DataBase.save_type_of_file, "");
            } else {
                data[i][2] = "null";
            }
        }

        // JTable sels not or rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable jt = new JTable(model);
        // Set columns width
        jt.getColumnModel().getColumn(0).setPreferredWidth(maintence_table_dates_column_width);
        jt.getColumnModel().getColumn(1).setPreferredWidth(maintence_table_workers_column_width);
        jt.getColumnModel().getColumn(2).setPreferredWidth(maintence_table_comments_column_width);
        // Set columns font
        JTableHeader header = jt.getTableHeader();
        header.setFont(table_columns_font);
        // Set te value of the rows in center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        // Set Rows height
        for (int r = 0; r < data.length; r++) {
            jt.setRowHeight(r, table_rows_height);
        }

        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    JTable table = (JTable)evt.getSource();
                    int row = table.getSelectedRow(); // get row
                    int column = table.getSelectedColumn(); // get colum
                    String value = (String)jt.getValueAt(row, column); // get value of cell

                    if (SwingUtilities.isRightMouseButton(evt) == true) { // Right click
                        if (column == 0) {
                            //rightClickDeleteWindow(client, client.maintence_dates, row, "maintence", value, "-Comments", null, null, frame, row);
                            rightClickWindow(client, client.maintence_dates, row, "maintence", value, "-Worker", "-Comments", null, null, frame, row);
                        } else {
                            if (column == 1) { // Worker
                                String path = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + client.maintence_dates.get(row) + "-Worker";
                                readDataClient(path,frame,"maintenceWin",client);
                            }
                            if (column == 2) { // Damage Type
                                String path = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + client.maintence_dates.get(row) + "-Comments";
                                readDataClient(path,frame,"maintenceWin",client);
                            }
                        }
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    //new WindowsData().print("[Error] restart the page.");
                    frame.dispose();
                    maintenceWin(client);
                }
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        jt.setFont(table_rows_font);
        jt.setForeground(Color.BLACK);
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.setBounds( x_scroll, y_scroll, width_scroll, height_scroll);
        tablePane.add(sp, BorderLayout.CENTER);
        frame.add(tablePane);

        // Button Change Category
        JButton buttonOne = new JButton("Characteristics:");
        buttonOne.setBounds(XButtonOne, YButtonOne, widthButtonOne,heightButtonOne);
        // Action Button
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_comments = jt.getColumnModel().getColumn(2);
                    int table_comments_column_width_new = column_comments.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != maintence_table_dates_column_width) {
                        maintence_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != maintence_table_workers_column_width) {
                        maintence_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_comments_column_width_new != maintence_table_comments_column_width) {
                        maintence_table_comments_column_width = table_comments_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Maintence_Table_Cels();
                    }
                    characteristicsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    characteristicsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonOne);

        // Button Change Category
        JButton buttonTow = new JButton("Maintence:");
        buttonTow.setBounds(XButtonTow, YButtonTow, widthButtonTow, heightButtonTow);
        // Action Button
        buttonTow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_damageTypes = jt.getColumnModel().getColumn(2);
                    int table_damageTypes_column_width_new = column_damageTypes.getWidth();
                    TableColumn column_fixTypes = jt.getColumnModel().getColumn(3);
                    int table_fixTypes_column_width_new = column_fixTypes.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != damages_table_dates_column_width) {
                        damages_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != damages_table_workers_column_width) {
                        damages_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_damageTypes_column_width_new != damages_table_DamageTypes_column_width) {
                        damages_table_DamageTypes_column_width = table_damageTypes_column_width_new;
                        check = true;
                    }
                    if (table_fixTypes_column_width_new != damages_table_FixTypes_column_width) {
                        damages_table_FixTypes_column_width = table_fixTypes_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Damages_Table_Cels();
                    }
                    maintenceWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    maintenceWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonTow);

        // Button Change Category
        JButton buttonFour = new JButton("Damages:");
        buttonFour.setBounds(XButtonFour, YButtonFour, widthButtonFour, heightButtonFour);
        // Action Button
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_comments = jt.getColumnModel().getColumn(2);
                    int table_comments_column_width_new = column_comments.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != maintence_table_dates_column_width) {
                        maintence_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != maintence_table_workers_column_width) {
                        maintence_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_comments_column_width_new != maintence_table_comments_column_width) {
                        maintence_table_comments_column_width = table_comments_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Maintence_Table_Cels();
                    }
                    DamageWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    DamageWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonFour);

        // Button Change Category
        JButton buttonThree = new JButton("Repears:");
        buttonThree.setBounds(XButtonThree, YButtonThree, widthButtonThree, heightButtonThree);
        // Action Button
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_comments = jt.getColumnModel().getColumn(2);
                    int table_comments_column_width_new = column_comments.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != maintence_table_dates_column_width) {
                        maintence_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != maintence_table_workers_column_width) {
                        maintence_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_comments_column_width_new != maintence_table_comments_column_width) {
                        maintence_table_comments_column_width = table_comments_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Maintence_Table_Cels();
                    }
                    RepairsWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) {
                    RepairsWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonThree);

        // Button Change Category Batteries
        JButton buttonBatteries = new JButton("Batteries:");
        buttonBatteries.setBounds(XButtonBattaries,YButtonBattaries,widthButtonBattaries,heightButtonBattaries);
        // Action Button
        buttonBatteries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_comments = jt.getColumnModel().getColumn(2);
                    int table_comments_column_width_new = column_comments.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != maintence_table_dates_column_width) {
                        maintence_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != maintence_table_workers_column_width) {
                        maintence_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_comments_column_width_new != maintence_table_comments_column_width) {
                        maintence_table_comments_column_width = table_comments_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Maintence_Table_Cels();
                    }
                    BattariesWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    BattariesWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonBatteries);

        // Button Change Category DocNum
        JButton buttonDocNum = new JButton("Costing:");
        buttonDocNum.setBounds(XButtonDocNum,YButtonDocNum,widthButtonDocNum,heightButtonDocNum);
        // Action Button
        buttonDocNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get columns width
                    TableColumn column_dates = jt.getColumnModel().getColumn(0);
                    int table_dates_column_width_new = column_dates.getWidth();
                    TableColumn column_workers = jt.getColumnModel().getColumn(1);
                    int table_workers_column_width_new = column_workers.getWidth();
                    TableColumn column_comments = jt.getColumnModel().getColumn(2);
                    int table_comments_column_width_new = column_comments.getWidth();
                    boolean check = false;
                    if (table_dates_column_width_new != maintence_table_dates_column_width) {
                        maintence_table_dates_column_width = table_dates_column_width_new;
                        check = true;
                    }
                    if (table_workers_column_width_new != maintence_table_workers_column_width) {
                        maintence_table_workers_column_width = table_workers_column_width_new;
                        check = true;
                    }
                    if (table_comments_column_width_new != maintence_table_comments_column_width) {
                        maintence_table_comments_column_width = table_comments_column_width_new;
                        check = true;
                    }
                    if (check) {
                        DataBase.Save_Maintence_Table_Cels();
                    }
                    DocNumWin(client);
                    frame.dispose();
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    DocNumWin(client);
                    frame.dispose();
                }
            }
        });
        frame.add(buttonDocNum);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_workers = jt.getColumnModel().getColumn(1);
                int table_workers_column_width_new = column_workers.getWidth();
                TableColumn column_comments = jt.getColumnModel().getColumn(2);
                int table_comments_column_width_new = column_comments.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != maintence_table_dates_column_width) {
                    maintence_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_workers_column_width_new != maintence_table_workers_column_width) {
                    maintence_table_workers_column_width = table_workers_column_width_new;
                    check = true;
                }
                if (table_comments_column_width_new != maintence_table_comments_column_width) {
                    maintence_table_comments_column_width = table_comments_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Maintence_Table_Cels();
                }
                frame.dispose();
                homeWindow();
            }
        });

        // Button Save
        int widthButtonSave = widthButtonPrint;
        int heightButtonSave = heightButtonPrint;
        int XButtonSave = XButtonPrint;
        int YButtonSave = YButtonPrint + button_y_space;
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(XButtonSave,YButtonSave,widthButtonSave,heightButtonSave);
        // Action Button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // Save Dates Files
                int rows = jt.getRowCount();
                if (rows <= client.maintence_dates.size()) {
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String Worker = (String) jt.getValueAt(i, 1);
                        String Comments = (String) jt.getValueAt(i, 2);
                        try {
                            client.maintence_dates.set(i, date);
                            client.maintence_worker.set(i, Worker);
                            client.maintence_comments.set(i, Comments);
                        } catch (java.lang.ArrayIndexOutOfBoundsException ai) {
                            System.err.println("[Error]: " + ai);
                        }
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(Worker);
                        lines.add(Comments);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_MaintenceType = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + last_date.get(i) + "-Comments";
                            String path_MaintenceType = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + date + "-Comments";
                            DataBase.renameFile(last_path_MaintenceType, path_MaintenceType);
                        }
                    }
                } else {
                    client.maintence_dates.clear();
                    client.maintence_worker.clear();
                    client.maintence_comments.clear();
                    for (int i = 0; i < rows; i++) {
                        String date = (String) jt.getValueAt(i, 0);
                        String Worker = (String) jt.getValueAt(i, 1);
                        String Comments = (String) jt.getValueAt(i, 2);
                        client.maintence_dates.add(date);
                        client.maintence_worker.add(Worker);
                        client.maintence_comments.add(Comments);
                        String my_path_Dates = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + date;
                        Vector<String> lines = new Vector<String>(4);
                        lines.add(date);
                        lines.add(Worker);
                        lines.add(Comments);
                        if (DataBase.Exists(my_path_Dates)) {
                            DataBase.write(my_path_Dates, lines);
                        } else {
                            String last_path = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + last_date.get(i);
                            DataBase.renameFile(last_path, my_path_Dates);
                            DataBase.write(my_path_Dates, lines);
                            String last_path_MaintenceType = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + last_date.get(i) + "-Comments";
                            String path_MaintenceType = DataBase.folderData + client.my_id + File.separator + "maintence" + File.separator + date + "-Comments";
                            DataBase.renameFile(last_path_MaintenceType, path_MaintenceType);
                        }
                    }
                }

                // Get columns width
                TableColumn column_dates = jt.getColumnModel().getColumn(0);
                int table_dates_column_width_new = column_dates.getWidth();
                TableColumn column_workers = jt.getColumnModel().getColumn(1);
                int table_workers_column_width_new = column_workers.getWidth();
                TableColumn column_comments = jt.getColumnModel().getColumn(2);
                int table_comments_column_width_new = column_comments.getWidth();
                boolean check = false;
                if (table_dates_column_width_new != maintence_table_dates_column_width) {
                    maintence_table_dates_column_width = table_dates_column_width_new;
                    check = true;
                }
                if (table_workers_column_width_new != maintence_table_workers_column_width) {
                    maintence_table_workers_column_width = table_workers_column_width_new;
                    check = true;
                }
                if (table_comments_column_width_new != maintence_table_comments_column_width) {
                    maintence_table_comments_column_width = table_comments_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Maintence_Table_Cels();
                }
                frame.dispose();
                maintenceWin(client);             
            }
        });
        frame.add(buttonSave);

        // Button Edit Client
        JButton buttonED = new JButton("Edit Client:");
        buttonED.setBounds(XButtonEdit,YButtonEdit,widthButtonEdit,heightButtonEdit);
        // Action Button
        buttonED.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editClient(client);
                frame.dispose();
            }
        });
        frame.add(buttonED);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Window Characteristics
    public void characteristicsWin(Client client) {

        JFrame frame = new JFrame("Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        //frame.setResizable(false);
        frame.getContentPane().setBackground(color);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                homeWindow();
            }
        });
        
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Labels Size
        int labels_left_width = frameWidth/13;
        int labels_right_width = frameWidth/4;
        int labels_height = frameHeight/37;

        // Labes text Size
        Font txt_size = imformations_font;

        // Addres labels
        int x_addres_label = frameWidth/33;
        int y_addres_label = frameHeight/7;
        int width_addres_label = labels_left_width;
        int hight_addres_label = labels_height;
        JLabel addres_label = new JLabel("Addres&Num:");
        addres_label.setBounds(x_addres_label, y_addres_label, width_addres_label, hight_addres_label);
        addres_label.setFont( txt_size );
        addres_label.setForeground(textColor);
        frame.add(addres_label);
        // Addres labels
        int x_addres_label_2 = x_addres_label + width_addres_label - (width_addres_label/7);
        int y_addres_label_2 = y_addres_label;
        int width_addres_label_2 = labels_right_width;
        int hight_addres_label_2 = labels_height;
        JLabel addres_label_2 = new JLabel(client.street);
        addres_label_2.setBounds(x_addres_label_2, y_addres_label_2, width_addres_label_2, hight_addres_label_2);
        addres_label_2.setFont( txt_size );
        addres_label_2.setForeground(clientDataColor);
        frame.add(addres_label_2);

        // Location labels
        int x_location_label = x_addres_label;
        int y_location_label = y_addres_label + (labels_height*2);
        int width_location_label = labels_left_width;
        int hight_location_label = labels_height;
        JLabel location_label = new JLabel("Location:");
        location_label.setBounds(x_location_label, y_location_label, width_location_label, hight_location_label);
        location_label.setFont( txt_size );
        location_label.setForeground(textColor);
        frame.add(location_label);
        // Location labels
        int x_location_label_2 = x_addres_label_2;
        int y_location_label_2 = y_location_label;
        int width_location_label_2 = labels_right_width;
        int hight_location_label_2 = labels_height;
        JLabel location_label_2 = new JLabel(client.location);
        location_label_2.setBounds(x_location_label_2, y_location_label_2, width_location_label_2, hight_location_label_2);
        location_label_2.setFont( txt_size );
        location_label_2.setForeground(clientDataColor);
        frame.add(location_label_2);

        // Type labels
        int x_type_label = x_location_label;
        int y_type_label = y_location_label + (labels_height*2);
        int width_type_label = labels_left_width;
        int hight_type_label = labels_height;
        JLabel type_label = new JLabel("Type:");
        type_label.setBounds(x_type_label, y_type_label, width_type_label, hight_type_label);
        type_label.setFont( txt_size );
        type_label.setForeground(textColor);
        frame.add(type_label);
        // Type labels
        int x_type_label_2 = x_addres_label_2;
        int y_type_label_2 = y_type_label;
        int width_type_label_2 = labels_right_width;
        int hight_type_label_2 = labels_height;
        JLabel type_label_2 = new JLabel(client.type);
        type_label_2.setBounds(x_type_label_2, y_type_label_2, width_type_label_2, hight_type_label_2);
        type_label_2.setFont( txt_size );
        type_label_2.setForeground(clientDataColor);
        frame.add(type_label_2);

        // Phone labels
        int x_phone_label = x_type_label;
        int y_phone_label = y_type_label + (labels_height*2);
        int width_phone_label = labels_left_width;
        int hight_phone_label = labels_height;
        JLabel phone_label = new JLabel("Phone:");
        phone_label.setBounds(x_phone_label, y_phone_label, width_phone_label, hight_phone_label);
        phone_label.setFont( txt_size );
        phone_label.setForeground(textColor);
        frame.add(phone_label);
        // Phone labels
        int x_phone_label_2 = x_addres_label_2;
        int y_phone_label_2 = y_phone_label;
        int width_phone_label_2 = labels_right_width;
        int hight_phone_label_2 = labels_height;
        JLabel phone_label_2 = new JLabel(client.phone);
        phone_label_2.setBounds(x_phone_label_2, y_phone_label_2, width_phone_label_2, hight_phone_label_2);
        phone_label_2.setFont( txt_size );
        phone_label_2.setForeground(clientDataColor);
        frame.add(phone_label_2);

        // Postal Code labels
        int x_postal_label = x_phone_label;
        int y_postal_label = y_phone_label + (labels_height*2);
        int width_postal_label = labels_left_width;
        int hight_postal_label = labels_height;
        JLabel postal_label = new JLabel("P.Code:");
        postal_label.setBounds(x_postal_label, y_postal_label, width_postal_label, hight_postal_label);
        postal_label.setFont( txt_size );
        postal_label.setForeground(textColor);
        frame.add(postal_label);
        // Postal Code labels
        int x_postal_label_2 = x_addres_label_2;
        int y_postal_label_2 = y_postal_label;
        int width_postal_label_2 = labels_right_width;
        int hight_postal_label_2 = labels_height;
        JLabel postal_label_2 = new JLabel(client.postallCode);
        postal_label_2.setBounds(x_postal_label_2, y_postal_label_2, width_postal_label_2, hight_postal_label_2);
        postal_label_2.setFont( txt_size );
        postal_label_2.setForeground(clientDataColor);
        postal_label_2.setBackground(Color.GRAY);
        frame.add(postal_label_2);

        // Id labels
        int x_id_label = x_phone_label;
        int y_id_label = y_postal_label + (labels_height*2);
        int width_id_label = labels_left_width;
        int hight_id_label = labels_height;
        JLabel id_label = new JLabel("ID:");
        id_label.setBounds(x_id_label, y_id_label, width_id_label, hight_id_label);
        id_label.setFont( txt_size );
        id_label.setForeground(textColor);
        frame.add(id_label);
        // Id labels
        int x_id_label_2 = x_addres_label_2;
        int y_id_label_2 = y_id_label;
        int width_id_label_2 = labels_right_width;
        int hight_id_label_2 = labels_height;
        JLabel id_label_2 = new JLabel(client.my_id);
        id_label_2.setBounds(x_id_label_2, y_id_label_2, width_id_label_2, hight_id_label_2);
        id_label_2.setFont( txt_size );
        id_label_2.setForeground(clientDataColor);
        id_label_2.setBackground(Color.GRAY);
        frame.add(id_label_2);

        // Button Add list
        int widthButtonAdd = frameWidth/12;
        int heightButtonAdd = frameHeight/20;
        int XButtonAdd = (x_addres_label*3) + (frameWidth/33);
        int YButtonAdd = y_postal_label + (frameHeight/7);
        // Button Y Space
        int button_y_space =  + heightButtonAdd + (heightButtonAdd/11);
        // Button Print
        int widthButtonPrint = frameWidth/12;
        int heightButtonPrint = heightButtonAdd;
        int XButtonPrint = (x_addres_label*3) + (frameWidth/33);
        int YButtonPrint = YButtonAdd + button_y_space;
        // Buttons size
        int widthButtonOne= widthButtonAdd;
        int heightButtonOne = heightButtonAdd;
        int XButtonOne = x_addres_label;
        int YButtonOne = YButtonAdd;
        int widthButtonTow= widthButtonOne;
        int heightButtonTow = heightButtonOne;
        int XButtonTow = x_addres_label;
        int YButtonTow = YButtonOne + button_y_space;
        int widthButtonFour = widthButtonOne;
        int heightButtonFour = heightButtonOne;
        int XButtonFour = x_addres_label;
        int YButtonFour = YButtonTow + button_y_space;
        int widthButtonThree = widthButtonOne;
        int heightButtonThree = heightButtonOne;
        int XButtonThree = x_addres_label;
        int YButtonThree = YButtonFour + button_y_space;
        int widthButtonBattaries = widthButtonOne;
        int heightButtonBattaries = heightButtonOne;
        int XButtonBattaries = x_addres_label;
        int YButtonBattaries = YButtonThree + button_y_space;
        int widthButtonDocNum = widthButtonOne;
        int heightButtonDocNum = heightButtonOne;
        int XButtonDocNum = x_addres_label;
        int YButtonDocNum = YButtonBattaries + button_y_space;

        // Scrolls Size
        int x_scroll = (frameWidth/4) + (frameWidth/17);
        int y_scroll = (frameHeight/9);// + (frameHeight/43);
        int width_scroll = (frameWidth/2) + (frameWidth/7);
        int height_scroll = frameHeight - (frameHeight/3) + (frameHeight/9);
        // Labels Size
        int width_tittle_label = frameWidth/7;
        int hight_tittle_label = frameHeight/23; 
        int x_tittle_label = x_scroll + (width_scroll/2) - (width_tittle_label/2);
        int y_tittle_label = frameHeight/20;
        // Button Edit Client
        int widthButtonEdit = (frameWidth/6)+(frameWidth/110);
        int heightButtonEdit = frameHeight/20;
        int XButtonEdit = XButtonFour;
        int YButtonEdit = YButtonDocNum + (button_y_space+(button_y_space/2));

        // Tittle Category label
        JLabel tittle_label = new JLabel("Characteristics:");
        tittle_label.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);
        tittle_label.setFont(textSize);
        tittle_label.setForeground(textColor);
        JPanel tittle_panel = new JPanel();
        tittle_panel.add(tittle_label);
        tittle_panel.setBackground(color);
        tittle_panel.setBounds(x_tittle_label, y_tittle_label, width_tittle_label, hight_tittle_label);      
        frame.add(tittle_panel);



        // Button Change Category
        JButton buttonOne = new JButton("Characteristics:");
        buttonOne.setBounds(XButtonOne, YButtonOne, widthButtonOne,heightButtonOne);
        // Action Button
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                characteristicsWin(client);
                frame.dispose();
            }
        });
        frame.add(buttonOne);

        // Button Change Category
        JButton buttonTow = new JButton("Maintence:");
        buttonTow.setBounds(XButtonTow, YButtonTow, widthButtonTow, heightButtonTow);
        // Action Button
        buttonTow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                maintenceWin(client);
                frame.dispose();
            }
        });
        frame.add(buttonTow);

        // Button Change Category
        JButton buttonFour = new JButton("Damages:");
        buttonFour.setBounds(XButtonFour, YButtonFour, widthButtonFour, heightButtonFour);
        // Action Button
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DamageWin(client);
                frame.dispose();
            }
        });
        frame.add(buttonFour);

        // Button Change Category
        JButton buttonThree = new JButton("Repears:");
        buttonThree.setBounds(XButtonThree, YButtonThree, widthButtonThree, heightButtonThree);
        // Action Button
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                RepairsWin(client);
                frame.dispose();
            }
        });
        frame.add(buttonThree);

        // Button Change Category Batteries
        JButton buttonBatteries = new JButton("Batteries:");
        buttonBatteries.setBounds(XButtonBattaries,YButtonBattaries,widthButtonBattaries,heightButtonBattaries);
        // Action Button
        buttonBatteries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                BattariesWin(client);
                frame.dispose();
            }
        });
        frame.add(buttonBatteries);

        // Button Change Category DocNum
        JButton buttonDocNum = new JButton("Costing:");
        buttonDocNum.setBounds(XButtonDocNum,YButtonDocNum,widthButtonDocNum,heightButtonDocNum);
        // Action Button
        buttonDocNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DocNumWin(client);
                frame.dispose();
            }
        });
        frame.add(buttonDocNum);

        // Text Area
        String characteristics_data_path = DataBase.folderData + client.my_id + File.separator + "characteristics" + File.separator + "data";
        String data = DataBase.read(characteristics_data_path);
        JTextArea area = new JTextArea(data);
        area.setFont(new Font("Arial", Font.BOLD, 17));
        JScrollPane jp = new JScrollPane(area);
        jp.setBounds(x_scroll, y_scroll, width_scroll, height_scroll);
        frame.add(jp);

        // Button Print
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);

        String str =  "\n    ADDRES: " + client.street
                        + "\n    LOCATION: " + client.location
                        + "\n    TYPE: " + client.type
                        + "\n    PHONE: " + client.phone
                        + "\n    POSTAL CODE: " + client.postallCode;

        str += "\n\nCHARACTERISTICS:\n";

        // Set to str string all data
        str += "\n" + area.getText();

        final String str_2 = str;
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(str_2);
            }
        });
        frame.add(buttonPrint);
        

        // Button Add list
        JButton buttonAdd = new JButton("Save");
        buttonAdd.setBounds(XButtonAdd,YButtonAdd,widthButtonAdd,heightButtonAdd);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String folder_path = DataBase.folderData + client.my_id + File.separator + "characteristics";
                DataBase.makeDir(folder_path);
                DataBase.write2(characteristics_data_path, area.getText());
                frame.dispose();
                characteristicsWin(client);
            }
        });
        frame.add(buttonAdd);

        // Button Edit Client
        JButton buttonED = new JButton("Edit Client:");
        buttonED.setBounds(XButtonEdit,YButtonEdit,widthButtonEdit,heightButtonEdit);
        // Action Button
        buttonED.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editClient(client);
                frame.dispose();
            }
        });
        frame.add(buttonED);

        // Pack
        frame.pack();

        // Set place ans size
        //frame.setSize(new Dimension(screenWidth(),screenHeight()));

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }


    ////////   Text window for categories o every client   ////////////////////////////////////////////////////////////////////////////////////////

    public void readDataClient(String path, JFrame w, String nameWindow, Client client) {

        JFrame frame = new JFrame("Data");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Get screen size
        int screenWidth = screenWidth();
        int screenHeight = screenHeight();

        int width = screenWidth/3;
        int height = screenHeight/2;
        int X = (screenWidth/2) - (width/2) + (width/77);
        int Y = (screenHeight/2) - (height/2);

        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addVetoableChangeListener("focusedWindow",
            new VetoableChangeListener() {
                private boolean gained = false;

                @Override
                public void vetoableChange(PropertyChangeEvent evt)
                    throws PropertyVetoException {
                if (evt.getNewValue() == frame) {
                    gained = true;
                }
                if (gained && evt.getNewValue() != frame) {
                    frame.dispose();
                }
                }
            });

        // Remove start bar with X button
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        // Read the data and put in textField
        String data = DataBase.read(path);
        int width_text = width-(width/47);
        int height_text = height-(height/10);
        int x_text = width/100;
        int y_text = height/80;
        JTextArea text = new JTextArea(data);
        text.setBounds( x_text, y_text, width_text, height_text );
        frame.add(text);

        // Button Apply
        JButton buttonAdd = new JButton("Apply:");
        int width_apply = width/5;
        int height_apply = height/17;
        int x_apply = (width/2)+(width/4);
        int y_apply = height-(height/14);
        buttonAdd.setBounds( x_apply, y_apply, width_apply, height_apply);
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Write the new data
                DataBase.write2(path, text.getText());
                frame.dispose();
                w.dispose();
                if (nameWindow.equals("BattariesWin")) {
                    BattariesWin(client);
                }
                if (nameWindow.equals("DocNumWin")) {
                    DocNumWin(client);
                }
                if (nameWindow.equals("DamageWin")) {
                    DamageWin(client);
                }
                if (nameWindow.equals("RepairsWin")) {
                    RepairsWin(client);
                }
                if (nameWindow.equals("maintenceWin")) {
                    maintenceWin(client);
                }
            }
        });
        frame.add(buttonAdd);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(X, Y, width, height);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

/*
    ////////   Right click delete window   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void rightClickDeleteWindow(Client client, Vector<String> list, int listIndex,
                                       String listName, String buttonValue, String file2, String file3, String file4, JFrame w1, int row) {

        JFrame frame = new JFrame("");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.GRAY);

        // Get mouse potision
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int mouseX = (int) b.getX();
        int mouseY = (int) b.getY();

        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        int width = frameWidth/9;
        int height = ((frameHeight/12)/2) - 2;

        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addVetoableChangeListener("focusedWindow",
                        new VetoableChangeListener() {
                            private boolean gained = false;
                            @Override
                            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                                if (evt.getNewValue() == frame) {
                                    gained = true;
                                }
                                if (gained && evt.getNewValue() != frame) {
                                    frame.dispose();
                                }
                            }
                        });

        // Remove start bar with x button
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        // Button delete
        JButton buttonDEL = new JButton("Delete:");
        buttonDEL.setBounds( 1, 1, width-2, frameHeight/26 );
        // Action Button
        buttonDEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Delete file
                    String clientPath = DataBase.folderData + client.my_id;
                    if ( new File(clientPath).list().length != 0 ) {
                        String listPath = DataBase.folderData + client.my_id + File.separator + listName;
                        if ( new File(listPath).list().length == 1 ) {
                            File file = new File(listPath);
                            DataBase.delete(file);
                            // Remove from list and dont show again
                            try {
                                for (String obj : list) {
                                    if ( buttonValue.equals(obj) ) {
                                        list.remove(obj);
                                    }
                                }
                            } catch (java.util.ConcurrentModificationException e) {
                            }
                        } else {
                            String path = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue;
                            File file = new File(path);
                            DataBase.delete(file);
                            if (!file2.equals(null)) {
                                String path2 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file2;
                                File file2 = new File(path2);
                                DataBase.delete(file2);
                            }
                            if (!file3.equals(null)) {
                                String path3 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file3;
                                File file3 = new File(path3);
                                DataBase.delete(file3);
                            }
                            if (!file4.equals(null)) {
                                String path4 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file4;
                                File file4 = new File(path4);
                                DataBase.delete(file4);
                            }
                            // Remove from list and dont show again
                            try {
                                for (String obj : list) {
                                    if ( buttonValue.equals(obj) ) {
                                        list.remove(obj);
                                    }
                                }
                            } catch (java.util.ConcurrentModificationException e) {

                            }
                        }
                    }
                } catch (java.lang.NullPointerException e) {

                } finally {
                    frame.dispose();
                    w1.dispose();
                    if (listName.equals("Damage")) {
                        client.damage_dates.remove(row);
                        client.damage_worker.remove(row);
                        client.damage_damage_type.remove(row);
                        client.damage_fix_type.remove(row);
                        DamageWin(client);
                    }
                    else if (listName.equals("Repairs")) {
                        client.repairs_dates.remove(row);
                        client.repairs_worker.remove(row);
                        client.repairs_repair_type.remove(row);
                        RepairsWin(client);
                    }
                    else if (listName.equals("maintence")) {
                        client.maintence_dates.remove(row);
                        client.maintence_worker.remove(row);
                        client.maintence_comments.remove(row);
                        maintenceWin(client);
                    }
                    else if (listName.equals("characteristics")) {
                        characteristicsWin(client);
                    }
                    else if (listName.equals("Battaries")) {
                        client.battaries_dates.remove(row);
                        client.battaries_type1.remove(row);
                        client.battaries_type2.remove(row);
                        client.battaries_type3.remove(row);
                        BattariesWin(client);
                    } else {
                        homeWindow();
                    }
                    //restart();
                }

            }
        });
        frame.add(buttonDEL);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(mouseX, mouseY, width, height);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }
*/

    // Right click window delete and rename
    public void rightClickWindow(Client client, Vector<String> list, int listIndex,
                                 String listName, String buttonValue, String file2, String file3, String file4,
                                 String file5,JFrame w1, int row) {

        JFrame frame = new JFrame("");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Get mouse potision
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int mouseX = (int) b.getX();
        int mouseY = (int) b.getY();

        int width = frameWidth/9;
        int height = (frameHeight/12) - 2;

        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addVetoableChangeListener("focusedWindow",
            new VetoableChangeListener() {
                private boolean gained = false;
                @Override
                public void vetoableChange(PropertyChangeEvent evt)
                    throws PropertyVetoException {
                if (evt.getNewValue() == frame) {
                    gained = true;
                }
                if (gained && evt.getNewValue() != frame) {
                    frame.dispose();
                }
                }
            });

        // Remove start bar with x button
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        // Button delete
        JButton buttonDEL = new JButton("Delete:");
        buttonDEL.setBounds( 1, 1, width-2, frameHeight/26 );
        // Action Button
        buttonDEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 try {
                    // Delete file in DataBase
                    String clientPath = DataBase.folderData + client.my_id;
                    String listPath = DataBase.folderData + client.my_id + File.separator + listName;
                    File folder = new File(listPath);
                    if (folder.exists()) {
                        // Date file
                        String path = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + DataBase.save_type_of_file;
                        File file = new File(path);
                        if (file.exists()) {
                            if (DataBase.delete(file)) {
                                //print("File deleted OK");
                            }
                        }
                        // file 2
                        if (!file2.equals(null)) {
                            String path2 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file2 + DataBase.save_type_of_file;
                            File file2 = new File(path2);
                            if (file2.exists()) {
                                if (DataBase.delete(file2)) {
                                    //print("File deleted OK");
                                }
                            }
                        }
                        // file 3
                        if (!file3.equals(null)) {
                            String path3 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file3 + DataBase.save_type_of_file;
                            File file3 = new File(path3);
                            if (file3.exists()) {
                                if (DataBase.delete(file3)) {
                                    //print("File deleted OK");
                                }
                            }
                        }
                        // file 4
                        if (!file4.equals(null)) {
                            String path4 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file4 + DataBase.save_type_of_file;
                            File file4 = new File(path4);
                            if (file4.exists()) {
                                if (DataBase.delete(file4)) {
                                    //print("File deleted OK");
                                }
                            }
                        }
                        // file 5
                        if (!file5.equals(null)) {
                            String path5 = DataBase.folderData + client.my_id + File.separator + listName + File.separator + buttonValue + file5 + DataBase.save_type_of_file;
                            System.out.println("[FILE5 PATH] " + path5);
                            File file5 = new File(path5);
                            if (file5.exists()) {
                                if (DataBase.delete(file5)) {
                                    //print("File deleted OK");
                                }
                            }
                        }
                    }
                     //frame.dispose();
                     //w1.dispose();
                } catch (java.lang.NullPointerException e) {
                     //frame.dispose();
                     //w1.dispose();
                } finally {
                    frame.dispose();
                    w1.dispose();
                    // Delete my list (RAM)
                    if (listName.equals("Damage")) {
                        client.damage_dates.remove(row);
                        client.damage_worker.remove(row);
                        client.damage_damage_type.remove(row);
                        client.damage_fix_type.remove(row);
                        DamageWin(client);
                    } else if (listName.equals("Repairs")) {
                        client.repairs_dates.remove(row);
                        client.repairs_worker.remove(row);
                        client.repairs_repair_type.remove(row);
                        RepairsWin(client);
                    } else if (listName.equals("maintence")) {
                        client.maintence_dates.remove(row);
                        client.maintence_worker.remove(row);
                        client.maintence_comments.remove(row);
                        maintenceWin(client);
                    } else if (listName.equals("characteristics")) {
                        characteristicsWin(client);
                    } else if (listName.equals("Battaries")) {
                        client.battaries_dates.remove(row);
                        client.battaries_type1.remove(row);
                        client.battaries_type2.remove(row);
                        client.battaries_type3.remove(row);
                        BattariesWin(client);
                    } else if (listName.equals("DocNum")) {
                        client.DocNum_dates.remove(row);
                        client.DocNum_docnum.remove(row);
                        client.DocNum_Work.remove(row);
                        client.DocNum_Price.remove(row);
                        client.DocNum_Repayment.remove(row);
                        DocNumWin(client);
                    }
                }
            }
        });
        frame.add(buttonDEL);
        
        // Button Rename
        JButton buttonRename = new JButton("Rename:");
        buttonRename.setBounds( 1 , frameHeight/24 , width-2 , frameHeight/26 );
        // Action Button
        buttonRename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                renameJListWindow(client, buttonValue, listName, w1);
            }
        });
        frame.add(buttonRename);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(mouseX, mouseY, width, height);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }


////////////   Rename Window   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void renameJListWindow(Client client, String buttonValue, String listName, JFrame w1) {
        JFrame frame = new JFrame("");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Get mouse potision
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int mouseX = (int) b.getX();
        int mouseY = (int) b.getY();

        int width = screenWidth();
        int height = screenHeight();

        int frame_width = (width/3)-(width/15);
        int frame_height = height/16;

        // Close window if click outside
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addVetoableChangeListener("focusedWindow",
            new VetoableChangeListener() {
                private boolean gained = false;
                @Override
                public void vetoableChange(PropertyChangeEvent evt)
                    throws PropertyVetoException {
                    if (evt.getNewValue() == frame) {
                        gained = true;
                    }
                    if (gained && evt.getNewValue() != frame) {
                        frame.dispose();
                    }
                }
            });

        // Remove start bar with x button
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        // TextField
        int textX = width/7 - width/10;    
        int textY = height/77;   
        JTextField jtext = new JTextField();
        jtext.setBounds( textX , textY , width/7 , height/25 );
        frame.add(jtext);

        // Label with name
        JLabel label = new JLabel("<html><font color='black'> Date: </font></html>");
        //label.setBounds( (textX-(width/15)) , textY , width/30 , height/25 );
        JPanel label_panel = new JPanel();
        label_panel.add(label);
        label_panel.setBounds( (textX-(width/27)) , textY , width/30 , height/25 );
        frame.add(label_panel);

        // Button Apply
        JButton apply = new JButton("Apply:");
        apply.setBounds( textX+(width/7)+(width/273) , textY , width/15 , height/25 );
        // Action Button
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Get the last date
                String old_name = buttonValue;
                // Get the new date
                String nameList = jtext.getText();

                // Make the date healthy for the programe
                boolean check = true;
                try {
                    nameList = DataBase.clear_date(nameList);
                } catch (java.lang.ArrayIndexOutOfBoundsException aie) { // Check if like 00-00-00
                    Thread thread = new Thread() {
                        public void run() {
                            WindowsData.Show("[ IT'S NOT DATE ]");
                            try { this.sleep(3000); } catch (InterruptedException ie) { }
                            WindowsData.Show_Close();
                        }
                    };
                    thread.start();
                    check = false;
                }
                if (check) { // Rename
                    // Get the main path
                    String path = DataBase.folderData + client.my_id + File.separator + listName;
                    // New date
                    String new_date = nameList;
                    // If Battaries
                    if (listName.equals("Battaries")) {
                        // Get the old paths
                        String old_path_date = path + File.separator + old_name + DataBase.save_type_of_file;
                        String old_path_type1 = path + File.separator + old_name + "-Type1" + DataBase.save_type_of_file;
                        String old_path_type2 = path + File.separator + old_name + "-Type2" + DataBase.save_type_of_file;
                        String old_path_type3 = path + File.separator + old_name + "-Type3" + DataBase.save_type_of_file;
                        // Get the new paths
                        String new_path_date = path + File.separator + new_date + DataBase.save_type_of_file;
                        String new_path_type1 = path + File.separator + new_date + "-Type1" + DataBase.save_type_of_file;
                        String new_path_type2 = path + File.separator + new_date + "-Type2" + DataBase.save_type_of_file;
                        String new_path_type3 = path + File.separator + new_date + "-Type3" + DataBase.save_type_of_file;
                        // If file date exist
                        File old_file_date = new File(old_path_date);
                        if (old_file_date.exists()) {
                            DataBase.renameFile(old_path_date, new_path_date);
                        }
                        // If file date_type1 exist
                        File old_file_date_type1 = new File(old_path_type1);
                        if (old_file_date_type1.exists()) {
                            DataBase.renameFile(old_path_type1, new_path_type1);
                        }
                        // If file date_type2 exist
                        File old_file_date_type2 = new File(old_path_type2);
                        if (old_file_date_type2.exists()) {
                            DataBase.renameFile(old_path_type2, new_path_type2);
                        }
                        // If file date_type3 exist
                        File old_file_date_type3 = new File(old_path_type3);
                        if (old_file_date_type3.exists()) {
                            DataBase.renameFile(old_path_type3, new_path_type3);
                        }
                        // Get the client id
                        String client_id = client.my_id;
                        // Restore Objects
                        DataBase.restoreObjects();
                        // Find the right client and open the window with the write client
                        for (Client c : Client.all_clients_list) {
                            if (c.my_id.equals(client_id)) {
                                frame.dispose();
                                w1.dispose();
                                BattariesWin(c);
                            }
                        }
                    }
                    // If DockNum
                    if (listName.equals("DocNum")) {
                        // Get the old paths
                        String old_path_date = path + File.separator + old_name + DataBase.save_type_of_file;
                        String old_path_DocNum = path + File.separator + old_name + "-DocNum" + DataBase.save_type_of_file;
                        String old_path_Work = path + File.separator + old_name + "-Work" + DataBase.save_type_of_file;
                        String old_path_Price = path + File.separator + old_name + "-Price" + DataBase.save_type_of_file;
                        String old_path_Repayment = path + File.separator + old_name + "-Repayment" + DataBase.save_type_of_file;
                        // Get the new paths
                        String new_path_date = path + File.separator + new_date + DataBase.save_type_of_file;
                        String new_path_DocNum = path + File.separator + new_date + "-DocNum" + DataBase.save_type_of_file;
                        String new_path_Work = path + File.separator + new_date + "-Work" + DataBase.save_type_of_file;
                        String new_path_Price = path + File.separator + new_date + "-Price" + DataBase.save_type_of_file;
                        String new_path_Repayment = path + File.separator + new_date + "-Repayment" + DataBase.save_type_of_file;
                        // If file date exist
                        File old_file_date = new File(old_path_date);
                        if (old_file_date.exists()) {
                            DataBase.renameFile(old_path_date, new_path_date);
                        }
                        // If file date_DocNum exist
                        File old_file_date_DocNum = new File(old_path_DocNum);
                        if (old_file_date_DocNum.exists()) {
                            DataBase.renameFile(old_path_DocNum, new_path_DocNum);
                        }
                        // If file date_Work exist
                        File old_file_date_Work = new File(old_path_Work);
                        if (old_file_date_Work.exists()) {
                            DataBase.renameFile(old_path_Work, new_path_Work);
                        }
                        // If file date_Price exist
                        File old_file_date_Price = new File(old_path_Price);
                        if (old_file_date_Price.exists()) {
                            DataBase.renameFile(old_path_Price, new_path_Price);
                        }
                        // If file date_Repayment exist
                        File old_file_date_Repayment = new File(old_path_Repayment);
                        if (old_file_date_Repayment.exists()) {
                            DataBase.renameFile(old_path_Repayment, new_path_Repayment);
                        }
                        // Get the client id
                        String client_id = client.my_id;
                        // Restore Objects
                        DataBase.restoreObjects();
                        // Find the right client and open the window with the write client
                        for (Client c : Client.all_clients_list) {
                            if (c.my_id.equals(client_id)) {
                                frame.dispose();
                                w1.dispose();
                                DocNumWin(c);
                            }
                        }
                    }
                    // If Damage
                    if (listName.equals("Damage")) {
                        // Get the old paths
                        String old_path_date = path + File.separator + old_name + DataBase.save_type_of_file;
                        String old_path_Worker = path + File.separator + old_name + "-Worker" + DataBase.save_type_of_file;
                        String old_path_FixType = path + File.separator + old_name + "-FixType" + DataBase.save_type_of_file;
                        String old_path_DamageType = path + File.separator + old_name + "-DamageType" + DataBase.save_type_of_file;
                        // Get the new paths
                        String new_path_date = path + File.separator + new_date + DataBase.save_type_of_file;
                        String new_path_Worker = path + File.separator + new_date + "-Worker" + DataBase.save_type_of_file;
                        String new_path_FixType = path + File.separator + new_date + "-FixType" + DataBase.save_type_of_file;
                        String new_path_DamageType = path + File.separator + new_date + "-DamageType" + DataBase.save_type_of_file;
                        //System.out.println("path_date" + path_date);
                        // If file date exist
                        File old_file_date = new File(old_path_date);
                        if (old_file_date.exists()) {
                            DataBase.renameFile(old_path_date, new_path_date);
                        }
                        // If file date_Worker exist
                        File old_file_date_Worker = new File(old_path_Worker);
                        if (old_file_date_Worker.exists()) {
                            DataBase.renameFile(old_path_Worker, new_path_Worker);
                        }
                        // If file date_FixType exist
                        File old_file_date_FixType = new File(old_path_FixType);
                        if (old_file_date_FixType.exists()) {
                            DataBase.renameFile(old_path_FixType, new_path_FixType);
                        }
                        // If file date_DamageType exist
                        File old_file_date_DamageType = new File(old_path_DamageType);
                        if (old_file_date_DamageType.exists()) {
                            DataBase.renameFile(old_path_DamageType, new_path_DamageType);
                        }
                        // Get the client id
                        String client_id = client.my_id;
                        // Restore Objects
                        DataBase.restoreObjects();
                        // Find the right client and open the window with the write client
                        for (Client c : Client.all_clients_list) {
                            if (c.my_id.equals(client_id)) {
                                frame.dispose();
                                w1.dispose();
                                DamageWin(c);
                            }
                        }
                    }
                    // If maintence
                    if (listName.equals("maintence")) {
                        // Get the old paths
                        String old_path_date = path + File.separator + old_name + DataBase.save_type_of_file;
                        String old_path_Worker = path + File.separator + old_name + "-Worker" + DataBase.save_type_of_file;
                        String old_path_Comments = path + File.separator + old_name + "-Comments" + DataBase.save_type_of_file;
                        // Get the new paths
                        String new_path_date = path + File.separator + new_date + DataBase.save_type_of_file;
                        String new_path_Worker = path + File.separator + new_date + "-Worker" + DataBase.save_type_of_file;
                        String new_path_Comments = path + File.separator + new_date + "-Comments" + DataBase.save_type_of_file;
                        //System.out.println("path_date" + path_date);
                        // If file date exist
                        File old_file_date = new File(old_path_date);
                        if (old_file_date.exists()) {
                            DataBase.renameFile(old_path_date, new_path_date);
                        }
                        // If file date_Worker exist
                        File old_file_date_Worker = new File(old_path_Worker);
                        if (old_file_date_Worker.exists()) {
                            DataBase.renameFile(old_path_Worker, new_path_Worker);
                        }
                        // If file date_Comments exist
                        File old_file_date_Comments = new File(old_path_Comments);
                        if (old_file_date_Comments.exists()) {
                            DataBase.renameFile(old_path_Comments, new_path_Comments);
                        }
                        // Get the client id
                        String client_id = client.my_id;
                        // Restore Objects
                        DataBase.restoreObjects();
                        // Find the right client and open the window with the write client
                        for (Client c : Client.all_clients_list) {
                            if (c.my_id.equals(client_id)) {
                                frame.dispose();
                                w1.dispose();
                                maintenceWin(c);
                            }
                        }
                    }
                    // If Repairs
                    if (listName.equals("Repairs")) {
                        // Get the old paths
                        String old_path_date = path + File.separator + old_name + DataBase.save_type_of_file;
                        String old_path_Worker = path + File.separator + old_name + "-Worker" + DataBase.save_type_of_file;
                        String old_path_RepairsType = path + File.separator + old_name + "-RepairsType" + DataBase.save_type_of_file;
                        // Get the new paths
                        String new_path_date = path + File.separator + new_date + DataBase.save_type_of_file;
                        String new_path_Worker = path + File.separator + new_date + "-Worker" + DataBase.save_type_of_file;
                        String new_path_RepairsType = path + File.separator + new_date + "-RepairsType" + DataBase.save_type_of_file;
                        //System.out.println("path_date" + path_date);
                        // If file date exist
                        File old_file_date = new File(old_path_date);
                        if (old_file_date.exists()) {
                            DataBase.renameFile(old_path_date, new_path_date);
                        }
                        // If file date_Worker exist
                        File old_file_date_Worker = new File(old_path_Worker);
                        if (old_file_date_Worker.exists()) {
                            DataBase.renameFile(old_path_Worker, new_path_Worker);
                        }
                        // If file date_RepairsType exist
                        File old_file_date_RepairsType = new File(old_path_RepairsType);
                        if (old_file_date_RepairsType.exists()) {
                            DataBase.renameFile(old_path_RepairsType, new_path_RepairsType);
                        }
                        // Get the client id
                        String client_id = client.my_id;
                        // Restore Objects
                        DataBase.restoreObjects();
                        // Find the right client and open the window with the write client
                        for (Client c : Client.all_clients_list) {
                            if (c.my_id.equals(client_id)) {
                                frame.dispose();
                                w1.dispose();
                                RepairsWin(c);
                            }
                        }
                    }
                }
            }
        });
        frame.add(apply);

        // Pack
        frame.pack();

        // Set place ans size
        frame.setBounds(mouseX, mouseY, frame_width, frame_height);

        // Set frame view
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    ////////   Home Window    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Home Page
    public static JFrame window = null;
    public void homeWindow() {

        DataBase.set_paths(); // Set the paths

        // Restote Objects
        try {
            DataBase.restoreAccountFTP(); // Set FTP test Connection
            DataBase.restoreObjects(); // Restore Clients
        } catch (java.lang.StringIndexOutOfBoundsException ie) {

        } catch (java.lang.NullPointerException ne) {

        }

        window = new JFrame("Clients");
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        //window.setResizable(false);
        window.getContentPane().setBackground(color);
        
        // Write all data to DATABASE
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // Set menuBar with
        JMenuBar menuBar = new JMenuBar();
        if (color != null) {
            menuBar.setBackground(Color.LIGHT_GRAY);
        } else {
            menuBar.setBackground(Color.LIGHT_GRAY);
        }
        menuBar.setOpaque(true);
        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Arial", Font.PLAIN, 13));
        // Item 1
        JMenuItem item1 = new JMenuItem("setings");
        item1.setFont(new Font("Arial", Font.PLAIN, 13));
        // Function of Item1
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                settings();
            }
        });
        menu.add(item1);
        /*
        // Item 2
        JMenuItem item2 = new JMenuItem("Help");
        item2.setFont(new Font("Arial", Font.PLAIN, 13));
        // Function of Item2
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                hellp();
            }
        });
        menu.add(item2);
        */
        // Add menu to menuBar and manuBar to frame
        menuBar.add(menu);
        window.setJMenuBar(menuBar);

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        // Counter Label 1
        int count_label_width_1 = frameWidth/9;
        int count_label_height_1 = frameHeight/9;
        int count_label_X_1 = frameWidth/33;
        int count_label_Y_1 = frameHeight/33;
        JLabel count_label_first = new JLabel("Clients:");
        count_label_first.setBounds(count_label_X_1, count_label_Y_1, count_label_width_1, count_label_height_1);
        count_label_first.setFont (textSize);
        count_label_first.setForeground(textColor);
        window.add(count_label_first);

        // Counter Label 2
        int count_label_width_2 = count_label_width_1;
        int count_label_height_2 = count_label_height_1;
        int count_label_X_2 = frameWidth/9;
        int count_label_Y_2 = count_label_Y_1;
        JLabel count_label_second = new JLabel(String.valueOf(Client.id));
        count_label_second.setBounds(count_label_X_2, count_label_Y_2, count_label_width_2, count_label_height_2);
        count_label_second.setFont (textSize);
        count_label_second.setForeground(textColor);
        window.add(count_label_second);
        // Label Street right from searh area
        int label_street_Width = frameWidth/13;
        int label_street_Height = frameHeight/26;
        int label_street_X = (frameWidth/5) + (frameWidth/33);
        int label_street_Y = count_label_Y_1;
        JLabel label_street = new JLabel("Street: ");
        label_street.setForeground(textColor);
        label_street.setFont(textSize);
        label_street.setBounds(label_street_X,label_street_Y,label_street_Width,label_street_Height);
        window.add(label_street);
        // JText Search
        int widthText = frameWidth/3;
        int heightText = frameHeight/26;
        int xT = (frameWidth - (widthText + (frameWidth/135) + (frameWidth/11))) / 2;
        int yT = count_label_Y_1;
        JTextField jtextSearch = new JTextField();
        jtextSearch.setBounds(xT,yT,widthText,heightText);
        window.add(jtextSearch);
        // Button Search
        int buttonSearchWidth = frameWidth/11;
        int buttonSearchHeight = (frameHeight/24)-2;
        int buttonSearchX = xT + widthText + (frameWidth/135);
        int buttonSearchY =  count_label_Y_1;
        JButton buttonSearch = new JButton("Search:");
        buttonSearch.setBounds( buttonSearchX, buttonSearchY, buttonSearchWidth, buttonSearchHeight);
            
        // Action Button
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // Action Button HERE.
                String valueSearch = jtextSearch.getText();
                jtextSearch.setText("");

                // If you write somthing in Search
                if (!valueSearch.equals("")) {

                    // If found somthing
                    boolean found = false;

                    // Here i save fore now data i find with Search button
                    Vector<Vector<String>> myLocalClientList = new Vector<Vector<String>>();
                    Vector<String> myLocalClientListSTREET = new Vector<String>();
                    Vector<String> myLocalClientListLOCATION = new Vector<String>();
                    Vector<String> myLocalClientListTYPE = new Vector<String>();
                    Vector<String> myLocalClientListPHONE = new Vector<String>();
                    Vector<String> myLocalClientListCODE = new Vector<String>();
                    Vector<String> myLocalClientListID = new Vector<String>();

                    for (Client client : Client.all_clients_list) {
                        // If found name
                        if ( (client.street.indexOf(valueSearch) != -1? true : false)  /*             ||
                                        (client.location.indexOf(valueSearch) != -1? true : false)  ||
                                        (client.type.indexOf(valueSearch) != -1? true : false)      ||
                                        (client.phone.indexOf(valueSearch) != -1? true : false)     ||
                                        (client.postallCode.indexOf(valueSearch) != -1? true : false)*/ ) 
                                        {
                            found = true;
                            myLocalClientListSTREET.add(client.street);
                            myLocalClientListLOCATION.add(client.location);
                            myLocalClientListTYPE.add(client.type);
                            myLocalClientListPHONE.add(client.phone);
                            myLocalClientListCODE.add(client.postallCode);
                            myLocalClientListID.add(client.my_id);
                        }
                    }

                    // If my list is NOT EMPTY open the new window and give it
                    if (found) {
                        myLocalClientList.add(myLocalClientListSTREET);
                        myLocalClientList.add(myLocalClientListLOCATION);
                        myLocalClientList.add(myLocalClientListTYPE);
                        myLocalClientList.add(myLocalClientListPHONE);
                        myLocalClientList.add(myLocalClientListCODE);
                        myLocalClientList.add(myLocalClientListID);
                        window.dispose();
                        SearchWindow(myLocalClientList);
                        found = false;
                    }

                    // Clear Vectors
                    myLocalClientListSTREET.clear();
                    myLocalClientListLOCATION.clear();
                    myLocalClientListTYPE.clear();
                    myLocalClientListPHONE.clear();
                    myLocalClientListCODE.clear();
                    myLocalClientListID.clear();
                    myLocalClientList.clear();  
                }
            }
        });
        window.add(buttonSearch);
        
        // If press enter in Search text field buttonSearch pressed
        jtextSearch.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    buttonSearch.doClick();
                }
            }
        });

        // Sizes
        int spaceWidth = frameWidth/45;
        int rigthWidthSpace = (frameWidth/18)/2;
        int y_space = buttonSearchHeight + (frameWidth/33);
        
        int x_scroll = rigthWidthSpace;
        int y_scroll = count_label_Y_1 + y_space;
        int width_scroll = frameWidth - (frameWidth/18);
        int height_scroll = frameHeight-(frameHeight/3);

        // JTable
        // Find the biger Array Size
        int largest_size = Collections.max(  Arrays.asList( Client.streets.size(),
                                                                Client.locations.size(), 
                                                                Client.types.size(), 
                                                                Client.phones.size(),
                                                                Client.postallCodes.size(),
                                                                Client.id_s.size()
                                                            ) );

        // Make the 2D array for the table
        String[] columns = { "Addres & Numbers:", "Locations:", "Types:", "Phones:" , "P.Codes:", "ID:"};
        String[][] data = new String[largest_size][columns.length];

        int i = 0;
        for (String street : Client.streets) {
            data[i][0] = street;
            i++;
        }
        i = 0;
        for (String location : Client.locations) {
            data[i][1] = location;
            i++;
        }
        i = 0;
        for (String type : Client.types) {
            data[i][2] = type;
            i++;
        }
        i = 0;
        for (String phone : Client.phones) {
            data[i][3] = phone;
            i++;
        }
        i = 0;
        for (String postallCode : Client.postallCodes) {
            data[i][4] = postallCode;
            i++;
        }
        i = 0;
        for (String id : Client.id_s) {
            data[i][5] = id;
            i++;
        }

        // JTable sels not or rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable jt = new JTable(model);
        // Set columns width
        jt.getColumnModel().getColumn(0).setPreferredWidth(table_streets_column_width);
        jt.getColumnModel().getColumn(1).setPreferredWidth(table_locations_column_width);
        jt.getColumnModel().getColumn(2).setPreferredWidth(table_types_column_width);
        jt.getColumnModel().getColumn(3).setPreferredWidth(table_phones_column_width);
        jt.getColumnModel().getColumn(4).setPreferredWidth(table_codes_column_width);
        jt.getColumnModel().getColumn(5).setPreferredWidth(table_id_column_width);

        // Set columns font
        JTableHeader header = jt.getTableHeader();
        header.setFont(table_columns_font);
        // Set rows font
        jt.setFont(table_rows_font);
        // Set Rows height
        for (int r = 0; r < data.length; r++) {
            jt.setRowHeight(r, table_rows_height);
        }

        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                try {
                    JTable table = (JTable)evt.getSource();
                    int row = table.getSelectedRow(); // get row
                    int column = table.getSelectedColumn(); // get colum
                    String id = (String)jt.getValueAt(row, 5); // get value of cell in column 5 id

                    if (evt.getClickCount() == 2) { // 1 click detected
                        for (Client client : Client.all_clients_list ) {
                            if (client.my_id.equals(id)) {
                                window.dispose();
                                characteristicsWin(client);
                            }
                        }
                    }
                    // Right click
                    if (SwingUtilities.isRightMouseButton(evt) == true) {
                        
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException ie) {
                    System.err.println("[Error] home table: \n\t" + ie);
                }

            }
        });
    
        JScrollPane sp = new JScrollPane(jt);
        jt.setFont(new Font("Serif", Font.PLAIN, 20));
        jt.setForeground(Color.BLACK);
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.setBounds( x_scroll, y_scroll, width_scroll, height_scroll);
        tablePane.add(sp, BorderLayout.CENTER);
        window.add(tablePane);

        // Button add Client
        int widthButtonAddClient = frameWidth/7;
        int heightButtonAddClient = frameHeight/24;
        int xBA = x_scroll;
        int yBA = frameHeight - (frameHeight/6);
        JButton buttonAdd = new JButton("Add:");
        buttonAdd.setBounds( xBA , yBA , widthButtonAddClient , heightButtonAddClient );
        // Action Button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.dispose();
                addClient();
            }
        });
        window.add(buttonAdd);

        // Button Save
        int xBB = xBA + widthButtonAddClient + (spaceWidth/2);
        int yBB = yBA;
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds( xBB , yBB , widthButtonAddClient , heightButtonAddClient );
        // Action Button
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Get columns width
                TableColumn column_streets = jt.getColumnModel().getColumn(0);
                int table_streets_column_width_new = column_streets.getWidth();
                TableColumn column_locations = jt.getColumnModel().getColumn(1);
                int table_locations_column_width_new = column_locations.getWidth();
                TableColumn column_types = jt.getColumnModel().getColumn(2);
                int table_types_column_width_new = column_types.getWidth();
                TableColumn column_phones = jt.getColumnModel().getColumn(3);
                int table_phones_column_width_new = column_phones.getWidth();
                TableColumn column_codes = jt.getColumnModel().getColumn(4);
                int table_codes_column_width_new = column_codes.getWidth();
                TableColumn column_id = jt.getColumnModel().getColumn(5);
                int table_id_column_width_new = column_id.getWidth();
                boolean check = false;
                if (table_streets_column_width_new != table_streets_column_width) {
                    table_streets_column_width = table_streets_column_width_new;
                    check = true;
                }
                if (table_locations_column_width_new != table_locations_column_width) {
                    table_locations_column_width = table_locations_column_width_new;
                    check = true;
                }
                if (table_types_column_width_new != table_types_column_width) {
                    table_types_column_width = table_types_column_width_new;
                    check = true;
                }
                if (table_phones_column_width_new != table_phones_column_width) {
                    table_phones_column_width = table_phones_column_width_new;
                    check = true;
                }
                if (table_codes_column_width_new != table_codes_column_width) {
                    table_codes_column_width = table_codes_column_width_new;
                    check = true;
                }
                if (table_id_column_width_new != table_id_column_width) {
                    table_id_column_width = table_id_column_width_new;
                    check = true;
                }
                if (check) {
                    DataBase.Save_Home_Table_Cels();
                    DataBase.restart();
                }
            }
        });
        window.add(buttonSave);

        // Button Print
        int XButtonPrint = xBB + widthButtonAddClient + (spaceWidth/2);
        int YButtonPrint = yBA;
        int widthButtonPrint = frameWidth/7;
        int heightButtonPrint = frameHeight/24;
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setBounds(XButtonPrint,YButtonPrint,widthButtonPrint,heightButtonPrint);
        // Action Button
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Printer(jt);
            }
        });
        window.add(buttonPrint);
        

        // Streets alphavetical checkBox
        int checkX1 = XButtonPrint + widthButtonAddClient + (spaceWidth/2);
        int checkY1 = yBA - (frameHeight/23);
        int widthCheck1 = frameWidth/20;
        int heightCheck1 = frameHeight/24;      
        JCheckBox tseckBoxStreets = new JCheckBox("Streets");
        tseckBoxStreets.setBackground(color);
        tseckBoxStreets.setOpaque(false);
        tseckBoxStreets.setForeground(textColor);
        tseckBoxStreets.setFocusPainted( false );
        // Set check box eneble if it is
        tseckBoxStreets.setSelected(DataBase.streets_list_alphavetical_check);
        // Locations alphavetical checkBox
        int checkX2 = checkX1 + (widthCheck1);
        int checkY2 = checkY1;
        int widthCheck2 = frameWidth/17;
        int heightCheck2 = frameHeight/24;      
        JCheckBox tseckBoxLocation = new JCheckBox("Locations");
        tseckBoxLocation.setBackground(color);
        tseckBoxLocation.setOpaque(false);
        tseckBoxLocation.setForeground(textColor);
        tseckBoxLocation.setFocusPainted( false );
        // Set check box eneble if it is
        tseckBoxLocation.setSelected(DataBase.locations_list_alphavetical_check);
        // Action Button Streets
        tseckBoxStreets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                // if ( checked )     
                if ( ((JCheckBox)source).isSelected() ) {
                    DataBase.locations_list_alphavetical_check = false;
                    tseckBoxLocation.setSelected(DataBase.locations_list_alphavetical_check);
                    DataBase.streets_list_alphavetical_check = true;
                } else {
                    DataBase.streets_list_alphavetical_check = false;
                }
            }
        });
        // Action Button Locations
        tseckBoxLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                // if ( checked )
                if ( ((JCheckBox)source).isSelected() ) { 
                    DataBase.streets_list_alphavetical_check = false;
                    tseckBoxStreets.setSelected(DataBase.streets_list_alphavetical_check);
                    DataBase.locations_list_alphavetical_check = true;
                } else {
                    DataBase.locations_list_alphavetical_check = false;
                }
            }
        });
        // Add checkBox Streets
        JPanel mainPanelTseckBoxStreets = new JPanel();
        mainPanelTseckBoxStreets.setBounds(checkX1, checkY1, widthCheck1, heightCheck1);
        mainPanelTseckBoxStreets.setBackground(color);
        mainPanelTseckBoxStreets.add(tseckBoxStreets); 
        window.add(mainPanelTseckBoxStreets);
        // Add checkBox Locations
        JPanel mainPanelTseckBoxLocation = new JPanel();
        mainPanelTseckBoxLocation.setBounds(checkX2, checkY2, widthCheck2, heightCheck2);
        mainPanelTseckBoxLocation.setBackground(color);
        mainPanelTseckBoxLocation.add(tseckBoxLocation); 
        window.add(mainPanelTseckBoxLocation);

        // Button abc
        int X_abc = checkX1;
        int Y_abc = yBA;
        int width_abc = frameWidth/7;
        int height_abc = frameHeight/24;
        JButton button_abc = new JButton("Alphabetical Client Order:");
        button_abc.setBounds( X_abc , Y_abc , width_abc , height_abc );
        // Action Button
        button_abc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DataBase.set_abc_true();
                window.dispose();
                homeWindow();
            }
        });
        window.add(button_abc);

        // Button random
        int X_abc_stop = X_abc + widthButtonAddClient + (spaceWidth/2);;
        int Y_abc_stop = yBA;
        int width_abc_stop = frameWidth/7;
        int height_abc_stop = frameHeight/24;
        JButton button_abc_stop = new JButton("Random Client Order:");
        button_abc_stop.setBounds( X_abc_stop , Y_abc_stop , width_abc_stop , height_abc_stop );
        // Action Button
        button_abc_stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DataBase.set_abc_false();
                window.dispose();
                homeWindow();
            }
        });
        window.add(button_abc_stop);

        // Button UPLOAD
        int X_UPLOAD = X_abc_stop + widthButtonAddClient + (spaceWidth/2) + (spaceWidth);
        int Y_UPLOAD = yBA;
        int width_UPLOAD = (frameWidth/7)/2;
        int height_UPLOAD = frameHeight/24;
        JButton button_UPLOAD = new JButton("UPLOAD");
        button_UPLOAD.setBounds( X_UPLOAD , Y_UPLOAD , width_UPLOAD , height_UPLOAD );
        // Action Button
        button_UPLOAD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for uploading
                Thread thread = new Thread(){
                    public void run(){
                        //Show("UPLOADING...");
                        DataBase.UPLOAD();
                    }
                };
                // Start thread
                thread.start();
            }
        });
        window.add(button_UPLOAD);

        // Button DOWLOAD
        int X_DOWNLOAD = X_UPLOAD + width_UPLOAD + (spaceWidth/2);;
        int Y_DOWNLOAD = yBA;
        int width_DOWNLOAD = (frameWidth/7)/2;
        int height_DOWNLOAD = frameHeight/24;
        JButton button_DOWNLOAD = new JButton("DOWNLOAD");
        button_DOWNLOAD.setBounds( X_DOWNLOAD , Y_DOWNLOAD , width_DOWNLOAD , height_DOWNLOAD );
        // Action Button
        button_DOWNLOAD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for downloading
                Thread thread = new Thread(){
                    public void run(){
                        //Show("DOWNLOADING...");
                        DataBase.DOWNLOAD();
                        window.dispose();
                        homeWindow();
                    }
                };
                // Start thread
                thread.start();
            }
        });
        window.add(button_DOWNLOAD);

        // Pack
        window.pack();

        // Set place and size
        //window.setSize(new Dimension(screenWidth(), screenHeight()));

        // Fullscreen
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set frame view
        window.validate();
        window.repaint();
        window.setVisible(true);

        // If main window run ok delete cloen databse
        DataBase.delete(new File(DataBase.folderData_secure));
    }
   
    public void SearchWindow( Vector<Vector<String>> myList) {

        JFrame frame = new JFrame("");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(color);
        frame.setResizable(false);

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                homeWindow();
            }
        });

        // Frame size
        //int frameWidth = screenWidth();
        int frameHeight = screenHeight();
       
        //frame.setSize(new Dimension(screenWidth(), screenHeight()));


        // Make the 2D array for the table
        String[] columns = { "Addres & Number:", "Location:", "Type:", "Phone:", "Postal Code:", "ID:" };
        String[][] data = new String[myList.get(0).size()][columns.length];
        for (int j = 0; j < columns.length; j++) {
            for ( int i = 0; i < myList.get(j).size(); i++) {
                data[i][j] = myList.get(j).get(i);
            }
        }

        // JTable secl not rewriteble
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };

        JTable jt = new JTable(model);
        // Set the energy if do somthing
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JTable table = (JTable)evt.getSource();
                if (evt.getClickCount() == 2) { // 1 click detected
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    String id = (String)jt.getValueAt(row, 5); // Column 5 == id
                    for (Client client : Client.all_clients_list ) {
                        if (client.my_id.equals(id)) {
                            characteristicsWin(client);
                            frame.dispose();
                        }/*
                        else if (client.location.equals(clientValue)) {
                            characteristicsWin(client);
                            frame.dispose();
                        }
                        else if (client.type.equals(clientValue)) {
                            characteristicsWin(client);
                            frame.dispose();
                        }
                        else if (client.phone.equals(clientValue)) {
                            characteristicsWin(client);
                            frame.dispose();
                        }
                        else if (client.postallCode.equals(clientValue)) {
                            characteristicsWin(client);
                            frame.dispose();
                        }*/
                    }
                }
            }
        });
        
        JScrollPane sp = new JScrollPane(jt);
        jt.setRowHeight( frameHeight/25);
        jt.setFont(new Font("Serif", Font.PLAIN, 17));
        jt.setForeground(Color.LIGHT_GRAY);
        jt.setBackground(color);
        jt.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 15 ));
        jt.setFillsViewportHeight(true);
        JPanel tablePane = new JPanel(new BorderLayout());
        tablePane.add(sp, BorderLayout.CENTER);
        frame.add(tablePane);

        frame.pack();

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Add Client page
    public void addClient() {

        JFrame frame = new JFrame("Add Client");
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(color);

        // Fullscreen
        //frame.setSize(new Dimension(screenWidth(), screenHeight()));

        // If you exit
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                homeWindow();
            }
        });

        // Frame size
        int frameWidth = screenWidth();
        int frameHeight = screenHeight();

        //int labelsNumber = 4;
        int labelsWidth = frameWidth/7;
        int labelsHeight = frameHeight/15;
        int LabelsY = frameHeight/15;

        int x = frameWidth/5;

        // New Client Label
        int labelAddClientX = x;
        int labelAddClientY = frameHeight/7;
        int labelAddClientWidth = frameWidth/3;
        int labelAddClientHeight = frameHeight/10;
        JLabel labelAddClient = new JLabel("New Client:");
        labelAddClient.setFont (new Font("Arial", Font.PLAIN, 70));
        labelAddClient.setForeground(textColor);
        labelAddClient.setBounds(labelAddClientX,labelAddClientY,labelAddClientWidth,labelAddClientHeight);                  
        frame.add(labelAddClient);        
    
        // Street Label
        int labelStreetX = x;
        int labelStreetY = labelAddClientY + (frameHeight/5);
        JLabel labelStreet = new JLabel("Addres & Number:");
        labelStreet.setFont (textSize);
        labelStreet.setForeground(textColor);
        labelStreet.setBounds(labelStreetX,labelStreetY,labelsWidth,labelsHeight);                          
        frame.add(labelStreet);

        // Name Label
        int labelLocationX = x;
        int labelLocationY = labelStreetY + LabelsY; 
        JLabel labelLocation = new JLabel("Location:");
        labelLocation.setFont (textSize);
        labelLocation.setForeground(textColor);
        labelLocation.setBounds(labelLocationX,labelLocationY,labelsWidth,labelsHeight);                     
        frame.add(labelLocation);

        // Mobile Phone Label
        int labelTypeX = x;
        int labelTypeY = labelLocationY + LabelsY;
        JLabel labelType = new JLabel("Type:");
        labelType.setFont (textSize);
        labelType.setForeground(textColor);
        labelType.setBounds(labelTypeX,labelTypeY,labelsWidth,labelsHeight);            
        frame.add(labelType);

        // House Phone Label
        int labelPhoneX = x;
        int labelPhoneY = labelTypeY + LabelsY;
        JLabel labelPhone = new JLabel("Phone:");
        labelPhone.setFont (textSize);
        labelPhone.setForeground(textColor);
        labelPhone.setBounds(labelPhoneX,labelPhoneY,labelsWidth,labelsHeight);
        frame.add(labelPhone);

        // Postal Code Label
        int labelPostalCodeX = x;
        int labelPostalCodeY = labelPhoneY + LabelsY;
        JLabel labelPostalCode = new JLabel("Postal Code:");
        labelPostalCode.setFont (textSize);
        labelPostalCode.setForeground(textColor);
        labelPostalCode.setBounds(labelPostalCodeX,labelPostalCodeY,labelsWidth,labelsHeight);
        frame.add(labelPostalCode);


        ////   TextFields   ////

        int textFieldsWidth = frameWidth/2;

        // Text Field Variable positions
        int textFieldStreetX = (x + labelsWidth);
        int textFieldsStreetY = labelStreetY + (labelsHeight/4);
        int textFieldStreetHeight = labelsHeight/2;
        int textFieldStreetWidth = textFieldsWidth;

        int textFieldLocationX = (x + labelsWidth);
        int textFieldsLocationY = textFieldsStreetY + LabelsY;
        int textFieldLocationHeight = labelsHeight/2;
        int textFieldLocationWidth = textFieldsWidth;

        int textFieldTypeX = (x + labelsWidth);
        int textFieldsTypeY = textFieldsLocationY + LabelsY;
        int textFieldTypeHeight = labelsHeight/2;
        int textFieldTypeWidth = textFieldsWidth;   

        int textFieldPhoneX = (x + labelsWidth);
        int textFieldsPhoneY = textFieldsTypeY + LabelsY;
        int textFieldPhoneHeight = labelsHeight/2;
        int textFieldPhoneWidth = textFieldsWidth;         

        int textFieldPostalCodeX = (x + labelsWidth);
        int textFieldsPostalCodeY = textFieldsPhoneY + LabelsY;
        int textFieldPostalCodeHeight = labelsHeight/2;
        int textFieldPostalCodeWidth = textFieldsWidth;

        // Make the text fields
        JTextField jtextStreet = new JTextField("");
        JTextField jtextLocation = new JTextField("");
        JTextField jtextType = new JTextField("");
        JTextField jtextPhone = new JTextField("");
        JTextField jtextPostallCode = new JTextField("");

        // Position and size of textFields
        jtextStreet.setBounds(textFieldStreetX,textFieldsStreetY,textFieldStreetWidth,textFieldStreetHeight);
        jtextLocation.setBounds(textFieldLocationX,textFieldsLocationY,textFieldLocationWidth,textFieldLocationHeight);
        jtextType.setBounds(textFieldTypeX,textFieldsTypeY,textFieldTypeWidth,textFieldTypeHeight);
        jtextPhone.setBounds(textFieldPhoneX,textFieldsPhoneY,textFieldPhoneWidth,textFieldPhoneHeight);
        jtextPostallCode.setBounds(textFieldPostalCodeX,textFieldsPostalCodeY,textFieldPostalCodeWidth,textFieldPostalCodeHeight);
        
        // Add text fields in frame window
        frame.add(jtextStreet);
        frame.add(jtextLocation);
        frame.add(jtextType);
        frame.add(jtextPhone);
        frame.add(jtextPostallCode);

        // Button add Client
        int widthButtonAddClient = frameWidth/7;
        int heightButtonAddClient = frameHeight/25;
        int xBA = (frameWidth - (frameWidth/3)) + (frameWidth/30);
        int yBA = frameHeight - (frameHeight/5);

        JButton button = new JButton("Add Client:");
        button.setBounds(xBA,yBA,widthButtonAddClient,heightButtonAddClient);
        // Action Button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String street = jtextStreet.getText();
                String location = jtextLocation.getText();
                String type = jtextType.getText();
                String phone = jtextPhone.getText();
                String postalCode = jtextPostallCode.getText();
                if (street.equals("")) {
                    street = "-";
                }
                if (location.equals("")) {
                    location = "-";
                }
                if (type.equals("")) {
                    type = "-";
                }
                if (phone.equals("")) {
                    phone = "-";
                }
                if (postalCode.equals("")) {
                    postalCode = "-";
                }
                if (street.indexOf("&") != -1) {
                    street = street.replace("&"," AND ");
                }
                if (location.indexOf("&") != -1) {
                    location = location.replace("&"," AND ");
                }
                if (type.indexOf("&") != -1) {
                    type = type.replace("&"," AND ");
                }
                if (phone.indexOf("&") != -1) {
                    phone = phone.replace("&"," AND ");
                }
                if (postalCode.indexOf("&") != -1) {
                    postalCode = postalCode.replace("&"," AND ");
                }
                Vector<String> imformations = new Vector<String>();
                imformations.add(street);
                imformations.add(location);
                imformations.add(type);
                imformations.add(phone);
                imformations.add(postalCode);
                DataBase.addClient(imformations);
                frame.dispose();
                homeWindow();
            }
        });
        frame.add(button);

        frame.pack();

        // Fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.validate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Show without close application
    public void print(String somthing) {
        // Make the string html
        String html =   "<html>" +
                "<h2> [Error] </h2>" + "<br>" +
                somthing  +
                "</html>";

        int width = this.screenWidth()-(this.screenWidth()/3);
        int height = this.screenHeight()-(this.screenHeight()/3);
        int x = (this.screenWidth()/2)-(width/2);
        int y = (this.screenHeight()/2)-(height/2);
        JFrame jframe = new JFrame();
        jframe.setLayout(null);
        jframe.setResizable(false);
        jframe.getContentPane().setBackground(Color.BLACK);

        // If you exit
        jframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                jframe.dispose();
            }
        });

        // Label
        int label_width = width - (width/7);
        int label_height = (height/2) + (height/33);
        int label_x = x + (width/7);
        int label_y = y - label_height + (label_height/7);
        JLabel label = new JLabel(html);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Courier New", Font.ITALIC, 30));
        label.setBounds(label_x,label_y,label_width,label_height);
        jframe.add(label);

        jframe.pack();
        jframe.setBounds(x, y, width, height);
        jframe.validate();
        jframe.repaint();
        jframe.setVisible(true);
    }

    // Show a small message in smolle window
    public void show(String somthing) {
        // Make the string html
        String html =   "<html>" +
                            "<h2> [Error] </h2>" + "<br>" +
                             somthing  +
                        "</html>";
        int width = this.screenWidth();
        int height = this.screenHeight()/2;
        int x = (this.screenWidth()/2)-(width/2);
        int y = (this.screenHeight()/2)-(height/2);
        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setResizable(false);
        jframe.getContentPane().setBackground(this.color);
        /*
        // I X Button
        jframe.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        */

        // Panel
        int panel_width = width - (width/7);
        int panel_height = (height/2) + (height/33);
        int panel_x = x + (width/7);
        int panel_y = y - panel_height + (panel_height/7);
        JLabel label = new JLabel(html);
        label.setForeground(this.textColor);
        label.setFont(new Font("Courier New", Font.ITALIC, 30));
        label.setBounds(panel_x,panel_y,panel_width,panel_height);
        jframe.add(label);

        int a_width = width/13;
        int a_height = height/23;
        int a_x = width - (width/3);
        int a_y = height - (height/4);
        JButton button_down = new JButton("DOWNLOAD");
        button_down.setBounds(a_x,a_y,a_width,a_height);
        // Action Button
        button_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Make a sub class thread for downloading
                Thread thread = new Thread(){
                    public void run(){
                        //Show("DOWNLOADING...");
                        DataBase.DOWNLOAD();
                        homeWindow();
                    }
                };
                thread.start();
                jframe.dispose();
            }
        });
        jframe.add(button_down);

        int b_width = width/13;
        int b_height = height/23;
        int b_x = width - (width/4);
        int b_y = height - (height/4);
        JButton button = new JButton("Settings:");
        button.setBounds(b_x,b_y,b_width,b_height);
        // Action Button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jframe.dispose();
                settings();
            }
        });
        jframe.add(button);

        jframe.pack();
        jframe.setBounds(x, y, width, height);
        jframe.setVisible(true);
    }

    // Show a small message in smolle window
    private static JFrame jframe_show;
    public static void Show(String somthing) {
        // Make the string html
        String html =   "<html> <br><br> <h2>" + somthing  + "</h2> </html>";
        int width = new WindowsData().screenWidth()/3;
        int height = new WindowsData().screenHeight()/3;
        int x = (new WindowsData().screenWidth()/2)-(width/2);
        int y = (new WindowsData().screenHeight()/2)-(height/2);
        jframe_show = new JFrame();
        // Remove start bar with x button
        jframe_show.setUndecorated(true);
        jframe_show.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        jframe_show.setLayout(null);
        jframe_show.setResizable(false);
        jframe_show.getContentPane().setBackground( new Color(0, 6, 84) ); // new Color(32, 32, 32)   GRAY

        // I X Button
        jframe_show.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

            }
        });

        // Label
        int label_width = new WindowsData().screenWidth() / 5;
        int label_height = new WindowsData().screenHeight() / 9;
        int label_x = (width/2) - (label_width/2) + (label_width / 13);
        int label_y = 1;//height/3;

        if ( somthing.contains("ERROR")  ) {
            JLabel label = new JLabel(html);
            label.setForeground(Color.RED);
            label.setFont(new Font("Courier New", Font.ITALIC, 30));
            label.setBounds(label_x, label_y, label_width, label_height);
            jframe_show.add(label);
        } else {
            JLabel label = new JLabel(html);
            label.setForeground(Color.GREEN);
            label.setFont(new Font("Courier New", Font.ITALIC, 30));
            label.setBounds(label_x, label_y, label_width, label_height);
            jframe_show.add(label);
        }

        // Button
        int button_width = new WindowsData().screenWidth() / 15;
        int button_height = new WindowsData().screenHeight() / 33;
        int button_x = (width / 2) + (label_width / 7);
        int button_y = height/2;
        if ( !somthing.contains("DOWNLOADING") && !somthing.contains("UPLOADING") && somthing.contains("COPING") && somthing.contains("RENAMING") && somthing.contains("CONNECTING...") ) {
            JButton button = new JButton("Continue");
            button.setBounds(button_x, button_y, button_width, button_height);
            // Action Button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    jframe_show.dispose();
                }
            });
            jframe_show.add(button);
        }

        jframe_show.pack();
        jframe_show.setBounds(x, y, width, height);
        jframe_show.validate();
        jframe_show.repaint();
        jframe_show.setVisible(true);
    }

    // Show windows close
    public static void Show_Close() {
        jframe_show.dispose();
    }

    // Screen Sizes
    public int screenWidth() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return size.width;
    }
    public int screenHeight() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return size.height;
    }

}