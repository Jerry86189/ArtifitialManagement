package com.jerry86189.artifitialmanagement.controller;

import com.jerry86189.artifitialmanagement.service.impl.AsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TrainingController
 * Description: TODO
 * date: 2023/06/20 00:41
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/trains")
public class TrainingController {

    @Autowired
    private AsyncServiceImpl asyncService;

    @GetMapping("/both/describe_file/{fileId}")
    public ResponseEntity<String> describeFile(@PathVariable Long fileId) {
        String fileDescribe = asyncService.getFileDescribe(fileId);
        System.out.println(fileDescribe);
        return ResponseEntity.ok().body(fileDescribe);
    }

    
}
