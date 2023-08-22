package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    public String getFilePath(String fileName) {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\" + fileName;
    }

    public String getFilePath(String folderName, String fileName) {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\" + folderName + "\\" + fileName;
    }

    public JSONObject readCSVToJsonObj(String fileName) {
        JSONObject jsonObject = new JSONObject();
        int recordNumber = 0;
        try (CSVReader csvReader = new CSVReader(new FileReader(getFilePath(fileName)))) {
            String[] headerRow = csvReader.readNext();
            if (headerRow != null) {
                String[] dataRow;
                while ((dataRow = csvReader.readNext()) != null) {
                    JSONObject jsonObjectInside = new JSONObject();
                    for (int i = 0; i < dataRow.length; i++) {
                        jsonObjectInside.put(headerRow[i], dataRow[i]);
                    }
                    jsonObject.put(String.valueOf(recordNumber++), jsonObjectInside);
                }
            }
        } catch (CsvValidationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return jsonObject;
    }
}
