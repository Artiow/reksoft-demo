package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.UserEntity;

public class UserDTO {

    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private String phone;


    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.patronymic = entity.getPatronymic();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
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

    public String getPatronymic() {
        return patronymic;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
