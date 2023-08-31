package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.RegisterPage;
import utilities.Driver;
import utilities.ReusableMethods;
import static org.junit.Assert.assertTrue;
import static utilities.JSUtils.clickElementByJS;

public class US01_RegisterStepDefs {
    RegisterPage registerPage = new RegisterPage();
    HomePage homePage = new HomePage();


    @Given("go to the {string}")
    public void goToThe(String url) {

        Driver.getDriver().get(url);

    }


    @Given("click on register")
    public void click_on_register() {

        homePage.registerLink.click();

    }


    @When("Enter name {string}, surname {string},birth_place {string}, phone_number {string},gender {string}, birth_day {string},ssn {string},username {string},password{string}")
    public void enterNameSurnameBirth_placePhone_numberGenderBirth_daySsnUsernamePassword(String name, String surname, String birth_place, String phone_number, String gender, String birth_day, String ssn, String username, String password) {
        registerPage.nameInput.sendKeys(name);
        registerPage.surNameInput.sendKeys(surname);
        registerPage.birthPlaceInput.sendKeys(birth_place);
        registerPage.phoneNumberInput.sendKeys(phone_number);
        clickElementByJS(registerPage.femaleRadioButton);
        registerPage.birthDay.sendKeys(birth_day);
        registerPage.ssnInput.sendKeys(ssn);
        registerPage.userNameInput.sendKeys(username);
        registerPage.passwordInput.sendKeys(password);
    }

    @Then("click on register button")
    public void click_on_register_button() throws InterruptedException {

        Thread.sleep(3000);
        RegisterPage registerPage = new RegisterPage();
        clickElementByJS(registerPage.registerButton);
        ReusableMethods.waitForVisibility(registerPage.alertMessage,5);
        assertTrue(registerPage.alertMessage.getText().contains("Guest User registered"));

    }


    @Then("close browser")
    public void closeBrowser() {

        Driver.getDriver().quit();

    }
}
