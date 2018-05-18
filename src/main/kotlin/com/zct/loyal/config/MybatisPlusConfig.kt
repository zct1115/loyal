package com.zct.loyal.config

import com.alibaba.druid.pool.DruidDataSource
import com.zct.loyal.config.properties.DruidProperties
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@MapperScan(basePackages = arrayOf("com.zct.loyal.modular.system.*.dao"))
class MybatisPlusConfig {

    @Autowired
    var druidProperties: DruidProperties? = null

    /**
     * 数据源
     */
    private fun dataSourceGuns(): DruidDataSource {
        val dataSource = DruidDataSource()
        druidProperties!!.config(dataSource)
        return dataSource
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "loyal", name = arrayOf("muti-datasource-open"), havingValue = "false")
    fun singleDatasource(): DruidDataSource {
        return dataSourceGuns()
    }

}