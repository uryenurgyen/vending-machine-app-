package com.techelevator;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Logger {

    public static void log(String type, String money, String userMoney){
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream("Log.txt", true))) {
            String time;
            time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a").format(new Date());
            dataOutput.println(time + " " + type + " $" + money + " $" + userMoney);
            //System.out.println(time + " " + type + " $" + money + " $" + userMoney);
        } catch (Exception e) {
            System.err.println("File cannot be opened for writing.");
        }
    }

}
