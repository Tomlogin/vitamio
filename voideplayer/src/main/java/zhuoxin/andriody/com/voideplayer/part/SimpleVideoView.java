package zhuoxin.andriody.com.voideplayer.part;

import android.content.Context;
import android.graphics.PixelFormat;

import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.MediaPlayer;
import zhuoxin.andriody.com.voideplayer.R;
import zhuoxin.andriody.com.voideplayer.full.VideoViewActivity;

/**
 * Created by Administrator on 10/25 0025.
 */
public class SimpleVideoView extends FrameLayout {
    private static final int PROGRESS_MAX = 1000;//进度条控制（长短，进度）
    private boolean ispalying;//是否正在播放
    private boolean isPrepared;//是否準備好
    private SurfaceView surfaceview;
    private ImageView img;
    private ImageButton imgbtn;
    private String videopath;
    private ImageButton fullbtn;
    private ProgressBar progressbar;
    private SurfaceHolder surholder;
    private MediaPlayer mediaplayer;
    public SimpleVideoView(Context context) {
        this(context,null);
    }

    public SimpleVideoView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SimpleVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
public Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(ispalying){
            int progress=(int)(mediaplayer.getCurrentPosition()*PROGRESS_MAX/mediaplayer.getDuration());
            progressbar.setProgress(progress);
            handler.sendEmptyMessageDelayed(0,200);
        }
    }
};
    //视图初始化，只在构造方法中调用一次
    private void init() {
        //Vitamio的初始化
        Vitamio.isInitialized(getContext());
        //inflate初始当前视图内容
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_video_player,this,true);
        initSurface();// 初始化SurfaceView
        initControllerViews();// 初始化视频播放控制视图
    }

    private void initControllerViews() {
        //預覽圖
        img= (ImageView) findViewById(R.id.ivPreview);
        //播放按鈕
        imgbtn= (ImageButton) findViewById(R.id.btnToggle);
        imgbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaplayer.isPlaying()){
                  pauseMediaplayer();
                }else if(isPrepared){
                  startMediaplayer();
                }else{
                    Toast.makeText(getContext(),"Can't play now",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //进度条
        progressbar= (ProgressBar) findViewById(R.id.progressBar);
        progressbar.setMax(PROGRESS_MAX);
        //全屏播放按钮
        fullbtn= (ImageButton) findViewById(R.id.btnFullScreen);
        fullbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 全屏未实现
                VideoViewActivity.open(getContext(),videopath);
            }
        });
    }
    //点击开始时调用的方法，更新UI
    private void startMediaplayer() {
        img.setVisibility(View.INVISIBLE);//预览图隐藏
        imgbtn.setImageResource(R.drawable.ic_pause);//换成暂停图片
        mediaplayer.start();//开始播放
        ispalying=true;
        handler.sendEmptyMessage(0);

    }

    //点击暂停时调用的方法，更新UI
    private void pauseMediaplayer() {
        if(mediaplayer.isPlaying()){
            mediaplayer.pause();
        }
        ispalying=false;
        imgbtn.setImageResource(R.drawable.ic_play_arrow);//换成播放按钮
        handler.sendEmptyMessage(0);
    }
    // 初始化SurfaceView
    private void initSurface() {
        surfaceview= (SurfaceView) findViewById(R.id.surfaceView);
        surholder=surfaceview.getHolder();
        surholder.setFormat(PixelFormat.RGBA_8888);//不加會花屏
    }
    //設置視頻路勁
    public void setvideopath(String videopath){
        this.videopath=videopath;
    }
    public void onResume(){
        initMediaPlayer();// 初始化MediaPlayer，设置一系列的监听
        prepareMediaPlayer();// 准备MediaPlayer，更新UI
    }
    // 初始化MediaPlayer，设置一系列的监听
    private void initMediaPlayer() {
        mediaplayer = new MediaPlayer(getContext());
        mediaplayer.setDisplay(surholder);
        //准备情况的监听
        mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPrepared = true;
                startMediaplayer();
            }
        });
        //处理audio监听
        mediaplayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == io.vov.vitamio.MediaPlayer.MEDIA_INFO_FILE_OPEN_OK) {
                    mediaplayer.audioInitedOk(mediaplayer.audioTrackInit());
                    return true;
                }
                return false;
            }
        });
        //设置videosize变化监听
        mediaplayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                int layoutWidth = surfaceview.getWidth();
                int layoutHeight = layoutWidth * height / width;
                // 更新surfaceView的size
                ViewGroup.LayoutParams layoutparams = surfaceview.getLayoutParams();
                layoutparams.width = layoutWidth;
                layoutparams.height = layoutHeight;
                surfaceview.setLayoutParams(layoutparams);
            }
        });

    }
    // 准备MediaPlayer，更新UI
    private void prepareMediaPlayer() {
        try {
            mediaplayer.reset();//重置
            mediaplayer.setDataSource(videopath);//设置数据源
            mediaplayer.setLooping(true);//循环播放
            mediaplayer.prepareAsync();
            //预览图可见
            imgbtn.setVisibility(View.VISIBLE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 用来释放状态
    public void onpaues(){
        pauseMediaplayer(); // 暂停播放，同时更新UI状态
        releaseMediaPlayer(); // 释放MediaPlayer，同时更新UI状态
    }
    // 释放MediaPlayer，同时更新UI状态
    private void releaseMediaPlayer() {
        mediaplayer.release();//释放
        mediaplayer = null;
        isPrepared = false;
        progressbar.setProgress(0);
    }


}


