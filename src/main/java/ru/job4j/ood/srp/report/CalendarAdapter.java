package ru.job4j.ood.srp.report;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {
    @Override
    public Calendar unmarshal(String v) throws Exception {
        return null;
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return formatter.format(v.getTime());
    }
}
