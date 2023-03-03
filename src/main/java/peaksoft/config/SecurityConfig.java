//package peaksoft.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        UserDetails admin = userBuilder.username("Baytik02")
//                .password("baytik01")
//                .roles("ADMIN").build();
//        UserDetails instructor = userBuilder.username("Aijamal")
//                .password("aijamal01")
//                .roles("INSTRUCTOR").build();
//        UserDetails student = userBuilder.username("Syimyk00")
//                .password("syimik00")
//                .roles("STUDENT").build();
//
//
//        return new InMemoryUserDetailsManager(admin, instructor, student);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .requestMatchers("/hospitals").hasAnyRole("ADMIN", "INSTRUCTOR", "STUDENT")
//                .requestMatchers("/hospitals/new").hasRole("ADMIN")
//                .requestMatchers("/hospitals/save").hasRole("ADMIN")
//                .requestMatchers("/hospitals/new/save/{hospitalId}/delete").hasAnyRole("ADMIN", "INSTRUCTOR")
//                .requestMatchers("/hospitals/new/save/{hospitalId}/edit").hasRole("ADMIN")
//                .requestMatchers("/hospitals/new/save/{id}update").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .permitAll();
//        return http.build();
//    }
//
//
//}
