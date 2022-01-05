package learn.organizer.domain;

import learn.organizer.models.Contact;

public class ContactService {
    //validate contact

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
