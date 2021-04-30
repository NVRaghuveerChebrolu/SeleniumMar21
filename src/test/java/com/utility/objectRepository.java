package com.utility;

import org.openqa.selenium.By;

public class objectRepository {
	
	public static final String ID =constants.ID+"&"+"";
	public static final String phptraveluserName =constants.NAME+"&"+"username";
	public static final String phptravelloginButton =constants.CLASS+"&"+"btn btn-primary btn-lg btn-block loginbtn";
	public static final String phptravelpassword=constants.XPATH+"&"+"//span[contains(text(),'Password')]";
	
	public static final String actualheader = constants.XPATH+"&"+"//font[contains(text(),'GMO OnLine')]";
	public static final String EnterGmoOnline = constants.XPATH+"&"+"//input[@type='button' and @value='Enter GMO OnLine']";
	public static final String QTY_GLASSES=constants.XPATH+"&"+"//input[@name='QTY_GLASSES']";
	public static final String orderQuantitySubmitButton=constants.XPATH+"&"+"//input[@name='bSubmit']";
	public static final String orderQuantityUnitPrice=constants.XPATH+"&"+"//tbody/tr[2]/td[4]";
	public static final String orderQuantityTotalPrice=constants.XPATH+"&"+"//tbody/tr[2]/td[5]";
	public static final String orderQtysubmitButton=constants.XPATH+"&"+"//input[@type='submit']";
	public static final String customerbillName=constants.XPATH+"&"+"//input[@name='billName']";
	public static final String customerbillAddress=constants.XPATH+"&"+"//input[@name='billAddress']";
	public static final String customerbillCity=constants.XPATH+"&"+"//input[@name='billCity']";
	public static final String customerbillState=constants.XPATH+"&"+"//input[@name='billState']";
	public static final String customerbillZipCode=constants.XPATH+"&"+"//input[@name='billZipCode']";
	public static final String customerbillPhone=constants.XPATH+"&"+"//input[@name='billPhone']";
	public static final String customerbillEmail=constants.XPATH+"&"+"//input[@name='billEmail']";
	public static final String customerCardType=constants.XPATH+"&"+"//select[@name='CardType']";
	public static final String customerCardNumber=constants.XPATH+"&"+"//input[@name='CardNumber']";
	public static final String customerCardDate=constants.XPATH+"&"+"//input[@name='CardDate']";
}
