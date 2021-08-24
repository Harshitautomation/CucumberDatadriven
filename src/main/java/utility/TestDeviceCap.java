package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class TestDeviceCap {

    private static JSONArray parseJson(String jsonlocation) throws Exception {
        JSONParser jsonParser = new JSONParser();
        return (JSONArray) jsonParser.parse(new FileReader(jsonlocation));
    }

    private  static JSONObject getCap(String capname, String jsonlocation) throws Exception
    {
        JSONArray caparray = parseJson(jsonlocation);
        for(Object jsonobj : caparray)
        {
            JSONObject capability = (JSONObject) jsonobj;
            if(capability.get("name").toString().equalsIgnoreCase(capname))
            {
                return (JSONObject) capability.get("caps");
            }
        }
        return null;
    }

    private static HashMap<String,Object>converttomap(String capname,String jsonlocation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getCap(capname,jsonlocation).toString(),HashMap.class);
    }

    public static DesiredCapabilities getdesiredcap(String capname) throws Exception
    {
        String jsonlocation = System.getProperty("user.dir")+ "/src/test/resources/testdata/devices.json";

        HashMap<String,Object> caps = converttomap(capname,jsonlocation);
        caps.put("browserstack.debug",true);
        caps.put("browserstack.seleniumLogs",true);
        caps.put("browserstack.networkLogs",true);
        caps.put("browserstack.local",true);
        caps.put("acceptSslCerts",true);
        return new DesiredCapabilities(caps);
    }
}
