package com.data;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.net.SocketException;
import java.util.Vector;


// ftp://AlexandrosFTP:Platanios719791@66.220.9.50/Work/D/       # 5GB
// ftp://AlexandrosFTP:Platanios719791@2.86.45.85/Work           # Sync.com  5GB


public class FTP_Client {

    public FTPClient ftp;
    public String user_name = "";
    public String password = "";
    public String server = "";
    public int port = -1;
    public String path_ftp = "";
    public String ftpUrl = "";

    // Main
    public static void main(String[] args) {

        /*
        String server = "f25-preview.runhosting.com";
        int port = 21;
        String user_name = "3164684";
        String password = "Platanios719791";
        String path_ftp = "";
        */

        // FTP HOSTING   name dite        https://alexandrosftp.ftphosting.net/ftp/private/browse/#path=/&limit=25&sort=name
        String server = "alexandrosftp.ftphosting.net";
        int port = 21;
        String user_name = "alexandrosftp";
        String password = "Platanios719791";
        String path_ftp = "/Work";

        // Object ftp
        FTP_Client ftp = new FTP_Client(server, port, user_name, password, path_ftp);
        //my_FTP ftp = new my_FTP();

        // Connect to FTP
        if ( ftp.connect_login() ) {

            // Check if exist dirctory
            //ftp.existDir(ftp.path_ftp + "/D");

            // Upload directory on FTP
            //ftp.uploadFiles( ftp.path_ftp, "C:\\Users\\Alexandros\\Desktop\\UPLOADS\\D", "D" );

            // Delete Directory on FTP
            //ftp.delete_directory( ftp.path_ftp + "/D");

            // Download Directory
            //ftp.downloadFiles("/Work/D", "C:\\Users\\Alexandros\\Desktop\\DOWNLOADS" );

            // Disconnect FTP
            ftp.desconect();

        }

    }


    // Constructor 1
    FTP_Client(String server, int port, String user_name, String password, String path_ftp) {
        this.server = server;
        if (port != -1)
            this.port = port;
        this.user_name = user_name;
        this.password = password;
        this.path_ftp = path_ftp;
    }

    // Constructor 2
    FTP_Client() {

    }

    // Set server
    public void setServer(String server) {
        this.server = server;
    }

    // Set port
    public void setPort(int port) {
        if (port != -1)
            this.port = port;
    }

    // Set user_name
    public void set_User_name(String user_name) {
        this.user_name = user_name;
    }

    // Set password
    public void setPassword(String password) {
        this.password = password;
    }

    // Set path_ftp
    public void set_Path_ftp(String path_ftp) {
        this.path_ftp = path_ftp;
    }

    // Connection FTP
    public boolean connect_login() {
        this.ftpUrl = String.format("ftp://%s:%s@%s/%s", this.user_name,this.password,this.server,this.path_ftp);
        System.out.println("[URL]: " + ftpUrl);
        this.ftp = new FTPClient();
        boolean connected = false;
        boolean login = false;
        boolean setting = false;
        try {
            if (this.port != -1) {
                this.ftp.connect(this.server,this.port);
                System.out.println("[CONNECTED SUCCESS]       " + this.server + this.port);
                connected = true;
            } else {
                this.ftp.connect(this.server);
                System.out.println("[CONNECTED SUCCESS]       " + this.server);
                connected = true;
            }
        } catch (SocketException se) {
            System.err.println("[ERROR CONNECTING]        " + this.server);
            connected = false;
        } catch (IOException ioe) {
            System.err.println("[ERROR CONNECTING]        " + this.server);
            connected = false;
        }
        try {
            if ( this.ftp.login(this.user_name, this.password) ) {
                System.out.println("[LOGIN SUCCESS]           " + this.user_name + " , " + this.password);
                login = true;
            } else {
                login = false;
            }
        } catch (SocketException se) {
            System.err.println("[ERROR LOGIN]             " + this.user_name + " , " + this.password);
            login = false;
        } catch (IOException ioe) {
            System.err.println("[ERROR LOGIN]             " + this.user_name + " , " + this.password);
            login = false;
        }
        //this.ftp.setDataTimeout(30000);
        this.ftp.enterLocalPassiveMode();
        try {
            if ( this.ftp.setFileType(FTP.BINARY_FILE_TYPE) ) { // FTP.ASCII_FILE_TYPE   FTP.BINARY_FILE_TYPE
                System.out.println("[SETTING SUCCESS]         ");
                setting = true;
            } else {
                setting = false;
            }
        } catch(IOException ioe) {
            System.err.println("[ERROR SETTINGS]          " + ioe);
            setting = false;
        }
        // Return if evething OK
        if ( connected && login && setting) {
            return true;
        } else {
            return false;
        }
    }

