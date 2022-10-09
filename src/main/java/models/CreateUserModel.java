package models;


import lombok.Data;


@Data
public class CreateUserModel {

    private String name;
    private String gender;
    private String email;
    private String status;

    public CreateUserModel(String name,
                           String gender,
                           String email,
                           String status) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.status = status;
    }

    public CreateUserModel(String email,
                           String status) {

        this.email = email;
        this.status = status;
    }


}
