package zhuoxin.andriody.com.vitamiodemo.yoga;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 11/3 0003.
 */
public class AmapActivity extends AppCompatActivity {
    private MapView mapview;
    private AMap aMap;
    private LatLng latLng;
    private UiSettings uiSettings;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        mapview = (MapView) findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
//        aMap=mapview.getMap();
//        initMapOptions();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapview.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapview.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapview.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapview.onSaveInstanceState(outState);
    }

    /**
     * 配置地图信息
     * “+ -”号是否可用【是否显示】
     * 指南针是否显示
     * 默认定位按钮
     * 缩放比例和高德地图Logo
     * 旋转手势不可用
     * 设置定位监听
     */
    private void initMapOptions() {
        latLng = new LatLng(30.670000, 104.060000);//实例化地图坐标点
        aMap = mapview.getMap();
        uiSettings = aMap.getUiSettings();
        uiSettings.setScaleControlsEnabled(true);//显示比例尺控件
        uiSettings.setZoomControlsEnabled(true);//设施“+ -”是否可以用来控制地图放大倍数
        uiSettings.setCompassEnabled(true);//控制指南针是否显示
        uiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        uiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);//设置logo位置【左下角】
        uiSettings.setRotateGesturesEnabled(false);//设置旋转手势——不能通过手势来旋转地图
    }

}