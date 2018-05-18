package com.zct.loyal.core.security


import com.zct.loyal.modular.system.role.service.IRoleService
import com.zct.loyal.modular.system.user.service.IUserService
import org.omg.CORBA.UnknownUserException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service("userDetailsService")
class CustomUserService : UserDetailsService {

    @Autowired
    val userService: IUserService? = null

    @Autowired
    val roleService: IRoleService? = null

    override fun loadUserByUsername(p0: String): UserDetails {
        var userinfo = userService!!.getByAccount(p0)
        var roles=roleService!!.getRoles(userinfo.id)
        var user = SysUser(userinfo.id.toLong(), userinfo.account, userinfo.password, roles,userinfo.avatar)
        return user
    }
}
