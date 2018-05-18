package com.zct.loyal.config.properties

import com.alibaba.druid.pool.DruidDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

import java.sql.SQLException

/**
 *
 * 数据库数据源配置
 *
 * 说明:这个类中包含了许多默认配置,若这些配置符合您的情况,您可以不用管,若不符合,建议不要修改本类,建议直接在"application.yml"中配置即可
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
class DruidProperties {

    var url = "jdbc:mysql://127.0.0.1:3306/loyal?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"

    var username = "root"

    var password = "123456"

    var driverClassName = "com.mysql.jdbc.Driver"

    var initialSize: Int? = 2

    var minIdle: Int? = 1

    var maxActive: Int? = 20

    var maxWait: Int? = 60000

    var timeBetweenEvictionRunsMillis: Int? = 60000

    var minEvictableIdleTimeMillis: Int? = 300000

    var validationQuery = "SELECT 'x'"

    var testWhileIdle: Boolean? = true

    var testOnBorrow: Boolean? = false

    var testOnReturn: Boolean? = false

    var poolPreparedStatements: Boolean? = true

    var maxPoolPreparedStatementPerConnectionSize: Int? = 20

    var filters = "stat"

    fun config(dataSource: DruidDataSource) {

        dataSource.url = url
        dataSource.username = username
        dataSource.password = password

        dataSource.driverClassName = driverClassName
        dataSource.initialSize = initialSize!!     //定义初始连接数
        dataSource.minIdle = minIdle!!             //最小空闲
        dataSource.maxActive = maxActive!!         //定义最大连接数
        dataSource.maxWait = maxWait!!.toLong()             //最长等待时间

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis!!.toLong()

        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis!!.toLong()
        dataSource.validationQuery = validationQuery
        dataSource.isTestWhileIdle = testWhileIdle!!
        dataSource.isTestOnBorrow = testOnBorrow!!
        dataSource.isTestOnReturn = testOnReturn!!

        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.isPoolPreparedStatements = poolPreparedStatements!!
        dataSource.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize!!

        try {
            dataSource.setFilters(filters)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }
}
