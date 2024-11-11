package com.demoqa.pageobject;

import org.openqa.selenium.By;

public class Tables {

	public static final By DELETE = By.xpath("//span[@title='Delete']");
	public static final By SUBMIT = By.id("submit");
	public static final By EDIT = By.xpath("//span[@title='Edit']");
	public static final By NEW = By.id("addNewRecordButton");
	public static final By FIRSTNAME = By.id("firstName");
	public static final By LASTNAME = By.id("lastName");
	public static final By EMAIL = By.id("userEmail");
	public static final By AGE = By.id("age");
	public static final By SALARY = By.id("salary");
	public static final By DEPARTAMENT = By.id("department");
}
