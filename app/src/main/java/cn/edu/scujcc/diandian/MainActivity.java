package cn.edu.scujcc.diandian;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements ChannelRvAdapter.ChannelClickListener{
    private ChannelRvAdapter rvAdapter;
    private RecyclerView channelRv;
    private ChannelLab lab = ChannelLab.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.channelRv = findViewById(R.id.channel_rv);
        //ChannelRvAdapter rvAdapter = new ChannelRvAdapter(this);
        rvAdapter = new ChannelRvAdapter(this,p -> {
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);

            Channel c = lab.getChannel(p);
            intent.putExtra("channel", c);
            startActivity(intent);
        });

        initData();
        this.channelRv.setAdapter(rvAdapter);
        this.channelRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onChannelClick(int position) {
        Log.d("DianDian", "用户点击的频道编号是：" + position);
        Channel c = lab.getChannel(position);
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);

        intent.putExtra("channel", c);
        startActivity(intent);
    }

    private void initData(){
         Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                List<Channel> channels = (List<Channel>) msg.obj;
                lab.setData(channels);
                rvAdapter.notifyDataSetChanged();
            }
        };
        lab.getData(handler);
    }
    protected void onResume(){
        super.onResume();

        //lab.getData(handler);
    }
}


