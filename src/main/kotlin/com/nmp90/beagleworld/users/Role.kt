package com.nmp90.beagleworld.users

import org.springframework.security.core.GrantedAuthority


enum class Role : GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    override fun getAuthority(): String {
        return name
    }

}