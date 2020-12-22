package com.techcenture.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Product {
	
	WebDriver driver;
	
	@FindBy (xpath="//h1[text()='Blouse']")
	private WebElement productName;
	
	@FindBy (xpath="//span[text()='New']")
	private WebElement productCond;
	
	@FindBy (xpath="//div[@id='short_description_content']")
	private WebElement productDesc;
	
	@FindBy (xpath = "//p[@class='socialsharing_product list-inline no-print']")
	List <WebElement> socials;
	
	@FindBy (xpath = "//i[@class='icon-plus']")
	private WebElement increaseQuantity;
	
	@FindBy (xpath = "//select[@id='group_1']")
	private WebElement size;
	

	@FindBy (xpath = "//button[@class='exclusive']")
	private WebElement addCartBtn;
	
	@FindBy (xpath = "//div[@class='clearfix']")
	private WebElement popUpElement;
	
	@FindBy(xpath = "//button[@type ='button']")
	private List<WebElement> socialMedia;
	
	@FindBy(css = "[name ='Submit']")
	private WebElement cart;
	
	@FindBy (id = "our_price_display")
	private WebElement priceForEachProduct;
	
	@FindBy (id = "layer_cart_product_quantity")
	private WebElement quantityOfSelectedItem;
	
	@FindBy (id = "layer_cart_product_price")
	private WebElement total;
	
	String pricePerItem= "";
	
	
	public Product(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void verifySocialMediaBtns(){
		
		for (int i = 0; i < socials.size(); i++) {
			if (socials.get(i).isDisplayed()) {
				System.out.println("Button is displayed!");
			}
		}
}
	
	public void social_media_buttons() {
		String[] expected = { "Tweet", "Share", "Google+", "Pinterest" };
		for (int i = 0; i < socialMedia.size(); i++) {
			String actual = socialMedia.get(i).getText();
			assertEquals(actual, expected[i]);
		}
		System.out.println("Buttons verified");
	}
	
	private void changeProductQuantity(int amount) throws InterruptedException {
	
		for(int i = 1; i<amount; i++) {
			increaseQuantity.click();
			Thread.sleep(300);
		}

	}
	
	private void selectProductSize(String s) {
		 
			Select select = new Select(size);
			select.selectByVisibleText(s);
	}
	
	private void selectProductColor(String color) {
		driver.findElement(By.xpath("//ul[@id='color_to_pick_list']//a[@title='"+color+"']")).click();
	}
	
	public void productSelection(int amount, String s, String color) throws InterruptedException {
		
		changeProductQuantity(amount);
		selectProductSize(s);
		selectProductColor(color);
	}
	
	public void verifyAndClickCart() {
		if (addCartBtn.isDisplayed()) {
			System.out.println("Add to cart button is displayed!");
			
			pricePerItem = priceForEachProduct.getText().replace("$", "");
			addCartBtn.click();
		}
		
	}
	
	public void verifyPopUpWindow() throws InterruptedException {
		
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		Thread.sleep(3000);
		
		String checkInsidewindows = driver.findElement(By.xpath("(//h2)[1]")).getText();
		assertEquals(checkInsidewindows, "Product successfully added to your shopping cart");
		System.out.println(checkInsidewindows);
		
	}
	
	public void verifyTotalPrice() {
		
		String quantity = quantityOfSelectedItem.getText(); // 2
		double totalPrice = Integer.parseInt(quantity) * Double.parseDouble(pricePerItem); //54.0
		
	//	String totPrice =  String.valueOf(totalPrice);
		double actualTotal = Double.parseDouble(total.getText().replace("$", ""));
		
		assertEquals(totalPrice, actualTotal );
	}

	
//	public void verify_item_info() {
//
//		String[] actual = { productName.getText(), productCond.getText(), productDesc.getText() };
//		String[] expected = { "Blouse", "New", "Short sleeved blouse with feminine draped sleeve detail." };
//		for (int i = 0; i < actual.length; i++) {
//			assertEquals(actual[i], expected[i]);
//		}
//		System.out.println("Item is verified correctly!");
//	}
	
//	public void quantySizeColor(int index, String color) throws InterruptedException {
//		driver.findElement(By.xpath("//span//i[@class='icon-plus']")).click();
//		String actual = driver.findElement(By.name("qty")).getAttribute("value");
//		assertEquals(actual, "2");
//		WebElement selectedElement = driver.findElement(By.name("group_1"));
//		Select actualSelect = new Select(selectedElement);
//		index = index - 1;
//		actualSelect.selectByIndex(index);
//		Thread.sleep(1000);
//		String actualSize = driver.findElement(By.xpath("(//select//option)[" + (index + 1) + "]")).getText();
//		switch (index) {
//		case 0:
//			assertEquals(actualSize.toUpperCase(), "S");
//			System.out.println("S");
//			break;
//		case 1:
//			assertEquals(actualSize.toUpperCase(), "M");
//			System.out.println("M");
//			break;
//		case 2:
//			assertEquals(actualSize.toUpperCase(), "L");
//			System.out.println("L");
//			break;
//		default:
//			break;
//		}
//		WebElement verifySelected;
//		String verifyWBhiteisSelect = "";
//		if (color.toLowerCase().equals("white")) {
//			WebElement white = driver.findElement(By.cssSelector("[title='White']"));
//			white.click();
//			verifySelected = driver.findElement(By.cssSelector("[class= 'selected']"));
//			verifyWBhiteisSelect = verifySelected.getAttribute("class");
//			if (verifyWBhiteisSelect.equals("selected")) {
//				String colorW = white.getAttribute("title");
//				assertEquals(colorW, "White");
//				System.out.println("white");
//			}
//		} else if (color.toLowerCase().equals("black")) {
//			WebElement black = driver.findElement(By.cssSelector("[title='Black']"));
//			black.click();
//			verifySelected = driver.findElement(By.cssSelector("[class= 'selected']"));
//			verifyWBhiteisSelect = verifySelected.getAttribute("class");
//			if (verifyWBhiteisSelect.equals("selected")) {
//				String colorB = black.getAttribute("title");
//				assertEquals(colorB, "Black");
//				System.out.println("Black");
//			}
//		}
//	}
	
}
