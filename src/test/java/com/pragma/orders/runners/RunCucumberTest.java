package com.pragma.orders.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.pragma.orders.steps",
    plugin = {
        "pretty",
        "html:target/site/serenity/index.html",
        "json:target/site/serenity/serenity.json"
    },
    tags = "@order-persistence"
)
public class RunCucumberTest {
}