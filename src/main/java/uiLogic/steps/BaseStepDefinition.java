package uiLogic.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import uiLogic.pages.LoginPage;
import uiLogic.pages.MainPage;
import uiLogic.utils.Config;

import java.util.Properties;

public class BaseStepDefinition {
    protected WebDriver driver;
    protected final Properties config = Config.loadProperties("test.properties");

    protected MainPage mainPage;
    protected LoginPage loginPage;


    public BaseStepDefinition(){
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver"));
        driver = new ChromeDriver();
    }
}
