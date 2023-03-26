package com.LocalisFood.LocalisFood.Model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT,ROLE_FOOD_MAKER;

    public String getAuthority() {
        return name();
    }

}
