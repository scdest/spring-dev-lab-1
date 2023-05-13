package com.springdev.lab1.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentDto createDocument(DocumentDto documentDto, Integer ownerId) {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setName(documentDto.getName());
        document.setType(documentDto.getType());
        document.setBody(documentDto.getBody());
        document.setCreatedAt(LocalDateTime.now());
        document.setSignedAt(LocalDateTime.now());
        document.setOwnerId(ownerId);

        Document savedDocument = documentRepository.save(document);
        return convertToDto(savedDocument);
    }

    public DocumentDto updateDocument(UUID id, DocumentDto documentDto) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setName(documentDto.getName());
            document.setType(documentDto.getType());
            document.setBody(documentDto.getBody());
            document.setSignedAt(LocalDateTime.now());

            Document savedDocument = documentRepository.save(document);
            return convertToDto(savedDocument);
        } else {
            throw new NotFoundException("com.springdev.lab1.app.Document not found with ID " + id);
        }
    }

    public DocumentDto getDocument(UUID id) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            return convertToDto(document);
        } else {
            throw new NotFoundException("com.springdev.lab1.app.Document not found with ID " + id);
        }
    }

    public List<DocumentDto> getDocuments(Integer ownerId, Boolean signed, LocalDateTime from, LocalDateTime to) {
        List<Document> documents = documentRepository.findByOwnerId(ownerId);
        if (signed != null) {
            documents = documents.stream()
                    .filter(document -> signed.equals(document.getSignedAt() != null))
                    .collect(Collectors.toList());
        }
        if (from != null) {
            documents = documents.stream()
                    .filter(document -> document.getCreatedAt().isAfter(from))
                    .collect(Collectors.toList());
        }
        if (to != null) {
            documents = documents.stream()
                    .filter(document -> document.getCreatedAt().isBefore(to))
                    .collect(Collectors.toList());
        }
        return documents.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DocumentDto convertToDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(document.getId());
        documentDto.setName(document.getName());
        documentDto.setType(document.getType());
        documentDto.setBody(document.getBody());
        return documentDto;
    }
}
