package com.example.administrator.commonrecycleradapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.commonrecycleradapter.adapter.BaseAdapter;
import com.example.administrator.commonrecycleradapter.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);

        initData();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.item_recycler, mData);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void itemClickListener(BaseViewHolder viewHolder, int position) {
                Toast.makeText(MainActivity.this, "item" + position + "被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildClickListener(new BaseAdapter.OnItemChildClickListener() {
            @Override
            public void itemChildClickListener(View v, int position) {
                Toast.makeText(MainActivity.this, "button" + position + "被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add(i + "啦啦啦");
        }
    }
}
