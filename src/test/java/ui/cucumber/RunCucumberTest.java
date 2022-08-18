package ui.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/main/resources/features"},
        tags = "@UiTests",
        glue = {"uiLogic.steps"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}