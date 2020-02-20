package com.knowin.city;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.knowin.city.data.DomesticCity;
import com.knowin.city.data.IntlCity;
import com.knowin.city.data.Province;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class WorkingLooperThread extends Thread {

    public Handler mHandler;

    @SuppressLint("HandlerLeak")
    public void run() {
        Looper.prepare();

        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                // process incoming messages here
                String file = (String)msg.obj;
                switch(msg.what){
                    case MainActivity.MSG_DOMESTIC_CITY:
                    case MainActivity.MSG_DOMESTIC_VIEWSPOT:
                    case MainActivity.MSG_INTERNATIONAL_CITY:
                        importCityToDb(file, msg.what);
                        break;
                    default:
                        break;
                }
            }
        };

        Looper.loop();
    }

    private void importCityToDb(String filename, int type){
        try {
            InputStream in = CityApplication.getInstance().getResources().getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            int success = 0;
            while((line = reader.readLine())!=null){
                 if(handleLine(line, type)> 0){
                     success ++;
                 }
            }
            in.close();
            Log.e("fred", String.format("insert %d records success", success));
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private long handleLine(String line, int type){
        //有些双引号里的内容包含逗号，所以必须去掉。
        String formatted = line.replaceAll("(?<=\").*?(?=\")", " ");
        String fields[] = formatted.split(",");
        if(fields.length<7){
            Log.e("fred", "line not qualified!!!");
            Log.e("fred", line);
            return -1;
        }
        if(type == MainActivity.MSG_INTERNATIONAL_CITY ) {
            IntlCity city = new IntlCity();
            city.setCity(fields[6]);
            city.setEnglish_name(fields[5]);
            try {
                return CityApplication.getInstance().getIntlDao().insert(city);
            }catch(Exception e){
                Log.e("fred", String.format("insert %s failed", fields[6]));
                e.printStackTrace();
            }

        }else{
            try {
                DomesticCity city = new DomesticCity();
                city.setCity(fields[6]);
                city.setEnglish_name(fields[5]);
                city.setMojiId(Long.valueOf(fields[1]));
                city.setProvince(Province.getProvinceId(fields[2]));
                city.setIsSpot(type == MainActivity.MSG_DOMESTIC_VIEWSPOT);
                return CityApplication.getInstance().getDomesticCityDao().insert(city);
            }catch(Exception e){
                Log.e("fred", String.format("insert province:%d city:%s failed", Province.getProvinceId(fields[2]), fields[6]));
                e.printStackTrace();
            }
        }

        return -1L;
    }


}
