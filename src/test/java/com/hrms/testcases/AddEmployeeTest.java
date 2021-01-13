package com.hrms.testcases;

import com.hrms.pages.AddEmployee;
import com.hrms.pages.DashboardPage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.VerifyingEmployee;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddEmployeeTest extends CommonMethods {

    @Test(groups = "smoke")
    public void addEmployee(){
        //login to the hrms
        LoginPage login = new LoginPage();
        login.adminLogin(ConfigsReader.getPropertyValue("username"), ConfigsReader.getPropertyValue("password"));
        //navigate to add employee page
        DashboardPage dash = new DashboardPage();
        jsClick(dash.PIMButton);
        jsClick(dash.addEmployeeBtn);
        //add employee
        AddEmployee addEmployee = new AddEmployee(driver);
        sendText(addEmployee.firstNameField,"qwert");
        sendText(addEmployee.lastNameField,"asdf");
        click(addEmployee.saveButton);
        //validation
        VerifyingEmployee verifyingEmployee=new VerifyingEmployee();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(addEmployee.firstNameField,verifyingEmployee.enteredFirstName);


    }



}
