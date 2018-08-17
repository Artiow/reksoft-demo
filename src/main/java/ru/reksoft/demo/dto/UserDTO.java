package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.validation.annotations.Phone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class UserDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 4, max = 45, groups = FieldCheck.class)
    private String login;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 4, max = 90, groups = FieldCheck.class)
    private String password;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String name;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String surname;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String patronymic;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 90, groups = FieldCheck.class)
    private String address;

    @NotNull(groups = FieldCheck.class)
    @Phone(groups = FieldCheck.class)
    private String phone;

    @Null(groups = FieldCheck.class)
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


    public interface IdCheck {

    }

    public interface FieldCheck extends UserRoleDTO.IdCheck {

    }
}
