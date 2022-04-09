package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.Page;
import util.BrowserFactory;

public class Remove_All_Item_Test3 {

	WebDriver driver;

	@Test
	public void validateAllItemsBeenRemoved() {
		driver = BrowserFactory.init();
		Page page = PageFactory.initElements(driver, Page.class);
		page.clickToggleAll();
		page.clickRemoveButton();
		page.validateAllitemsBeenRemoved();
	}
}
