package com.demo.unit.adaptor.filesystem.impl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUpdate {
    private final String fileName;
    private final String newContent;

    public FileUpdate(String fileName, String newContent) {
        this.fileName = fileName;
        this.newContent = newContent;
    }
}
