package com.demo.unit.adaptor;

import com.demo.unit.adaptor.filesystem.impl.FileContent;
import com.demo.unit.adaptor.filesystem.impl.FileUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuditManagerTests {
    DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

    /*@Test
    public void a_new_file_is_created_when_the_current_file_overflows() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String visitorName = "Alice";
        String newRecord = visitorName + ";" + dtf.format(now);
        IFileSystem fileSystem = Mockito.mock(IFileSystem.class);
        Mockito.when(fileSystem.getDirPath("audits"))
                .thenReturn("/audits/");
        Mockito.when(fileSystem.getFilePaths("audits"))
                .thenReturn(List.of(
                        "/audits/audit_1.txt",
                        "/audits/audit_2.txt"
                ));
        Mockito.when(fileSystem.readAllLines("/audits/audit_2.txt"))
                .thenReturn(List.of(
                        "Peter; 2019-04-06T16:30:00",
                        "Jane; 2019-04-06T16:40:00",
                        "Jack; 2019-04-06T17:00:00"
                ));

        AuditManager sut = new AuditManager("audits", 3, fileSystem);
        sut.addRecord(visitorName, now);

        Mockito.verify(fileSystem).writeAllText("/audits/audit_3.txt", newRecord);
    }*/

    @Test
    public void a_new_file_is_created_when_the_current_file_overflows() throws IOException {
        List<FileContent> fileContents = new ArrayList<>();
        fileContents.add(new FileContent("audit_1.txt", new ArrayList<>()));
        fileContents.add(new FileContent("audit_2.txt", List.of(
                "Peter; 2019-04-06T16:30:00",
                "Jane; 2019-04-06T16:40:00",
                "Jack; 2019-04-06T17:00:00"
        )));
        String visitorName = "Alice";
        LocalDateTime now = LocalDateTime.now();

        AuditManager sut = new AuditManager(3);
        FileUpdate update = sut.addRecord(fileContents, visitorName, now);

        Assertions.assertEquals("audit_3.txt", update.getFileName());
        Assertions.assertEquals(visitorName + ";" + dtf.format(now), update.getNewContent());
    }
}
