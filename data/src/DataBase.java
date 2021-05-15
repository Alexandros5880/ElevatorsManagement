package com.data;

import com.window.WindowsData;

import java.lang.Math;

import java.io.IOException;
import java.io.File;
import java.io.FilenameFilter;

import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.Vector;
import java.util.Collections;
import java.util.HashSet;

import java.lang.Thread;

public class DataBase {

    public static String name_database = "D";
    public static String name_clone_database = "D2";
    public static String save_type_of_file = ".sql";
    public static String copy_database_path = "";
    public static String myDirectory = "";
    public static String folderData = "";
    public static String folderData_secure = "";
    public static String edit_data = "";
    public static String abc_file = "";
    public static String path_file = "";
    public static String path_name_database_file = "";
    public static String home_table_columns_W_data = "";
    public static String maintence_table_columns_W_data = "";
    public static String repairs_table_columns_W_data = "";
    public static String damages_table_columns_W_data = "";
    public static String battaries_table_columns_W_data = "";
    public static String docnum_table_columns_W_data = "";
    // Text Size database
    public static String text_size = "";
    // Text color database
    public static String text_color = "";
    // Background color database
    public static String back_color = "";
    // FTP Data database
    public static String ftp_imfo = "";
    // Alphaveticals orders
    public static boolean check_abc = false;
    public static boolean streets_list_alphavetical_check = false;
    public static boolean locations_list_alphavetical_check = false;
    public static boolean set_default_path = false;
    public static String file_path = "";
    // FTP Object
    public static FTP_Client ftp;

    // Set the data path
    public static void set_paths() {
        // First get from data the name of the DataBase folder and it's clone
        DataBase.myDirectory = System.getProperty("user.dir");

        // Set Paths if is outside of directory [Clients.jar] data or instade [make]
        String diractory = DataBase.myDirectory;
        //System.out.println("[DIRECTORY BEFORE] " + diractory); // !!!!
        if ( !diractory.contains("data") ) {
            diractory += File.separator + "data";
        }
        //System.out.println("[DIRECTORY AFTER] " + diractory);

        DataBase.myDirectory = diractory;
        DataBase.path_name_database_file = DataBase.myDirectory + File.separator + "edit_data" + File.separator + "name_database_file" + DataBase.save_type_of_file;
        // Read the name of my dataBase
        String new_name_database = DataBase.read(path_name_database_file);
        if ( !new_name_database.equals("") ) {
            DataBase.name_database = new_name_database;
        }
        DataBase.folderData = DataBase.myDirectory + File.separator + DataBase.name_database + File.separator;
        DataBase.folderData_secure = DataBase.myDirectory + File.separator + DataBase.name_clone_database + File.separator;
        DataBase.edit_data = DataBase.myDirectory + File.separator + "edit_data" + File.separator;
        DataBase.abc_file = DataBase.edit_data + "abc" + DataBase.save_type_of_file;
        DataBase.path_file = DataBase.edit_data + "path" + DataBase.save_type_of_file;
        DataBase.home_table_columns_W_data = DataBase.edit_data + "home_data" + DataBase.save_type_of_file;
        DataBase.maintence_table_columns_W_data = DataBase.edit_data + "maintence_data" + DataBase.save_type_of_file;
        DataBase.repairs_table_columns_W_data = DataBase.edit_data + "repairs_data" + DataBase.save_type_of_file;
        DataBase.damages_table_columns_W_data = DataBase.edit_data + "damages_data" + DataBase.save_type_of_file;
        DataBase.battaries_table_columns_W_data = DataBase.edit_data + "battaries_data" + DataBase.save_type_of_file;
        DataBase.docnum_table_columns_W_data = DataBase.edit_data + "docnum_data" + DataBase.save_type_of_file;
        DataBase.text_size = DataBase.edit_data + "text_size" + DataBase.save_type_of_file;
        DataBase.text_color = DataBase.edit_data + "text_color" + DataBase.save_type_of_file;
        DataBase.back_color = DataBase.edit_data + "back_color" + DataBase.save_type_of_file;
        DataBase.ftp_imfo = DataBase.edit_data + "ftp_data" + DataBase.save_type_of_file;
    }

    // Set the home table sizes
    public static void Save_Home_Table_Cels() {
        Vector<String> data = new Vector<String>(5);
        data.add(Integer.toString(WindowsData.table_streets_column_width));
        data.add(Integer.toString(WindowsData.table_locations_column_width));
        data.add(Integer.toString(WindowsData.table_types_column_width));
        data.add(Integer.toString(WindowsData.table_phones_column_width));
        data.add(Integer.toString(WindowsData.table_codes_column_width));
        data.add(Integer.toString(WindowsData.table_id_column_width));
        write(home_table_columns_W_data, data);
    }

    // Set the maintence table sizes
    public static void Save_Maintence_Table_Cels() {
        Vector<String> data = new Vector<String>(3);
        data.add(Integer.toString(WindowsData.maintence_table_dates_column_width));
        data.add(Integer.toString(WindowsData.maintence_table_workers_column_width));
        data.add(Integer.toString(WindowsData.maintence_table_comments_column_width));
        write(maintence_table_columns_W_data, data);
    }

    // Set the repairs table sizes
    public static void Save_Repairs_Table_Cels() {
        Vector<String> data = new Vector<String>(3);
        data.add(Integer.toString(WindowsData.repairs_table_dates_column_width));
        data.add(Integer.toString(WindowsData.repairs_table_workers_column_width));
        data.add(Integer.toString(WindowsData.repairs_table_RepairsTypes_column_width));
        write(repairs_table_columns_W_data, data);
    }

    // Set the battaries table sizes
    public static void Save_Battaries_Table_Cels() {
        Vector<String> data = new Vector<String>(3);
        data.add(Integer.toString(WindowsData.battaries_table_dates_column_width));
        data.add(Integer.toString(WindowsData.battaries_table_type1_column_width));
        data.add(Integer.toString(WindowsData.battaries_table_type2_column_width));
        data.add(Integer.toString(WindowsData.battaries_table_type3_column_width));
        write(battaries_table_columns_W_data, data);
    }

    // Set the DocNum table sizes
    public static void Save_DocNum_Table_Cels() {
        Vector<String> data = new Vector<String>(3);
        data.add(Integer.toString(WindowsData.DocNum_table_dates_column_width));
        data.add(Integer.toString(WindowsData.DocNum_table_DocNum_column_width));
        data.add(Integer.toString(WindowsData.DocNum_table_Work_column_width));
        data.add(Integer.toString(WindowsData.DocNum_table_Price_column_width));
        data.add(Integer.toString(WindowsData.DocNum_table_Repayment_column_width));
        write(docnum_table_columns_W_data, data);
    }

    // Set the damages table sizes
    public static void Save_Damages_Table_Cels() {
        Vector<String> data = new Vector<String>(3);
        data.add(Integer.toString(WindowsData.damages_table_dates_column_width));
        data.add(Integer.toString(WindowsData.damages_table_workers_column_width));
        data.add(Integer.toString(WindowsData.damages_table_DamageTypes_column_width));
        data.add(Integer.toString(WindowsData.damages_table_FixTypes_column_width));
        write(damages_table_columns_W_data, data);
    }

