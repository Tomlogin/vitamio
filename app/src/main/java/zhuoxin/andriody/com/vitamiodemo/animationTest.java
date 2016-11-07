package zhuoxin.andriody.com.vitamiodemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 10/24 0024.
 */
public class animationTest extends AppCompatActivity {
    private ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        img= (ImageView) findViewById(R.id.img);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        img.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("test", "onAnimationStart: " );
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("test", "onAnimationend: " );
                Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                img.startAnimation(animation1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e("test", "onAnimationrepeat: " );
            }
        });
    }
}
