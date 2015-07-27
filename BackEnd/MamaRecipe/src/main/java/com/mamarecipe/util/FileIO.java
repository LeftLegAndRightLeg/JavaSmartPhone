package com.mamarecipe.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jeremiah on 7/26/15.
 */
public class FileIO {
    public Properties readPropertiesFile(String filename){
        Properties properties = new Properties();
        try(FileInputStream in = new FileInputStream(filename)){
            properties.load(in);
        }catch(IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}
