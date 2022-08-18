package uiLogic.pages;

import uiLogic.utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class LoginPage {
    private static final Properties config = Config.loadProperties("test.properties");

    private final WebDriver driver;

    @FindBy(id = "UserLogin_username")
    private WebElement login;

    @FindBy(id = "UserLogin_password")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUsername() {
        login.clear();
        login.sendKeys(config.getProperty("prop.username"));
    }

    public void fillPassword() {
        password.clear();
        password.sendKeys(config.getProperty("prop.password"));
    }

    public MainPage Login() {
        loginButton.click();
        return new MainPage(driver);
    }


}
