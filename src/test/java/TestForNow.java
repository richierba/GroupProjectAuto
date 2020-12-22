import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.techcenture.driver.Driver;
import com.techcenture.pageobject.HomePage;

public class TestForNow {
	
	@Test
	public void EndToEnd() throws InterruptedException {
		
		WebDriver driver=null;
		driver = Driver.getDriver("Chrome");
		
		HomePage homePage = new HomePage(driver);
		
		
		//Go to home page
		driver.get("http://automationpractice.com/index.php");
		
		//Scroll down to best sellers
		//Click best sellers
		homePage.clickBtnBestSellers();
		
		//Verify there are 7 products under best seller
		homePage.verifyBestSellersProducts();
		
		 //Verify the names of best seller items (hard code the values into method)
		homePage.verifyBestSellersNames();
		
		
		//Hover over Blouse and click more
		homePage.clickProductMoreBtn("Blouse");
		//clickBtnBlouseMore(); 
		
		//You will be taken to product page (so optionally you can create a product page)
		//Verify Item information such as name, 
		//condition, and 
		//description
		
		
		//Close the browser
		//driver.quit();
		
	}

}
