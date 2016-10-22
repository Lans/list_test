package com.example.administrator.list_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnClickListener {

    private RecyclerView recyclerView;
    private MainActivity mContext;
    private List<String> stringList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        createData();

        adapter = new MyAdapter(mContext, stringList);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void createData() {
        for (int i = 0; i < 6; i++) {
            stringList.add("测试局部刷新---》" + i * 1000);
        }
    }

    @Override
    public void setOnChange(int position) {
        String string;
        string = "我改变了哟";
        stringList.remove(position);
        stringList.add(position,string);
        Toast.makeText(mContext, string+position, Toast.LENGTH_LONG).show();
        adapter.notifyItemRangeChanged(position,1);
    }
}
