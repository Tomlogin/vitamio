package zhuoxin.andriody.com.vitamiodemo.okhttp;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.NewFrament;
import zhuoxin.andriody.com.vitamiodemo.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView r;
    private ViewPager viewPager;
    private RadioButton one,two,three;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Log.e("TAG", "onCreate: ");
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        one= (RadioButton) findViewById(R.id.one);
        two= (RadioButton) findViewById(R.id.two);
        three= (RadioButton) findViewById(R.id.three);
        list=new ArrayList<>();
        list.add(new NewFrament());
        viewPager.setAdapter(new mAdapter(getSupportFragmentManager(),list));


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("TAG", "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                viewPager.setCurrentItem(0);
            break;
            case R.id.two:
                break;
            case R.id.three:
                break;
        }

    }

    class mAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public mAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size()==0 ? 0:list.size();
        }
    }




}
