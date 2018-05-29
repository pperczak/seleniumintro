import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WebElementsTests {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver","C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://przyklady.javastart.pl/test/full_form.html");
    }

    //@Test
    public void typingIntoWebElementTest() {
        WebElement userNameField = driver.findElement(By.id("username"));
        //sleep();
        userNameField.sendKeys("ericl@o2.pl");
        sleep();
        String typeUserNameField = userNameField.getAttribute("value");
        sleep();
        assertEquals(typeUserNameField, "ericl@o2.pl");
    }

    //@Test
    public void filePickingTest(){
        sleep();
        WebElement uploadFilePicker = driver.findElement(By.id("upload_file"));
        uploadFilePicker.sendKeys("C:\\temp\\capture.jpg");

        sleep();
    }

    //@Test
    public void typingAndClearingValueInsideWebElementTest() {
        WebElement userNameField = driver.findElement(By.id("username"));
        sleep();
        userNameField.sendKeys("Selenium Start");

        String typeUserNameValue = userNameField.getAttribute("value");
        sleep();

        assertEquals(typeUserNameValue, "Selenium Start");

        userNameField.clear();
        sleep();

        String emptyUserNameField = userNameField.getAttribute("value");
        assertEquals(emptyUserNameField,"");
    }
    //@Test
    public void checkRadioButtonTest() {

        WebElement femaleRadioButton = driver.findElement(By.cssSelector("input[value='female']"));
        WebElement maleRadioButton = driver.findElement(By.cssSelector("input[value='male']"));

        sleep();
        femaleRadioButton.click();
        sleep();
        assertTrue(femaleRadioButton.isSelected());
        sleep();

        maleRadioButton.click();
        sleep();
        assertTrue(maleRadioButton.isSelected());
        assertFalse(femaleRadioButton.isSelected()); //check if female is no longer selected since we have chosen male lately
    }

    //@Test
    public void checkboxButtonTest() {
        WebElement checkboxPizza = driver.findElement(By.cssSelector("input[value='pizza']"));
        WebElement checkboxSpaghetti = driver.findElement(By.cssSelector("input[value='spaghetti']"));
        WebElement checkboxHamburger = driver.findElement(By.cssSelector("input[value='hamburger']"));

        checkboxHamburger.click();
        sleep();
        checkboxPizza.click();
        sleep();
        assertTrue(checkboxHamburger.isSelected());
        assertTrue(checkboxPizza.isSelected());
        assertFalse(checkboxSpaghetti.isSelected());
    }

    @Test
    public void dropDownListingTest() {
        WebElement CountryWebElement = driver.findElement(By.name("country"));
        Select countryDropDown = new Select(CountryWebElement);
        countryDropDown.selectByValue("pl_PL");
        sleep();

    }


    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }

    //tylko do celow edukacyjnych zeby zobaczyc co sie dzieje jak selenium cos robi
    //nie stosowac Thread.sleep przy pisaniu testow!!!bo to zla praktyka
    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
