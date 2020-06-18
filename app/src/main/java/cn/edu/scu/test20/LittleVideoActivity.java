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
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.edu.scu.test20.bean.User;
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
        recyclerView=findViewById(R.id.video_list);
        Bmob.initialize(this, "56a832cf0b1a430d9eada88f2c39a39a");//绑定Bmob后端

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        new Thread(){
            @Override
            public void run() {
                super.run();
                getVideoList();
            }
        }.start();
    }

    private void getVideoList(){
        String bql="select Title,Url from Video";
        list = new ArrayList();
        BmobQuery<Video> bmobQuery = new BmobQuery<Video>();
        bmobQuery.setSQL(bql);
        bmobQuery.doSQLQuery(new SQLQueryListener<Video>() {
            @Override
            public void done(BmobQueryResult<Video> bmobQueryResult, BmobException e) {
                if (e == null) {
                    List<Video> v_list=(List<Video>) bmobQueryResult.getResults();
                    if(v_list!=null && v_list.size()>=0){
                        for (int count=0;count<v_list.size();count++){
                            HashMap map=new HashMap();
                            map.put("video_title",v_list.get(count).getTitle());
                            map.put("video_url",v_list.get(count).getUrl());
                            list.add(map);
                        }
                        videoAdapter=new VideoAdapter(LittleVideoActivity.this,list);
                        recyclerView.setAdapter(videoAdapter);
                        Toast.makeText(LittleVideoActivity.this,"获取视频列表成功",Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LittleVideoActivity.this,"获取视频列表失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}