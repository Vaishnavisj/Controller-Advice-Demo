package com.example.poc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.poc.validator.PocValidator;

@SpringBootTest
class PocApplicationTests {

	@Test
	void contextLoads() {
	
	}

	@Test
	public void testDeptIsValid()
	{
		assertTrue(PocValidator.getInstance().IsValidDeptId(3));
		assertTrue(PocValidator.getInstance().IsValidDeptName("abc"));
		// to check invalid id
		assertFalse(PocValidator.getInstance().IsValidDeptId(0));
	}
	
}
