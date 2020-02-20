package com.knowin.city;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int  MSG_DOMESTIC_CITY = 100;
    public static final int  MSG_DOMESTIC_VIEWSPOT = 200;
    public static final int  MSG_INTERNATIONAL_CITY = 300;

    WorkingLooperThread thread  = new WorkingLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        thread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        thread.stop();
        super.onDestroy();
    }

        //导入国内城市
    public void importDomestic(View v){
       loadDb(MSG_DOMESTIC_CITY);
    }

    //导入国内景点
    public void importViewSpot(View v){
        loadDb(MSG_DOMESTIC_VIEWSPOT);
    }

    //导入国际城市
    public void importIntl(View v){
        loadDb(MSG_INTERNATIONAL_CITY);
    }

    private void loadDb(int type){
        Intent i = new Intent(this, CityListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("type", type);
        startActivity(i);
    }


//    //导入国内城市
//    public void importDomestic(View v){
//       importFile("国内列表.csv", 100);
//    }
//
//    //导入国内景点
//    public void importViewSpot(View v){
//        importFile("国内景点.csv", 200);
//    }
//
//    //导入国际城市
//    public void importIntl(View v){
//        importFile("国际城市.csv", 300);
//    }

    /**
     *
     * @param fileName  城市列表文件
     * @param type 城市类型
     */
    private void importFile(String fileName, int type){
        Message msg = new Message();
        msg.what = type;
        msg.obj = fileName;
        thread.mHandler.dispatchMessage(msg);
    }
}
