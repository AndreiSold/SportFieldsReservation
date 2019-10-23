package andrei.sold.upt.com.sportfieldsreservation.Models;

public class User {

    private String name;
    private String number;
    private String age;
    private String location;
    private String email;


    public User(String name, String number, String age, String location, String email) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.location = location;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }
}
