package zhuoxin.andriody.com.voideplayer.full;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import io.vov.vitamio.widget.MediaController;
import zhuoxin.andriody.com.voideplayer.R;


/**
 * Created by Administrator on 10/26 0026.
 */
public class CustomMediaController extends MediaController {
    private MediaPlayerControl mediaPlayerControl;//自定义视频控制器
    private AudioManager audioManager;//音频管理者
    private int maxVolume;//最大音量
    private Window window;//视频亮度管理
    private int currentVolume;//当前音量
    private float currentBrightness;//当前亮度

    public CustomMediaController(Context context) {
        super(context);
        audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        window = ((Activity) context).getWindow();
    }

    @Override
    protected View makeControllerView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_custom_video_controller, this);
        initview(view);
        return view;
    }



    //让我自定义的Controller可以控制mediaplayer
    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);
        mediaPlayerControl = (MediaPlayerControl) player;
    }

    private void initview(View view) {
        //forward快进
        ImageButton btnFastForward = (ImageButton) view.findViewById(R.id.btnFastForward);
        btnFastForward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            long postion=mediaPlayerControl.getCurrentPosition();
                postion += 10000;//快进10秒
                //快进的位置大于最大长度判断
                if(postion>=mediaPlayerControl.getDuration()){
                    postion=mediaPlayerControl.getDuration();
                }
                mediaPlayerControl.seekTo(postion);
            }
        });
        //rewind快退
        ImageButton btnFastRewind = (ImageButton) view.findViewById(R.id.btnFastRewind);
        btnFastRewind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                long postion = mediaPlayerControl.getCurrentPosition();
                postion -= 10000;//快退10秒
                //快退的位置是否小于0秒
                if (postion <=0 ){
                    postion = 0;
                }
                mediaPlayerControl.seekTo(postion);
            }
        });
        // 调整视图（左边调整亮度，右边调整音量）
    final View adjustView = view.findViewById(R.id.adjustView);
        // 依赖GestureDetector来进行滑屏调整音量和亮度的手势处理
        final GestureDetector gestureDetector=new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float startX = e1.getX();//开始的X轴
                float startY = e1.getY();//开始的Y轴
                float endX = e2.getX();//结束的X轴
                float endY = e2.getY();//结束的Y轴
                float width = adjustView.getWidth();//拿到我整个视图的宽
                float height = adjustView.getHeight();//视图的高
                float percentage = (startY - endY) / height;//高度滑动的百分比
                //左侧：亮度
                if(startX<width/2){
                    // TODO: 10/26 0026  左侧：亮度
                    adjustbRrightness(percentage);
                }else if(startX>width*1/2){
                     //TODO: 10/26 0026  右侧：音量
                    adjustVolume(percentage);
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }



        });
        //对view进行touch监听
        //但是我们不去做touch事件的处理，交给GestureDetector处理
        adjustView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判断用户的操作是不是一个摁下的操作
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
                    //当前音量
                    currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    //当前亮度
                    currentBrightness = window.getAttributes().screenBrightness;
                }
                //传给gD用户初始操作，后续gD自行处理
                gestureDetector.onTouchEvent(event);
                // 在调整的过程中，一直显示
                show();
                return true;
            }
        });
    }
//调整亮度
    private void adjustbRrightness(float percentage) {
        //最终亮度 = 0.? + 当前亮度0.？
//        亮度增加    0.7       =     0.2    +    0.5
//        亮度减少    0.3         =    -0.2    +    0.5
        float brightness = percentage + currentBrightness;
        //最终亮度是否大于最大亮度
        brightness = brightness > 1.0f ? 1.0f : brightness;
        //最终亮度是否小于最小亮度
        brightness = brightness < 0 ? 0 : brightness;
        //设置亮度
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = brightness;
        window.setAttributes(layoutParams);
    }
    //调整音量
    private void adjustVolume(float percentage){
        //最终音量 = 最大音量*百分比 + 当前音量
//       音量增大   35       =   100*15%      +   20
//       音量减小   5        =   100*-15%     +   20
        int volume = (int) ((maxVolume * percentage) + currentVolume);
        //最终音量是否大于最大音量
        volume = volume > maxVolume ? maxVolume : volume;
        //最终音量是否小于最小音量
        volume = volume < 0 ? 0 : volume;
        //设置音量
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,
                audioManager.FLAG_SHOW_UI);
    }
}