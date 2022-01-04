package learn.organizer.models;

public class Contact {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    public Contact(int userId,String firstName,String lastName,String email,String location){
        this.userId=userId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.location=location;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
