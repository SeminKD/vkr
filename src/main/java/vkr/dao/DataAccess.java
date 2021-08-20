package vkr.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DataAccess {

    public static Connection getNewConnection(){
        Connection connection=null;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\application.properties"));
            String url = properties.getProperty("spring.datasource.url");
            String user = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            //Class.forName(properties.getProperty("spring.datasource.driver-classname"));
            connection = DriverManager.getConnection(url,user,password);
        } catch (IOException | SQLException e) {
            writeError(e);
        }
        return connection;
    }

    public static void writeError(Exception ex){
        try {
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            String error = errors.toString();
            ex.printStackTrace();
            FileWriter writer = new FileWriter("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\log.txt", true);
            writer.append(error);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getLoginData(){
        String[] result = new String[2];
        int i=0;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\loginData.txt");
            Scanner scanner = new Scanner(fileReader);
            while(scanner.hasNextLine()){
                result[i] = scanner.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
