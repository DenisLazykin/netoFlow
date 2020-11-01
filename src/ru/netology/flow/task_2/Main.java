package ru.netology.flow.task_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        GameProgress game1 = new GameProgress(7, 8, 2, 18.3);
        GameProgress game2 = new GameProgress(6, 6, 5, 35.2);
        GameProgress game3 = new GameProgress(3, 0, 7, 49.9);

        List<String> listStringsNames = new ArrayList<>();

        File dirGameRunner = new File("/C:/Games/GameRunner");
        checkDir(dirGameRunner, "/C:/Games/GameRunner");

        File dirSavegames = new File("/C:/Games/GameRunner/savegames");
        checkDir(dirSavegames, "/C:/Games/GameRunner/savegames");

        File save1 = new File("/C:/Games/GameRunner/savegames", "save1.dat");
        checkFile(save1, "save1.dat");
        saveGame(game1, "/C:/Games/GameRunner/savegames/save1.dat");
        listStringsNames.add("/C:/Games/GameRunner/savegames/save1.dat");

        File save2 = new File("/C:/Games/GameRunner/savegames/", "save2.dat");
        checkFile(save2, "save2.dat");
        saveGame(game2, "/C:/Games/GameRunner/savegames/save2.dat");
        listStringsNames.add("/C:/Games/GameRunner/savegames/save2.dat");

        File save3 = new File("/C:/Games/GameRunner/savegames", "save3.dat");
        checkFile(save3, "save3.dat");
        saveGame(game3, "/C:/Games/GameRunner/savegames/save3.dat");
        listStringsNames.add("/C:/Games/GameRunner/savegames/save3.dat");

        zipFiles(listStringsNames, "/C:/Games/GameRunner/savegames/zip.zip");

        zipFiles1(dirSavegames, "/C:/Games/GameRunner/savegames/zip.zip");

        cleanDir(dirSavegames);

    }

    public static void zipFiles(List<String> listStrings, String pathZip) throws FileNotFoundException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip))) {
            int i = 1;
            for (String string : listStrings) {
                try (FileInputStream fis = new FileInputStream(string)) {
                    ZipEntry entry = new ZipEntry("/C:/Games/GameRunner/savegames/package_save_" + i + ".dat");
                    zout.putNextEntry(entry);
                    byte[] info = new byte[fis.available()];
                    fis.read(info);
                    zout.write(info);
                    zout.closeEntry();
                    i++;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Файлы успешно заархивированы.");
    }

    public static void checkDir(File dir, String dirName) {
        if (dir.mkdir()) {
            System.out.println("Директория '" + dirName + "'успешно создана.\n");
        }
    }

    public static void checkFile(File file, String fileName) {
        try {
            if (file.createNewFile()) {
                System.out.println("Файл '" + fileName + "' успешно создан.\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveGame(GameProgress gameProgress, String path) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void cleanDir(File dir) {
        for (File file : dir.listFiles()) {
            if (!("zip.zip".equals(file.getName()))) {
                file.delete();
            }
        }
        System.out.println("Файлы, не лежашие в архиве, успешно удалены.");
    }


    public static void zipFiles1(File dir, String pathZip) throws FileNotFoundException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip))) {
            int i = 1;
            for (File file : dir.listFiles()) {
                try (FileInputStream fis = new FileInputStream(file.getName())) {
                    ZipEntry entry = new ZipEntry("/C:/Games/GameRunner/savegames/package_save_" + i + ".dat");
                    System.out.println(file.getName());
                    zout.putNextEntry(entry);
                    byte[] info = new byte[fis.available()];
                    fis.read(info);
                    zout.write(info);
                    zout.closeEntry();
                    i++;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Файлы успешно заархивированы.");
    }

}
