package com.zct.loyal.core.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class SysUser(private var id: Long = 0, private var username: String = "", private var password: String = "",private var roles:String,private var head:String?="") : UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var auths = ArrayList<GrantedAuthority>()
        auths.add(SimpleGrantedAuthority(roles))
        return auths
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}
