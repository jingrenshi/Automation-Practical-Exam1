package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.Page;
import util.BrowserFactory;

public class Toggle_All_CheckBox_Test1 {

	WebDriver driver;

	String itemName = "TVSHOW ";
	String category = "Test September2021 by Jingren";

	@Test
	public void validate_Toggle_All_CheckBox() {

		driver = BrowserFactory.init();
		Page page = PageFactory.initElements(driver, Page.class);

		page.EnterItems(itemName, category);

		page.clickToggleAll();
		page.validateEachItemCHeckedOrNot();
		
		
	}


	

}
