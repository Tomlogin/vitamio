package zhuoxin.andriody.com.voideplayer.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Surface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class MediaPlayerManager {
    private static MediaPlayerManager mediaPlayerManager;

    private String videoId;// 视频ID（用来区分当前操作谁）
    private List<OnPlaybackListener> onPlaybackListenerList;// 接口的集合
    private MediaPlayer mediaPlayer;
    private Context context;
    private boolean needRelease = false;// 是否需要释放（如果还没有资源的话，release可能报空指针）

    //单例
    public static MediaPlayerManager getInstance(Context context){
        if (mediaPlayerManager == null){
            mediaPlayerManager = new MediaPlayerManager(context);
        }
        return mediaPlayerManager;
    }

    private MediaPlayerManager(Context context){
        this.context = context;//拿到上下文对象，实例化MediaPlayer
        Vitamio.isInitialized(context);//vitamio初始化
        onPlaybackListenerList = new ArrayList<>();// 初始化接口
    }

    // 获取videoId
    public String getVideoId(){
        return videoId;
    }

    //生命周期控制
    //初始化MediaPlayer
    public void onResume(){
        mediaPlayer = new MediaPlayer(context);//实例化
        // 监听Prepared - 设置缓冲空间大小
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.setBufferSize(512 * 1024);
                mediaPlayer.start();
            }
        });
        // 监听Completion - 播放到最后，停止播放
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlayer();
            }
        });
        // 监听Info - 缓冲状态处理并且更新UI
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    // vitamio做音频初始处理!!!!
                    case MediaPlayer.MEDIA_INFO_FILE_OPEN_OK:
                        mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());
                     return true;
                    // 缓冲开始
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        startBuffering();
                     return true;
                    // 缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        endBuffering();
                     return true;
                }
                return false;
            }
        });

    };

    //释放MediaPlayer
    public void onPause(){
        stopPlayer();
        //判断是否需要释放
        if (needRelease){
            mediaPlayer.release();
        }
        mediaPlayer = null;
    };

    // 开始播放
    public void startPlayer(@NonNull Surface surface,
                            @NonNull String path,
                            @NonNull String videoId) {
        //判断是否是唯一播放（当前是否有其他视频存在）
        if (this.videoId !=null){
            stopPlayer();
        }
        //更新一下当前视频ID
        this.videoId = videoId;
        //通知UI进行更新
        for (OnPlaybackListener listener : onPlaybackListenerList){
            listener.onStartPlay(videoId);
        }
        // 准备播放
        try {
            mediaPlayer.setDataSource(path);//设置资源
            needRelease = true;// 需要释放
            mediaPlayer.setSurface(surface);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    // 停止播放(并且通知更新UI)
    public void stopPlayer() {
        if (videoId == null){
            return;
        }
        // 通知UI更新
        for (OnPlaybackListener listener : onPlaybackListenerList){
            listener.onStopPlay(videoId);
        }
        this.videoId = null;
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.reset();//重置
    };
    // 添加监听
    public void addPlayerbackListener(OnPlaybackListener listener) {
        onPlaybackListenerList.add(listener);
    };
    // 移除监听
    public void removeAllListeners(){
        onPlaybackListenerList.clear();
    };


    // 开始缓冲，并且更新UI（通过接口callback）
    private void startBuffering(){
        //判断：正在播放的时候要暂停
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
        for (OnPlaybackListener listener : onPlaybackListenerList){
            listener.onStartBuffering(videoId);
        }
    }

    //结束缓冲，并且更新UI（通过接口callback）
    private void endBuffering(){
        mediaPlayer.start();
        //通知UI更新
        for (OnPlaybackListener listener : onPlaybackListenerList){
            listener.onStopBuffering(videoId);
        }
    }


    //视图接口
    //在视频播放模块完成播放的一些处理，视图层（app模块）实现这些接口，完成视图层UI更新
    public interface OnPlaybackListener{
        //视频缓冲开始
        void onStartBuffering(String videoId);
        //视频缓冲结束
        void onStopBuffering(String videoId);
        //开始播放
        void onStartPlay(String videoId);
        //停止播放
        void onStopPlay(String videoId);
        //大小更改
        void onSizeMeasured(String videoId, int width, int height);

    }
}
