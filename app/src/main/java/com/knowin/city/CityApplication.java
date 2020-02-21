package com.knowin.city;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.knowin.city.data.DaoDbOpenHelper;
import com.knowin.city.data.DomesticCity;

import org.greenrobot.greendao.database.DatabaseOpenHelper;

public class CityApplication extends Application {
    private static CityApplication sInstance = null;

    static public CityApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initDB();
    }

    private DomesticCityDao citDao = null;
    private IntlCityDao intlDao = null;
    public DomesticCityDao getDomesticCityDao(){
        return citDao;
    }

    public IntlCityDao getIntlDao(){
        return intlDao;
    }
    private void initDB() {
        DbCopyUtil.copyDb(getApplicationContext(), "city-db");

        DaoMaster.OpenHelper helper =new DaoDbOpenHelper(this,"city-db",null);
        //初始化时，必须使用DevOpenHelper，它负责创建DB， 否则会报表已存在的错误。
//        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this, "city-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster =new DaoMaster(db);

        DaoSession daoSession = daoMaster.newSession();

    // do this in your activities/fragments to get hold of a DAO
        citDao =daoSession.getDomesticCityDao();
        intlDao = daoSession.getIntlCityDao();
    }
}
