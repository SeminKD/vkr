package vkr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import vkr.dao.DataAccess;

@Configuration
@EnableWebSecurity
public class WevSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().disable()
                .csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/", "/company",
                        "/map", "/mapFiltered", "/news", "/products",
                        "/fuel", "/otherProducts", "/service").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    . loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();

        //http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/media/**");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        String[] details = DataAccess.getLoginData();
        UserDetails user =
                User.withDefaultPasswordEncoder().
                        username(details[0]).
                        password(details[1]).
                        roles("ADMIN").
                        build();

        return new InMemoryUserDetailsManager(user);
    }
}
