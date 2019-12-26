package com.itdr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUTils {
    public static String getValues(String key) throws IOException {
        Properties p=new Properties();
        InputStream in=PropertiesUTils.class.getClassLoader().getResourceAsStream("Sets.properties");
        p.load(in);
        String property = p.getProperty(key);
        return property;
    }
}

























