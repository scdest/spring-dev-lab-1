package com.springdev.lab1.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(@RequestHeader(name = "owner-id") Integer ownerId, @RequestBody DocumentDto documentDto) {
        DocumentDto createdDocument = documentService.createDocument(documentDto, ownerId);
        return new ResponseEntity<>(createdDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDto> updateDocument(@PathVariable UUID id, @RequestBody DocumentDto documentDto) {
        DocumentDto updatedDocument = documentService.updateDocument(id, documentDto);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable UUID id) {
        DocumentDto documentDto = documentService.getDocument(id);
        return new ResponseEntity<>(documentDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getDocuments(
            @RequestHeader(name = "owner-id") Integer ownerId,
            @RequestParam(name = "signed", required = false) Boolean signed,
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<DocumentDto> documents = documentService.getDocuments(ownerId, signed, from, to);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}
