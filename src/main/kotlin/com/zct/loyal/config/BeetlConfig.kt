package com.zct.loyal.config

import com.zct.loyal.config.properties.BeetlProperties
import com.zct.loyal.core.beetl.BeetlConfiguration
import org.beetl.core.resource.ClasspathResourceLoader
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * web 配置类
 */
@Configuration
class BeetlConfig {

    @Autowired
    internal var beetlProperties: BeetlProperties? = null

    /**
     * beetl的配置
     */
    @Bean(initMethod = "init")
    fun beetlConfiguration(): BeetlConfiguration {
        val beetlConfiguration = BeetlConfiguration()
        beetlConfiguration.setResourceLoader(ClasspathResourceLoader(BeetlConfig::class.java.classLoader, beetlProperties!!.prefix))
        beetlConfiguration.setConfigProperties(beetlProperties!!.properties)
        return beetlConfiguration
    }

    /**
     * beetl的视图解析器
     */
    @Bean
    fun beetlViewResolver(): BeetlSpringViewResolver {
        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.config = beetlConfiguration()
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8")
        beetlSpringViewResolver.order = 0
        return beetlSpringViewResolver
    }
}