package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.model.GraduatePortalDocument;
import za.ac.cput.service.IGraduatePortalDocumentService;


import java.util.List;

@RestController
@RequestMapping("api/v1/graduate-recruitment-portal-api/document/")
@Slf4j
public class GraduatePortalDocumentController {
    private final IGraduatePortalDocumentService service;

    @Autowired
    public GraduatePortalDocumentController (IGraduatePortalDocumentService service)
    {
        this.service = service;
    }

    @PostMapping("save")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<GraduatePortalDocument> save(@RequestParam MultipartFile document, String userId)
    {
        GraduatePortalDocument file = null;
        try { file = service.save(document, userId); }
        catch (IllegalArgumentException exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(file);
    }

    @GetMapping("read/{documentId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> read(@PathVariable String documentId)
    {
        GraduatePortalDocument document = service.read(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.FOUND)
                             .contentType(MediaType.valueOf(document.getDocumentType()))
                             .body(document.getDocumentData());
    }

    @GetMapping("find-all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("find-all-documents")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> findAllDocuments()
    {
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.ALL)
                .body(service.findAllDocuments());
    }

    //@DeleteMapping("delete/{vacancyId}")
    public ResponseEntity<Void> deleteById(@PathVariable String documentId)
    {
        throw new UnsupportedOperationException();
    }

    //@DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody GraduatePortalDocument document)
    {
        throw new UnsupportedOperationException();
    }
}
