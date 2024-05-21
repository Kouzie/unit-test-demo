package com.demo.unit.adaptor.filesystem;

import com.demo.unit.adaptor.filesystem.impl.FileContent;
import com.demo.unit.adaptor.filesystem.impl.FileUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersisterTests {

    Persister persister = new Persister();

    @Test
    void readDirectory() throws IOException {
        String directoryName = "audit";

        List<FileContent> sut = persister.readDirectory(directoryName);

        Assertions.assertEquals(1, sut.size());
        Assertions.assertEquals( "audit_1.txt", sut.get(0).getFileName());
        Assertions.assertEquals(1, sut.get(0).getLines().size());
    }

    @Test
    void applyUpdate() throws IOException {
        String directoryName = "";
        String contents = "hello world";
        FileUpdate update = new FileUpdate("audit_1.txt", contents);

        persister.applyUpdate(directoryName, update);
    }
}
