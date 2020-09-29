package smart.BackEnd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time_Stamp {

    private String TS;

    public String getCreationTime() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE MM/dd/yyyy HH:mm:ss");
        this.TS = dateFormat.format(date);
        return TS;
    }



}
