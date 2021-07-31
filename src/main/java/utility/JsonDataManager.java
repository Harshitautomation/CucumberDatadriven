package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import dataType.customerdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonDataManager
{
    public String GetSceanrioDatainJsonFormat(List<List<String>> dTable,String jsonfile) throws IOException {
        String Path = System.getProperty("user.dir") + "/src/test/resources/testData/" +jsonfile;
        System.out.println(Path);
        String originalJson = GetTextFileContent(Path);
        String jsonpath,value,field,temp2;
        String temp1=originalJson;
        for(int i=0;i<dTable.size();i++)
        {
            field= dTable.get(i).get(0).replace("-",".");
            value=dTable.get(i).get(1);
            jsonpath= "$." + field;
            System.out.println("Jsonpath" + jsonpath);
            temp2=UpdateJsonString(temp1,jsonpath,value);
            temp1=temp2;

        }
        return temp1;
    }

    private String UpdateJsonString(String jsomString, String jsonpath, String value) {
        Configuration configuration = Configuration.builder()
                                        .jsonProvider(new JacksonJsonNodeJsonProvider())
                                        .mappingProvider(new JacksonMappingProvider())
                                        .build();
        JsonNode updatedJson = JsonPath.using(configuration).parse(jsomString).set(jsonpath,value).json();
        return updatedJson.toString();
    }

    public static String GetTextFileContent(String filePath) throws IOException {
        String data= new String(Files.readAllBytes(Paths.get(filePath)));
        return data;
    }

    public customerdata GetApplicationDataObject(String jsonString)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonString,customerdata.class);
    }
}
