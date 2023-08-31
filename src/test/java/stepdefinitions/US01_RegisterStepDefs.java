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

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.JSUtils.clickElementByJS;

public class US01_RegisterStepDefs {
    RegisterPage registerPage = new RegisterPage();
    HomePage homePage = new HomePage();
    private static String fakeUsername;
    private static String fakeSsn;
    private static String fakePhoneNumber;
    Connection connection;
    Statement statement;
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
        fakePhoneNumber = faker.number().numberBetween(100, 999) + " " + faker.number().numberBetween(100, 999) + " " + faker.number().numberBetween(1000, 9999);
        registerPage.phoneNumberInput.sendKeys(phone_number);

        if (gender.equalsIgnoreCase("male")) {
            clickElementByJS(registerPage.maleRadioButton);
        } else {
            clickElementByJS(registerPage.femaleRadioButton);
        }

        registerPage.birthDay.sendKeys(birth_day);
        fakeSsn = faker.idNumber().ssnValid();
        registerPage.ssnInput.sendKeys(ssn);
        fakeUsername = faker.name().username();
        registerPage.userNameInput.sendKeys(username);
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

    @Given("connect to database")
    public void connect_to_database() throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql://managementonschools.com:5432/school_management", "select_user", "43w5ijfso");

    }

    @When("get guest user via username {string}")
    public void get_guest_user_via_username(String username) throws SQLException {

        statement = connection.createStatement();

        String query = "select * from guest_user where username = '" + username + "'";

        resultSet = statement.executeQuery(query);

    }

    @Then("validate body contains birthday {string}, birthplace {string}, gender {string}, name {string}, phoneNumber {string}, ssn {string}, surname {string}, username {string}")
    public void validate_body_contains_birthday_birthplace_gender_name_phone_number_ssn_surname_username(String birthday, String birthplace, String gender, String name, String phoneNumber, String ssn, String surname, String username) throws SQLException {

        resultSet.next();
        String actualBirthDay = resultSet.getString("birth_day");
        String actualBirthPlace = resultSet.getString("birth_place");
        String actualGender = resultSet.getString("gender");
        String actualName = resultSet.getString("name");
        String actualPhoneNumber = resultSet.getString("phone_number");
        String actualSsn = resultSet.getString("ssn");
        String actualSurname = resultSet.getString("surname");
        String actualUsername = resultSet.getString("username");

        assertEquals(birthday, actualBirthDay);
        assertEquals(birthplace, actualBirthPlace);
        assertEquals(gender, actualGender);
        assertEquals(name, actualName);
        assertEquals(phoneNumber, actualPhoneNumber);
        assertEquals(ssn, actualSsn);
        assertEquals(surname, actualSurname);
        assertEquals(username, actualUsername);

    }

}