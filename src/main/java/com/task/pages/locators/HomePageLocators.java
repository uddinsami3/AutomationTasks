package com.task.pages.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageLocators {

    @FindBy(how= How.XPATH,using="//*[@data-key='endpoint']")
    public WebElement endpoints;

    public final By endpoint = By.xpath("//*[@id=\"console\"]/div[1]/ul/li[*]/a");
    public final By URI = By.xpath("//*[@id=\"console\"]/div[2]/div[1]/p/strong/a/span");
    public final By responseCode = By.xpath("//*[@id=\"console\"]/div[2]/div[2]/p/strong/span");
    public final By responseBody = By.xpath("//*[@id=\"console\"]/div[2]/div[2]/pre");
    public final By supportPageButton = By.xpath("/html/body/div[2]/div/div/div[1]/button/a");
    public final By inputAmount = By.xpath("//*[@id=\"supportForm\"]/div[1]/input[2]");
    public final By supportReqButton = By.xpath("//*[@id=\"supportForm\"]/button");
    public final By oneTimeAmountResult = By.xpath("//*[@id=\"ProductSummary-totalAmount\"]/span");
    public final By monthlySupportSelect = By.xpath("//*[@id=\"supportRecurring\"]");
    public final By monthlySupportResult = By.xpath("//*[@id=\"ProductSummary-totalAmount\"]/div/div[1]/span");
}
