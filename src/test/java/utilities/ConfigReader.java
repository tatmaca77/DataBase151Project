package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //    creating Properties object
    private static Properties properties;
    //    creating a static bloc to run FIRST
    static {
//        path of properties file
        String path = System.getProperty("user.dir") + "//configuration.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //creating a method that will accept a key and returns the value
    public static String getProperty(String key){
        return properties.getProperty(key);
    }




}
