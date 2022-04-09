package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.Page;
import util.BrowserFactory;

public class Remove_Single_Item_Test2 {

	WebDriver driver;

	String itemName = "TVSHOW ";
	String category = "Test September2021 by Jingren";
	String testItem = "Need to be deleted item!";
	
	@Test
	public void validateRemoveSingleItem() {
		driver = BrowserFactory.init();
		Page page = PageFactory.initElements(driver, Page.class);

		page.clickToggleAll();
		page.clickRemoveButton();
		page.EnterItems(itemName, category);
		page.insertTestItem(testItem);
		page.removeSingleItem();
		page.validateRemoveButton();
	}
}
