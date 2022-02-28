package com.wanho.searchnearbyplaces.config;


import com.wanho.searchnearbyplaces.error.UserLoginExceptionHandler;
import com.wanho.searchnearbyplaces.user.User;
import com.wanho.searchnearbyplaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final UserLoginExceptionHandler userLoginExceptionHandler;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic().disable();

        httpSecurity.csrf();

        httpSecurity.rememberMe();

        httpSecurity.authorizeRequests()
                .antMatchers("/", "/user/**", "/login").permitAll()
                .anyRequest().authenticated();

        httpSecurity.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/place/list")
                .failureHandler(userLoginExceptionHandler)
                .permitAll();

        httpSecurity.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/img/**", "/css/**", "/js/**", "/assets/**");
        //web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = userService.findByEmail(email);
            if(user == null) {
                throw new UsernameNotFoundException(email);
            }

            return user;
        };

    }
}
