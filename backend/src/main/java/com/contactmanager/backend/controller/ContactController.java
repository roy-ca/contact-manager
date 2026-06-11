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

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable int id, @RequestBody Contact updatedContact) {
        Optional<Contact> existing = contacts.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if(existing.isPresent()) {
            Contact c = existing.get();
            c.setName(updatedContact.getName());
            c.setPhone(updatedContact.getPhone());

            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        Optional<Contact> contactToDelete = contacts.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if(contactToDelete.isPresent()) {
            Contact c = contactToDelete.get();
            contacts.remove(c);

           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
