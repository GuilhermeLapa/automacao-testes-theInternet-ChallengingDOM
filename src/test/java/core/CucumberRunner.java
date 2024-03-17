package core;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
				tags = "@Testar",  
				glue = "steps", 
				plugin = {"pretty:target/pretty", "html:target/cucumber-reports.html"}, 
				monochrome = true, 
				dryRun = false
			)
public class CucumberRunner {

}
