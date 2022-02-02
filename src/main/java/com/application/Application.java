package com.application;

import com.generator.Generator;
import com.enums.GenerateConditionType;
import com.utils.AppContext;
import com.utils.PropertyReader;

public class Application {
    public static void main(String[] args) {
        Generator generator = new Generator();
        PropertyReader reader = new PropertyReader();
        reader.loadAllProperties();
        if (AppContext.get("generateConditionType", String.class).equalsIgnoreCase("HARD") && Boolean.parseBoolean(AppContext.get("isArchive", String.class))) {
            generator.createZIP(
                    AppContext.get("path", String.class),
                    AppContext.get("fileName", String.class),
                    AppContext.get("fileType", String.class),
                    AppContext.get("archiveName", String.class),
                    Integer.valueOf(AppContext.get("fileCount", String.class)),
                    Integer.valueOf(AppContext.get("fileSize", String.class)),
                    GenerateConditionType.HARD
            );
        } else if (!AppContext.get("generateConditionType", String.class).equalsIgnoreCase("HARD") && Boolean.parseBoolean(AppContext.get("isArchive", String.class))) {
            generator.createZIP(
                    AppContext.get("path", String.class),
                    AppContext.get("fileName", String.class),
                    AppContext.get("fileType", String.class),
                    AppContext.get("archiveName", String.class),
                    Integer.valueOf(AppContext.get("fileCount", String.class)),
                    Integer.valueOf(AppContext.get("fileSize", String.class)),
                    GenerateConditionType.SIMPLE
            );
        } else {
            generator.createFile(
                    AppContext.get("path", String.class),
                    AppContext.get("fileName", String.class),
                    AppContext.get("fileType", String.class),
                    Integer.valueOf(AppContext.get("fileSize", String.class))
            );
        }
    }
}
