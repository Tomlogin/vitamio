package zhuoxin.andriody.com.vitamiodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.R;

public class Listview_video extends AppCompatActivity {
private ListView listview;
    private List<Demo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_video);
        list=new ArrayList<>();
        list.add(new Demo("http://112.253.22.157/17/z/z/y/u/" +
                "zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/" +
                "D046015255134077DDB3ACA0D7E68D45.flv",this));
        listview= (ListView) findViewById(R.id.listview);
        MyAdapter adapter=new MyAdapter(list,this);
        listview.setAdapter(adapter);
    }
}
