package com.sadi.hackathonbase.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.util.StringUtils;

public class CookieBearerTokenResolver implements BearerTokenResolver {

    private final String cookieName;
    private final Logger log = LoggerFactory.getLogger(CookieBearerTokenResolver.class);

    public CookieBearerTokenResolver(String cookieName) {
        this.cookieName = cookieName;
    }

    @Override
    public String resolve(HttpServletRequest request) {
        // First, check for the standard Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            log.debug("Token received from header");
            return authorizationHeader.substring(7);
        }

        // If not found, check for the token in the cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    log.debug("Token received from cookie");
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}