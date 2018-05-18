package com.zct.loyal.core.error

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class MainErrorController {

    @RequestMapping(value = "/error/{code}")
    fun error(@PathVariable code: Int): String {

        when (code) {
            404 -> {
                return "/error/404.html"
            }
            500 -> {
                return  "/error/500.html"
            }
            else->{
                return  "/error/500.html"
            }

        }
    }


}