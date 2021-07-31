package Stepdef;

import io.cucumber.java.en.Given;
import utility.JsonDataManager;
import utility.SceanrioContext;

import java.io.IOException;
import java.util.List;

public class homepageStepDef extends BaseStep

{

    private SceanrioContext sceanrioContext;

    public homepageStepDef(SceanrioContext sceanrioContext)
    {
        this.sceanrioContext= sceanrioContext;
    }

    @Given("I am on Selenium practice site")
    public void iAmOnSeleniumPracticeSite(List<List<String>> dtable) throws IOException {

        String finaljsonDataString = new JsonDataManager().GetSceanrioDatainJsonFormat(dtable,"data.json");
        sceanrioContext.csdata= new JsonDataManager().GetApplicationDataObject(finaljsonDataString);
        System.out.println(sceanrioContext.csdata);
        basePage.intialize();


    }
}
