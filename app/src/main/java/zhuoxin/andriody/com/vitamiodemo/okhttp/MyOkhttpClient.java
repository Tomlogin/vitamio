package zhuoxin.andriody.com.vitamiodemo.okhttp;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 10/31 0031.
 */
public class MyOkhttpClient implements UserApi{
    private static MyOkhttpClient mOkhttpClient;
    private Gson gson;
    private OkHttpClient  okHttpClient;
    private String URL="http://op.juhe.cn/onebox/weather/query?key=9120b125a61bcb084092888566528ea2";
    private MyOkhttpClient(){
        gson=new Gson();
        okHttpClient=new OkHttpClient.Builder().

                build();
    }
    public static MyOkhttpClient getInstance(){
        if(mOkhttpClient==null){
            mOkhttpClient=new MyOkhttpClient();
        }
        return mOkhttpClient;
    }
    @Override
    public Call loging() {//get请求
        Request request=new Request.Builder()
                .url(URL)
                .get()
                .build();
        return okHttpClient.newCall(request);
    }

    @Override
    public Call register(User user) {//post请求
        String content=gson.toJson(user);
        RequestBody requestBody=RequestBody.create(null,content);
        Request request=new Request.Builder()
                .post(requestBody)
                .url(URL)
                .build();
        return okHttpClient.newCall(request);
    }
}
