package zhuoxin.andriody.com.vitamiodemo.yoga;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 11/1 0001.
 */
public class twoFragment extends Fragment {
    private ListView listimg;
    private int [] imgs={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listimg= (ListView) view.findViewById(R.id.listview_img);
        madapter adapter=new madapter(imgs,getContext());
        listimg.setAdapter(adapter);



    }
    class madapter extends BaseAdapter{
        int [] imgs;
        Context context;
        public madapter(int[] imgs,Context context) {
            this.imgs = imgs;
            this.context=context;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public Object getItem(int position) {
            return imgs[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.item_fragmenttwo,parent,false);
            }
            ImageView img= (ImageView) convertView.findViewById(R.id.imgitem);
            img.setImageResource(imgs[position]);
            return convertView;
        }
    }
}
