package zhuoxin.andriody.com.vitamiodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import zhuoxin.andriody.com.vitamiodemo.R;

public class DemoC extends AppCompatActivity {
private VideoView videoView;
    String s="http://112.253.22.157/17/z/z/y/u/" +
            "zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/" +
            "D046015255134077DDB3ACA0D7E68D45.flv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);
        videoView= (VideoView) findViewById(R.id.vv);
        videoView.setVideoPath(s);
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
