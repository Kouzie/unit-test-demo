package com.demo.unit.adaptor;

import com.demo.unit.adaptor.filesystem.impl.FileContent;
import com.demo.unit.adaptor.filesystem.impl.FileUpdate;
import lombok.Getter;

import java.io.IOException;
import java.text.Collator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class AuditManager {
    private final DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
    private final int maxEntriesPerFile;
//    private final String directoryName;
//    private final IFileSystem fileSystem;

    public AuditManager(int maxEntriesPerFile) {
        this.maxEntriesPerFile = maxEntriesPerFile;
//        this.directoryName = directoryName;
//        this.fileSystem = filesystem;
    }

    public FileUpdate addRecord(List<FileContent> files, String visitorName, LocalDateTime timeOfVisit) throws IOException {
        String newRecord = visitorName + ";" + dtf.format(timeOfVisit);
        if (files == null || files.size() == 0) {
            return new FileUpdate("audit_1.txt", newRecord);
        }
        FileContent lastFile = getLastFilePath(files);
        List<String> lines = lastFile.getLines();
        if (lines.size() < maxEntriesPerFile) {
            return new FileUpdate(lastFile.getFileName(), newRecord);
        } else {
            int newIndex = lastFile.getIndex() + 1;
            String newFileName = "audit_" + newIndex  + ".txt";
            return new FileUpdate(newFileName, newRecord);
        }
    }

    private FileContent getLastFilePath(List<FileContent> files) {
        List<FileContent> sortedList = files.stream()
                .sorted(Comparator.comparing(FileContent::getFileName, Collator.getInstance(Locale.getDefault())))
                .collect(Collectors.toList());
        return sortedList.get(sortedList.size() - 1);
    }
}
