package com.zct.loyal.modular.system.role.dao

import com.baomidou.mybatisplus.mapper.BaseMapper
import com.zct.loyal.modular.system.role.model.Role
import org.apache.ibatis.annotations.Param

interface RoleMapper : BaseMapper<Role> {
   fun getRoles(@Param("id") id:Int):String
}