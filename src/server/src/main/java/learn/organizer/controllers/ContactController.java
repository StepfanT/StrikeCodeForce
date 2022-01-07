package learn.organizer.controllers;


import learn.organizer.domain.ActivityService;
import learn.organizer.domain.ContactService;
import learn.organizer.domain.Result;
import learn.organizer.models.Activity;
import learn.organizer.models.Contact;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/contact")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }


    @GetMapping("/{userId}")
    public Contact findByAppUserId(@PathVariable int userId) throws DataAccessException {
        return service.getContactByUserId(userId);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable int userId, @RequestBody Contact contact) {
        if (userId != contact.getUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Contact> result = service.editContact(contact);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

}
