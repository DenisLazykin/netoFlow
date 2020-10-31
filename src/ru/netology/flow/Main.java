package ru.netology.flow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static StringBuilder text = new StringBuilder();

    public static void main(String[] args) {

        text.append("В каталоге 'Games' произведены следующие действия:\n");

        //  Путь к папке Games: "C:/Games"

        File dirTemp = new File("/C:/Games/temp");
        checkDir(dirTemp, "temp");

        File tempTxt = new File("/C:/Games/temp", "temp.txt");
        checkFile(tempTxt, "temp.txt");

        File dirSrc = new File("/C:/Games/src");
        checkDir(dirSrc, "src");

        File dirRes = new File("/C:/Games/res");
        checkDir(dirRes, "res");

        File dirSavegames = new File("/C:/Games/savegames");
        checkDir(dirSavegames, "savegames");

        File dirMain = new File("/C:/Games/src/main");
        checkDir(dirMain, "main");

        File dirTest = new File("/C:/Games/src/test");
        checkDir(dirTest, "test");

        File dirDrawables = new File("/C:/Games/res/drawables");
        checkDir(dirDrawables, "drawables");

        File dirVectors = new File("/C:/Games/res/vectors");
        checkDir(dirVectors, "vectors");

        File dirIcons = new File("/C:/Games/res/icons");
        checkDir(dirIcons, "icons");

        File mainJava = new File("/C:/Games/src/main", "Main.java");
        checkFile(mainJava, "Main.java");

        File utilsJava = new File("/C:/Games/src/main", "Utils.java");
        checkFile(utilsJava, "Utils.java");

        // Удаление и переименование директорий и файлов

        File dirFiles = new File("/C:/Games/files");
        checkDir(dirFiles, "files");

        File newDirFiles = new File("/C:/Games/files_new");
        if (dirFiles.renameTo(newDirFiles)) {
            text.append("Директория 'files' успешно переименована в 'files_new'.\n");
        }

        File file = new File("/C:/Games/files_new", "file.txt");
        checkFile(file, "file.txt");

        File newFile = new File("/C:/Games/files_new", "file_new.txt");

        if (file.renameTo(newFile)) {
            text.append("Файл 'file.txt' успешно переименован в 'file_new.txt'.\n");
        }

        if (newFile.delete()) {
            text.append("Файл 'file_new.txt' из каталога 'files_new' успешно удалён.\n");
        }

        if (newDirFiles.delete()) {
            text.append("Директория 'files_new' успешно удалена.\n");
        }

        try (FileWriter writerFinalText = new FileWriter("/C:/Games/temp/temp.txt", false)) {
            writerFinalText.write(String.valueOf(text));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (tempTxt.exists()) {
            System.out.println("Размер файла 'temp.txt' в байтах: " + tempTxt.length());
        } else {
            System.out.println("Нет такого файла!");
        }
    }

    public static void checkDir(File dir, String dirName) {
        if (dir.mkdir()) {
            text.append("Директория '" + dirName + "' успешно создана.\n");
        }
    }

    public static void checkFile(File file, String fileName) {
        try {
            if (file.createNewFile()) {
                text.append("Файл '" + fileName + "' успешно создан.\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
