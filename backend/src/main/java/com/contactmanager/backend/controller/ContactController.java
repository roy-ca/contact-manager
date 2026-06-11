package com.contactmanager.backend.controller;

import com.contactmanager.backend.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    List<Contact> contacts;
    private Integer counter;

    ContactController() {
        contacts = new ArrayList<>();
        counter = 0;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contacts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable int id) {
        return contacts.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        contact.setId(++counter);
        contacts.add(contact);
        return contact;
    }
}
