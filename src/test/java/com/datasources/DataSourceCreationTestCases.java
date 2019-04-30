package com.datasources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import sample.testng.GenericMethods;
import sample.testng.SendEmails;
import sample.testng.TestNGReports;
import sample.testng.TestNGIReporter;
import sample.testng.*;

@Listeners(value =TestNGIReporter.class)

//@Listeners(TestNGReports.class)
public class DataSourceCreationTestCases {
	@BeforeTest
	@Parameters ({"browser"})
	public void openBrowserAndLogin() throws InterruptedException, IOException {
		GenericMethods.openBrowser("http://172.16.0.43:8080/home/#/login","chrome");
		Thread.sleep(5000);
		GenericMethods.enterText(com.pageobjects.LoginPageElements.username, "raghu");
		GenericMethods.enterText(com.pageobjects.LoginPageElements.password, "raghu@123");
		GenericMethods.buttonClick(com.pageobjects.LoginPageElements.loginBtn);
		Thread.sleep(5000);
	}

@Test(priority=0)
public static void createSqlServerDatasource() throws InterruptedException {
	Thread.sleep(5000);
	GenericMethods.buttonClick(com.pageobjects.HomePageElements.connectLink);
	Thread.sleep(5000);
	GenericMethods.buttonClick(com.pageobjects.HomePageElements.createDsLink);
	Thread.sleep(5000);
	GenericMethods.verifyText(com.pageobjects.CreateDatasouce.createDsPageTitle, "Create Data Connection");
	GenericMethods.createDs("SQLServer");
	GenericMethods.dataSourceDetails("sqlserver_0000", "172.16.7.80", "1433", "sa", "Welcome@1234");
	
}

	//@AfterTest
		@AfterSuite
	public static void quitBrowser() {
		GenericMethods.closeBrowser();
		SendEmails.sendEmails();
	}
}
