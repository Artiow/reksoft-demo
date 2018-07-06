package ru.reksoft.demo.mapper.manual.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class UserDetailsMapper {

    private static final String SEPARATOR = ", ";

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final TypeReference<Map<String, Object>> mapTypeReference = new TypeReference<Map<String, Object>>() {

    };


    public Map<String, Object> toMap(UserDetails userDetails) {
        Map<String, Object> map = mapper.convertValue(userDetails, mapTypeReference);
        Iterator<? extends GrantedAuthority> i = userDetails.getAuthorities().iterator();
        if (i.hasNext()) {
            StringBuilder builder = new StringBuilder(i.next().getAuthority());
            while (i.hasNext()) {
                builder.append(SEPARATOR).append(i.next().getAuthority());
            }

            map.put("authorities", builder.toString());
        }

        return map;
    }

    public UserDetails toUserDetails(Map<String, Object> map) {
        return User.builder()
                .password((String) map.get("password"))
                .username((String) map.get("username"))
                .authorities(((String) map.get("authorities")).trim().split(SEPARATOR))
                .accountExpired(!((boolean) map.get("accountNonExpired")))
                .accountLocked(!((boolean) map.get("accountNonLocked")))
                .credentialsExpired(!((boolean) map.get("credentialsNonExpired")))
                .disabled(!((boolean) map.get("enabled")))
                .build();
    }
}
