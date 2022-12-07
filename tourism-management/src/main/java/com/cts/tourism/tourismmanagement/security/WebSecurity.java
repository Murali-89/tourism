package com.cts.tourism.tourismmanagement.security;

import com.cts.tourism.tourismmanagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    UserServiceImpl usersService;
    Environment environment;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurity(UserServiceImpl usersService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment environment ) {
        this.usersService = usersService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();*/

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/v1/**").permitAll()
                .and()
                .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();
    }

    private UserAuthenticationFilter getAuthenticationFilter() throws Exception {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter(usersService, environment, authenticationManager());

        userAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return userAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }


}
