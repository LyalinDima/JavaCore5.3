import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        System.out.println(json);
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    private static String readString(String fileName) {
        JSONParser parser = new JSONParser();
        String result = "";
        try {
            Object object = parser.parse(new FileReader(fileName));
            JSONArray jsonObject = (JSONArray) object;
            result = jsonObject + "";

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<Employee> jsonToList(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> result = gson.fromJson(json, listType);
        return result;
    }
}
