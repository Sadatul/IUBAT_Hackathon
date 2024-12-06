package com.sadi.hackathonbase.utils;


import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Long getOwnerID(){
        String owner = SecurityContextHolder.getContext().getAuthentication().getName();
        return Long.parseLong(owner);
    }

//    public static Boolean isSubscribedWithPackage(SubscriptionType ...types){
//        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long subscribed = principal.getClaim("subscribed");
//        return Arrays.stream(types).anyMatch((val) -> val.getValue() == subscribed);
//    }
//
//    public static void throwExceptionIfNotSubscribed(SubscriptionType ... type){
//        if(!isSubscribedWithPackage(type)){
//            throw new AuthorizationDeniedException(
//                    "User needs a subscribed account to access this feature", () -> false);
//        }
//    }

//    public static Boolean hasRole(String role){
//        return SecurityContextHolder.getContext().getAuthentication()
//                .getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(role));
//    }
}
