package com.demo.unit.adaptor.filesystem.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileContent {
    private final String fileName;
    private final List<String> lines;

    public FileContent(String fileName, List<String> lines) {
        this.fileName = fileName;
        this.lines = lines;
    }


    public int getIndex() {
        String intStr = fileName.replaceAll("[^\\d]", "");
        return Integer.parseInt(intStr);
    }
}
