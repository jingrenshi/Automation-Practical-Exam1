package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class Page extends BasePage {

	WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "/html/body/div[4]/input[1]")
	WebElement ADD_ITEM_ELEMENT;
	@FindBy(how = How.XPATH, using = "/html/body/div[4]/input[2]")
	WebElement ADD_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"extra\"]/select[1]")
	WebElement CATEGORY_ELEMENT;
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/input[3]")
	WebElement TOGGLE_ALL_ELEMENT;
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/input[1]")
	WebElement REMOVE_BUTTON_ELEMENT;

	String generatedRandomItems;
	public void insertRandomItem(String itemName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,200)");
		
		int generatedNumber = generateRandomNumber(999);
		generatedRandomItems = itemName + generatedNumber;
		ADD_ITEM_ELEMENT.sendKeys(generatedRandomItems);
	}

	String generatedTestItem;
	public void insertTestItem(String testItem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,200)");

		int generatedNumber = generateRandomNumber(999);
		generatedTestItem = generatedNumber + testItem;
		ADD_ITEM_ELEMENT.sendKeys(generatedTestItem);
		clickAddButton();
	}

	public void selectCategory(String string) {

		selectFromDropDown(CATEGORY_ELEMENT, string);
	}
	
	public void EnterItems(String itemName, String category) {
		for (int i = 0; i < 5; i++) {
			insertRandomItem(itemName);
			selectCategory(category);
			clickAddButton();
		}
	}

	public void clickAddButton() {
		ADD_BUTTON_ELEMENT.click();
	}

	public void clickToggleAll() {
		TOGGLE_ALL_ELEMENT.click();
	}
	
	public void clickRemoveButton() {
		REMOVE_BUTTON_ELEMENT.click();
	}

//	todo[0]  
//	before_path + 0 + after_path
	String before_path = "todo[";
	String after_path = "]";

	public void validateEachItemCHeckedOrNot() {
		for (int i = 0; i < 5; i++) {
			boolean result = driver.findElement(By.name(before_path + i + after_path)).isSelected();
			Assert.assertEquals(result, true, "Unchecked!!!");
		}
	}

//	#todos-content > form > ul > li:nth-child(1)
	String checkbox_before_path = "#todos-content > form > ul > li:nth-child(";
	String checkbox_after_path = ") > input[type=checkbox]";
	String conten_before_path = "#todos-content > form > ul > li:nth-child(";
	String conten_after_path = ")";

	public void removeSingleItem() {
		for (int j = 1; j <= 6; j++) {
			String itemConten = driver.findElement(By.cssSelector(conten_before_path + j + conten_after_path))
					.getText();
			System.out.println(itemConten);
			if (itemConten.contains(generatedTestItem)) {
				driver.findElement(By.cssSelector(checkbox_before_path + j + checkbox_after_path)).click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				REMOVE_BUTTON_ELEMENT.click();
			}
		}
		
	}
	
	public void validateRemoveButton() {
		for (int k = 1; k <= 5; k++) {
			String eachItemConten = driver.findElement(By.cssSelector(conten_before_path + k + conten_after_path))
					.getText();
			boolean result = eachItemConten.contains(generatedTestItem);
			Assert.assertEquals(result, false, "Item was not been deleted! ");	
		}
	}
	
	public void validateAllitemsBeenRemoved() {
		takesScreenshot(driver);

	}
}
