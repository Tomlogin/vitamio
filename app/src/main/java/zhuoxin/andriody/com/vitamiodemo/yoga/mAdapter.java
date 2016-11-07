package zhuoxin.andriody.com.vitamiodemo.yoga;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 11/1 0001.
 */
public class mAdapter extends RecyclerView.Adapter {
   List<String> list;

    public mAdapter(List<String> list) {
        this.list = list;
    }

    interface itemOnClickListener{
        void itemonClick(int p);
    }
    itemOnClickListener itemOnClickListener=null;
    void setitemOnClickListener(itemOnClickListener itemOnClickListener){
        this.itemOnClickListener=itemOnClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yoga,parent,false);
        mHolder holder=new mHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
          mHolder holderl= (mHolder) holder;
          holderl.img.setImageResource(R.drawable.ic_launcher);
         holderl.tv.setText(list.get(position));
        holderl.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    itemOnClickListener.itemonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class mHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv;

        public mHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.imgone);
            tv= (TextView) itemView.findViewById(R.id.tvtitle);
        }
    }
}
