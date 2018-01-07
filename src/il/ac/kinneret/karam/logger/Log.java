package il.ac.kinneret.karam.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import static il.ac.kinneret.karam.logger.ConsoleColor.*;


public class Log {
    private static String FILE_PATH = "/tmp/logs/main.log";
    private static File LOG_FILE = new File(FILE_PATH);
    private static BufferedWriter bw = null;

    private static final char NEW_LINE_CHAR = 0x000d;

    public static void setLogFile(String filePath) {
        FILE_PATH = filePath;
        LOG_FILE = new File(FILE_PATH);
    }

    //Information
    public static void i(String logTag,String message){
        log( "Information :: " + logTag + " : " + message, "Information",ANSI_GREEN);
    }

    //Warning
    public static void w(String logTag,String message){
        log( "Warning :: " + logTag + " : " + message, "Warning",ANSI_YELLOW);
    }

    //Error
    public static void e(String logTag,String message){
        log( "Error :: " + logTag + " : " + message, "Error",ANSI_RED);
    }

    //Debug
    public static void d(String logTag,String message){
        log( "Debug :: " + logTag + " : " + message, "Debug",ANSI_BLUE);
    }

    private static void log(String line,String type,String color){
        synchronized (System.out) {
            System.out.println(color + line + ANSI_RESET);
        }

        try {
            if (!LOG_FILE.getParentFile().exists())
                LOG_FILE.getParentFile().mkdirs();
            if (!LOG_FILE.exists())
                LOG_FILE.createNewFile();
        } catch (IOException e) {
            System.out.println("Can not create log file.");
            return;
        }

        String msg = new Date().toString() + " : " + type + " : " + line + NEW_LINE_CHAR;
        try {
            Files.write(Paths.get(LOG_FILE.getAbsolutePath()), (msg).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e1) {
            System.out.println("Can not write to log file.");
        }

    }
    public static void main(String args[]) {
        setLogFile("/home/karam/Desktop/log.txt");
        d("asd","Asd");
    }

}
