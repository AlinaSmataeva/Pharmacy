package org.smataeva.finalproject.model.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DatabasePropertiesReader {
    public static String DB_LOGIN;
    public static String DB_PASSWORD;
    public static String DB_DRIVER;
    public static String DB_URL;
    public static int DB_POOL_SIZE;
    static {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("D:\\EDU\\Proga\\JAVA\\epam_final_project\\src\\main\\resources\\db.properties"));
                DB_DRIVER = properties.getProperty("db.driver");
                DB_URL = properties.getProperty("db.url");
                DB_PASSWORD = properties.getProperty("db.password");
                DB_LOGIN = properties.getProperty("db.login");
                DB_POOL_SIZE = Integer.parseInt(properties.getProperty("db.pool_size"));
                Class.forName(DB_DRIVER);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
