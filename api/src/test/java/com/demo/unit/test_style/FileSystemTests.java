package com.demo.unit.test_style;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class FileSystemTests {
    @Test
    public void fileListTest() throws IOException {
        String directoryName = "audit";
        File directory = new ClassPathResource(directoryName).getFile();
        String[] documentsName = directory.list();
        for (String documentName : documentsName) {
            System.out.println("docName:" + documentName);
        }
        String filePath = directory.getPath() + "audit_1.txt";
        System.out.println(filePath);
        // File file = new File(pathName);
    }
}
