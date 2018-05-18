package com.zct.loyal.modular.system.role.service

import com.baomidou.mybatisplus.service.IService
import com.zct.loyal.modular.system.role.model.Role
import org.apache.ibatis.annotations.Param

/**
 * 角色相关业务
 */
interface IRoleService : IService<Role> {

    fun getRoles(id:Int):String
}
