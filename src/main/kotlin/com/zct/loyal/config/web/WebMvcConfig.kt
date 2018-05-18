package com.zct.loyal.config.web

import com.zct.loyal.core.error.ErrorPageInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig : WebMvcConfigurer{

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(ErrorPageInterceptor()).addPathPatterns("/**")
        super.addInterceptors(registry)
    }
}