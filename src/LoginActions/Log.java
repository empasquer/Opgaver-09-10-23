package LoginActions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private String log;
    private String date;

    public Log(String log){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        this.log = log;
        this.date = formatter.format(date);
    }

    public String getDate() {
        return date;
    }

    public String getLog() {
        return log;
    }

    @Override
    public String toString() {
        return getDate() + ": add log : " + getLog();
    }
}
