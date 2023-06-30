package com.demo.unit.ui;

import com.demo.unit.adaptor.AuditManager;
import com.demo.unit.adaptor.filesystem.Persister;
import com.demo.unit.adaptor.filesystem.impl.FileContent;
import com.demo.unit.adaptor.filesystem.impl.FileUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuditController {


    private Persister persister;
    private String directoryName;
    private AuditManager auditManager;

    @PostConstruct
    public void init() {
        this.directoryName = "audit";
        this.auditManager = new AuditManager(3);
        this.persister = new Persister();
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) throws IOException {
        // 공유 의존성을 가지는 persister 사용
        List<FileContent> files = persister.readDirectory(directoryName);
        // auditManager 는 함수형으로 변경
        FileUpdate update = auditManager.addRecord(files, visitorName, timeOfVisit);
        persister.applyUpdate(directoryName, update);
    }
}
