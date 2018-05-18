package com.zct.loyal.core.error

/**
 * 抽象接口
 *
 */
interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    val code: Int?

    /**
     * 获取异常信息
     */
    val message: String?
}
