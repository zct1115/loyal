package com.zct.loyal.modular.system.role.service.impl

import com.baomidou.mybatisplus.service.impl.ServiceImpl
import com.zct.loyal.modular.system.role.dao.RoleMapper
import com.zct.loyal.modular.system.role.model.Role
import com.zct.loyal.modular.system.role.service.IRoleService
import org.springframework.stereotype.Service

@Service
class IRoleServiceImpl : ServiceImpl<RoleMapper,Role>(),IRoleService{

    override fun getRoles(id: Int): String {
        return this.baseMapper.getRoles(id)
    }

}