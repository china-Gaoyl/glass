package com.glass.user;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(IdUtil.simpleUUID());
		}


	}
}
