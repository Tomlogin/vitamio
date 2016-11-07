package zhuoxin.andriody.com.vitamiodemo;

import android.content.Context;
import android.widget.MediaController;

/**
 * Created by Administrator on 10/24 0024.
 */
public class Demo {
    String path;
    MediaController mediaController;
    public Demo(String path,Context context) {
        this.path = path;
        mediaController=new MediaController(context);
    }
}
