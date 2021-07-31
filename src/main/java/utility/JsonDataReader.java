package utility;

import com.google.gson.Gson;
import dataType.customerdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader
{

    private final String filepath = System.getProperty("user.dir") +"/src/test/resources/testData/data.json";

    private customerdata custlist;

    public JsonDataReader()
    {
        custlist= getcustomerdata();
    }

    public customerdata getcustomerdata()
    {
        Gson gson= new Gson();
        BufferedReader bufferedReader = null;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(filepath));
            customerdata custdata = gson.fromJson(bufferedReader,customerdata.class);
            return custdata;
        }

         catch (FileNotFoundException e) {
            throw new RuntimeException("file not found" + filepath);
        }
        finally {
            try
            {
                if(bufferedReader!=null)
                {bufferedReader.close();
                }
            }
            catch (IOException exception)
            {}
        }

    }
}
