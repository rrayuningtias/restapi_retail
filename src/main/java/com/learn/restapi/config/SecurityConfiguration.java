package com.learn.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String H2_LOGIN
            = "SELECT USERNAME, PASSWORD, ACTIVE AS enabled "
            + "FROM S_USER WHERE USERNAME = ?";

    private static final String H2_PERMISSION
            = "SELECT A.USERNAME, R.ROLE_NAME as authority FROM S_USER A JOIN USERS_ROLE RA ON A.ID = RA.ID_USERS JOIN ROLE R ON RA.ID_ROLES = R.ID WHERE A.USERNAME = ?";

    @Autowired private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(H2_LOGIN)
                .authoritiesByUsernameQuery(H2_PERMISSION);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/css/**","/images/**","/vendor/**","/fonts/**","/js/**","/h2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/index").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/webTransaction/checkout").hasRole("CUSTOMER")
                .antMatchers("/webUser/detailsUser/updateUser/").hasRole("ADMIN")
                .antMatchers("/webUser/delete/{id}").hasRole("ADMIN")
                .antMatchers("/webWarehouse/createWarehouse").hasRole("ADMIN")
                .antMatchers("/webWarehouse/delete/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home/index", true)
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/error");
    }

}
