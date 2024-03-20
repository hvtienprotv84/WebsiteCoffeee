package com.tynadmin.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtils {

    public static void saveFile(String dir, String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(dir);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, multipartFile.getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void delete(String dir) {
        Path dirPath = Paths.get(dir);
        try {
            Files.delete(dirPath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void cleanDir(String dir) {
        Path dirPath = Paths.get(dir);
        try {
            Files.list(dirPath).forEach(file -> {
                if (!Files.isDirectory(file)) {
                    try {
                        Files.delete(file);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        } catch (IOException exception) {
            System.out.println("Could not list directory: " + dirPath);
        }
    }
}
