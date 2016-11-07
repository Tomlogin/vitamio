package zhuoxin.andriody.com.vitamiodemo;


import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;
import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 10/21 0021.
 */
public class DemoB extends AppCompatActivity {
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private MediaPlayer mediaPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView= (SurfaceView) findViewById(R.id.surface);
        holder=surfaceView.getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);//不加则会花屏
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                mediaPlayer=new MediaPlayer(DemoB.this);
                try {
                    mediaPlayer.setDataSource("http://112.253.22.157/17/z/z/y/u/" +
                            "zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/" +
                            "D046015255134077DDB3ACA0D7E68D45.flv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setDisplay(holder);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
                // vitamio5.0，要进行audio处理,才能对在线视频进行播放!!!!
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                       if(what==MediaPlayer.MEDIA_INFO_FILE_OPEN_OK){
                           mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());
                           return true;
                       }
                        return false;
                    }
                });
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                  mediaPlayer.stop();//不停止返回后还在播放
            }
        });
    }
}
