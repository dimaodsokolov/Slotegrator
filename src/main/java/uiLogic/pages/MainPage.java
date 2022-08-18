package uiLogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import uiLogic.utils.TestHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private static final String listOfUsersForSorting = "//*[@id='payment-system-transaction-grid']/table/tbody/tr/td[2]";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(),'Players online / total')]")
    private WebElement usersListTab;

    @FindBy(xpath = "//*[@class='sort-link' and contains(text(),'Username')]")
    private WebElement usernameSortLink;

    @FindBy(xpath = "//*[@id='payment-system-transaction-grid']/table/tbody")
    private WebElement usersTable;


    public String getTextPlayersOnline() {
        return usersListTab.getText();
    }

    public void ClickOnUsersList() {
        usersListTab.click();
    }

    public String getUserNameSortLinkText() {
        return usernameSortLink.getText();
    }

    public void ClickOnUsernameForSorting() {
        usernameSortLink.click();
        TestHelper.sleep5Seconds();
    }


    public Boolean isTabSortedByUsername() {

        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath(listOfUsersForSorting));
        for (WebElement we : elementList) {
            obtainedList.add(we.getText());

        }

        ArrayList<String> sortedList = new ArrayList<>();
        sortedList.addAll(obtainedList);

        Collections.sort(sortedList);


        return sortedList.equals(obtainedList);


    }

}
