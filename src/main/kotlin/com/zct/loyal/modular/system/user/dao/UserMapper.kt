package com.zct.loyal.modular.system.user.dao

import com.baomidou.mybatisplus.mapper.BaseMapper
import com.zct.loyal.modular.system.user.model.User
import org.apache.ibatis.annotations.Param

/**
 *
 *
 * 管理员表 Mapper 接口
 *
 *
 */
interface UserMapper : BaseMapper<User> {

    /**
     * 修改用户状态
     */
    fun setStatus(@Param("userId") userId: Int?, @Param("status") status: Int): Int

    /**
     * 修改密码
     */
    fun changePwd(@Param("userId") userId: Int?, @Param("pwd") pwd: String): Int

    /**
     * 设置用户的角色
     */
    fun setRoles(@Param("userId") userId: Int?, @Param("roleIds") roleIds: String): Int

    /**
     * 通过账号获取用户
     */
    fun getByAccount(@Param("account") account: String): User
}