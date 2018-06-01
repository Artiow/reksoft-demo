package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.UserEntity;

public class UserDTO {

    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String address;


    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
    }


    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
