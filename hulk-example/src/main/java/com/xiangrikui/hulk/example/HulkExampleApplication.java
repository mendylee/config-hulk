package com.xiangrikui.hulk.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xiangrikui.hulk.client.spring.annotation.EnableHulkClient;

@SpringBootApplication
@EnableHulkClient
public class HulkExampleApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(HulkExampleApplication.class, args);
    }
}
