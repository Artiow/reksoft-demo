package ru.reksoft.demo.service.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface IdentifiedUserDetails extends UserDetails {

    int getId();
}
