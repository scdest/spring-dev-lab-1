package com.springdev.lab1.app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository  extends JpaRepository<Document, UUID> {

    List<Document> findByOwnerId(Integer ownerId);
}
