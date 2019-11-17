package bm.isbntools;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class StockManagementTests {

	@Test
	void testCanGetACorrectLocatorCode() {
		ExternalISBNDataService myWebService = new ExternalISBNDataService() {			
			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
			}
		};
		
		ExternalISBNDataService myDatabaseService = new ExternalISBNDataService() {			
			@Override
			public Book lookup(String isbn) {
				return null;
			}
		};
		

		StockManager stockManager = new StockManager();
		stockManager.setWebService(myWebService);
		stockManager.setDatabaseService(myDatabaseService);
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		assertEquals("7396J4", locatorCode);		
	}
	
	@Test
	void databaseIsUsedIfDataIsPresent() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
		
		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
				
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDatabaseService(databaseService);
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		fail("");
	}

}
