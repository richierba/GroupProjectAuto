package com.techcenture.pageobject;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;

	@FindBy(xpath = "//a[@class='blockbestsellers']")
	private WebElement BtnBestSellers;

	@FindBy(xpath = "//ul[@id='blockbestsellers']/li")
	List<WebElement> bestSellersProduct;

	@FindBy(xpath = "//*[@id=\"blockbestsellers\"]/li[3]/div/div[2]/div[2]/a[2]/span")
	private WebElement btnBlouseMore;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickBtnBestSellers() throws InterruptedException {
		BtnBestSellers.click();
		Thread.sleep(2000);
	}

	public void verifyBestSellersProducts() {

		int size = bestSellersProduct.size();

		assertEquals("7", String.valueOf(size));
		System.out.println("Successful");

	}

	public void verifyBestSellersNames() {

		String[] productNames = { "Printed Chiffon Dress", "Faded Short Sleeve T-shirts", "Blouse",
				"Printed Summer Dress", "Printed Dress", "Printed Summer Dress", "Printed Dress" };
		// String name = bestSellersProduct.get(1).getAttribute("title");

		// ul[@id='blockbestsellers']/li//h5/a
		for (int i = 0; i < productNames.length; i++) {
			assertEquals(productNames[i], driver.findElements(By.xpath("//ul[@id='blockbestsellers']/li//h5/a")).get(i)
					.getAttribute("title").trim());

		}

		System.out.println("Successful Next");
	}

	public void clickBtnBlouseMore() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(bestSellersProduct.get(2)).build().perform();
		actions.moveToElement(btnBlouseMore).click().build().perform();

		// actions.click().build().perform();
		System.out.println("Successfully clicked more!");

		Thread.sleep(2000);

	}


	public void clickProductMoreBtn(String productName) throws InterruptedException {
		WebElement moreBtn = driver.findElement(By.xpath("//ul[@id='blockbestsellers']/li//h5/a[@title='"+productName+"']/../../div[@class='button-container']/a/span[text()='More']"));
		
		new Actions(driver).moveToElement(bestSellersProduct.get(2)).build().perform();
		new Actions(driver).moveToElement(moreBtn).build().perform();
		moreBtn.click();
		System.out.println("Clicked more!");
		Thread.sleep(2000);
	}
}
