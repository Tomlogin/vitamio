package zhuoxin.andriody.com.vitamiodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import zhuoxin.andriody.com.vitamiodemo.okhttp.TestActivity;
import zhuoxin.andriody.com.vitamiodemo.retrofit.MyRetrofitActivity;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
private ListView listview;
    private Data[] datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.e("TAG-m", "onCreate: ");
       listview= (ListView) findViewById(R.id.lv);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,setdatas());
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(datas[position].intent);
    }

    public Data[] setdatas(){
        datas=new Data[]{
                new Data("A  android Mediplayer and surfaceview",new Intent(this,DemoA.class)),
                new Data("B  vitamio Mediplayer and surfaceview",new Intent(this,DemoB.class)),
                new Data("C  videoview and mediaController",new Intent(this,DemoC.class)),
                new Data("part and full",new Intent(this,mPlayerActivity.class)),
                new Data("okhttp",new Intent(this,TestActivity.class)),
                new Data("retrofit",new Intent(this,MyRetrofitActivity.class)),
                new Data("yoga",new Intent(this, zhuoxin.andriody.com.vitamiodemo.yoga.MainActivity.class))
        };
        return datas;
    }
    class Data{
        String name;
        Intent intent;

        public Data(String name, Intent intent) {
            this.name = name;
            this.intent = intent;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG-m", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG-m", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("TAG-m", "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG-m", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG-m", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG-m", "onDestroy: ");
    }
}

