package zhuoxin.andriody.com.vitamiodemo.retrofit;

import retrofit2.http.GET;

/**
 * Created by Administrator on 11/1 0001.
 */
public interface RetrofitApi {
    @GET()
    void getData();
    void postData();
}
