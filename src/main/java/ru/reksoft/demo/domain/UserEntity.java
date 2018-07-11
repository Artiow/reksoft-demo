package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "demo")
public class UserEntity extends AbstractIdentifiedEntity {

    @Basic
    @Column(name = "login", nullable = false)
    private String login;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "surname", nullable = false)
    private String surname;

    @Basic
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Basic
    @Column(name = "address", nullable = false)
    private String address;

    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "pk.user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<CurrentBasketEntity> basket;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private UserRoleEntity role;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<CurrentBasketEntity> getBasket() {
        return basket;
    }

    public void setBasket(Collection<CurrentBasketEntity> baskets) {
        this.basket = baskets;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }
}
