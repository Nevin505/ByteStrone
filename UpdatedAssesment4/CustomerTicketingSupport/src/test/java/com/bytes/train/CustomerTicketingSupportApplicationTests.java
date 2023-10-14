package com.bytes.train;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectPackages({"com.bytes.train.serviceImpl",
"com.bytes.train.serviceImpl"})
class CustomerTicketingSupportApplicationTests {

//	@Autowired
//	private UserService userService;
	
	@Test
	void contextLoads() {
//		assertNotNull(userService);
	}
	

}
