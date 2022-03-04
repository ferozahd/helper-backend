package com.shippingoo.Service.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
public interface StorageService {
    public void init();

    public Boolean save(MultipartFile file,String filename);
  
    public Resource load(String filename);
  
    public int deleteOne(String filenmae);
  
    public Stream<Path> loadAll();
}
