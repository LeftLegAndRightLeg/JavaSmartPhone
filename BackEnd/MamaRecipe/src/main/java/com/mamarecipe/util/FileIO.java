package com.mamarecipe.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class FileIO {
    public Properties readPropertiesFile(String filename){
        Properties properties = new Properties();
        try(InputStream in = getClass().getResourceAsStream("/"+filename)){
            properties.load(in);
        }catch(IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}
