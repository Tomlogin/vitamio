package zhuoxin.andriody.com.vitamiodemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 10/24 0024.
 */
public class ScrollviewActivity extends AppCompatActivity {
    private LinearLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        layout = (LinearLayout) findViewById(R.id.layout);

        for(int i = 0; i < 20; i++)
        {
            //  通过资源文件来获得指定一个Drawable对象
            TextView textView=new TextView(this);
            textView.setText("地址：");
            textView.setMovementMethod(new LinkMovementMethod());
            layout.addView(textView);
        }
    }


}
