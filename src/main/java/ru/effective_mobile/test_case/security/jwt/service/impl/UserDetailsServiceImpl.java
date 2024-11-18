package ru.effective_mobile.test_case.security.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.effective_mobile.test_case.security.repository.SecurityRepository;
import ru.effective_mobile.test_case.utils.exception.exceptions.ObjectNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecurityRepository securityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return new AppUserDetails(securityRepository.findByEmail(email).orElseThrow(()-> {

            log.info("User with email: %s is no where near our Db".formatted(email));
            return new ObjectNotFoundException("No User with email: %s in our Db, sorry".formatted(email));
        }));
    }
}
