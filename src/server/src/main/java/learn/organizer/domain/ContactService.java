package learn.organizer.domain;

import learn.organizer.data.ContactRepository;
import learn.organizer.models.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    //validate contact

    private final ContactRepository repository;

    public Contact getContactByUserId(int id){
        return repository.getContactByUserId(id);
    }

    public Result<Contact> editContact(Contact contact){
        Result<Contact> result=new Result<>();
        result.setPayload(contact);
        if(!repository.editContact(contact)){
            result.addMessage("Failed to update contact",ResultType.INVALID);
        }
        return result;
    }

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public Result<Contact> validate(Contact contact){
        Result<Contact> result=new Result<>();
        if(contact.getFirstName().isBlank()){
            result.addMessage("Input a valid Name",ResultType.INVALID);
        }
        if(contact.getLastName().isBlank()){
            result.addMessage("Input a valid Lastname",ResultType.INVALID);
        }
        if(contact.getLocation().isBlank()){
            result.addMessage("Input a valid Location",ResultType.INVALID);
        }
        if(contact.getEmail().isBlank()){
            result.addMessage("Input a valid Email",ResultType.INVALID);
        }
        return result;
    }
}
