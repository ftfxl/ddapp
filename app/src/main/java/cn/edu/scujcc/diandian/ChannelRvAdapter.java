package cn.edu.scujcc.diandian;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ChannelRvAdapter extends RecyclerView.Adapter<ChannelRvAdapter.ChannelRowHolder> {

    private ChannelLab lab = ChannelLab.getInstance();
    private ChannelClickListener listener;
    private Context context;

    public ChannelRvAdapter(Context context, ChannelClickListener lis){
        this.listener = lis;
        this.context = context;
    }

    //负责创建新行对象ChannelRowHolder
    @NonNull
    @Override
    public ChannelRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_row,parent,false);
        ChannelRowHolder holder = new ChannelRowHolder(rowView);
        return holder;
    }

    /**
     * 用于确定列表有几行（有多少ChannelRowHolder对象）
     * @return
     */

    @Override
    public int getItemCount() {

        return lab.getSize();
    }

    /*
    用于确定每一行的内容，既填充行中各个视图的内容
     */
    @Override
    public void onBindViewHolder(@NonNull ChannelRowHolder holder, int position) {

        Channel c = lab.getChannel(position);
        holder.bind(c);
        //c.setCover(R.drawable.a);


 //       list<Channel> data;
//        Channel c = data.get(position)
       // Log.d("DD","onBindViewHolder position="+position);
        //Channel c = new Channel();
        //c.setTitle("中央"+position+"台");
        //c.setQuality("1080p");
        //c.setCover(R.drawable.a);
        //holder.bind(c);
        //TODO
        //holder.bind("中央1台","1080p",R.drawable.a);
    }



    /**
     * 单行布局对应的java控制类
     */
    public class ChannelRowHolder extends RecyclerView.ViewHolder{
        private TextView title;//频道标题
        private TextView quality;//频道清晰度
        private ImageView cover;

        public ChannelRowHolder(@NonNull View row) {
            super(row);
            this.title=row.findViewById(R.id.channel_title);
            this.quality=row.findViewById(R.id.channel_quality);
            this.cover=row.findViewById(R.id.channel_cover);
            row.setOnClickListener((v) -> {
                    int position = getLayoutPosition();
                    Log.d("DianDian",position+"行被点击了！");

                    listener.onChannelClick(position);
            });
        }

        /*

        自定义方法，用于向内部title提供数据
         */
       /* public void bind(String title,String quality,int coverId){
            this.title.setText(title);
            this.quality.setText(quality);
            this.cover.setImageResource(coverId);
        }*/

        public void bind(Channel c) {
            this.title.setText(c.getTitle());
            this.quality.setText(c.getQuality());
            //RoundedCorners rc = new RoundedCorners(6);

            Log.d("DianDian",c.getTitle()+":准备从网络加载封面："+c.getCover());
            Glide.with(context).load(c.getCover()).placeholder(R.drawable.a).into(this.cover);
           // this.cover.setImageResource(c.getCover());
        }
    }
    public interface ChannelClickListener{
        public void onChannelClick(int position);
    }
}
