@Register
Feature: Registration Feature

  Scenario Outline:Register
    Given go to the "https://managementonschools.com"
    Given click on register
    When Enter name "<name>", surname "<surname>",birth_place "<birth_place>", phone_number "<phone_number>",gender "<gender>", birth_day "<birth_day>",ssn "<ssn>",username "<username>",password"<password>"
    Then click on register button
    Then close browser

    Examples:
      | username  | birth_day  | birth_place | gender | name | phone_number | ssn         | surname | password |
      | johndoe13 | 25-01-2000 | New York    | Male   | John | 332-654-3987 | 139-35-7836 | Doe     | John.123 |



  Scenario Outline: Validate registered user
    #kayitli kullaniciyi database'de dogrula
    Given connect to database
    When get guest user via username "<username>"
    Then validate body contains birthday "<birthday>", birthplace "<birthplace>", gender "<gender>", name "<name>", phoneNumber "<phoneUmber>", ssn "<ssn>", surname "<surname>", username "<username>"
    Examples:
      | username | birthday | birthplace | gender | name | phoneUmber | ssn | surname |
      | johndoe13 | 2000-01-25 | New York  | Male | John | 332-654-3987 | 139-35-7836 | John.123  |




