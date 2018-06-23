package me.robinvdb.runetracker.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
@EnableWebSecurity
class SecurityWebConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/api/**")
    }

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()

        val admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build()

        manager.createUser(admin)

        return manager
    }
}
