import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.techcenture.driver.Driver;
import com.techcenture.pageobject.HomePage;
import com.techcenture.pageobject.Product;
import com.techcenture.pageobject.Tabs;

public class TestForNow {
	
	@Test
	public void EndToEnd() throws InterruptedException {
		
		WebDriver driver=null;
		driver = Driver.getDriver("Chrome");
		
		HomePage homePage = new HomePage(driver);
		
		Product product = new Product(driver);

		Tabs tab = new Tabs(driver);
		
		
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

		//verify total price
		product.verifyTotalPrice();

		//verify total price plus shipping
		product.verifyTotalPriceShipping();

		Double shippingCost = product.getShipingCost();

		//click proceed to check out
		product.clickBtnProceedToCheckout();

		//get all tab names
		String[] tabNames = tab.getTabNames();

		//Verify you are under summary tab
		tab.verifyTabSummary(tabNames[0]);

		//Verify Delete option is available but don’t click it
		tab.verifyImgDelete();

		//Click Proceed to Checkout
		tab.clickBtnProceedToCheckOut();

		//jakes@yahoo.com    jakes123
		//Sign in
		tab.typeEmail("jakes@yahoo.com");
		tab.typePassword("jakes123");

		//Verify you are under tab Sign in
		tab.verifyTabSignIn(tabNames[1]);

		tab.clickBtnSignIn();

		// Verify You are under Address tab
		tab.verifyTabAddress(tabNames[2]);

		//Click Proceed to Checkout
		tab.clickBtnProceedToCheckOut();

		//Verify you are under Shipping tab
		tab.verifyTabShipping(tabNames[3]);

		//Verify radio button is selected, click term of conditions, verify shipping cost
		tab.verifySelectRadioBtnShipping();
		tab.clickTermsOfCond();
		tab.verifyShippingCost(shippingCost);

		//Proceed to checkout
		//tab.clickBtnProceedToCheckOut();
		tab.clickBtnShippingProceedToCheckOut();

		//Verify you are under Payment tab
		tab.verifyTabPayment(tabNames[4]);

		//Choose Pay by bank wire
		tab.clickBtnPayByBankWire();

		//Verify talking points of bank wire payment (texts)
		tab.verifyPayBankWireText();

		// Click I confirm my order
		tab.clickBtnIConfirmMyOrder();

		//Verify confirmation
		//tab.verifyPaymentText();

		//Copy the order reference and click back to orders (it will take you to order history)
		//Compare the order reference and verify it is the same value
		// Verify the status is On backorder
		//Log out


		//Close the browser
		Driver.quitBrowser();
		
	}

}