    // Rename Directory FTP
    public boolean renameFTP(String old_dir, String new_dir) {
        boolean success = false;
        try {
            success = this.ftp.rename(old_dir, new_dir);
        } catch (IOException ioe) {
            System.err.println("[ERROR] " + ioe);
        } finally {
            return success;
        }
    }

    // Download Directory
    public void downloadFiles(String remote_path, String store_path) {
        System.out.println("[REMOTE_PATH BEFORE] " + remote_path);
        // Remove from remote_path the last File.separator
        remote_path = remote_path.substring(0,remote_path.lastIndexOf("/"));
        // Remove from remote_path path the type of file like .sql
        if ( remote_path.contains(DataBase.save_type_of_file) ) {
            remote_path = remote_path.substring( 0, remote_path.indexOf(DataBase.save_type_of_file) );
        }
        System.out.println("[REMOTE_PATH AFTER] " + remote_path);
        try {
            // If is directory
            if (this.ftp.changeWorkingDirectory(remote_path)) {
                // Get the names of files in this directory on FTP
                Vector<String> files_names = new Vector<String>();
                String status = this.ftp.getStatus(remote_path);
                String[] lines = status.split("\n");
                for (String line : lines) {
                    String[] imfo = line.split("\\s+");
                    String file = imfo[imfo.length - 1];
                    if (!file.contains(remote_path) && !file.contains("status")) {
                        //System.out.println("[FILE]: " + file);
                        files_names.add(file);
                    }
                }
                // If directory it's not empty
                if (files_names.size() > 0) {
                    // Get the new path in every file
                    for (String file : files_names) {
                        String path = remote_path + "/" + file;
                        //System.out.println("[PATH]: " + path);
                        // If it's Directory
                        if (this.ftp.changeWorkingDirectory(path)) {
                            // Make dir in store directory if dir not exist
                            String directory_path = store_path + File.separator + file;
                            File dir = new File(directory_path);
                            if (!dir.exists()) {
                                if (dir.mkdir()) {
                                    System.out.println("[CREATE DIRECTORY]        " + directory_path);
                                    this.downloadFiles(path+"/", directory_path);
                                } else {
                                    System.out.println("[ERROR CREATING]          " + directory_path);
                                }
                            } else {
                                System.out.println("[EXIST DIRECTORY]         " + directory_path);
                                this.downloadFiles(path+"/", directory_path);
                            }
                        } else {
                            //System.out.println("[DOWNLOAD FILE]:          " + path);
                            // Download the file
                            this.download(path, store_path);
                            this.downloadFiles(path+"/", store_path);
                        }
                    } // For in files Over
                } // If file_names.size() > 0  Over
            } // If remote_path directore Over
        } catch (org.apache.commons.net.ftp.FTPConnectionClosedException ce) {
            System.err.println("[ERROR DOWNLOAD]          " + remote_path);
        } catch (IOException ioe) {
            System.err.println("[ERROR DOWNLOAD]          " + remote_path);
        } catch (Exception e) {
            System.err.println("[ERROR DOWNLOAD]          " + remote_path);
        }
    }

