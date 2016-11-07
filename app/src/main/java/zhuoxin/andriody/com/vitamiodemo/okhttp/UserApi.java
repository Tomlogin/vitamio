package zhuoxin.andriody.com.vitamiodemo.okhttp;


import okhttp3.Call;

/**
 * Created by Administrator on 10/31 0031.
 */
public interface UserApi {

   Call loging();

   Call register(User user);
}
