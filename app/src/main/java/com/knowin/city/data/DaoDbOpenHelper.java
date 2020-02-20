package com.knowin.city.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.knowin.city.DaoMaster;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

public class DaoDbOpenHelper extends DaoMaster.OpenHelper {
    public DaoDbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DaoDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        //super.onCreate(db);
    }
}
