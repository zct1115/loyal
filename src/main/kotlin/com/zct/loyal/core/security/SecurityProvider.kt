package com.zct.loyal.core.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class SecurityProvider():AuthenticationProvider {

    @Autowired
    val userDetailsService: UserDetailsService ?= null


    override fun authenticate(p0: Authentication): Authentication {
        val token=p0
        val name=token.name
        var userDetails: UserDetails? =null
        if (name!=null){
            userDetails=userDetailsService!!.loadUserByUsername(name)
        }

        if (userDetails==null){
            throw UsernameNotFoundException("用户名不存在")
        }
        else if(!userDetails.isEnabled){
            throw DisabledException("该用户已被停用")
        }
        else if(!userDetails.isAccountNonLocked){
            throw LockedException("该用户已被锁定")
        }
        else if(!userDetails.isAccountNonExpired){
            throw LockedException("该用户已过期")
        }
        else if(!userDetails.isCredentialsNonExpired){
            throw LockedException("该用户凭证已过期")
        }

        var password= token.credentials.toString()

        if(!BCryptPasswordEncoder().matches(password,userDetails.password))
        {
            throw BadCredentialsException("用户名或者密码有误！")
        }
        return UsernamePasswordAuthenticationToken(userDetails,password,userDetails.authorities)
    }

    override fun supports(p0: Class<*>?): Boolean {
        return true
    }
}