package zhuoxin.andriody.com.vitamiodemo.yoga;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.vitamiodemo.R;

/**
 * Created by Administrator on 11/1 0001.
 */
public class oneFragment extends Fragment {
    private RecyclerView recyclerView;
    private GridLayoutManager manager;
    private mAdapter adapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= (RecyclerView)view.findViewById(R.id.recyclerview);
        manager=new GridLayoutManager(getContext(),2);
        List<String> list =new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("第"+i+"个");
        }
        adapter=new mAdapter(list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setitemOnClickListener(new mAdapter.itemOnClickListener() {
            @Override
            public void itemonClick(int p) {
                Toast.makeText(getContext(),"点击",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one,container,false);
    }
}
