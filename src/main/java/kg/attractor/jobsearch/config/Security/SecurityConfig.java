package kg.attractor.jobsearch.config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String fetchUser = "select email, password, enabled " +
                "from users " +
                "where email = ?";
        String fetchRoles = "select email, role " +
                "from users u, role r " +
                "where u.email = ? " +
                "and u.role_id = r.id";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(fetchUser)
                .authoritiesByUsernameQuery(fetchRoles);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/vacancy/create**","/vacancy/edit/**","vacancy/delete/**").hasAnyAuthority("USER-EMPLOYER", "ADMIN")
                        .requestMatchers("/resume/category/**", "/resume/by-user/**", "/resume/id/**").hasAnyAuthority("USER-APPLICANT","USER-EMPLOYER", "ADMIN")
                        .requestMatchers("/resume/create**","/resume/edit/**","resume/delete/**").hasAnyAuthority("USER-APPLICANT", "ADMIN")
                        .requestMatchers("/user/add-avatar**","/user/edit/**","/user/delete/**").hasAnyAuthority("USER-APPLICANT", "ADMIN", "USER-EMPLOYER")
                        .anyRequest().permitAll());

        return http.build();
    }
}
