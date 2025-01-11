import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTestCasesInAutomation {

	WebDriver driver = new ChromeDriver();
	String website = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	JavascriptExecutor js=(JavascriptExecutor) driver;
    Actions action=new Actions(driver);
	@BeforeTest
	public void mySetup() {
		driver.get(website);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, description = "Radio Button Test", invocationCount = 3, enabled = false)
	public void Radio_Button_Example() {
		List<WebElement> AllRoadioButtons = driver.findElements(By.className("radioButton"));
		int randomIndex = rand.nextInt(AllRoadioButtons.size());
		AllRoadioButtons.get(randomIndex).click();
		boolean expected=true;
		boolean actual=AllRoadioButtons.get(randomIndex).isSelected();
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2, description = "DropDown dynamic Test", enabled = false)
	public void Dynamic_Dropdown_Example() throws InterruptedException {

		String[] countryCodes = { "OM", "JO", "Qa", "BA", "Sy", "LE", "US", "IR" };
		int randomIndex = rand.nextInt(countryCodes.length);

		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));
		DynamicListInput.sendKeys(countryCodes[randomIndex]);
		Thread.sleep(1000);
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		
		String DataInSideTheInput= (String) js.executeScript("return arguments[0].value", DynamicListInput);
        String UpdatedValue=DataInSideTheInput.toLowerCase();
        boolean expected=true;
        boolean Actul=UpdatedValue.contains(countryCodes[randomIndex].toLowerCase());
        Assert.assertEquals(Actul, expected);
	}

	@Test(priority = 3, description = "Static Dropdown Test", enabled = false)
	public void Static_Dropdown_Example() {

		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(SelectElement);

		sel.selectByIndex(3);
//		sel.selectByValue("option2");
//		sel.selectByVisibleText("Selenium");
		
		
	}

	@Test(priority = 4, description = "CheckBoxes Test", enabled = false)
	public void Checkbox_Example() throws InterruptedException {

		List<WebElement> CheckBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
//		CheckBoxes.getFirst().click();
//		CheckBoxes.getLast().click();
		System.out.println(CheckBoxes.size());
		int randomindex = rand.nextInt(CheckBoxes.size());
		Thread.sleep(1000);
		CheckBoxes.get(randomindex).click();
		boolean Actual=CheckBoxes.get(randomindex).isSelected();
		boolean expected=true;
		Assert.assertEquals(Actual, expected);

	}

	@Test(priority = 5, description = "THis Test To Move from Window To Other ", enabled = false)
	public void Switch_Window_Example() {

		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));

		OpenWindowButton.click();
		List<String> WindowsHandels = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(WindowsHandels.size());

		driver.switchTo().window(WindowsHandels.get(1));
		WebElement ContactButton = driver.findElement(By.id("menu-item-9680"));
		ContactButton.click();
		System.out.println(driver.getTitle() + "Hello from second window");
		driver.close();
		driver.switchTo().window(WindowsHandels.get(0));

	}

	@Test(priority = 6, description = "Move to other Tap",enabled=false)
	public void Switch_Tab_Example() throws InterruptedException {

		WebElement OpenTapButton = driver.findElement(By.id("opentab"));
		OpenTapButton.click();
		List<String> WindowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(WindowsHandels.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
	}

	@Test(priority = 7, description = "Switch To Alert Example Test", enabled = false)
	public void Switch_To_Alert_Example() throws InterruptedException {
		WebElement nameBox = driver.findElement(By.id("name"));
		nameBox.sendKeys("Esraa");
//		WebElement AlertButton = driver.findElement(By.id("alertbtn"));
		WebElement ConfirmButton = driver.findElement(By.id("confirmbtn"));
//		AlertButton.click();
		Thread.sleep(3000);
//		driver.switchTo().alert().accept();
//		driver.switchTo().alert().dismiss();
//		
		ConfirmButton.click();
//		driver.switchTo().alert().accept();
//		driver.switchTo().alert().dismiss();

		System.out.println(driver.switchTo().alert().getText());
//		

	}
	
	@Test(priority=8,description = "Web Table Example",enabled=false)
	public void Web_Table_Example() {
		WebElement TheTable=driver.findElement(By.id("product"));
		List<WebElement> TheDataInSideTheTable = TheTable.findElements(By.tagName("tr"));
		for(int i=1;i<TheDataInSideTheTable.size();i++) {
			
//			System.out.println(TheDataInSideTheTable.get(i).getText()); 
			
			int totalTDInTheRow=TheDataInSideTheTable.get(i).findElements(By.tagName("td")).size();
			System.out.println(TheDataInSideTheTable.get(i).findElements(By.tagName("td")).get(totalTDInTheRow-1).getText());
		}
	}
	@Test(priority=9,description = "Hide and Show",enabled=false)
	public void Emlement_Displayed_Example() throws InterruptedException {
		WebElement HideButton=driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton=driver.findElement(By.id("show-textbox"));
		WebElement TextButton=driver.findElement(By.id("displayed-text"));
		SoftAssert myAssertion=new SoftAssert();
		js.executeScript("alert('soso')");
		driver.switchTo().alert().accept();
		js.executeScript("window.scrollTo(0,1500)");
		HideButton.click();
		Assert.assertEquals(TextButton.isDisplayed(), false);
//		myAssertion.assertEquals(TextButton.isDisplayed(),true);
		Thread.sleep(5000);
		ShowButton.click();
		Assert.assertEquals(TextButton.isDisplayed(), true);
		myAssertion.assertAll();
	}
	@Test(priority=10,description = "Enabled/Disabled Test",enabled=false)
	public void Enabled_Disabled_Example() throws InterruptedException {
		
		WebElement DisabledButton=driver.findElement(By.id("disabled-button"));
		WebElement EnabledButton=driver.findElement(By.id("enabled-button"));
		WebElement InputFeild=driver.findElement(By.id("enabled-example-input"));
		DisabledButton.click();
		js.executeScript("window.scrollTo(1,1900)");
		boolean ActualValue=InputFeild.isEnabled();
		boolean ExpectedValue=false;
		Assert.assertEquals(ActualValue, ExpectedValue);
		Thread.sleep(2000);
		EnabledButton.click();
		boolean ActualValue2=InputFeild.isEnabled();
		boolean ExpectedValue2=true;
		Assert.assertEquals(ActualValue2, ExpectedValue2);
		InputFeild.sendKeys("123");
		
	}
	@Test(priority=11,description = "Mouse Hover Example",enabled=false)
	public void Mouse_Hover_Example() throws InterruptedException {
		
		WebElement MouseHover=driver.findElement(By.id("mousehover"));
		js.executeScript("window.scrollTo(1,1900)");
		action.moveToElement(MouseHover).perform();;
		Thread.sleep(2000);
//		driver.findElement(By.linkText("Top")).click();
		driver.findElement(By.partialLinkText("Relo")).click();
	}
	@Test(priority=12,description = "Calendar Example",enabled=false)
	public void Calendar__Example() throws InterruptedException {
		
		WebElement CalendarButton =driver.findElement(By.linkText("Booking Calendar"));
		CalendarButton.click();
		List<String> WindowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(WindowsHandels.get(1));
		System.out.println(driver.getTitle());
		int totalAvailableDates=driver.findElements(By.className("date_available")).size();
		driver.findElements(By.className("date_available")).get(0).click();
		driver.findElements(By.className("date_available")).get(totalAvailableDates-1).click();
		System.out.println(totalAvailableDates);
		
	}
	
	@Test(priority=13,description = "iFrame Test",enabled=false)
	public void IFrame_Example() throws InterruptedException {
		WebElement Iframe=driver.findElement(By.id("courses-iframe"));
//	driver.switchTo().frame(0);
	driver.switchTo().frame("iframe-name");
//	driver.switchTo().frame(Iframe);
String output=driver.findElement(By.xpath("//*[@id=\"ct_text_editor-be8c5ad\"]/div/div/p")).getText();
	System.out.println(output);
	
		
	}
	
	@Test(priority=14,description = "Download File in the main Page",enabled=true)
	public void Download_Example() throws InterruptedException {
//		WebElement TheFile= driver.findElement(By.linkText("Download Apk files"));
//	WebElement TheFile=driver.findElement(By.xpath("//a[@href='http://codenboxautomationlab.com/wp-content/uploads/2022/12/APKFiles-1.zip']"));
//		WebElement TheFile=driver.findElement(By.xpath("//a[@class='wp-block-button__link wp-element-button']"));
		WebElement TheFile=driver.findElement(By.cssSelector(".wp-block-button__link.wp-element-button"));
		TheFile.click();

	
		
	}
	
	
	
	
	
	
	
	
	
	
	@Test(priority=14,description = "CheckTitle", enabled=false)
	public void CHeckTiltle() {
		String ActualTitle="Automation Practice - CodenBox AutomationLab";
		String ExpectedTitle=driver.getTitle();
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		
	}
	
	

}
