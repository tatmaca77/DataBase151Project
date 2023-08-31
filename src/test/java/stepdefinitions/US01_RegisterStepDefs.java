package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.RegisterPage;
import utilities.Driver;
import utilities.JDBCUtils;
import utilities.ReusableMethods;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.JSUtils.clickElementByJS;

public class US01_RegisterStepDefs {
    RegisterPage registerPage = new RegisterPage();
    HomePage homePage = new HomePage();
    private static String fakeUsername;
    private static String fakeSsn;
    private static String fakePhoneNumber;
    ResultSet resultSet;


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
        Faker faker = new Faker();
        registerPage.nameInput.sendKeys(name);
        registerPage.surNameInput.sendKeys(surname);
        registerPage.birthPlaceInput.sendKeys(birth_place);
        fakePhoneNumber = faker.number().numberBetween(100, 999) + "-" + faker.number().numberBetween(100, 999) + "-" + faker.number().numberBetween(1000, 9999);
        registerPage.phoneNumberInput.sendKeys(fakePhoneNumber);

        if (gender.equalsIgnoreCase("male")){
            clickElementByJS(registerPage.maleRadioButton);
        }else {
            clickElementByJS(registerPage.femaleRadioButton);
        }


        registerPage.birthDay.sendKeys(birth_day);
        fakeSsn = faker.idNumber().ssnValid();
        registerPage.ssnInput.sendKeys(fakeSsn);
        fakeUsername = faker.name().username();
        registerPage.userNameInput.sendKeys(fakeUsername);
        registerPage.passwordInput.sendKeys(password);
    }

    @Then("click on register button")
    public void click_on_register_button() throws InterruptedException {

        Thread.sleep(3000);
        RegisterPage registerPage = new RegisterPage();
        clickElementByJS(registerPage.registerButton);
        ReusableMethods.waitForVisibility(registerPage.alertMessage, 5);
        assertTrue(registerPage.alertMessage.getText().contains("Guest User registered"));

    }


    @Then("close browser")
    public void closeBrowser() {

        Driver.getDriver().quit();

    }

    @When("connect to database")
    public void connectToDatabase() {


    }


    @Given("get guest user via username {string}")
    public void getGuestUserViaUsername(String username) throws SQLException {

        String sqlQuery = "SELECT * FROM guest_user WHERE username ='" + fakeUsername + "'";

        resultSet = JDBCUtils.executeQuery(sqlQuery);
        resultSet.next();


    }

    @Then("body contains birth_day {string}, birth_place {string}, gender {string}, name {string}, phone_number {string}, ssn {string}, surname {string}, username {string}")
    public void body_contains_birth_day_birth_place_gender_name_phone_number_ssn_surname_username(String birth_day, String birth_place, String gender, String name, String phone_number, String ssn, String surname, String username) throws SQLException {

        String actBirth_day = resultSet.getString("birth_day");
        String actBirth_place = resultSet.getString("birth_place");
        String actGender = resultSet.getString("gender");
        String actName = resultSet.getString("name");
        String actPhone_number = resultSet.getString("phone_number");
        String actSsn = resultSet.getString("ssn");
        String actSurname = resultSet.getString("surname");
        String actUsername = resultSet.getString("username");
        assertEquals(birth_day, actBirth_day);
        assertEquals(birth_place.trim(), actBirth_place.trim());
        assertEquals(gender, actGender);
        assertEquals(name, actName);
        assertEquals(fakePhoneNumber, actPhone_number);
        assertEquals(fakeSsn, actSsn);
        assertEquals(surname.trim(), actSurname.trim());
        assertEquals(fakeUsername, actUsername);
    }
}