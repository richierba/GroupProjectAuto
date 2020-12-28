package com.techcenture.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.techcenture.utils.ConfigReader;

import org.openqa.selenium.WebElement;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tabs {

	WebDriver driver;
	String[] tabNames = new String[5];
	
	@FindBy(xpath = "//ul[@class='step clearfix']/li")
	List<WebElement> tabs;

	@FindBy(xpath = "//i[@class='icon-trash']")
	private WebElement imgDelete;

	@FindBy(xpath = "//span[text()=\' Summary\']")
	private WebElement tabSummary;

	@FindBy(xpath = "//span[text()=\' Sign in\']")
	private WebElement tabSignIn;

	@FindBy(xpath = "//span[text()=\' Address\']")
	private WebElement tabAddress;

	@FindBy(xpath = "//span[text()=\' Shipping\']")
	private WebElement tabShipping;

	@FindBy(xpath = "//span[text()=\' Payment\']")
	private WebElement tabPayment;

	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	private WebElement btnProceedToCheckOut;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "passwd")
	private WebElement passwd;

	@FindBy(id = "SubmitLogin")
	private WebElement btnSignIn;

	@FindBy(id = "delivery_option_420580_0")
	private WebElement radioBtnShipping;

	@FindBy(id = "cgv")
	private WebElement termsOfCond;

	@FindBy(xpath = "//button[@name='processCarrier']")
	private WebElement btnShippingProceedToCheckOut;

	@FindBy(xpath = "//div[@class='delivery_option_price']")
	private WebElement shippingCost;

	@FindBy(xpath = "//a[@class='bankwire']")
	private WebElement btnPayByBankWire;

	@FindBy(xpath = "//p[@class='cheque-indent']")
	List<WebElement> payBankWireText;

	@FindBy(xpath = "//span[text()='I confirm my order']")
	private WebElement btnIConfirmMyOrder;

	String[] bankWireText = { "You have chosen to pay by bank wire. Here is a short summary of your order:",
			"The total amount of your order comes to:", "We allow the following currency to be sent via bank wire:",
			"Bank wire account information will be displayed on the next page",
			"Please confirm your order by clicking \"I confirm my order.\"." };

	@FindBy(xpath = "//div[@class='box']")
	private WebElement paymentText;

	@FindBy(xpath = "//div[@class='box']/text()[11]")
	private WebElement refNum;

	@FindBy(xpath = "//a[@title='Back to orders']")
	private WebElement btnBackToOrders;

	String[] actualPaymentText = { "Please send us a bank wire with", "Amount", "Name of account owner",
			"Include these details", "Bank name",
			"Do not forget to insert your order reference ACYELRQTX in the subject of your bank wire",
			"Your order will be sent as soon as we receive payment.",
			"If you have questions, comments or concerns, please contact our expert customer support team" };

	@FindBy(xpath = "//table[@id='order-list']/tbody/tr[1]//a[@class='color-myaccount']")
	private WebElement orderRefId;

    //String actualPaymentText;

	@FindBy(xpath = "//table[@id='order-list']/tbody/tr[1]//span[@class='label dark']")
	private WebElement orderStatus;

	@FindBy(xpath = "//a[@class='logout']")
	private WebElement signOut;

	public Tabs(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// initialize tab names
	public String[] getTabNames() {
		for (int i = 0; i < tabs.size(); i++) {
			tabNames[i] = tabs.get(i).getText();
		}

		return tabNames;
	}

	
	private void verifyCurrentTab(String tabName) {

		//To verify the text value of tab name
		WebElement currentTabTxt = driver
				.findElement(By.xpath("//ul[@id='order_step']/li[contains(@class,'step_current')]/span"));
		String tabString = currentTabTxt.getText().trim();
		assertTrue(tabString.contains(tabName));
	}

    public void verifyAllTabs(String tabName){
        assertEquals(tabName, driver.findElements(By.xpath("//span[text()=\' "+tabName+"\']")));
    }

    public void verifyTabSummary(String summaryTitle){
        assertEquals(tabSummary.getText(),summaryTitle);


		//To verify tab value is white while active
		WebElement currentTabTxt = driver
				.findElement(By.xpath("//ul[@id='order_step']/li[contains(@class,'step_current')]/span"));
		String color = currentTabTxt.getCssValue("color");
		String hex = Color.fromString(color).asHex();

		assertEquals("#ffffff", hex);

	}

//	public void verifyTabSummary(String summaryTitle) {
//		verifyCurrentTab(summaryTitle);
//	}

	public void verifyTabSignIn(String signInTitle) {
		verifyCurrentTab(signInTitle);
	}

	public void verifyTabAddress(String addressTitle) {
		verifyCurrentTab(addressTitle);
		
	}

	public void verifyTabShipping(String shippingTitle) {
		verifyCurrentTab(shippingTitle);
	}

	public void verifyTabPayment(String paymentTitle) {

		verifyCurrentTab(paymentTitle);
	}

	public void verifyImgDelete() {
		Boolean display = imgDelete.isDisplayed();
		assertEquals(display, true);

	}

	public void clickBtnProceedToCheckOut() {
		btnProceedToCheckOut.click();
	}

	
	public void signIn() {
		
		String userEmail = ConfigReader.getProperty("useremail");
		String password = ConfigReader.getProperty("userpassword");
		
		typeEmail(userEmail);
		typePassword(password);
		clickBtnSignIn();
	}
	
	private void typeEmail(String userEmail) {
		email.sendKeys(userEmail);
	}

	private void typePassword(String userPassword) {
		passwd.sendKeys(userPassword);
	}

	private void clickBtnSignIn() {
		btnSignIn.click();
	}

	public void verifySelectRadioBtnShipping() {
		boolean radioBtn = radioBtnShipping.isSelected();
		assertEquals(radioBtn, true);
	}

	public void clickTermsOfCond() {
		termsOfCond.click();
	}

	public void clickBtnShippingProceedToCheckOut() {
		btnShippingProceedToCheckOut.click();
	}

	public void verifyShippingCost(Double actualCost) {
		assertEquals(actualCost, parseDouble(shippingCost.getText().replace("$", "")));
	}

	public void clickBtnPayByBankWire() {
		btnPayByBankWire.click();
	}

    public void verifyPaymentText() {
		paymentText.isDisplayed();
		//assertEquals(actualPaymentText,paymentText.getText());
		System.out.println("Payment Text Displayed");
	}
	public void verifyPayBankWireText() {
		for (int i = 0; i < payBankWireText.size(); i++) {
			// assertEquals(bankWireText[i], payBankWireText.get(i).getText());
			assertEquals(payBankWireText.get(i).getText(), bankWireText[i]);

		}
	}

		public String getRefNum () {
			String refNumber = paymentText.getText();
			//System.out.println(paymentText.getText());
			String[] splitAtRef = paymentText.getText().split("reference");
			refNumber = splitAtRef[1].trim().substring(0, 9);

			return refNumber;

		}
			public void clickBtnIConfirmMyOrder () {
				btnIConfirmMyOrder.click();
			}

//			public void verifyPaymentText () {
//				paymentText.isDisplayed();
//				System.out.println("Payment Text Displayed");
//
//			}

//			public String getRefNum () {
//				String refNumber = paymentText.getText();
//				String[] splitAtRef = paymentText.getText().split("reference");
//
//				refNumber = splitAtRef[1].trim().substring(0, 9);
//
//				return refNumber;
//			}

			public void clickBtnBackToOrders () {
				btnBackToOrders.click();
			}

			public void verifyOrderRefId (String acutalID){
				assertEquals(orderRefId.getText().trim(), acutalID);
			}

			public void verifyOrderStatus (String orderStat){
				assertEquals(orderStatus.getText().trim(), orderStat);
			}


			public void clickSignOut () {
				signOut.click();
			}

			public void setActualPaymentText (String refNum){
//        actualPaymentText = "Your order on My Store is complete.\n Please send us a bank wire with\n"+
//                "- Amount $56.00\n"+
//                "- Name of account owner Pradeep Macharla\n" +
//                "- Include these details xyz\n" +
//                "- Bank name RTP\n" +
//                "- Do not forget to insert your order reference "+refNum+" in the subject of your bank wire.\n" +
//                "An email has been sent with this information.\n" +
//                "Your order will be sent as soon as we receive payment.\n" +
//                "If you have questions, comments or concerns, please contact our expert customer support team. .";
			}
//    public void clickSignOut(){
//        signOut.click();
//    }


}