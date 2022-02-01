package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public void loadAllProperties() {
        getRequestProperties();
    }

    private void getRequestProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/generateConfiguration.properties")) {
            properties.load(fileInputStream);
            AppContext.put("path", properties.getProperty("generate.path"));
            AppContext.put("fileName", properties.getProperty("generate.fileName"));
            AppContext.put("fileType", properties.getProperty("generate.fileType"));
            AppContext.put("archiveName", properties.getProperty("generate.archiveName"));
            AppContext.put("fileCount", properties.getProperty("generate.fileCount"));
            AppContext.put("fileSize", properties.getProperty("generate.fileSize"));
            AppContext.put("generateConditionType", properties.getProperty("generate.generateConditionType"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}