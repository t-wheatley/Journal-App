package uk.ac.tees.q5071488.journal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 24/02/2017.
 */

public class DateComparator implements Comparator<HashMap<String, String>>
{
    private final String key;

    public DateComparator(String key)
    {
        this.key = key;
    }

    @Override
    public int compare(HashMap<String, String> first, HashMap<String, String> second)
    {
        String firstValue = first.get(key);
        String secondValue = second.get(key);

        Date d1 = null;
        Date d2 = null;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try
        {
            d1 = dt.parse(firstValue);
            d2 = dt.parse(secondValue);
        } catch (ParseException e)
        {

        }

        return d1.compareTo(d2);
    }
    /*
    @Override
    public int compare(JournalEntry o1, JournalEntry o2)
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try
        {
            d1 = dt.parse(o1.getDatetime());
            d2 = dt.parse(o2.getDatetime());;
        } catch (ParseException e)
        {

        }

        int comp = d1.compareTo(d2);

        return comp;
    }
    */
}
