package com.ingsis.jibberjabberposts.security;

import com.ingsis.jibberjabberposts.dto.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) throw new RequestRejectedException("No cookies");
            String jwt = null;
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) jwt = cookie.getValue();
            }
            //send request to auth service
            RestTemplate restTemplate = new RestTemplate();
            String authUrl = "http://localhost:8081/user-info";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cookie", "token=" + jwt);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<UserDto> userInfoResponse = restTemplate.exchange(authUrl, HttpMethod.GET, httpEntity, UserDto.class);
            if (userInfoResponse.getStatusCodeValue() != 200) throw new RequestRejectedException("Invalid JWT");
            UserDto userInfo = userInfoResponse.getBody();

            if (userInfo == null) throw new RequestRejectedException("Invalid user information");

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userInfo.getRole()));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userInfo, jwt, authorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (RequestRejectedException e){
            response.sendError(401, "Unauthorized");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