    ////////////////////////////////////////////  FTP  /////////////////////////////////////////////////////////////////
    // Set client data to FTP and in project
    public static void setAccountFTP(String addres, int port, String user_name, String password, String ftp_data_dir) {
        ftp = new FTP_Client(addres, port, user_name, password, ftp_data_dir);
        Vector<String> list = new Vector<String>(5);
        list.add(addres);
        list.add(String.valueOf(port));
        list.add(user_name);
        list.add(password);
        list.add(ftp_data_dir);
        DataBase.write(DataBase.ftp_imfo,list);
    }

    // Restore Acount FTP
    public static void restoreAccountFTP() {
        String imfo = DataBase.read(DataBase.ftp_imfo);
        if ( !imfo.isEmpty() ) {
            String[] lines = imfo.split("\n");
            ftp = new FTP_Client(lines[0], Integer.parseInt(lines[1]), lines[2], lines[3], lines[4]);
        }
    }

    // Get FTP URL
    public static String getURL() {
        return ftp.server;
    }

    // Get FTP PORT
    public static String getPORT() {
        return String.valueOf(ftp.port);
    }

    // Get FTP USERNAME
    public static String getUSERNAME() {
        return ftp.user_name;
    }

    // Get FTP PASSWORD
    public static String getPASSWORD() {
        return ftp.password;
    }

    // Get FTP PATH
    public static String getPATH() {
        return ftp.path_ftp;
    }

    // Check Connection FTP
    public static boolean checkConnectionFTP() {
        return ftp.connect_login();
    }

    // FTP UPLOAD database
    public static void UPLOAD() {
        if ( ftp.connect_login() ) {
            try {
                WindowsData.Show("UPLOADING...");
                try {
                    ftp.delete_directory(ftp.path_ftp + "/" + DataBase.name_database);
                } catch (java.lang.NullPointerException mpe) {

                }
                System.out.println("[FTP PATH , FOLDER_DATA , NAME_DATABASE] " + ftp.path_ftp + " " + DataBase.folderData + " " + DataBase.name_database);
                ftp.uploadFiles(ftp.path_ftp, DataBase.folderData, DataBase.name_database);
                ftp.desconect();
                WindowsData.Show_Close();
            } catch (Exception e) {
                WindowsData.Show_Close();
                WindowsData.Show("[ERROR UPLOADING]");
                try { Thread.sleep(3000); } catch (InterruptedException ie) { }
                WindowsData.Show_Close();
            }
        } else {
            WindowsData.Show("[ERROR CONNECTING]");
        }
    }

    // FTP DOWNLOAD database
    public static void DOWNLOAD() {
        if ( ftp.connect_login() ) {
            new WindowsData().Show("DOWNLOADING...");
            // Copy loacal database
            File sourceFolder = new File(DataBase.folderData);
            File destinationFolder = new File(DataBase.folderData_secure);
            DataBase.copyFolder(sourceFolder, destinationFolder);
            // Delete Directory
            DataBase.delete(new File(DataBase.folderData));
            // Download files
            ftp.downloadFiles(ftp.path_ftp + "/" + DataBase.name_database, DataBase.myDirectory);
            ftp.desconect();
            WindowsData.Show_Close();
        } else {
            WindowsData.Show("[ERROR CONNECTING]");
        }
    }

    // Rename DIR FTP
    public static boolean renameDirFtp(String old_name, String new_name) {
        boolean saccess = false;
        if ( ftp.connect_login() ) {
            ftp.renameFTP(ftp.path_ftp + "/" + old_name, ftp.path_ftp + "/" + new_name);
            saccess = true;
        }
        return saccess;
    }

    ////////////////////////////////////////////  FTP STOP  ////////////////////////////////////////////////////////////

    // Copy DataBase to my DataBase
    public static boolean getDataBase(String new_data_base_path) {
        if ( new File(new_data_base_path).exists() && (!new_data_base_path.isEmpty()) ) {
            // Copy loacal database
            File sourceFolder = new File(new_data_base_path);
            File destinationFolder = new File(DataBase.folderData);
            if ( destinationFolder.exists() ) {
                // Delete Directory
                DataBase.delete(new File(DataBase.folderData));
            }
            DataBase.copyFolder(sourceFolder, destinationFolder);
            return true;
        } else {
            return false;
        }
    }

    // Copy Directory
    public static void copyFolder(File sourceFolder, File destinationFolder) {
        try {
            // IF DIRECTORY
            if ( sourceFolder.isDirectory() ) {
                // Creaty directory if not exist
                destinationFolder.mkdir();
                // Get all files from source directory
                String files[] = sourceFolder.list();
                // Iterate over all files and copy them to destinationFolder one by one
                for (String file : files) {
                    File srcFile = new File(sourceFolder, file);
                    File destFile = new File(destinationFolder, file);
                    //Recursive function call
                    copyFolder(srcFile, destFile);
                }
            } else { // IF FILE
                // Copy the file content from one place to another
                Files.copy(sourceFolder.toPath(), destinationFolder.toPath());
            }
        } catch (IOException ioe) {
            System.err.println("[ERROR COPING DIRECTORY]");
        }
    }

    // List with all Directories and Files
    public static void TreeFiles(String path) {
        try {
            File folder = new File(path);
            if ( folder.isDirectory() ) { // FOLDER
                System.out.println("[FOLDER]: " + path);
                String[] listOfFiles = folder.list();
                for (String file : listOfFiles) {
                    String inside_path = path + File.separator + file;
                    TreeFiles(inside_path);
                }
            } else { // FILE
                System.out.println("[FILE]: " + path);
            }
        } catch (Exception e) {
            System.err.println("[ERROR] " + e);
        }
    }

    // File exist test
    public static boolean Exists(String path) {
        File tmpDir = new File(path);
        return tmpDir.exists();
    }

    // Write
    public static void write(String name, Vector<String> data) {
        String all_data = new String();
        for (String str : data) {
            all_data += str + "\n";
        }
        try {
            if ( !name.contains(DataBase.save_type_of_file) ) {
                name += DataBase.save_type_of_file;
            }
            Path path = Paths.get(name);
            Charset charset = StandardCharsets.UTF_8;
            Files.write(path, all_data.getBytes(charset));
        }catch(IOException e) {
            //System.err.println("[Error] DataBase.write \n\t" + e);
        }
    }

    // Write
    public static void write2(String my_path, String data) throws NullPointerException {
        try{
            if ( !my_path.contains(DataBase.save_type_of_file) ) {
                my_path += DataBase.save_type_of_file;
            }
            Path path = Paths.get(my_path);
            Charset charset = StandardCharsets.UTF_8;
            Files.write(path, data.getBytes(charset));
        }catch(IOException e) {
            //System.err.println("[Error] can't write to file.\n\t" + e);
        }
    }

    // Replace
    public static void replace(String id, String before, String after) {
        try {
            String str_path = folderData + id + File.separator + "imformations"+DataBase.save_type_of_file;
            //System.out.println(str_path);
            Path path = Paths.get(str_path);
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(before, after);
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            //System.err.println("[Error]: " + e);
        }
    }

