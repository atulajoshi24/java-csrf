package com.securecode.ssrf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CsrfController {

    @GetMapping("/form")
    public String showForm() {
        return "csrf-form";
    }

    @PostMapping("/submit")
    public String processForm() {
        return "success";
    }
}
