package com.demo.transaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransactionDemoController {

    @RequestMapping("/")
    public String index() {
        return "IndexPage";
    }

}

