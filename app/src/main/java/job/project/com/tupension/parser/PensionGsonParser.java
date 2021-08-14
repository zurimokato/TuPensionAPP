package job.project.com.tupension.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import job.project.com.tupension.pojo.Pension;

public class PensionGsonParser {
    public List<Pension> leerFlujo(InputStream in) throws IOException{
        Gson gson=new Gson();
        JsonReader reader=new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Pension>pensiones=new ArrayList<>();
        Pension pension=null;
        reader.beginArray();
        while (reader.hasNext()){
            pension=gson.fromJson(reader,Pension.class);
            pensiones.add(pension);

        }

        reader.endArray();
        reader.close();

        return pensiones;


    }
}
