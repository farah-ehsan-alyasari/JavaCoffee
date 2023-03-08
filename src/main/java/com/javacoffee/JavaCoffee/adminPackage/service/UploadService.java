package com.javacoffee.JavaCoffee.adminPackage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    public void encryptPDFFile(String path, String fileName, MultipartFile multipartFile, String clientID) throws IOException;



}
