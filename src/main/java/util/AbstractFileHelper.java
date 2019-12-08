package util;

import java.io.File;
import java.time.LocalDateTime;

public abstract class AbstractFileHelper {
    protected static final String PATH_TO_STORAGE = "C:\\TaxReportJ\\web\\files\\";

    protected File getFile() {
        LocalDateTime dateTime = LocalDateTime.now();
        return new File(PATH_TO_STORAGE + dateTime.toLocalDate() +
                "-" + dateTime.toLocalTime().getNano() + ".json");
    }
}
