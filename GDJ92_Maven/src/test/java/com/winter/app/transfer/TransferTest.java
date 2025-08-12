package com.winter.app.transfer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferTest {

	@Autowired
	private Transfers transfers;
	@Autowired
	private Pay pay;
	
	@Test
	void test() {
		
		transfers.takeBus();
		
		transfers.takeSubway();
		
	}

}
