package util;

import com.google.gson.*;
import domain.ReportStructure;
import exception.ReportFileException;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;


public class JsonHelper extends AbstractFileHelper {
    private static final Logger LOGGER = Logger.getLogger(JsonHelper.class);

    public ReportStructure readFromJson(String fileUrl) {
        ReportStructure structure;

        try (Reader reader = new FileReader(fileUrl)) {
            structure = new Gson().fromJson(reader, ReportStructure.class);
        } catch (IOException e) {
            LOGGER.warn("Your file was corrupted", e);
            throw new ReportFileException("Your file was corrupted", e);
        }

        return structure;
    }

    public String createJsonFileByFileContent(String fileContent) {
        validate(fileContent);
        File jsonFile = getFile();
        byFileContent(fileContent, jsonFile);

        return jsonFile.getAbsolutePath();
    }

    public void updateJsonFileByFileContent(File jsonFile, String fileContent) {
        validate(fileContent);
        byFileContent(fileContent, jsonFile);
    }

    public String createJsonFileByForm(ReportStructure formContent) {
        File jsonFile = getFile();

        try {
            byReportObject(formContent, jsonFile);
        } catch (ReportFileException e) {
            try {
                Files.delete(jsonFile.toPath());
            } catch (IOException ex) {
                LOGGER.warn("Your file has wrong structure", e);
                throw new ReportFileException("Your file has wrong structure", e);
            }
        }

        return jsonFile.getAbsolutePath();
    }

    public void updateJsonFileByForm(File jsonFile, ReportStructure formContent) {
        byReportObject(formContent, jsonFile);
    }

    private void byFileContent(String fileContent, File jsonFile) {
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new Gson();
            ReportStructure reportStructure = gson.fromJson(fileContent, ReportStructure.class);
            gson = new GsonBuilder().create();
            gson.toJson(reportStructure, writer);
        } catch (JsonSyntaxException | IOException e) {
            LOGGER.warn("Your file has wrong structure", e);
            throw new ReportFileException("Your file has wrong structure", e);
        }
    }

    private void byReportObject(ReportStructure formContent, File jsonFile) {
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(formContent, writer);
        } catch (JsonSyntaxException | IOException e) {
            LOGGER.warn("Your file has wrong structure", e);
            throw new ReportFileException("Your file has wrong structure", e);
        }
    }

    protected void validate(String jsonContent) {
        JsonObject asJsonObject;

        try {
            asJsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            LOGGER.warn("Your file has wrong structure", e);
            throw new ReportFileException("Your file has wrong structure", e);
        }

        if (!asJsonObject.has("fullName")
                || !asJsonObject.has("type")
                || !asJsonObject.has("innCode")
                || !asJsonObject.has("periodStart")
                || !asJsonObject.has("periodEnd")
                || !asJsonObject.has("incomeCode")
                || !asJsonObject.has("incomeValue")
                || !asJsonObject.has("outcomeCode")
                || !asJsonObject.has("outcomeValue")
                || !asJsonObject.has("percentCode")
                || !asJsonObject.has("percentValue")
                || !asJsonObject.has("clearCode")
                || !asJsonObject.has("clearValue")) {
            LOGGER.warn("Your file has wrong structure");
            throw new ReportFileException("Your file has wrong structure");
        }
    }
}