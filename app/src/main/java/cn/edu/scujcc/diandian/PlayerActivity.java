package cn.edu.scujcc.diandian;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class PlayerActivity extends AppCompatActivity {
    private PlayerView tvPlayerView;
    private SimpleExoPlayer player;
    private MediaSource videoSource;
    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        channel = (Channel) getIntent().getSerializableExtra("channel");
        quality();
        updateUI();
        initPlayer();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        clean();
    }
    @Override
    protected void onResume() {
        super.onResume();
       if (player == null) {
            initPlayer();
            if (tvPlayerView != null) {
                tvPlayerView.onResume();
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (tvPlayerView != null) {
            tvPlayerView.onPause();
        }
        clean();
    }

    private void initPlayer() {
        if (player == null) {
            tvPlayerView = findViewById(R.id.tv_player);

            player = ExoPlayerFactory.newSimpleInstance(this);
            player.setPlayWhenReady(true);

            tvPlayerView.setPlayer(player);

            Uri url = Uri.parse(channel.getUrl());

            //Uri url = Uri.parse("http://219.153.252.50/PLTV/88888888/224/3221225531/chunklist.m3u8");
            DataSource.Factory factory = new DefaultDataSourceFactory(this, "DianDian");
            videoSource = new HlsMediaSource.Factory(factory).createMediaSource(url);
        }
            player.prepare(videoSource);

        }
        private void updateUI(){
            TextView tvName = findViewById(R.id.tv_name);

            tvName.setText(this.channel.getTitle());
            TextView tvQuality = findViewById(R.id.tv_quality);
            tvQuality.setText(this.channel.getQuality());
        }
    private void quality(){
        TextView tvQuality = findViewById(R.id.tv_quality);
        tvQuality.setText(this.channel.getQuality());
    }
        private void clean(){
            if (player != null) {
                player.release();
                player = null;
                videoSource = null;

            }
        }
    }
