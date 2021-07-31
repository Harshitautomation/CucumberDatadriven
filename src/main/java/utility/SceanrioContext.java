package utility;

import dataType.customerdata;
import io.cucumber.java.Scenario;

import java.util.HashMap;

public class SceanrioContext
{

    public customerdata csdata;

    public Scenario scenario;

    public HashMap<String,String> data = new HashMap<>();
}
