package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String testsFilePath = args[0]; //"tests.json";
        String valuesFilePath = args[1]; //"values.json";
        String reportFilePath = args[2]; //"report.json";

        try {
            // Чтение JSON из файла tests.json
            JsonReader testsReader = new JsonReader(new FileReader(testsFilePath));
            JsonObject testsJson = new Gson().fromJson(testsReader, JsonObject.class);

            // Чтение JSON из файла values.json
            JsonReader valuesReader = new JsonReader(new FileReader(valuesFilePath));
            JsonObject valuesJson = new Gson().fromJson(valuesReader, JsonObject.class);

            // Формирование отображения значений по идентификаторам
            Map<Integer, String> valueMap = new HashMap<>();
            for (JsonElement valueElement : valuesJson.getAsJsonArray("values")) {
                JsonObject valueObject = valueElement.getAsJsonObject();
                int id = valueObject.get("id").getAsInt();
                String value = valueObject.get("value").getAsString();
                valueMap.put(id, value);
            }

            // Заполнение значений value в tests.json
            fillTestValues(testsJson.getAsJsonArray("tests"), valueMap);

            // Запись результата в файл report.json
            FileWriter writer = new FileWriter(reportFilePath);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(testsJson, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillTestValues(JsonArray testsArray, Map<Integer, String> valueMap) {
        for (JsonElement testElement : testsArray) {
            JsonObject testObject = testElement.getAsJsonObject();
            int id = testObject.get("id").getAsInt();
            if (testObject.has("values")) {
                fillTestValues(testObject.getAsJsonArray("values"), valueMap);
            }
            String value = valueMap.getOrDefault(id, "");
            testObject.addProperty("value", value);
        }
    }
}