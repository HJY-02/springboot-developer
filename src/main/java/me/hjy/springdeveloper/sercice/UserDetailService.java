package me.hjy.springdeveloper.sercice;

import lombok.RequiredArgsConstructor;
import me.hjy.springdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    //    @RequiredArgsConstructor을 통해 밑에 주석이 만들어진다
//    public UserDetailService(UserRepository rep) {
//        this.userRepository
//    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException(email));
    }

    //LoadUserByUsename()
}
