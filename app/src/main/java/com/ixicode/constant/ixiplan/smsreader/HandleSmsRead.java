package com.ixicode.constant.ixiplan.smsreader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.ixicode.constant.ixiplan.common.util.AppUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by akash on 9/4/17.
 */

public class HandleSmsRead
{
    private String COLUMN_NAME_ADDRESS = "address";
    private final String CONST_IRCTC = "DM-IRCTCi";
    private final String CONST_BODY = "body";
    private final String CONST_DATE = "date";
    private final int TOTAL_BODY = 6;
    private final int CODE_STATION = 5;
    private final int CODE_DATE = 3;
    private final int CODE_DEP = 6;

    public ArrayList<Sms> getSms(Context context)
    {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        ArrayList<Sms> arrayList = new ArrayList<>();

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";



                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);

                    if(COLUMN_NAME_ADDRESS.equals(cursor.getColumnName(idx)) &&
                            CONST_IRCTC.equals(cursor.getString(idx)))
                    {
                        String body = AppUtil.getCursorString(cursor, CONST_BODY);
                        long date = AppUtil.getCursorLong(cursor, CONST_DATE);
                        arrayList.add(parseSms(body, date));
                    }
                }

                System.out.println(msgData);


                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        return arrayList;
    }

    private Sms parseSms(String body, long date)
    {
        Sms sms = new Sms();
        StringTokenizer stringTokenizerBody = new StringTokenizer(body, ",");

        int count = stringTokenizerBody.countTokens();

//        if(TOTAL_BODY == count)
        {
            for(int i  = 0; i < 2; i++)
            {
                stringTokenizerBody.nextToken();
            }
        }

        String dep = stringTokenizerBody.nextToken();
        Date doj = parseDoj(dep);
        sms.date = date;
        sms.doj = doj;

        stringTokenizerBody.nextToken();

        String strCode = stringTokenizerBody.nextToken();

        Place plc[] = parseStationCode(strCode);

        sms.from = plc[0];
        sms.to = plc[1];

        return sms;

    }

    private Date parseDoj(String str)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ":");
        stringTokenizer.nextElement();
        String doj = stringTokenizer.nextToken();
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd-MM-yy").parse(doj);
        }
        catch (Exception ex)
        {

        }


        return date1;

    }

    private Place[] parseStationCode(String code)
    {
        Place plc[] = new Place[2];
        StringTokenizer stringTokenizer = new StringTokenizer(code, "-");
        Place from = getStationXId(stringTokenizer.nextToken());
        Place to = getStationXId(stringTokenizer.nextToken());

        plc[0] = from;
        plc[1] = to;
        return plc;
    }

    private Place getStationXId(String code)
    {
        //Mocking data as of now as no API available for converting "CDG" to Ixigo city ID, but SMS is being read in real time.
        Place place = new Place();

        switch (code)
        {
            case "CDG":
                place.code = "1075379";
                place.name = "Chandigarh";
                place.id = "503b2a8fe4b032e338f13897";
            return place;

            default:
                place.code = "1065223";
                place.name = "New Delhi";
                place.id = "503b2a70e4b032e338f0ee67";
                return place;
        }

    }
}
