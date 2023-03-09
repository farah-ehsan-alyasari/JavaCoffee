package com.javacoffee.JavaCoffee.adminPackageTest;


import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.adminPackage.service.UploadService;
import com.javacoffee.JavaCoffee.adminPackage.service.UploadServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaCoffeeApplication.class)
public class UploadServiceImplTest {



    @Test
    public void isFilePDFTest(){

        String testFileName = "testFileName.pdf";
        boolean result = UploadServiceImpl.isFilePDF(testFileName);
        Assertions.assertEquals(true, result);
    }
}
