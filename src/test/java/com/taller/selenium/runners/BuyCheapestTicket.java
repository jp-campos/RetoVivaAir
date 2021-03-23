package com.taller.selenium.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/buy_cheapest_ticket.feature",
        glue = "com.taller.selenium.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class BuyCheapestTicket {
}
