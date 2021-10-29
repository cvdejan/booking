package com.cvdejan.booking.service;

import com.cvdejan.booking.model.AppUser;
import com.cvdejan.booking.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUser addUser(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    public AppUser getUser(String userName){
        return appUserRepo.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser=appUserRepo.findByUserName(userName);
        if (userName==null)
            throw new UsernameNotFoundException("User not found");

        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        appUser.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(appUser.getUserName(), appUser.getPassword(),authorities);
    }
}
