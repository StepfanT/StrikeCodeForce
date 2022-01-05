package learn.organizer.domain;

import learn.organizer.models.Contact;

public class ContactService {
    //validate contact

    public Result<Contact> validate(Contact contact){
        Result<Contact> result=new Result<>();
        if(contact.getFirstName().isBlank()){
            result.addMessage("Blank Name",ResultType.INVALID);
        }
        if(contact.getLastName().isBlank()){
            result.addMessage("Blank Contact",ResultType.INVALID);
        }
        if(contact.getLocation().isBlank()){
            result.addMessage("Blank Location",ResultType.INVALID);
        }
        if(contact.getEmail().isBlank()){
            result.addMessage("Blank Email",ResultType.INVALID);
        }
        return result;
    }
}
