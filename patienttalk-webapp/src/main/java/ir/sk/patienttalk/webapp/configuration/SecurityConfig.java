package ir.sk.patienttalk.webapp.configuration;

import ir.sk.patienttalk.webapp.security.JCGUserDetailsService;
import ir.sk.patienttalk.webapp.security.PatientTalkSimpleUrlAuthenticationSuccessHandler;
import ir.sk.patienttalk.webapp.security.PatientTalkUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ir.sk.patienttalk")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
     //   http.exceptionHandling().authenticationEntryPoint(loginUrlAuthenticationEntryPoint());

   //     http.addFilterBefore(authenticationFilter(), PatientTalkUsernamePasswordAuthenticationFilter.class);

        /*http.logout().invalidateHttpSession(true).logoutUrl("/logout/p").deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home");*/

        http.authorizeRequests()
                .antMatchers("/WEB-INF/**", "/view/**", "/jsp/**").denyAll()
                .antMatchers("/home", "/403", "/404", "/ban", "/favicon.ico").permitAll()
                .antMatchers("/login(/p)?(\\?lang=\\w*)?").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/search**").permitAll()
                .antMatchers("/redirectLogin**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/exception**").permitAll()
                .antMatchers("/forum/**").permitAll()
                .antMatchers("/manage(/**)?").hasAuthority("SUPER_ADMIN")
                .antMatchers("/users(/**)?").hasAuthority("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login/p")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).logoutUrl("/logout/p").deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home")
                .and()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository());
    }

    /*private PatientTalkUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        PatientTalkUsernamePasswordAuthenticationFilter authFilter = new PatientTalkUsernamePasswordAuthenticationFilter();
     //   authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
        authFilter.setAuthenticationManager(authenticationManagerBean());
        authFilter.setAuthenticationSuccessHandler(new PatientTalkSimpleUrlAuthenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login"));
        authFilter.setUsernameParameter("username");
        authFilter.setPasswordParameter("password");
        authFilter.setFilterProcessesUrl("/login/p");
        return authFilter;
    }*/

    /*@Bean
    public AuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login");
    }*/

    /*@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

    private CsrfTokenRepository csrfTokenRepository()
    {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
