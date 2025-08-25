package com.winter.app.lambda;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestFunctionTest {

	@Test
	void testF1() {
		int n1 = 10;
		int n2 = 10;
		
		TestFunction testFunction = (int a, int b) -> a + b;
		int result = testFunction.f1(n1, n2);
		
		System.out.println(result);
		
		// 내장
		Consumer<Integer> consumer = (n) -> System.out.println(n);
		consumer.accept(3);
	}

}
