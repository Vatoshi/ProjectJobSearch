//package kg.attractor.jobsearch.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecuirityConfig {
//    private final DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        String fetchUser = "select email, password, enabled " +
//                "from users " +
//                "where email = ?";
//        String fetchRoles = "select email, role " +
//                "from users u, role r " +
//                "where u.email = ? " +
//                "and u.role_id = r.id";
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(fetchUser)
//                .authoritiesByUsernameQuery(fetchRoles);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetails() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("123qwe"))
//                .roles("ADMIN")
//                .authorities("FULL")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("321qwe"))
//                .roles("GUEST")
//                .authorities("READ_ONLY")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .formLogin(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/reviews/**").fullyAuthenticated()
//                        .requestMatchers("/movies/**").hasAuthority("ADMIN")
//                        .anyRequest().permitAll());
//
//        return http.build();
//    }
//}
