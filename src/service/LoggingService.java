package service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

public class LoggingService {
    private String LOG_DIR;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static File logFile;

    public LoggingService() {
        try (InputStream input = new FileInputStream("src/resources/external-resources.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            this.LOG_DIR = prop.getProperty("LOG_DIR");
            createLogFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLogFile() throws IOException {
        if (LOG_DIR == null || LOG_DIR.isEmpty()) {
            throw new IOException("Diretório de log não configurado!");
        }
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = "API_" + dateFormat.format(new Date()) + ".log";
        logFile = new File(dir, fileName);
        logFile.createNewFile();
    }

    public void logData(String data) throws IOException {
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedMessage = dateTime + " => " + data;

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.append(formattedMessage + System.lineSeparator());
        }
    }
}
