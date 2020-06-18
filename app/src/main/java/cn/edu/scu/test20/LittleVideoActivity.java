package cn.edu.scu.test20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.edu.scu.test20.bean.Video;
import cn.edu.scu.test20.tool_class.VideoAdapter;

public class LittleVideoActivity extends AppCompatActivity {

    private ArrayList list;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_video);
        Bmob.initialize(this, "56a832cf0b1a430d9eada88f2c39a39a");//绑定Bmob后端

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //getVideoList();
    }

    private void getVideoList(){
        list = new ArrayList();
        BmobQuery<Video> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<Video>() {
            @Override
            public void done(List<Video> Videos, BmobException e) {
                if (e == null) {
                    for (int count=1;count<Videos.size();count++){
                        HashMap map=new HashMap();
                        map.put("video_title",Videos.get(count).getTitle());
                        map.put("video_url",Videos.get(count).getUrl());
                        list.add(map);
                        videoAdapter=new VideoAdapter(LittleVideoActivity.this,list);
                        recyclerView.setAdapter(videoAdapter);
                    }
                } else {
                    Toast.makeText(LittleVideoActivity.this,"获取视频列表成功",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}