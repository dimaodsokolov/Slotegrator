package uiLogic.steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import uiLogic.pages.LoginPage;
import uiLogic.pages.MainPage;
import java.time.Duration;



public class CustomerStepDefinition extends BaseStepDefinition{
    @Given("^открыть браузер$")
    public void setUpBrowser() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @And("Авторизоваться в админке")
    public void openLoginPage() {
        loginPage = new LoginPage(driver);
        mainPage=new MainPage(driver);
        driver.get(config.getProperty("baseurl"));

        loginPage.fillUsername();
        loginPage.fillPassword();
        loginPage.Login();

        String actualResult = mainPage.getTextPlayersOnline();
        String expectedResult = "Players online / total";
        Assert.assertTrue(actualResult.contains(expectedResult),"Если тест провален, не найден текст на странице админки");

    }
    @And("^Открыть список игроков$")
    public void openListOFPlayers() {
        mainPage.ClickOnUsersList();
        String actualResult = mainPage.getUserNameSortLinkText();
        String expectedResult = "Username";
        Assert.assertEquals(expectedResult,actualResult);
    }
    @And("^Отсортировать по любому столбцу$")
    public void clickUsernameForSorting() {
        mainPage.ClickOnUsernameForSorting();

    }
    @And("Закрыть браузер")
    public void cleanup(){
        if (driver!=null){
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }
    @Then("Список отсортирован")
        public void verifyListIsSorted(){
        Boolean actual = mainPage.isTabSortedByUsername();
        Assert.assertTrue(actual,"если false, !sorted");
        }


}
