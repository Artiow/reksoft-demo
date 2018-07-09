package ru.reksoft.demo.dto.shortcut;

import ru.reksoft.demo.dto.UserRoleDTO;
import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

public class UserShortDTO extends AbstractIdentifiedDTO {

    private Integer id;
    private String login;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private String phone;
    private UserRoleDTO role;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public UserShortDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserShortDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserShortDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserShortDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public UserShortDTO setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserShortDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserShortDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public UserShortDTO setRole(UserRoleDTO role) {
        this.role = role;
        return this;
    }
}
