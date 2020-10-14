package com.KpApp.SpringBootApp.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Manager,Master,Customer,Director,Deputy_director;

    @Override
    public String getAuthority() {
        return name();
    }
}
