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


    public UserDTO() {

    }

    public UserDTO(UserEntity entity) {
        id = entity.getId();
        login = entity.getLogin();
        password = entity.getPassword();
        name = entity.getName();
        surname = entity.getSurname();
        phone = entity.getPhone();
        address = entity.getAddress();
    }


    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO that = (UserDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
