package com;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Generate {

    public static void main(String[] args) {
        createZIP("C:\\Users\\kuril\\Documents\\testCom", "test", 11);
    }

    public static String createZIP(String directoryPath, String zipFileName, Integer countFiles) {
        try {
            final int BUFFER = 104857600; // 100MB
            final long MAX_ZIP_SIZE = 3221225400L; //3 GB
            long currentSize = 0;
            int zipSplitCount = 0;
            if (!directoryPath.endsWith("/")) {
                directoryPath = directoryPath + "/";
            }
            byte fileRAW[] = new byte[BUFFER];
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(directoryPath + zipFileName.toUpperCase()));
            ZipEntry zipEntry;
            FileInputStream entryFile;
            for (int i = 0; countFiles > i; i++) {
                File file = new File(directoryPath + "test" + i + ".txt");
                OutputStream op = new FileOutputStream(file);
                op.write("simple text".getBytes());
                op.close();
                zipEntry = new ZipEntry("test" + i + ".txt");
                if (currentSize >= MAX_ZIP_SIZE) {
                    zipSplitCount++;
                    //zipOut.closeEntry();
                    zipOut.close();
                    zipOut = new ZipOutputStream(new FileOutputStream(directoryPath + zipFileName.toLowerCase().replace(".zip", "_" + zipSplitCount + ".zip").toUpperCase()));
                    currentSize = 0;
                }
                zipOut.putNextEntry(zipEntry);

                entryFile = new FileInputStream(directoryPath + "test" + i + ".txt");

                int count;
                while ((count = entryFile.read(fileRAW, 0, BUFFER)) != -1) {
                    zipOut.write(fileRAW, 0, count);

                    //System.out.println("number of Bytes read = " + count);
                }
                entryFile.close();
                zipOut.closeEntry();
                currentSize += zipEntry.getCompressedSize();
                Files.delete(file.toPath());
            }
            zipOut.close();

            //System.out.println(directory + " -" + zipFileName + " -Number of Files = " + files.length);
        } catch (FileNotFoundException e) {
            return "FileNotFoundException = " + e.getMessage();
        } catch (IOException e) {
            return "IOException = " + e.getMessage();
        } catch (Exception e) {
            return "Exception = " + e.getMessage();
        }

        return "1";
    }
}