    // Download File
    protected boolean download(String remoteFilePath, String local_directory) {
        boolean check = false;
        //System.out.println("[DOWNLOAD]: " + remoteFilePath);
        String[] remote_path_split = remoteFilePath.split("/");
        String localFile = local_directory + File.separator + remote_path_split[remote_path_split.length-1];
        try {
            File localfile = new File(localFile);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localfile));
            check =  this.ftp.retrieveFile(remoteFilePath, outputStream);
            outputStream.close();
            if (check)
                System.out.println("[DOWNLOAD SUCCESS]        " + remoteFilePath);
            else
                System.err.println("[DOWNLOAD FAILED]         " + remoteFilePath);
        } catch (IOException ioe) {
            System.err.println("[ERROR DOWNLOAD FILE]     " + remoteFilePath);
        } finally {
            return check;
        }
    }

    // Remove direcory on FTP
    public void delete_directory(String server_path) {
        try {
            FTPFile ftp_file = this.ftp.mlistFile(server_path);
            ftp_file.setPermission(FTPFile.USER_ACCESS,FTPFile.WRITE_PERMISSION|FTPFile.READ_PERMISSION,true);
        } catch (IOException ioe) {
            System.err.println("[ERROR PREMISSIONS]       " + server_path);
        }
        try {
            if ( this.ftp.removeDirectory(server_path) ) {
                System.out.println("[REMOVED SUCCESS]         " + server_path);
            } else {
                System.out.println("[CANT REMOVE SUCCESS]         " + server_path);
            }
        } catch (IOException ioe) {
            System.err.println("[ERROR REMOVE]            " + server_path);
        }
    }

    // Upload Directory
    public void uploadFiles(String remoteDirPath, String localParentDir, String remoteParentDir) {
        // DOWNLOADING START
        try {
            // If remoteDirPath equals /  remove it
            if ( remoteDirPath.equals("/") ) {
                remoteDirPath = "";
            }
            // If last char of remoteDirPath equals /  remove it
            if ( remoteDirPath.length() > 2 ) {
                if ( remoteDirPath.substring( remoteDirPath.length()-1, remoteDirPath.length() ).equals("/") ) {
                    remoteDirPath = remoteDirPath.substring(0, remoteDirPath.length()-1);
                }
            }
            // Make new Directory in FTP if directory not exist
            boolean check_dir = false;
            if ( !this.ftp.changeWorkingDirectory(remoteDirPath + "/" + remoteParentDir) ) {
                if (this.ftp.makeDirectory(remoteDirPath + "/" + remoteParentDir)) {
                    System.out.println("[CREATED  SUCCESS]        " + remoteDirPath + "/" + remoteParentDir);
                    check_dir = true;
                } else {
                    System.out.println("[ERROR CREATED]           " + remoteDirPath + "/" + remoteParentDir);
                    check_dir = false;
                }
            } else {
                check_dir = true;
            }
            // If directory ctreated or exist
            if (check_dir) {
                // Get Files list
                File localDir = new File(localParentDir);
                File[] subFiles = localDir.listFiles();
                // Upload Files
                if (subFiles != null && subFiles.length > 0) {
                    for (File item : subFiles) {
                        String remoteFilePath = remoteDirPath + "/" + remoteParentDir + "/" + item.getName();
                        if (remoteParentDir.equals("")) {
                            remoteFilePath = remoteDirPath + "/" + item.getName();
                        }
                        if (item.isFile()) {
                            // upload the file
                            String localFilePath = item.getAbsolutePath();
                            //System.out.println("[ABOUT FILE]:             " + localFilePath);
                            boolean uploaded = this.uploadSingleFile(localFilePath, remoteFilePath);
                            if (uploaded) {
                                System.out.println("[UPLOADED SUCCESS]        " + remoteFilePath);
                            } else {
                                System.out.println("[ERROR UPLOADED FILE]     " + localFilePath);
                            }
                        } else {
                            // create directory on the server
                            boolean created = this.ftp.makeDirectory(remoteFilePath);
                            if (created) {
                                System.out.println("[CREATED  SUCCESS]        " + remoteFilePath);
                            } else {
                                System.out.println("[ERROR CREATED]           " + remoteFilePath);
                            }
                            // upload the sub directory
                            String parent = remoteParentDir + "/" + item.getName();
                            if (remoteParentDir.equals("")) {
                                parent = item.getName();
                            }
                            localParentDir = item.getAbsolutePath();
                            uploadFiles(remoteDirPath, localParentDir, parent);
                        }
                    }
                }
            }
        } catch (org.apache.commons.net.ftp.FTPConnectionClosedException ce) {
            System.err.println("[ERROR UPLOAD]            " + localParentDir);
        } catch (IOException ioe) {
            System.err.println("[ERROR UPLOAD]            " + localParentDir);
        } catch (Exception e) {
            System.err.println("[ERROR UPLOAD]            " + localParentDir);
        }
    }

    // Upload File
    protected boolean uploadSingleFile(String localFilePath, String remoteFilePath) throws IOException {
        File localFile = new File(localFilePath);
        InputStream inputStream = new FileInputStream(localFile);
        try {
            this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
            return this.ftp.storeFile(remoteFilePath, inputStream);
        } finally {
            inputStream.close();
        }
    }

    // Check if directory exist
    public boolean existDir(String path) {
        boolean check = false;
        try {
            if (this.ftp.changeWorkingDirectory(this.path_ftp)) {
                System.out.println("[DIRECTORY EXIST]         " + this.path_ftp);
                check = true;
            } else {
                System.out.println("[DIRECTORY NOT EXIST]     " + this.path_ftp);
                check = false;
            }
        } catch (IOException ioe) {
            System.err.println("[ERROR EXIST DIR]         " + ioe);
            check = false;
        } finally {
            return check;
        }
    }

    // Disconnect from FTP
    public boolean desconect() {
        try {
            if ( this.ftp.isConnected() ) {
                this.ftp.logout();
                this.ftp.disconnect();
            }
            System.out.println("[DISCONNECTED SUCCESS]    ");
            return true;
        } catch(IOException ioe) {
            System.err.println("[ERROR DISCONNECTING]     " + ioe);
            return false;
        }
    }

}