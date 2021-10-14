package com.dynamodb.springbootdynamodb.service;

import com.dynamodb.springbootdynamodb.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class FetchFilesService implements FetchFiles {
    private static final Logger logger = LoggerFactory.getLogger(FetchFilesService.class);

    @Autowired
    MQService mqService;

    public void readFiles() {
        try {
            //Creating a File object for datasrource
            File directoryPath = new File("C:\\home\\test");
            //List of all files and directories
            File filesList[] = directoryPath.listFiles();
            Scanner sc = null;
            for (File file : filesList) {
                sc = new Scanner(file);
                String input;
                while (sc.hasNextLine()) {
                    input = sc.nextLine();
                    String[] fields = input.split(",");
                    Product product = new Product(fields[0].trim(), fields[1].trim(), fields[2].trim(), Integer.parseInt(fields[3].trim()), Integer.parseInt(fields[4].trim()));
                    System.out.println(product);
                    //publish to AWS SQS queue
                    mqService.pushToMQ(product);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
