package com.ac.documentmanagement.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().cors()
                .and().oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(new JwtAuthenticationConverter()
                {
                    @Override
                    protected Collection<GrantedAuthority> extractAuthorities(final Jwt jwt)
                    {
                        Collection<GrantedAuthority> authorities = super.extractAuthorities(jwt);
                        ArrayList<String> resourcePermission = jwt.getClaim("permissions");

                      Map<String, Object> resourceRoles = jwt.getClaim("https://csp.accesscorp/user_authorization");
                        ArrayList<String> roles = (ArrayList<String>) resourceRoles.get("roles");

                        if (resourcePermission != null)
                            authorities.addAll(resourcePermission.stream()
                                    .map(x -> new SimpleGrantedAuthority("PERMISSIONS_" + x))
                                    .collect(Collectors.toSet()));

                        if (roles != null)
                            authorities.addAll(roles.stream()
                                    .map(x -> new SimpleGrantedAuthority("ROLES_" + x))
                                    .collect(Collectors.toSet()));

                        return authorities;
                    }
                })
        ;
    }

}
