import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String website = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.get(website);
		driver.manage().window().maximize();

	}

	@Test(priority = 1, description = "Radio Button Test", invocationCount = 3, enabled = false)
	public void Radio_Button_Example() {
		List<WebElement> AllRoadioButtons = driver.findElements(By.className("radioButton"));
		int randomIndex = rand.nextInt(AllRoadioButtons.size());
		AllRoadioButtons.get(randomIndex).click();

	}

	@Test(priority = 2, description = "DropDown dynamic Test", enabled = false)
	public void Dynamic_Dropdown_Example() throws InterruptedException {

		String[] countryCodes = { "OM", "JO", "Qa", "BA", "Sy", "LE", "US", "IR" };
		int randomIndex = rand.nextInt(countryCodes.length);

		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));
		DynamicListInput.sendKeys(countryCodes[randomIndex]);
		Thread.sleep(1000);
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

	}

	@Test(priority = 3, description = "Static Dropdown Test", enabled = false)
	public void Static_Dropdown_Example() {

		WebElement SelectElement = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(SelectElement);

//		sel.selectByIndex(3);
//		sel.selectByValue("option2");
		sel.selectByVisibleText("Selenium");
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

	}

	@Test(priority = 5, description = "THis Test To Move from Window To Other ", enabled = true)
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

	@Test(priority = 6, description = "Move to other Tap")
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

}
