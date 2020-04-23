package cn.edu.scujcc.diandian;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChannelLab {
    private static ChannelLab INSTANCE=null;
    private List<Channel> data;

    private ChannelLab(){
        data = new ArrayList<>();
        //data = new ArrayList<>();
       // test();
       // getData();
    }

    public static ChannelLab getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ChannelLab();
        }
        return INSTANCE;
    }

    public void setData(List<Channel> newData){
        this.data=newData;
    }
//引入网络前的项目
    /*public void test(){
        data = new ArrayList<>();
        Channel c = new Channel();
        c.setTitle("CCTV-1 综合");
        c.setQuality("高清");
        c.setCover(R.drawable.a);
        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8");
        data.add(c);
        c = new Channel();
        c.setTitle("CCTV-2 财经");
        c.setQuality("高清");
        c.setCover(R.drawable.a);
        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv2hd.m3u8");
        data.add(c);
        c = new Channel();
        c.setTitle("CCTV-3 综艺");
        c.setQuality("标清");
        c.setCover(R.drawable.a);
        c.setUrl("http://ivi.bupt.edu.cn/hls/cctv3.m3u8");
        data.add(c);
    }*/

//获取频道
    public int getSize(){
        return data.size();
    }

//获取指定频道
    public Channel getChannel(int position){
        return data.get(position);
    }


    public void getData(Handler handler){
        Retrofit retrofit = RetrofitClient.get();
        //Retrofit retrofit = new Retrofit.Builder().baseUrl("http://47.112.225.217:8080").addConverterFactory(MoshiConverterFactory.create()).build();
        ChannelApi api = retrofit.create(ChannelApi.class);
        Call<List<Channel>> call = api.getAllChannels();
        call.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(Call<List<Channel>> call, Response<List<Channel>> response) {
                if (null != response && null != response.body()){
                Log.d("DianDian","网络访问成功，从阿里云得到的数据是：");
                Log.d("DianDian",response.body().toString());

                Message msg = new Message();
                msg.obj = response.body();
                handler.sendMessage(msg);
            }else {
                Log.w("DianDian","response没有数据！");
                }
            }

            @Override
            public void onFailure(Call<List<Channel>> call, Throwable t) {
                Log.e("DianDian","访问网络出错了",t);

            }
        });

    }
}
