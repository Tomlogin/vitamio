package zhuoxin.andriody.com.vitamiodemo.okhttp;

/**
 * Created by Administrator on 10/31 0031.
 */
public class User {
   String protocol;
    int code;
    String message;
    String url;

    public User(String protocol, int code, String message, String url) {
        this.protocol = protocol;
        this.code = code;
        this.message = message;
        this.url = url;
    }
}
