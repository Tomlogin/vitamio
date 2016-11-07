package zhuoxin.andriody.com.vitamiodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 10/24 0024.
 */
public class MyAdapter extends BaseAdapter {
    List<Demo> demoCs;
    LayoutInflater inflater;

    public MyAdapter(List<Demo> demoCs, Context context) {
        this.demoCs = demoCs;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return demoCs.size();
    }

    @Override
    public Demo getItem(int position) {
        return demoCs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listview_item, parent, false);
        VideoView videoView = (VideoView) convertView.findViewById(R.id.videoview);
        videoView.setVideoPath(getItem(position).path);
        videoView.setMediaController(getItem(position).mediaController);
        videoView.start();
        return convertView;
    }
}
