package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdentifierCheck.class)
    @Min(value = 1, groups = IdentifierCheck.class)
    private Integer id;

    private String login;
    private String password;
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

    public String getPatronymic() {
        return patronymic;
    }

    public UserDTO setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public UserDTO setRole(UserRoleDTO role) {
        this.role = role;
        return this;
    }


    public interface IdentifierCheck extends UpdateCheck {

    }

    public interface CreateCheck extends UpdateCheck {

    }

    public interface UpdateCheck {

    }
}
