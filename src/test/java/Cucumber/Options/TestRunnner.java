package Cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/Feature",
plugin={"json:target/jsonReports/cucumber-report.json",
"pretty",
"summary"  },
glue= {"StepDefinations"},
monochrome= true

)

public class TestRunnner {
	//tags= "@DeletePet"
}
