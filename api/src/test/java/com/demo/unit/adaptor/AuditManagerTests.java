package com.demo.unit.adaptor;

import com.demo.unit.adaptor.filesystem.impl.FileContent;
import com.demo.unit.adaptor.filesystem.impl.FileUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// AuditManager 클래스를 함수형 프로그래밍 방식으로 변경
@JsonTest
public class AuditManagerTests {
    DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

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
