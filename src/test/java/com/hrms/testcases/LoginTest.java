package com.hrms.testcases;

import com.hrms.pages.DashboardPage;
import com.hrms.pages.LoginPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;


public class LoginTest extends CommonMethods {

    @Test
    public void adminLogin(){
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigsReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigsReader.getPropertyValue("password"));
        click(login.loginBtn);
        //validation
        assertTrue(DashboardPage.welcomeMessage.isDisplayed(), "Welcome message is NOT displayed");
    }

    @Test(dataProvider = "invalidData")
    public void invalidLoginErrorMessageValidation(String username, String password, String message){
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernameBox,username);
        sendText(loginPage.passwordBox,password);
        click(loginPage.loginBtn);
        String actualError = loginPage.errorMsg.getText();
        Assert.assertEquals(actualError, message, "Error message text is not matched");

    }

    @DataProvider
    public Object[][] invalidData(){
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Syntax123!", "Invalid credentials"},
                {"James", "", "Password cannot be empty"},
                {"", "Syntax123!", "Username cannot be empty"},
        };
        return data;
    }

}
