package andrei.sold.upt.com.sportfieldsreservation.Models;

public class User {

    private String name;
    private String number;
    private String age;
    private String location;


    public User(String name, String number, String age, String location) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.location = location;
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
}