    // Read
    public static String read(String name) {
        if ( !name.contains(DataBase.save_type_of_file) ) {
            name += DataBase.save_type_of_file;
        }
        String data = new String();
        try {
            data = String.join("\n", Files.readAllLines(Paths.get(name), StandardCharsets.UTF_8));
        } catch (IOException e) {
            //System.out.println("[Error] reading");
        }
        return data;
    }

    // Rename Dir
    public static void renameDir(String oldName, String newName, String path) {
        String oldFile = path + oldName;
        File dir = new File(oldFile);
        if ( !dir.isDirectory() ) {
            System.err.println("There is no directory @ given path");
        } else {
            File newDir = new File(dir.getParent() + File.separator + newName);
            if ( dir.renameTo(newDir) ) {
                //System.out.println("Rename Ok from: " + oldName + "   to: " + newName);
            }
        }
    }

    // Set the write Id in folders Name
    public static void update_id() {
        // Get all files in this directory  (clients id in D)
        File file = new File(folderData);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        // Sort Directories
        Vector<Integer> directories_int = new Vector<Integer>();
        for (String d : directories) {
            directories_int.add(Integer.parseInt(d));
        }
        Collections.sort(directories_int);
        for (int i = 0; i < directories_int.size(); i++) {
            directories[i] = String.valueOf(directories_int.get(i));
        }
        // Rename the folder with the new ID
        for (int i = 0; i < directories.length; i++) {
            //System.out.println("directories["+String.valueOf(i)+"]: " + directories[i]);
            String oldName = directories[i];
            String newName = String.valueOf(i);
            renameDir(oldName,newName,folderData);
        }
        // Get all files in this directory  (clients id in D)  new
        String[] directories_new = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        // Rename the data inside the imformations.sql
        Vector<String> data = new Vector<String>();
        for (int i = 0; i < directories.length; i++) {
            data.add( Client.all_clients_list.get(i).street ); // street
            data.add( Client.all_clients_list.get(i).location ); // location
            data.add( Client.all_clients_list.get(i).type ); // type
            data.add( Client.all_clients_list.get(i).phone ); // phone
            data.add( Client.all_clients_list.get(i).postallCode ); // p.code
            Client.all_clients_list.get(i).my_id = String.valueOf(i); // id
            data.add( Client.all_clients_list.get(i).my_id ); // id
            String path = folderData + String.valueOf(i) + File.separator + "imformations" + DataBase.save_type_of_file;
            DataBase.write(path, data);
            data.clear();
            //System.out.println("Rename this path: " + path);
        }

    }

    // Delete directory Client
    public static void deleteClient(Client client) {
        // Delete from dataBase
        String path = folderData + client.my_id;
        File directory = new File(path);
        if ( delete(directory) ) {
            // Becose after that we call homeWindow and home window has the function restoreObjects here we have comment te function
            //restoreObjects();
        }
    }

