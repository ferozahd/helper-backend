package com.shippingoo.controller.Impl;


import com.shippingoo.Service.storage.StorageService;
import com.shippingoo.controller.FileController;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class FileControllerImpl implements FileController {
    private final StorageService storageService;

    
    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                 .body(file);
    }

}
