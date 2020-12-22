package com.techcenture.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product {
	
	WebDriver driver;
	
	@FindBy (xpath="//h1[text()='Blouse']")
	private WebElement productName;
	
	@FindBy (xpath="//span[text()='New']")
	private WebElement productCond;
	
	@FindBy (xpath="//div[@id='short_description_content']']")
	private WebElement productDesc;
	
	public Product(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
