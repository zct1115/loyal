package com.zct.loyal

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class LoyalApplicationTests {

	@Value("\${spring.mvc.view.prefix}")
	var prefix: String=""

	@Test
	fun contextLoads() {
		print(prefix)
	}

}
