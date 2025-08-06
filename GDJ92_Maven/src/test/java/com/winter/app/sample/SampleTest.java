package com.winter.app.sample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleTest {

	@Test
	void test() {
		// 1	=>	1
		// 2	=>	2
		// 4	=>	10
		// 5	=>	11
		// 6	=>	12
		// 7	=>	13
		// 9	=>	21
		// 
		
		int param = 11;
		
		int mul = 1;
		int result = 0;
		
		while (param > 0) {
			result += (param % 4) * mul;
			param /= 4;
			mul *= 10;
		}
		
		System.out.println(result);
	}
	
	@Test
	void test2() {
		// 편의점
		int price = 34_500;
		int money = 50_000;
		
		int result = money - price;
		
		int man = result / 10_000;
		result %= 10_000;
		int chun = result / 1_000;
		int baek = result % 1_000;
		
		System.out.println(man + "만 " + chun +"천 " + baek +"원");
	}

}
