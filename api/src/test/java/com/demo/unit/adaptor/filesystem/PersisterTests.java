package com.demo.unit.adaptor.filesystem;

import com.demo.unit.adaptor.filesystem.impl.FileContent;
import com.demo.unit.adaptor.filesystem.impl.FileUpdate;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersisterTests {

    public List<FileContent> readDirectory(String directoryName) throws IOException {
        File dir = new ClassPathResource(directoryName).getFile();
        String dirPath = dir.getPath();
        String[] fileNames = dir.list();
        List<FileContent> result = new ArrayList<>();
        for (String fileName : fileNames) {
            FileContent fileContent = new FileContent(fileName, readAllLines(dirPath + fileName));
            result.add(fileContent);
        }
        return result;
    }

    private List<String> readAllLines(String filePath) throws IOException {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }

    public void applyUpdate(String directoryName, FileUpdate update) throws IOException {
        File dir = new ClassPathResource(directoryName).getFile();
        String dirPath = dir.getPath();
        String filePath = dirPath + update.getFileName();
        File file = new File(filePath);
        // file 에 문자열 추가
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.newLine();
            writer.append(update.getNewContent() );
        } catch (IOException e) {
            throw e;
        }
    }
}
