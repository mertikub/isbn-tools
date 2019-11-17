package bm.isbntools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void checkAValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0321125215");		
		assertTrue(result, "first isbn");
		
		result = validator.checkISBN("0134494164");
		assertTrue(result, "second isbn");
	}
	
	@Test
	void TenDigitsISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");		
		assertTrue(result);
	}
	
	@Test
	void checkAnInvalid10DigitsISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0321125225");
		
		assertFalse(result);
	}
	
	@Test
	void checkAValid13DigitsISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780134494166");
		assertTrue(result);
		result = validator.checkISBN("9780321125217");
		assertTrue(result);
	}
	
	@Test
	void checkAnInvalid13DigitsISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780134494266");
		assertFalse(result);
	}
	
	@Test
	void nineDigitsISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();		
		assertThrows(NumberFormatException.class, () -> validator.checkISBN("123456789"));
	}

	@Test
	void lettersAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();		
		assertThrows(NumberFormatException.class, () -> validator.checkISBN("helloworld"));
	}

}
