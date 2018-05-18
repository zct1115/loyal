package com.zct.loyal.modular.system.user.service.impl

import com.baomidou.mybatisplus.service.impl.ServiceImpl
import com.zct.loyal.modular.system.user.dao.UserMapper
import com.zct.loyal.modular.system.user.model.User
import com.zct.loyal.modular.system.user.service.IUserService
import org.springframework.stereotype.Service

/**
 *
 *
 * 管理员表 服务实现类
 *
 *
 */
@Service("userService")
class UserServiceImpl : ServiceImpl<UserMapper, User>(), IUserService {

    override fun setStatus(userId: Int?, status: Int): Int {
        return this.baseMapper.setStatus(userId, status)
    }

    override fun changePwd(userId: Int?, pwd: String): Int {
        return this.baseMapper.changePwd(userId, pwd)
    }


    override fun setRoles(userId: Int?, roleIds: String): Int {
        return this.baseMapper.setRoles(userId, roleIds)
    }

    override fun getByAccount(account: String): User {
        return this.baseMapper.getByAccount(account)
    }
}