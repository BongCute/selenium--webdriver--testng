package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	

	@Test
	public void TC_01_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		//Textbox
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		 
		//Button
		driver.findElement(By.id("btnLogin")).click();
		sleepInSecond(5);
		
		//At Dashboard page: "Add Employee" sub-menu link is not displayed
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());
		
		String firstname = "Luis";
		String lastName = "Suarez";
		String editFirstname = "Steven";
		String editLastName = "Gerranrd";
		
		//Open 'Add Employee' page
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		//Enter fistname/Lastname
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		
		//Verify hien thị thong tin employee tu dong duoc get tu API
		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		
		//Click save
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);
		
		By firstNameTextboxBy = By.id("personal_txtEmpFirstName");
		By lastNameTextboxBy = By.id("personal_txtEmpLastName");
		By employeeIDTextboxBy = By.id("personal_txtEmployeeId");
		
		//Verify FistName/LastName/ EmployeeID textbox disable
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		//Verify FistName/LastName/ EmployeeID textbox matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIDTextboxBy).getAttribute("value"), employeeID);
		
		//Click Edit button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		//Verify FistName/LastName/ EmployeeID textbox Ensable
		Assert.assertTrue(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		
		//Edit FistName/LastName
		driver.findElement(firstNameTextboxBy).clear();
		driver.findElement(firstNameTextboxBy).sendKeys(editFirstname);
	
		driver.findElement(lastNameTextboxBy).clear();
		driver.findElement(lastNameTextboxBy).sendKeys(editLastName);
		
		//Click Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(5);
		
		//Verify FistName/LastName/ EmployeeID textbox disable
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());
		
		//Verify FistName/LastName/ EmployeeID textbox matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), editFirstname);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), editLastName);
		
		//Click to 'Imigration' tab
		driver.findElement(By.xpath("//a[text() ='Immigration' ]")).click();
		
		//Click to 'Add' button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		//Enter to 'Imigration' nuber textbox
		driver.findElement(By.id("immigration_number")).sendKeys("31195855");
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("Steven's\nPassport\nID");
		sleepInSecond(5);
		
		//Click to 'Save' button
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);
		
		//Click Passport
		driver.findElement(By.xpath("//td[@class= 'document']/a[text() ='Passport']"));
		
		//Verify 
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"),"31195855");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"),"Steven's\\nPassport\\nID");
				
    }
	
	@Test
	public void TC_02_Register_Invalid_Email() {
	}
	
	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
	}
	
	@Test
	public void TC_04_Register_Invaid_Password() {
	}
	
	@Test
	public void TC_05_Register_Imvalid_Confirm_Password() {
	}
	
	@Test
	public void TC_06_Register_Imvalid_PhoneNumber() {
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public void sleepInSecond(long second) {
	try {
		Thread.sleep(second * 1000);
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
}
}