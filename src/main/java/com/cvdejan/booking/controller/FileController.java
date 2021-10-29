package com.cvdejan.booking.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(value="/api/file")
public class FileController {

    public static final String DIRECTORY=System.getProperty("user.home")+"/Downloads/uploads";

    @PostMapping(value = "/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames=new ArrayList<>();
        for(MultipartFile file: multipartFiles){
            String fileName= StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage=get(DIRECTORY,fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            fileNames.add(fileName);
        }
        return new ResponseEntity<List<String>>(fileNames, HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName) throws IOException {
        Path filePath=get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName);
        if (!exists(filePath)) throw new FileNotFoundException(fileName+" was not found on the server");
        Resource resource=new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("File-Name",fileName);
        httpHeaders.add(CONTENT_DISPOSITION,"attachment:File-Name="+resource.getFilename());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(probeContentType(filePath)))
                .headers(httpHeaders).body(resource);

    }

}
