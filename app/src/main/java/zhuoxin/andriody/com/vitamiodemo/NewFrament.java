package zhuoxin.andriody.com.vitamiodemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zhuoxin.andriody.com.vitamiodemo.okhttp.MyOkhttpClient;
import zhuoxin.andriody.com.vitamiodemo.okhttp.User;

/**
 * Created by Administrator on 10/31 0031.
 */
public class NewFrament extends Fragment {

    ListView listView;
    List<String> list;
    ArrayAdapter adapter;
    MyOkhttpClient mOkhttpClient;
    User user;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list.add(msg.toString());
            adapter.notifyDataSetChanged();
        }
    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG-F", "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG-F", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TAG-F", "onCreateView: ");
        return inflater.inflate(R.layout.activity_test,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("TAG-F", "onActivityCreated: ");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= (ListView) view.findViewById(R.id.lv);
        list=new ArrayList<>();
        list.add("123");
        list.add("321");
        list.add("231");
        adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listView.setAdapter(adapter);
        Log.e("TAG-F", "onViewCreated: ");

        MyOkhttpClient.getInstance().loging().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("test-sb", e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Message msg = new Message();
                msg.obj=response.code();
                handler.sendMessage(msg);
                Log.e("test-cg",response.body().string());
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG-F", "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG-F", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG-F", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG-F", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG-F", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG-F", "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG-F", "onDetach: ");
    }
}
