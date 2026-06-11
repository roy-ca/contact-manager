package com.contactmanager.backend.controller;

import com.contactmanager.backend.model.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @GetMapping
    public List<Contact> getContacts() {
        List<Contact> list = new ArrayList<>();
        list.add(new Contact("Roy", "8848431405"));
        list.add(new Contact("Nikita", "5565"));
        return list;
    }
}
