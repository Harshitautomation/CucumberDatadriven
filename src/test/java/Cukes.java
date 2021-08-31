import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        plugin ={"json:target/cucumber-reports/Cucumber.json",
                  "html:target/cucumber-reports/Cucumber.html",
                   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue={"Stepdef"},
        features = {"src/test/resources/features/"},
        monochrome = true,
        tags = ""
)

public class Cukes
{

}
