import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.techcenture.driver.Driver;
import com.techcenture.pageobject.HomePage;
import com.techcenture.pageobject.Product;

public class TestForNow {
	
	@Test
	public void EndToEnd() throws InterruptedException {
		
		WebDriver driver=null;
		driver = Driver.getDriver("Chrome");
		
		HomePage homePage = new HomePage(driver);
		
		Product product = new Product(driver);
		
		
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
		
		
		//Verify Item information such as name, 
		//product.verify_item_info();
		
		product.social_media_buttons();
		
		//product.quantySizeColor(2, "white");
		
		product.productSelection(2, "M", "White");
		
		product.verifyAndClickCart();
		
		product.verifyPopUpWindow();
		
		product.verifyTotalPrice();
		
		//Close the browser
		//driver.quit();
		
	}

}
