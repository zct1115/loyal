package com.zct.loyal

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class LoyalApplication

fun main(args: Array<String>) {
    runApplication<LoyalApplication>(*args)
}
