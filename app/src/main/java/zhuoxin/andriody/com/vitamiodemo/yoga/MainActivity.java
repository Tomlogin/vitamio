package zhuoxin.andriody.com.vitamiodemo.yoga;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentTransaction transaction;
    private Button one,two,three;
    private ImageView leftimg,rightimg;
    private TextView title;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);
        one= (Button) findViewById(R.id.btnone);
        two= (Button) findViewById(R.id.btntwo);
        three= (Button) findViewById(R.id.btnthree);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        leftimg= (ImageView) findViewById(R.id.leftimg);
        leftimg.setOnClickListener(this);
        rightimg= (ImageView) findViewById(R.id.rightimg);
        title= (TextView) findViewById(R.id.title);
        Fragment fragmentone=new oneFragment();
        replace(fragmentone);


    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btnone:
               Fragment fragmentone=new oneFragment();
               replace(fragmentone);
               leftimg.setVisibility(View.VISIBLE);
               rightimg.setVisibility(View.VISIBLE);
               title.setText("课程");
               break;
           case R.id.btntwo:
               Fragment fragmenttwo=new twoFragment();
               replace(fragmenttwo);
               leftimg.setVisibility(View.GONE);
               rightimg.setVisibility(View.GONE);
               title.setText("计划");
           break;
           case R.id.btnthree:
               Fragment fragmentthree=new threeFragment();
               replace(fragmentthree);
               leftimg.setVisibility(View.GONE);
               rightimg.setVisibility(View.GONE);
               title.setText("更多");
               break;
           case R.id.leftimg:
               Intent intent=new Intent(this,AmapActivity.class);
                    startActivity(intent);
               break;
       }

    }
    private void replace(Fragment fragment){
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
