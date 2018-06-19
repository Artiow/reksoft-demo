package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDescriptiveDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_role")
public class UserRoleEntity extends AbstractDescriptiveDictionaryEntity {

    private Collection<UserEntity> users;


    @OneToMany(mappedBy = "role")
    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }
}
