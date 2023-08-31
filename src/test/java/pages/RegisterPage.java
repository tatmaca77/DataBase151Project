package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class RegisterPage {
    public RegisterPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="name")
    public WebElement nameInput;
    @FindBy(id="surname")
    public WebElement surNameInput;
    @FindBy(id="birthPlace")
    public WebElement birthPlaceInput;
     @FindBy(id="phoneNumber")
    public WebElement phoneNumberInput;
     @FindBy(xpath = "//input[@value='FEMALE']")
    public WebElement femaleRadioButton;
     @FindBy(xpath = "//input[@value='MALE']")
    public WebElement maleRadioButton;
    @FindBy(id= "birthDay")
    public WebElement birthDay;
    @FindBy(id= "ssn")
    public WebElement ssnInput;
    @FindBy(id= "username")
    public WebElement userNameInput;
    @FindBy(id= "password")
    public WebElement passwordInput;
    @FindBy(xpath = "//button[text()='Register']")
    public WebElement registerButton;
    @FindBy(xpath = "//div[@role='alert']")
    public WebElement alertMessage;



}
