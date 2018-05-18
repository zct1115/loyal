package com.zct.loyal.core.error

import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class ErrorPageInterceptor:HandlerInterceptorAdapter() {

    var errorList = arrayListOf(404,500)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (errorList.contains(response.getStatus())) {
            response.sendRedirect("/error/" + response.getStatus())
            return false
        }
        return super.preHandle(request, response, handler)
    }


}
