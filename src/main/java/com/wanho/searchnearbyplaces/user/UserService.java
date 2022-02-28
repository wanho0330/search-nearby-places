package com.wanho.searchnearbyplaces.user;

import com.wanho.searchnearbyplaces.place.Place;
import com.wanho.searchnearbyplaces.user.UserDto.Request;
import com.wanho.searchnearbyplaces.user.UserDto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.wanho.searchnearbyplaces.user.UserDto.Request.requestToEntity;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Response signup(Request request) {
        User user = requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return Response.entityToResponse(userRepository.save(user));
    }


    @Transactional
    public Response edit(User authUser, Request request) {

        User user = userRepository.findByEmail(authUser.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return Response.entityToResponse(userRepository.save(user));
    }

    @Transactional
    public Boolean delete(User authUser) {

        User user = userRepository.findByEmail(authUser.getEmail());
        if ( user == null) {
            return false;
        }

        userRepository.delete(user);
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
