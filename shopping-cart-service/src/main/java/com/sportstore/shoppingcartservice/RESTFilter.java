package com.sportstore.shoppingcartservice;


import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(1)
public class RESTFilter implements Filter {

    private UserInfo userInfo;

    @Bean
    public UserInfo userInfo() {
        UserInfo userInfo = new UserInfo();
        this.userInfo = userInfo;
        return userInfo;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null) {
            final HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);
            final HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange("http://localhost:5000/connect/userinfo", HttpMethod.GET, entity, Map.class);
            //TODO set fields in userInfo
        } else {
            userInfo.setName("Bob");
            userInfo.setTenant("TenantX");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
