package zhuoxin.andriody.com.vitamiodemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import zhuoxin.andriody.com.voideplayer.part.SimpleVideoView;

/**
 * Created by Administrator on 10/26 0026.
 */
public class mPlayerActivity extends AppCompatActivity {
    private SimpleVideoView simpleVideoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mplayer);
        simpleVideoView= (SimpleVideoView) findViewById(R.id.simplayer);
        simpleVideoView.setvideopath("http://112.253.22.157/17/z/z/y/u/" +
                "zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/" +
                "D046015255134077DDB3ACA0D7E68D45.flv");

    }
    @Override
    protected void onResume() {
        super.onResume();
        simpleVideoView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleVideoView.onpaues();
    }
}
