package com.zct.loyal.core.error

class LoyalException(var serviceExceptionEnum: ServiceExceptionEnum) : RuntimeException() {
    private var code: Int? = null

    private var errormessage: String? = null

    fun getCode(): Int? {
        return serviceExceptionEnum.code
    }

    fun setCode(code: Int?) {
        this.code = code
    }
    fun getErrormessage():String?{
        return serviceExceptionEnum.message
    }

    fun setMessage(errormessage: String) {
        this.errormessage = errormessage
    }
}