    // Delete file
    public static boolean delete(File directory) {
        boolean check = false;
        if ( directory.isFile() ) { // == File
            if ( directory.delete() ) { // Edw einai to delete file
                check = true;
            } else {
                check = false;
            }
        } else if ( directory.isDirectory() ) { // == Directory
            // Delete all files in directory and next delete directory
            File[] paths = directory.listFiles();
            for (File f : paths) {
                delete(f);
            }
            if ( directory.delete() ) { // Edw einai delete directory
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    // Rename a file
    public static boolean renameFile(String oldPath, String newPath) {
        File oldfile = new File(oldPath);
        File newfile = new File(newPath);
        boolean check = oldfile.renameTo(newfile);
        delete(oldfile);
        return check;
    }

    // Make folder
    public static boolean makeDir(String pathName) throws NullPointerException {
        File file = new File(pathName);
        return file.mkdir();
    }

    // Set the list with order of tha table
    // Set Bataries list with data[][] order
    public static void setLists(String[][] data, String nameWindow, Client client) {
        if (nameWindow.equals("BattariesWin")) {
            client.battaries_dates.clear();
            client.battaries_type1.clear();
            client.battaries_type2.clear();
            client.battaries_type3.clear();
            for (int r = 0; r < data.length; r++) {
                client.battaries_dates.add(data[r][0]);
                client.battaries_type1.add(data[r][1]);
                client.battaries_type2.add(data[r][2]);
                client.battaries_type3.add(data[r][3]);
            }
        }
        if (nameWindow.equals("DocNumWin")) {
            client.DocNum_dates.clear();
            client.DocNum_docnum.clear();
            client.DocNum_Work.clear();
            client.DocNum_Price.clear();
            client.DocNum_Repayment.clear();
            for (int r = 0; r < data.length; r++) {
                client.DocNum_dates.add(data[r][0]);
                client.DocNum_docnum.add(data[r][1]);
                client.DocNum_Work.add(data[r][2]);
                client.DocNum_Price.add(data[r][3]);
                client.DocNum_Repayment.add(data[r][4]);
            }
        }
        if (nameWindow.equals("DamageWin")) {
            client.damage_dates.clear();
            client.damage_worker.clear();
            client.damage_damage_type.clear();
            client.damage_fix_type.clear();
            for (int r = 0; r < data.length; r++) {
                client.damage_dates.add(data[r][0]);
                client.damage_worker.add(data[r][1]);
                client.damage_damage_type.add(data[r][2]);
                client.damage_fix_type.add(data[r][3]);
            }
        }
        if (nameWindow.equals("RepairsWin")) {
            client.repairs_dates.clear();
            client.repairs_worker.clear();
            client.repairs_repair_type.clear();
            for (int r = 0; r < data.length; r++) {
                client.repairs_dates.add(data[r][0]);
                client.repairs_worker.add(data[r][1]);
                client.repairs_repair_type.add(data[r][2]);
            }
        }
        if (nameWindow.equals("maintenceWin")) {
            client.maintence_dates.clear();
            client.maintence_worker.clear();
            client.maintence_comments.clear();
            for (int r = 0; r < data.length; r++) {
                client.maintence_dates.add(data[r][0]);
                client.maintence_worker.add(data[r][1]);
                client.maintence_comments.add(data[r][2]);
            }
        }
    }

    // Ordered a data[][0] (dates) and sychronize the other lists
    public static void setOrder(String data[][]) {
        try {
            // Get number of columns
            int num_columns = data[0].length;
            // Make tow list one with only dates and one with all rows
            Vector<String> dates = new Vector<String>();
            Vector<String> rows = new Vector<String>();
            String row = new String();
            for (int r = 0; r < data.length; r++) { // Rows
                dates.add(data[r][0]);
                for (int c = 0; c < data[r].length; c++) { // Columns
                    row += data[r][c] + "&";
                }
                rows.add(row);
                row = "";
            }
            // Get only the years from the dates
            Vector<Integer> years = new Vector<Integer>();
            for (String date : dates) {
                String[] d = date.split("-");
                years.add(Integer.parseInt(d[2]));
            }
            // Remove dublicates from years
            HashSet<Integer> years_no_dublicates = new HashSet<Integer>(years);
            years.clear();
            for (Integer year : years_no_dublicates) {
                years.add(year);
            }
            // Years sort
            Collections.sort(years);
            // Years reverce
            Collections.reverse(years);
            // Copy dates list only for the categories
            Vector<String> new_dates = new Vector<String>();
            for (String date : dates) {
                new_dates.add(date);
            }
            // Make list with dates how has tha same year with order the bigest first
            Vector<Vector<String>> dates_years = new Vector<Vector<String>>();
            int count = -1;
            int count2 = 0;
            for (Integer year : years) {
                count++;
                dates_years.add(new Vector<String>());
                String y = String.valueOf(year);
                while (count2 < new_dates.size()) {
                    if (new_dates.get(count2).indexOf(y) != -1) {
                        dates_years.get(count).add(new_dates.get(count2));
                    }
                    count2++;
                }
                count2 = 0;
            }
            // Make list with months in every date_year list
            Vector<Vector<Integer>> months_in_dates_years = new Vector<Vector<Integer>>();
            count = -1;
            for (Vector<String> dy : dates_years) {
                count++;
                months_in_dates_years.add(new Vector<Integer>());
                for (String date : dy) {
                    String[] nums = date.split("-");
                    String month = nums[1];
                    months_in_dates_years.get(count).add(Integer.parseInt(month));
                }
            }
            // Remove dublicates and set with order biger month in months_in_dates_years
            for (Vector<Integer> mdy : months_in_dates_years) {
                // Remove dublicates from years
                HashSet<Integer> months_no_dublicates = new HashSet<Integer>(mdy);
                mdy.clear();
                for (Integer month : months_no_dublicates) {
                    mdy.add(month);
                }
                // Clear months_no_dublicates
                months_no_dublicates.clear();
                // mdy sort
                Collections.sort(mdy);
                // mdy reverce
                Collections.reverse(mdy);
            }
            // Make order with months_in_dates_years
            Vector<Vector<String>> dates_years_months = new Vector<Vector<String>>();
            int count_y_m = -1;
            count2 = 0;
            for (Vector<Integer> months : months_in_dates_years) {
                dates_years_months.add(new Vector<String>());
                count_y_m++;
                for (int i = 0; i < months.size(); i++) {
                    String month = String.valueOf(months.get(i));
                    while (count2 < dates_years.get(count_y_m).size()) {
                        String[] values = dates_years.get(count_y_m).get(count2).split("-");
                        if (values[1].equals(month)) {
                            dates_years_months.get(count_y_m).add(dates_years.get(count_y_m).get(count2));
                        }
                        count2++;
                    }
                    count2 = 0;
                }
            }
            // Days !!!
            // Make a list with tha same months
            Vector<Vector<Vector<String>>> same_months = new Vector<Vector<Vector<String>>>();
            int count_months_order = -1;
            for (Vector<String> months_order : dates_years_months) {
                same_months.add(new Vector<Vector<String>>());
                count_months_order++;
                int month_now = -1;
                int month_before = -1;
                int count_same_months = -1;
                for (String date_month : months_order) {
                    String[] date = date_month.split("-");
                    month_now = Integer.parseInt(date[1]);
                    if (month_now != month_before) {
                        month_before = month_now;
                        same_months.get(count_months_order).add(new Vector<String>());
                        count_same_months++;
                    }
                    same_months.get(count_months_order).get(count_same_months).add(date_month);
                }
            }
            // Make list with days in every date_year_months list
            Vector<Vector<Vector<Integer>>> days_months_in_dates_years = new Vector<Vector<Vector<Integer>>>();
            int count_dates_months = -1;
            int day = -1;
            for (Vector<Vector<String>> same_month : same_months) {
                days_months_in_dates_years.add(new Vector<Vector<Integer>>());
                count_dates_months++;
                int count_same_months = -1;
                for (Vector<String> sam_month : same_month) {
                    days_months_in_dates_years.get(count_dates_months).add(new Vector<Integer>());
                    count_same_months++;
                    for (String date : sam_month) {
                        String[] value = date.split("-");
                        day = Integer.parseInt(value[0]);
                        days_months_in_dates_years.get(count_dates_months).get(count_same_months).add(day);
                    }
                    // Remove dublicates from days_months_in_dates_years
                    HashSet<Integer> days_months_no_dublicates = new HashSet<Integer>(days_months_in_dates_years.get(count_dates_months).get(count_same_months));
                    days_months_in_dates_years.get(count_dates_months).get(count_same_months).clear();
                    for (Integer d : days_months_no_dublicates) {
                        days_months_in_dates_years.get(count_dates_months).get(count_same_months).add(d);
                    }
                    // Clear days_no_dublicates
                    days_months_no_dublicates.clear();
                    // days_order sort
                    Collections.sort(days_months_in_dates_years.get(count_dates_months).get(count_same_months));
                    // days_order reverce
                    Collections.reverse(days_months_in_dates_years.get(count_dates_months).get(count_same_months));
                }
            }
            // Make order with dates_years_months_days
            Vector<Vector<String>> rady_dates = new Vector<Vector<String>>();
            for (int c_y = 0; c_y < days_months_in_dates_years.size(); c_y++) {
                rady_dates.add(new Vector<String>());
                int count_dates_month = -1;
                for (int c_m = 0; c_m < days_months_in_dates_years.get(c_y).size(); c_m++) {
                    for (int c_d = 0; c_d < days_months_in_dates_years.get(c_y).get(c_m).size(); c_d++) {
                        String d = String.valueOf(days_months_in_dates_years.get(c_y).get(c_m).get(c_d));
                        int counter = 0;
                        while (counter < same_months.get(c_y).get(c_m).size()) { // In list with same months
                            String[] date = same_months.get(c_y).get(c_m).get(counter).split("-");
                            String day_in_same_months = date[0];
                            if (day_in_same_months.equals(d)) {
                                rady_dates.get(c_y).add(same_months.get(c_y).get(c_m).get(counter));
                            }
                            counter++;
                        }
                    }
                }
            }
            // Clear dates
            dates.clear();
            // Dates add rady Years
            for (Vector<String> year : rady_dates) {
                for (String date : year) {
                    dates.add(date);
                }
            }
            // Make clone list of rows
            Vector<String> new_rows = new Vector<String>();
            for (String r : rows) {
                new_rows.add(r);
            }
            // Set data rows with the order of dates
            rows.clear();
            for (String date : dates) {
                int count3 = 0;
                while (new_rows.size() > 0) {
                    if (new_rows.get(count3).indexOf(date) != -1) {
                        rows.add(new_rows.get(count3));
                        new_rows.remove(count3);
                        break;
                    }
                    count3++;
                }
            }
            // Set rows and columns in new Vector Example: new_data = [[dates],[Workers],[Fix_Types],[Damage_Types]]
            Vector<Vector<String>> new_data = new Vector<Vector<String>>(num_columns);
            for (int c = 0; c < num_columns; c++) {
                new_data.add(new Vector<String>());
            }
            for (String r : rows) {
                String[] colms = r.split("&");
                for (int i = 0; i < colms.length; i++) {
                    new_data.get(i).add(colms[i]);
                }
            }
            // Set the data for table
            for (int c = 0; c < new_data.size(); c++) {
                for (int r = 0; r < new_data.get(c).size(); r++) {
                    data[r][c] = new_data.get(c).get(r);
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException aie) {

        } catch (java.lang.NumberFormatException fe) {

        }
    }

    // Make a list with list with the selected list order
    public static void sort_Ordered(Vector<Vector<String>> list, int selected_list) {
        // Creating a list tha has the selected list that would be made sort
        Vector<String> ordered_list = new Vector<String>();
        for (String l : list.get(selected_list)) {
            ordered_list.add(l);
        }
        // Sort the list
        Collections.sort(ordered_list);
        // Get the lines
        Vector<String> lines = new Vector<String>();
        for (int l_count = 0; l_count < list.get(selected_list).size(); l_count++) {
            String line = new String();
            for (int r_count = 0; r_count < list.size(); r_count++) {
                line += list.get(r_count).get(l_count) + "&";
            }
            lines.add(line);
        }
        // Make the new lines with the ordered stile
        Vector<String> new_lines = new Vector<String>();
        for ( String ord : ordered_list ) {
            int counter = 0;
            while ( counter < lines.size() ) {
                String[] values = lines.get(counter).split("&");
                String find_value = values[selected_list];
                if ( find_value.equals(ord) ) {
                    new_lines.add(lines.get(counter));
                    lines.remove(counter);
                    counter = -1;
                }
                counter++;
            }
        }
        // Clear the lists
        for (Vector<String> l : list) {
            l.clear();
        }
        // Order the list the same with new_lines
        for ( int l = 0; l < new_lines.size(); l++ ) {
            String[] values = new_lines.get(l).split("&");
            for ( int i = 0; i < values.length; i++ ) {
                list.get(i).add(values[i]);
            }
        }
    }

    // Set check_abc true;
    public static void set_abc_true() {
        check_abc = true;
        Vector<String> data = new Vector<String>(3);
        data.add("true");
        if (streets_list_alphavetical_check) {
            data.add("true");
            //Collections.sort(Client.streets);
            Vector<Vector<String>> list = new Vector<Vector<String>>();
            list.add(Client.streets);
            list.add(Client.locations);
            list.add(Client.types);
            list.add(Client.phones);
            list.add(Client.postallCodes);
            list.add(Client.id_s);
            sort_Ordered(list, 0);
        } else if (!streets_list_alphavetical_check) {
            data.add("false");
        }
        if (locations_list_alphavetical_check) {
            data.add("true");
            //Collections.sort(Client.locations);
            Vector<Vector<String>> list = new Vector<Vector<String>>();
            list.add(Client.streets);
            list.add(Client.locations);
            list.add(Client.types);
            list.add(Client.phones);
            list.add(Client.postallCodes);
            list.add(Client.id_s);
            sort_Ordered(list, 1);
        } else if (!locations_list_alphavetical_check) {
            data.add("false");
        }
        write(abc_file,data);
    }

    // Set check_abc false;
    public static void set_abc_false() {
        check_abc = false;
        Vector<String> data = new Vector<String>(3);
        data.add("false");
        data.add("false");
        data.add("false");
        write(abc_file,data);
    }

    // Random the list
    public static void random_list() {
        int size = Client.streets.size();
        int random = 0;
        int[] randoms = new int[size];
        // Make random indexs for new list
        for (int i = 0; i < size-1; ) {
            random = (int) (Math.random() * size) + 0;
            if (!contains(randoms, random)) {
                randoms[i] = random;
                i++;
            }
        }
        // Make the new Random list
        Vector<String> new_streets = new Vector<String>();
        Vector<String> new_locations = new Vector<String>();
        Vector<String> new_types = new Vector<String>();
        Vector<String> new_phones = new Vector<String>();
        Vector<String> new_postallCodes = new Vector<String>();
        Vector<String> new_id_s = new Vector<String>();
        for(int n : randoms) {
            new_streets.add(Client.streets.get(n));
            new_locations.add(Client.locations.get(n));
            new_types.add(Client.types.get(n));
            new_phones.add(Client.phones.get(n));
            new_postallCodes.add(Client.postallCodes.get(n));
            new_id_s.add(Client.id_s.get(n));
        }
        // Add new random list to Client.streets
        Client.streets.clear();
        for (String str : new_streets) {
            Client.streets.add(str);
        }
        Client.locations.clear();
        for (String str : new_locations) {
            Client.locations.add(str);
        }
        Client.types.clear();
        for (String str : new_types) {
            Client.types.add(str);
        }
        Client.phones.clear();
        for (String str : new_phones) {
            Client.phones.add(str);
        }
        Client.postallCodes.clear();
        for (String str : new_postallCodes) {
            Client.postallCodes.add(str);
        }
        Client.id_s.clear();
        for (String str : new_id_s) {
            Client.id_s.add(str);
        }
    }

    // If int array conteins integer
    public static boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    // Clear Date and make it like 7-7-2007
    public static String clear_date(String nameList) throws ArrayIndexOutOfBoundsException {
        // Clear the name list make it like '7-7-2007'
        nameList = nameList.replace("\\\\", "-");
        nameList = nameList.replace("\\", "-");
        nameList = nameList.replace("//", "-");
        nameList = nameList.replace("/", "-");
        nameList = nameList.replace(" ", "-");
        nameList = nameList.replace(".", "-");
        nameList = nameList.replace(":", "-");
        nameList = nameList.replace(";", "-");
        nameList = nameList.replace(",", "-");
        nameList = nameList.replace("_", "-");
        nameList = nameList.replace("+", "-");
        nameList = nameList.replace("=", "-");
        nameList = nameList.replace("!", "-");
        nameList = nameList.replace("?", "-");
        nameList = nameList.replace("<", "-");
        nameList = nameList.replace(">", "-");
        nameList = nameList.replace("[", "-");
        nameList = nameList.replace("]", "-");
        nameList = nameList.replace("(", "-");
        nameList = nameList.replace(")", "-");
        nameList = nameList.replace("{", "-");
        nameList = nameList.replace("}", "-");
        nameList = nameList.replace("|", "-");
        nameList = nameList.replace("#", "-");
        nameList = nameList.replace("@", "-");
        nameList = nameList.replace("&", "-");
        nameList = nameList.replace("--", "-");
        nameList = nameList.replace("---", "-");
        nameList = nameList.replace("----", "-");
        nameList = nameList.replace("-----", "-");
        nameList = nameList.replace("------", "-");
        nameList = nameList.replace("-------", "-");
        String[] date = nameList.split("-");
        if (date[0].length() > 1) {
            if (date[0].substring(0, 1).equals("0")) {
                StringBuilder day = new StringBuilder(date[0]);
                day.deleteCharAt(0);
                date[0] = day.toString();
            }
        }
        if (date[1].length() > 1) {
            if (date[1].substring(0, 1).equals("0")) {
                StringBuilder day = new StringBuilder(date[1]);
                day.deleteCharAt(0);
                date[1] = day.toString();
            }
        }
        if (date[2].length() > 1) {
            if (date[2].substring(0, 1).equals("0")) {
                StringBuilder day = new StringBuilder(date[2]);
                day.deleteCharAt(0);
                date[2] = day.toString();
            }
        }
        nameList = "";
        for (String d : date) {
            nameList += d + "-";
        }
        StringBuilder name = new StringBuilder(nameList);
        name.deleteCharAt(nameList.length() - 1);
        nameList = name.toString();
        return nameList;
    }

    // Add Date int categories Battaries, maintence, Repairs, Damage, characteistics
    public static void addData(Client client, Vector<String> list,String nameList, String listName) throws ArrayIndexOutOfBoundsException {
        // Paths for Battaries, maintence, Repairs, Damage, characteistics
        final String damage_path = DataBase.folderData + client.my_id + File.separator + "Damage";
        final String maintence_path = DataBase.folderData + client.my_id + File.separator + "maintence";
        final String repairs_path = DataBase.folderData + client.my_id + File.separator + "Repairs";
        final String battaries_path = DataBase.folderData + client.my_id + File.separator + "Battaries";
        final String DocNum_path = DataBase.folderData + client.my_id + File.separator + "DocNum";
        //final String charcteristics_path = DataBase.folderData + client.my_id + File.separator + "characteristics";
        // Make the date healthy for the programe
        nameList = DataBase.clear_date(nameList);
        if ( listName.equals("Damage") ) {
            client.damage_dates.add(nameList);
            client.damage_worker.add("null");
            client.damage_damage_type.add("null");
            client.damage_fix_type.add("null");
            DataBase.makeDir(damage_path);
            String path = damage_path + File.separator + nameList;
            Vector<String> data = new Vector<String>();
            data.add(nameList);
            data.add("null");
            data.add("null");
            data.add("null");
            try {
                DataBase.write(path, data);
            } catch (java.nio.file.InvalidPathException ie) {

            }
        }
        if ( listName.equals("Repairs") ) {
            client.repairs_dates.add(nameList);
            client.repairs_worker.add("null");
            client.repairs_repair_type.add("null");
            DataBase.makeDir(repairs_path);
            String path = repairs_path + File.separator + nameList;
            Vector<String> data = new Vector<String>();
            data.add(nameList);
            data.add("null");
            data.add("null");
            try {
                DataBase.write(path, data);
            } catch (java.nio.file.InvalidPathException ie) {

            }
        }
        if ( listName.equals("maintence") ) {
            client.maintence_dates.add(nameList);
            client.maintence_worker.add("null");
            client.maintence_comments.add("null");
            DataBase.makeDir(maintence_path);
            String path = maintence_path + File.separator + nameList;
            Vector<String> data = new Vector<String>();
            data.add(nameList);
            data.add("null");
            data.add("null");
            try {
                DataBase.write(path, data);
            } catch (java.nio.file.InvalidPathException ie) {

            }
        }
        if ( listName.equals("Battaries") ) {
            client.battaries_dates.add(nameList);
            client.battaries_type1.add("null");
            client.battaries_type2.add("null");
            client.battaries_type3.add("null");
            DataBase.makeDir(battaries_path);
            String path = battaries_path + File.separator + nameList;
            Vector<String> data = new Vector<String>();
            data.add(nameList);
            data.add("null");
            data.add("null");
            data.add("null");
            try {
                DataBase.write(path, data);
            } catch (java.nio.file.InvalidPathException ie) {

            }
        }
        if ( listName.equals("DocNum") ) {
            client.DocNum_dates.add(nameList);
            client.DocNum_docnum.add("null");
            client.DocNum_Work.add("null");
            client.DocNum_Price.add("null");
            client.DocNum_Repayment.add("null");
            DataBase.makeDir(DocNum_path);
            String path = DocNum_path + File.separator + nameList;
            Vector<String> data = new Vector<String>();
            data.add(nameList);
            data.add("null");
            data.add("null");
            data.add("null");
            data.add("null");
            try {
                DataBase.write(path, data);
            } catch (java.nio.file.InvalidPathException ie) {

            }
        }
    }

    // Edit Client
    public static void editClient(Vector<String> now, Client client) {
        // Edw tha allazw ta kenoyria stoixeia
        String street = now.get(0);
        String location = now.get(1);
        String type = now.get(2);
        String phone  = now.get(3);
        String postallCode = now.get(4);

        // Delete the old file 'imformations.sql'
        String str_path = folderData + client.my_id + File.separator + "imformations" + DataBase.save_type_of_file;
        File file = new File(str_path);
        DataBase.delete(file);

        // Make the list with files
        Vector<String> list = new Vector<String>();
        list.add(street);
        list.add(location);
        list.add(type);
        list.add(phone);
        list.add(postallCode);
        list.add(client.my_id);

        // Update the client data
        client.street = street;
        client.location = location;
        client.type = type;
        client.phone = phone;
        client.postallCode = postallCode;
        //client.my_id = client.my_id;

        // Write new File
        DataBase.write(str_path, list);
    }

    // Add Client
    public static void addClient(Vector<String> imformations) {
        // Get th Imformations
        String street = imformations.get(0);
        String location = imformations.get(1);
        String type = imformations.get(2);
        String phone = imformations.get(3);
        String p_code = imformations.get(4);
        // Make new Client
        Client client = new Client();
        client.street = street;
        client.location = location;
        client.type = type;
        client.phone = phone;
        client.postallCode = p_code;
        Client.all_clients_list.add(client);
        // Save the Data
        String path = folderData + client.my_id;
        String baseFile = path + File.separator + "imformations";
        // Write the base imformations of client
        System.out.println("[ADD CLIENT TO PATH] " + path);
        makeDir(path);
        File file = new File(baseFile);
        imformations.add(client.my_id);
        write(baseFile, imformations);
    }

    // Restore all objects
    public static void restoreObjects() {
        try {
            // First clear all data
            Client.clear();
            try {
                // Set the Path of Clients Data
                String all_lines = read(path_file);
                if (!all_lines.equals(null)) {
                    String[] lines_1 = all_lines.split("\n");
                    if (lines_1[0].charAt(lines_1[0].length() - 1) != '\\' || lines_1[0].charAt(lines_1[0].length() - 1) != '/') {
                        String new_line = lines_1[0] + File.separator;
                        lines_1[0] = new_line;
                    }
                    if (lines_1[1].equals("true")) {
                        set_default_path = true;
                    } else {
                        set_default_path = false;
                    }
                    if (set_default_path) {
                        folderData = lines_1[0];
                    }
                    all_lines = null;
                    lines_1[0] = null;
                    lines_1[1] = null;
                }

                // Set home table columns width
                all_lines = read(home_table_columns_W_data);
                if (!all_lines.equals(null)) {
                    String[] lines_2 = all_lines.split("\n");
                    WindowsData.table_streets_column_width = Integer.parseInt(lines_2[0]);
                    WindowsData.table_locations_column_width = Integer.parseInt(lines_2[1]);
                    WindowsData.table_types_column_width = Integer.parseInt(lines_2[2]);
                    WindowsData.table_phones_column_width = Integer.parseInt(lines_2[3]);
                    WindowsData.table_codes_column_width = Integer.parseInt(lines_2[4]);
                    WindowsData.table_id_column_width = Integer.parseInt(lines_2[5]);
                    lines_2[0] = null;
                    lines_2[1] = null;
                    lines_2[2] = null;
                    lines_2[3] = null;
                    lines_2[4] = null;
                    lines_2[5] = null;
                }
                all_lines = null;

                // Set maintence table columns width
                all_lines = read(maintence_table_columns_W_data);
                if (!all_lines.equals(null)) {
                    String[] lines_3 = all_lines.split("\n");
                    WindowsData.maintence_table_dates_column_width = Integer.parseInt(lines_3[0]);
                    WindowsData.maintence_table_workers_column_width = Integer.parseInt(lines_3[1]);
                    WindowsData.maintence_table_comments_column_width = Integer.parseInt(lines_3[2]);
                    lines_3[0] = null;
                    lines_3[1] = null;
                    lines_3[2] = null;
                }
                all_lines = null;

                // Set repairs table columns width
                all_lines = read(repairs_table_columns_W_data);
                if (!all_lines.equals(null)) {
                    String[] lines_4 = all_lines.split("\n");
                    WindowsData.repairs_table_dates_column_width = Integer.parseInt(lines_4[0]);
                    WindowsData.repairs_table_workers_column_width = Integer.parseInt(lines_4[1]);
                    WindowsData.repairs_table_RepairsTypes_column_width = Integer.parseInt(lines_4[2]);
                    lines_4[0] = null;
                    lines_4[1] = null;
                    lines_4[2] = null;
                }
                all_lines = null;

                // Set battaries table columns width
                all_lines = read(battaries_table_columns_W_data);
                if (!all_lines.equals(null)) {
                    String[] lines_4 = all_lines.split("\n");
                    WindowsData.battaries_table_dates_column_width = Integer.parseInt(lines_4[0]);
                    WindowsData.battaries_table_type1_column_width = Integer.parseInt(lines_4[1]);
                    WindowsData.battaries_table_type2_column_width = Integer.parseInt(lines_4[2]);
                    WindowsData.battaries_table_type3_column_width = Integer.parseInt(lines_4[3]);
                    lines_4[0] = null;
                    lines_4[1] = null;
                    lines_4[2] = null;
                    lines_4[3] = null;
                }
                all_lines = null;

                // Set docnum table columns width
                all_lines = read(docnum_table_columns_W_data);
                if (!all_lines.equals(null)) {
                    String[] lines_4 = all_lines.split("\n");
                    WindowsData.DocNum_table_dates_column_width = Integer.parseInt(lines_4[0]);
                    WindowsData.DocNum_table_DocNum_column_width = Integer.parseInt(lines_4[1]);
                    WindowsData.DocNum_table_Work_column_width = Integer.parseInt(lines_4[2]);
                    WindowsData.DocNum_table_Price_column_width = Integer.parseInt(lines_4[3]);
                    WindowsData.DocNum_table_Repayment_column_width = Integer.parseInt(lines_4[4]);
                    lines_4[0] = null;
                    lines_4[1] = null;
                    lines_4[2] = null;
                    lines_4[3] = null;
                    lines_4[4] = null;
                }
                all_lines = null;

                // Set damages table columns width
                all_lines = read(damages_table_columns_W_data);
                if (!all_lines.equals(null)) {
                    String[] lines_5 = all_lines.split("\n");
                    WindowsData.damages_table_dates_column_width = Integer.parseInt(lines_5[0]);
                    WindowsData.damages_table_workers_column_width = Integer.parseInt(lines_5[1]);
                    WindowsData.damages_table_DamageTypes_column_width = Integer.parseInt(lines_5[2]);
                    WindowsData.damages_table_FixTypes_column_width = Integer.parseInt(lines_5[3]);
                    lines_5[0] = null;
                    lines_5[1] = null;
                    lines_5[2] = null;
                    lines_5[3] = null;
                }
                all_lines = null;

            } catch (java.lang.NumberFormatException nfe) {
                System.err.println("[Error] restore edit_home_window at:\n\t" + nfe);
            }

            // Objects Clients
            Client.all_clients_list.clear();
            // D directory
            String path = folderData;
            // Get all files in this directory  (clients id in D)
            File file = new File(path);
            String[] directories = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File current, String name) {
                    return new File(current, name).isDirectory();
                }
            });
            // Sort Directories
            Vector<Integer> directories_int = new Vector<Integer>();
            for (String d : directories) {
                directories_int.add(Integer.parseInt(d));
            }
            Collections.sort(directories_int);
            for (int i = 0; i < directories_int.size(); i++) {
                directories[i] = String.valueOf(directories_int.get(i));
            }
            String lines = new String();
            //Resete Client id
            Client.id = 0;
            for (int i = 0; i < directories.length; i++) {
                // Inside in every client folder
                String clientFolder = path + directories[i];
                // Get the base imformasion of every client from imformations.txt file
                File folder = new File(clientFolder);
                String baseFile = folder.getAbsolutePath() + File.separator + "imformations";
                file_path = baseFile;
                lines = read(baseFile);
                //System.out.println(lines);
                String[] parts = lines.split("\n");
                lines = null;
                // Make client object
                Client client = new Client(parts[0]);
                //client.street = parts[0];
                client.location = parts[1];
                client.type = parts[2];
                client.phone = parts[3];
                client.postallCode = parts[4];
                client.my_id = parts[5];
                Client.all_clients_list.add(client);
                // Get the folder without imformations.txt of every client
                Vector<String> category = new Vector<String>();
                for (final File fileEntry : folder.listFiles()) {
                    if (!fileEntry.getName().equals("imformations" + DataBase.save_type_of_file)) {
                        category.add(fileEntry.getName());
                        String characteristics = new String();
                        String maintence = new String();
                        String Repairs = new String();
                        String Battaries = new String();
                        String DocNums = new String();
                        String Damage = new String();
                        ////   Get the Paths of iner folder of every client   ////
                        // If you find folder with name characteristics
                        if (fileEntry.getName().equals("characteristics")) {
                            characteristics = folder + File.separator + fileEntry.getName();
                        }
                        // If you find folder with name maintenance
                        if (fileEntry.getName().equals("maintence")) {
                            maintence = folder + File.separator + fileEntry.getName();
                        }
                        // If you find folder with name Repairs
                        if (fileEntry.getName().equals("Repairs")) {
                            Repairs = folder + File.separator + fileEntry.getName();
                        }
                        // If you find folder with name Battaries
                        if (fileEntry.getName().equals("Battaries")) {
                            Battaries = folder + File.separator + fileEntry.getName();
                        }
                        // If you find folder with name DocNum
                        if (fileEntry.getName().equals("DocNum")) {
                            DocNums = folder + File.separator + fileEntry.getName();
                        }
                        // If you find folder with name Damage
                        if (fileEntry.getName().equals("Damage")) {
                            Damage = folder + File.separator + fileEntry.getName();
                        }
                        // Get the imformations of everey client all of them
                        // Characteristics
                        if (!characteristics.equals("")) {
                            File chara = new File(characteristics);
                            for (final File c : chara.listFiles()) {
                                Client.all_clients_list.get(i).characteristics_names.add(c.getName());
                            }
                        }
                        // maintenance
                        if (!maintence.equals("")) {
                            File mai = new File(maintence);
                            for (final File m : mai.listFiles()) {
                                if ((m.getName().toString().indexOf("Comments") == -1) && (m.getName().toString().indexOf("Worker") == -1)) {
                                    String files_maintence_date = m.getName();
                                    Client.all_clients_list.get(i).maintence_dates.add(files_maintence_date);
                                    String file_path = maintence + File.separator + files_maintence_date;
                                    // Read data
                                    try {
                                        String lines2 = read(file_path);
                                        String newLine = "\n";
                                        String[] parts2 = lines2.split(newLine);
                                        lines2 = null;
                                        Client.all_clients_list.get(i).maintence_worker.add(parts2[1]);
                                        Client.all_clients_list.get(i).maintence_comments.add(parts2[2]);
                                    } catch (java.lang.NullPointerException np) {

                                    } catch (java.lang.ArrayIndexOutOfBoundsException ae) {

                                    }
                                }
                            }
                        }
                        // Repairs
                        if (!Repairs.equals("")) {
                            File rep = new File(Repairs);
                            for (final File r : rep.listFiles()) {
                                if ((r.getName().toString().indexOf("RepairsType") == -1) && (r.getName().toString().indexOf("Worker") == -1)) {
                                    String files_Repairs_date = r.getName();
                                    Client.all_clients_list.get(i).repairs_dates.add(files_Repairs_date);
                                    String file_path = Repairs + File.separator + files_Repairs_date;
                                    // Read data
                                    try {
                                        String lines2 = read(file_path);
                                        String newLine = "\n";
                                        String[] parts2 = lines2.split(newLine);
                                        lines2 = null;
                                        Client.all_clients_list.get(i).repairs_worker.add(parts2[1]);
                                        Client.all_clients_list.get(i).repairs_repair_type.add(parts2[2]);
                                    } catch (java.lang.NullPointerException np) {

                                    } catch (java.lang.ArrayIndexOutOfBoundsException ae) {

                                    }
                                }
                            }
                        }
                        // Battaries
                        if (!Battaries.equals("")) {
                            File bat = new File(Battaries);
                            for (final File d : bat.listFiles()) {
                                if ((d.getName().toString().indexOf("Type1") == -1) && (d.getName().toString().indexOf("Type2") == -1) && (d.getName().toString().indexOf("Type3") == -1)) {
                                    String files_battaries_date = d.getName();
                                    Client.all_clients_list.get(i).battaries_dates.add(files_battaries_date);
                                    String file_path = Battaries + File.separator + files_battaries_date;
                                    // Read data
                                    try {
                                        String lines2 = read(file_path);
                                        String newLine = "\n";
                                        String[] parts2 = lines2.split(newLine);
                                        lines2 = null;
                                        Client.all_clients_list.get(i).battaries_type1.add(parts2[1]);
                                        Client.all_clients_list.get(i).battaries_type2.add(parts2[2]);
                                        Client.all_clients_list.get(i).battaries_type3.add(parts2[3]);
                                    } catch (java.lang.NullPointerException np) {

                                    } catch (java.lang.ArrayIndexOutOfBoundsException ae) {

                                    }
                                }
                            }
                        }
                        // DocNum
                        if (!DocNums.equals("")) {
                            File bat = new File(DocNums);
                            for (final File d : bat.listFiles()) {
                                if ((d.getName().toString().indexOf("DocNum") == -1) && (d.getName().toString().indexOf("Work") == -1)
                                                                                        && (d.getName().toString().indexOf("Price") == -1)
                                                                                        && (d.getName().toString().indexOf("Repayment") == -1)) {
                                    String files_DocNum_date = d.getName();
                                    Client.all_clients_list.get(i).DocNum_dates.add(files_DocNum_date);
                                    String file_path = DocNums + File.separator + files_DocNum_date;
                                    // Read data
                                    try {
                                        String lines2 = read(file_path);
                                        String newLine = "\n";
                                        String[] parts2 = lines2.split(newLine);
                                        lines2 = null;
                                        Client.all_clients_list.get(i).DocNum_docnum.add(parts2[1]);
                                        Client.all_clients_list.get(i).DocNum_Work.add(parts2[2]);
                                        Client.all_clients_list.get(i).DocNum_Price.add(parts2[3]);
                                        Client.all_clients_list.get(i).DocNum_Repayment.add(parts2[4]);
                                    } catch (java.lang.NullPointerException np) {

                                    } catch (java.lang.ArrayIndexOutOfBoundsException ae) {

                                    }
                                }
                            }
                        }
                        // Damage
                        if (!Damage.equals("")) {
                            File dam = new File(Damage);
                            for (final File d : dam.listFiles()) {
                                if ((d.getName().toString().indexOf("DamageType") == -1) && (d.getName().toString().indexOf("FixType") == -1) && (d.getName().toString().indexOf("Worker") == -1)) {
                                    String files_Damage_date = d.getName();
                                    Client.all_clients_list.get(i).damage_dates.add(files_Damage_date);
                                    String file_path = Damage + File.separator + files_Damage_date;
                                    // Read data
                                    try {
                                        String lines2 = read(file_path);
                                        String newLine = "\n";
                                        String[] parts2 = lines2.split(newLine);
                                        lines2 = null;
                                        Client.all_clients_list.get(i).damage_worker.add(parts2[1]);
                                        Client.all_clients_list.get(i).damage_damage_type.add(parts2[2]);
                                        Client.all_clients_list.get(i).damage_fix_type.add(parts2[3]);
                                    } catch (java.lang.NullPointerException np) {

                                    } catch (java.lang.ArrayIndexOutOfBoundsException ae) {

                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Restore Base imformartions Streeets, locations, types, phones, postalCodes
            Client.streets.clear();
            for (Client c : Client.all_clients_list) {
                Client.streets.add(c.street);
            }
            Client.locations.clear();
            for (Client c : Client.all_clients_list) {
                Client.locations.add(c.location);
            }
            Client.types.clear();
            for (Client c : Client.all_clients_list) {
                Client.types.add(c.type);
            }
            Client.phones.clear();
            for (Client c : Client.all_clients_list) {
                Client.phones.add(c.phone);
            }
            Client.postallCodes.clear();
            for (Client c : Client.all_clients_list) {
                Client.postallCodes.add(c.postallCode);
            }
            Client.id_s.clear();
            for (Client c : Client.all_clients_list) {
                Client.id_s.add(c.my_id);
            }

            // Set the Order of the lists
            String data = read(abc_file);
            String[] lines_abc = data.split("\n");
            if (lines_abc[0].equals("true")) {

                if (lines_abc[1].equals("true")) { // Streets Alphavetical
                    streets_list_alphavetical_check = true;
                    set_abc_true();
                }
                if (lines_abc[2].equals("true")) { // Locations alpavetical
                    locations_list_alphavetical_check = true;
                    set_abc_true();
                }

            } else { // Random
                random_list();
            }
        } catch (Exception e) {
            System.err.println("[ERROR RESTORING DATA]");
        }
    }

    // Take a list with files in directory
    public static void getFiles(String path, Vector<String> myFiles) {
        try {
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    myFiles.add(listOfFiles[i].getName());
                }
            }
        } catch (java.lang.NullPointerException en) {
            System.out.println("\n[Cant check the files]\n" + en);
        }
    }

    // Restart Clients.jar
    public static void restart() {

        try {
            final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            final File currentJar = new File(com.execute.Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            // Is it a jar file?
            if(!currentJar.getName().endsWith(".jar"))
                return;
            // Build command: java -jar application.jar
            final Vector<String> command = new Vector<String>();
            command.add(javaBin);
            command.add("-jar");
            command.add(currentJar.getPath());
            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
        } catch(java.net.URISyntaxException ur) {

        } catch(IOException io) {

        } finally {
            System.exit(0);
        }

    }

}