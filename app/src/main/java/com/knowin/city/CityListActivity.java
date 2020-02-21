package com.knowin.city;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.knowin.city.data.DomesticCity;
import com.knowin.city.data.IntlCity;
import com.knowin.city.data.Province;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class CityListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        setContentView(R.layout.activity_city_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.list);
        loadData();
    }

    private void loadData(){
        int type = getIntent().getExtras().getInt("type");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
//        itemDecoration.setDrawable(getDrawable(R.drawable.divider));
//        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(new CityAdapter(type));

    }

    class CityAdapter extends RecyclerView.Adapter{
        AbstractDao dao = null;
        List<Object> listItems = null;
        int type = MainActivity.MSG_DOMESTIC_CITY;
        public CityAdapter(int type){
            if(type == MainActivity.MSG_INTERNATIONAL_CITY){
                dao = CityApplication.getInstance().getIntlDao();
            }else {
                dao = CityApplication.getInstance().getDomesticCityDao();
            }

            if(type == MainActivity.MSG_DOMESTIC_VIEWSPOT){
                listItems = dao.queryBuilder().where(DomesticCityDao.Properties.IsSpot.eq(Boolean.TRUE)).build().list();
            }else {
//                listItems = dao.queryBuilder().where(DomesticCityDao.Properties.Province.eq(1)
//                , DomesticCityDao.Properties.IsSpot.eq(Boolean.FALSE)).build().list();
                listItems = dao.queryBuilder().build().list();
            }
            this.type = type;
        }
        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            CityViewHolder viewholder = (CityViewHolder)holder;
            if(position == 0){
                viewholder.seq.setText("SeqNo.");
                viewholder.cityId.setText("cityId");
                viewholder.province.setText("省份");
                viewholder.city.setText("城市");
                viewholder.engName.setText("英文名字");
                return;
            }
            if(type != MainActivity.MSG_INTERNATIONAL_CITY ){
                DomesticCity city = (DomesticCity)listItems.get(position-1);
                viewholder.seq.setText(String.valueOf(position-1));
                viewholder.cityId.setText(String.valueOf(city.getMojiId()));
                viewholder.province.setText(Province.getProvinceName(city.getProvince()));
                viewholder.city.setText(city.getCity());
                viewholder.engName.setText(city.getEnglish_name());
            }else{
                IntlCity city = (IntlCity)listItems.get(position-1);
                viewholder.seq.setText(String.valueOf(position-1));
                viewholder.cityId.setText(String.valueOf(city.getMojiId()));
                viewholder.province.setText("");
                viewholder.city.setText(city.getCity());
                viewholder.engName.setText(city.getEnglish_name());
            }

        }

        @Override
        public int getItemCount() {
            if(listItems!=null){
                return listItems.size()+1;
            }

            return 1;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.city_item, parent, false);
            return new CityViewHolder(view);
        }

        class CityViewHolder extends RecyclerView.ViewHolder{
            public TextView city;
            public TextView seq;
            public TextView cityId;
            public TextView province;
            public TextView engName;
            public CityViewHolder(@NonNull View itemView) {
                super(itemView);
                city = itemView.findViewById(R.id.city);
                seq = itemView.findViewById(R.id.seqNo);
                cityId = itemView.findViewById(R.id.cityId);
                province = itemView.findViewById(R.id.province);
                engName = itemView.findViewById(R.id.engName);
            }
        }
    }


}
