package com.generater;

import com.enums.GenerateConditionType;

import java.io.*;
import java.nio.file.Files;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Generator {
    public void createZIP(String directoryPath, String includesFileName,  String fileType, String zipFileName, Integer countFiles, Integer fileSizeInKB, GenerateConditionType condition) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(directoryPath + zipFileName + ".zip"))) {
            ZipEntry zipEntry;
            FileInputStream entryFile;
            for (int index = 0; countFiles > index; index++) {
                File file;
                switch (condition) {
                    case HARD:
                        file = generateHardFile(directoryPath, includesFileName, index, fileType, fileSizeInKB);
                        break;
                    default:
                        file = generateSimpleFile(directoryPath, includesFileName, index, fileType, fileSizeInKB);
                }
                zipEntry = new ZipEntry(file.getName());
                zipOut.putNextEntry(zipEntry);
                entryFile = new FileInputStream(directoryPath  + file.getName());
                int count;
                byte[] fileRAW = new byte[1000000000];
                while ((count = entryFile.read(fileRAW, 0, 1000)) != -1) {
                    zipOut.write(fileRAW, 0, count);
                }
                entryFile.close();
                zipOut.closeEntry();
                Files.delete(file.toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFile(String directoryPath, String fileName, String fileType, Integer fileSizeInKb) {
        try {
            File file = generateHardFile(directoryPath, fileName, 1, fileType, fileSizeInKb);
            System.out.println("File " + file.getName() + " was been created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File generateHardFile(String directoryPath, String includesFileName, int currentFile, String fileType, Integer fileSizeInKB) {
        File file = new File(directoryPath + includesFileName + currentFile + fileType);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Random random = new Random();
            FileWriter fileWriter = new FileWriter(file);
            for (int z = 0; z < fileSizeInKB*1024; z++) {
                fileWriter.write(random.nextInt(127));
               // fileOutputStream.write((int)Math.floor(Math.random()*(122-65+1)+65));
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private static File generateSimpleFile(String directoryPath, String includesFileName, int currentFile, String fileType, Integer fileSizeInKB) {
        File file = new File(directoryPath + includesFileName + currentFile + fileType);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.setLength(fileSizeInKB*1024);
            randomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}


