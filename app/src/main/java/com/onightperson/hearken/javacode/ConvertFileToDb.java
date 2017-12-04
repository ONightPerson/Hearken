package com.onightperson.hearken.javacode;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liubaozhu on 17/12/2.
 */

public class ConvertFileToDb {

    private static final String CSV_FILE_DATA_SEPARATOR = ",";
    private static final int CSV_DATA_COLUMN_COUNT = 3;
    private static final String STUDENT_TABLE = "student_info";
    private static final String LINE_BREAKS = "\n";
    private static final String CHARSET = "UTF-8";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    public static void main(String[] args) {

        String srcFile = args[0];
        String desFile = args[1];
        String dbVersion = args[2];
        System.out.println("srcFile: " + srcFile + ", desFile: " + desFile);

        convertFileToDb(srcFile, desFile, dbVersion);
    }

    private static void convertFileToDb(String srcFile, String desFile, String dbVersion) {
        StringBuilder sb = new StringBuilder();
        // PRAGMA
        String pragma = "PRAGMA user_version = " + dbVersion + ";";
        System.out.println(pragma);
        sb.append(pragma + LINE_BREAKS);
        String dropTableIfNeeded = "DROP TABLE IF EXISTS " + STUDENT_TABLE + ";";
        System.out.println(dropTableIfNeeded);
        sb.append(dropTableIfNeeded + LINE_BREAKS);
        String createTable = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_AGE + " INTEGER);";
        System.out.println(createTable);
        sb.append(createTable + LINE_BREAKS);
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = br.readLine()) != null) {
                String[] infos = line.split(CSV_FILE_DATA_SEPARATOR);
                if (infos == null) {
                    continue;
                }
                if (infos.length < CSV_DATA_COLUMN_COUNT) {
                    continue;
                }
                String insertRecord = "INSERT INTO " + STUDENT_TABLE + "(" + COLUMN_ID
                        + ", " + COLUMN_NAME + ", " + COLUMN_AGE + ") values ("
                        + "'" + infos[0] + "'" + ", " + "'" + infos[1] + "'" + ", " + "'" + infos[2] + "'" + ");";
                System.out.println(insertRecord);
                sb.append(insertRecord + LINE_BREAKS);
            }

            fos = new FileOutputStream(desFile);
            fos.write(sb.toString().getBytes(CHARSET));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos == null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
