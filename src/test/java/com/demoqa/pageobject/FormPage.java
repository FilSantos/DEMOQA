package com.demoqa.pageobject;

import org.openqa.selenium.By;

public class FormPage {
	public static final By CLOSEMODAL = By.id("closeLargeModal");
	public static final By MODAL = By.id("example-modal-sizes-title-lg");
	public static final By UPLOADDOCUMENT = By.id("uploadPicture");
	public static final By SUBMIT = By.id("submit");
	public static final By FIRSTNAME = By.id("firstName");
	public static final By LASTNAME = By.id("lastName");
	public static final By EMAIL = By.id("userEmail");
	public static final By SUBJECT = By.id("subjectsInput");
	public static final By SUBJECTOPTIONS = By.xpath("//div[contains(@class, 'subjects-auto-complete__menu')]//div[contains(@id,'react-select-2-option')]");
	public static final By PHONE = By.id("userNumber");
	public static final By ADDRESS = By.id("currentAddress");
	public static final By STATE = By.id("state");
	public static final By STATEOPTIONS = By.xpath("//div[contains(@id,'react-select-3-option')]");
	public static final By CITY = By.id("city");
	public static final By CITYOPTIONS = By.xpath("//div[contains(@id,'react-select-4-option')]");
	public static final By HOBBIES = By.xpath("//label[contains(@for,'hobbies-checkbox')]");
	public static final By GENDER = By.xpath("//label[contains(@for,'gender-radio')]");
	
}
