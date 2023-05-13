package com.springdev.lab1.app;

import lombok.Data;

import java.util.UUID;

@Data
public class DocumentDto {
    private UUID id;

    private String name;

    private DocumentType type;

    private String body;
}
