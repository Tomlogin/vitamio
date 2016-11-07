package zhuoxin.andriody.com.vitamiodemo;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import zhuoxin.andriody.com.vitamiodemo.R;


public class DemoA extends AppCompatActivity{
    private MediaPlayer mediaplayer;
    private SurfaceView surface;
    private SurfaceHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surface = (SurfaceView) findViewById(R.id.surface);
        holder=surface.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mediaplayer=new MediaPlayer();
                    mediaplayer.setDisplay(holder);
                    mediaplayer.setDataSource("http://112.253.22.157/17/z/z/y/u/" +
                            "zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/" +
                            "D046015255134077DDB3ACA0D7E68D45.flv");
                    mediaplayer.prepareAsync();
                    mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaplayer.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });



    }
}
