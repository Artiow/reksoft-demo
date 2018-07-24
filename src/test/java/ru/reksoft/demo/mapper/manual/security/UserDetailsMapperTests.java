package ru.reksoft.demo.mapper.manual.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import ru.reksoft.demo.boot.ReksoftDemoApplication;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUser;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReksoftDemoApplication.class)
public class UserDetailsMapperTests {

    private UserDetailsMapper userDetailsMapper;

    private Collection<GrantedAuthority> authorities;


    @Before
    public void setUp() {
        userDetailsMapper = new UserDetailsMapper();
        authorities = Collections.emptySet();
    }


    @Test
    public void generateMap() {

        // arrange
        IdentifiedUserDetails identifiedUserDetails = new IdentifiedUser(0, "username", "password", true, true, true, true, authorities);

        // act
        Map<String, Object> map = userDetailsMapper.toMap(identifiedUserDetails);

        // assert
        Assert.assertEquals(0, (int) map.get("id"));
        Assert.assertEquals("username", map.get("username"));
        Assert.assertEquals("password", map.get("password"));
        Assert.assertTrue((boolean) map.get("accountNonExpired"));
        Assert.assertTrue((boolean) map.get("accountNonLocked"));
        Assert.assertTrue((boolean) map.get("credentialsNonExpired"));
        Assert.assertTrue((boolean) map.get("enabled"));

        Assert.assertTrue(((Collection) map.get("authorities")).isEmpty());
    }

    @Test
    public void parseMap() {

        // arrange
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0);
        map.put("username", "username");
        map.put("password", "password");
        map.put("accountNonExpired", true);
        map.put("accountNonLocked", true);
        map.put("credentialsNonExpired", true);
        map.put("enabled", true);
        map.put("authorities", authorities);

        // act
        IdentifiedUserDetails identifiedUserDetails = userDetailsMapper.toIdentifiedUserDetails(map);

        // assert
        Assert.assertEquals(0, identifiedUserDetails.getId());
        Assert.assertEquals("username", identifiedUserDetails.getUsername());
        Assert.assertEquals("password", identifiedUserDetails.getPassword());
        Assert.assertTrue(identifiedUserDetails.isAccountNonExpired());
        Assert.assertTrue(identifiedUserDetails.isAccountNonLocked());
        Assert.assertTrue(identifiedUserDetails.isCredentialsNonExpired());
        Assert.assertTrue(identifiedUserDetails.isEnabled());

        Assert.assertTrue(identifiedUserDetails.getAuthorities().isEmpty());
    }
}
