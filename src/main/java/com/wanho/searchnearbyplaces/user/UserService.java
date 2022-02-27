package com.wanho.searchnearbyplaces.user;

import com.wanho.searchnearbyplaces.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserDto.Response signup(UserDto.Request request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority("ROLE_OWNER")
                .build();

        userRepository.save(user);

        UserDto.Response response = UserDto.Response.builder()
                .email(request.getEmail())
                .build();

        return response;

    }


    @Transactional
    public UserDto.Response edit(User user, UserDto.Request request) {

        User byEmail = userRepository.findByEmail(user.getEmail());
        byEmail.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        UserDto.Response response = UserDto.Response.builder()
                .email(user.getEmail())
                .build();

        return response;
    }

    @Transactional
    public Boolean delete(User user) {

        User byEmail = userRepository.findByEmail(user.getEmail());
        if ( byEmail == null) {
            return false;
        }

        userRepository.delete(byEmail);
        return true;
    }


    /*
    *   SpringSecurity DetailServices 를 구현하기 위한 함수 (String : email -> User)
    *
     */
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}
