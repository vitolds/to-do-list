package lv.javaguru.java2.service.security;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.database.springJPA.UserRoleRepository;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitol on 22/01/2017.
 */

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRole role : userRoleRepository.findByUserId(user.getUserId())){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
