package org.sfactor.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.sfactor.pages.LoginPage;
import org.testng.Assert;

public class LoginPageStepDef {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the OpenCart login page")
    public void i_am_on_the_open_cart_login_page() {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        loginPage = new LoginPage(driver);
    }

    @Given("I have entered a valid username and password")
    public void i_have_entered_a_valid_username_and_password() {
        loginPage.enterEmail("qatestertest@gmail.com");
        loginPage.enterPassword("Test@123");
    }

    @Given("I have entered invalid {string} and {string}")
    public void i_have_entered_invalid_username_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertEquals(loginPage.checkLogoutLink(), true);
    }

    @Then("I should see an error message indicating {string}")
    public void i_should_an_see_error_message_indicating(String errorMessage) {
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed());
    }

    @When("I click on the \"Forgotten Password\" link")
    public void i_click_on_the_forgotten_password_link() {
        loginPage.clickForgotPwdLink();
    }

    @Then("I should be redirected to the password reset page")
    public void i_should_be_redirected_to_the_password_rest_page() {
        Assert.assertTrue(loginPage.getForgotPwdPageUrl().contains("account/forgotten"));
    }
}
