package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class Users {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String accountType;
    private int id;
}
