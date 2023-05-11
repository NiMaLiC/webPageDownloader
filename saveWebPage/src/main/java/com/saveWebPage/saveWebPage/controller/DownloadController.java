package com.saveWebPage.saveWebPage.controller;

import java.io.IOException;


import org.jsoup.Jsoup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("url") String url) throws IOException {

        // Download the web page
        String html = Jsoup.connect(url).get().html();

        // Convert the web page to a byte array
        byte[] data = html.getBytes();

        // Create a response entity with the byte array as the body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(data.length);
        headers.setContentDispositionFormData("attachment", "page.html");
        ResponseEntity<byte[]> response = new ResponseEntity<>(data, headers, HttpStatus.OK);

        return response;
    }
}



