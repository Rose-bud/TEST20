package cn.edu.scu.test20;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.edu.scu.test20.bean.Contents;
import cn.edu.scu.test20.bean.News;
import cn.edu.scu.test20.tool_class.NewsAdapter;


public class MainPageActivity extends AppCompatActivity{



    //底部导航栏

    RelativeLayout imgshow;

    private ArrayList newsList=new ArrayList();
    //图片
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private TextView tv_desc;
    private ListView listView;
    private NewsAdapter adapter;
    boolean isRunning=false;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        //imgshow=findViewById(R.id.imgshow);//左滑菜单
        preProcess();
        Log.d("getNews", "main");
        getNews();
        //img
        initViews();
        initData();
        initAdapter();


    }

    private void preProcess(){
        Bmob.initialize(this, "56a832cf0b1a430d9eada88f2c39a39a");//绑定Bmob后端
        listView=(ListView)findViewById(R.id.newsListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap map = (HashMap)newsList.get(position);
                Intent intent = new Intent(MainPageActivity.this, PageContentActivity.class);
                intent.putExtra("news_title", (String)map.get("title"));
                intent.putExtra("news_abstract", (String)map.get("abstract"));
                intent.putExtra("news_imageId", (String)map.get("imageSrc"));
                intent.putExtra("news_content", (String)map.get("content"));
                startActivity(intent);
            }
        });
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                newsList.clear();
                getNews();
                refreshLayout.finishRefresh(true);

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
                refreshLayout.finishLoadMore(true);
            }
        });
    }





    //img

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }
    private void initViews(){
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        ll_point_container=(LinearLayout)findViewById(R.id.ll_point_container);
        tv_desc=(TextView)findViewById(R.id.tv_desc1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_desc.setText(contentDescs[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initData(){
        imageResIds=new int[]{R.drawable.img_main1,R.drawable.img_main2,R.drawable.img_main3,R.drawable.img_main4,R.drawable.img_main5};
        contentDescs=new String[]{
                "李鹏同志遗体在京火化 习近平等到八宝山革命公墓送别",
                "习近平会见全国退役军人工作会议代表",
                "习近平同阿联酋阿布扎比王储穆罕默德举行会谈",
                "习近平再次会见阿联酋阿布扎比王储穆罕默德",
                "习近平会见2017年度驻外使节工作会议于会使节"
        };
        imageViewList=new ArrayList<ImageView>();

        for(int i=0;i<imageResIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResIds[i]);

            imageViewList.add(imageView);

        }
    }
    private void initAdapter(){
//        ll_point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(contentDescs[0]);
        viewPager.setAdapter(new MyAdapter());
        //viewPager.setCurrentItem(0);
    }


    class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);

            ImageView imageView=imageViewList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println("destroyItem销毁: " + position);
            container.removeView(imageViewList.get(position));
        }

    }

    private void getNews(){
        Log.d("getNews", "hello");
        newsList = new ArrayList<News>();
        BmobQuery<News> query = new BmobQuery<News>();
        query.setLimit(5);
        query.order("-createdAt");
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if(e == null){

                    for(News item: list){
                        HashMap map = new HashMap();
                        map.put("title", item.getTitle());

                        if(item.getimageSrc() == null){
                            map.put("imageSrc", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1592705543&di=55d6867a8fdce326f50c3b9627f14219&src=http://www.zhufu.pro/updoc/allimg/15022822296-www.zhufu.pro-15022R25242.jpg");
                        } else {
                            map.put("imageSrc", item.getimageSrc());
                        }
                        map.put("abstract", item.getAbstract());
                        map.put("content", item.getContent());
                        newsList.add(map);
                    }
                    //这里添加前端代码

                    adapter = new NewsAdapter(MainPageActivity.this, newsList);
                    listView.setAdapter(adapter);

                }else {
                    Log.i("getNews", e.getMessage() + "," + e.getErrorCode());
                }

            }
        });

    }

    private void loadMore(){
        int count = newsList.size();
        BmobQuery<News> query = new BmobQuery<News>();
        query.setSkip(count);
        query.setLimit(5);
        query.order("-createdAt");
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> list, BmobException e) {
                if (e == null) {
                    ArrayList tempList = new ArrayList();
                    for (News item : list) {
                        HashMap map = new HashMap();
                        map.put("title", item.getTitle());

                        if (item.getimageSrc() == null) {
                            map.put("imageSrc", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1592705543&di=55d6867a8fdce326f50c3b9627f14219&src=http://www.zhufu.pro/updoc/allimg/15022822296-www.zhufu.pro-15022R25242.jpg");
                        } else {
                            map.put("imageSrc", item.getimageSrc());
                        }
                        map.put("abstract", item.getAbstract());
                        map.put("content", item.getContent());
                        tempList.add(map);
                    }
                    newsList.addAll(tempList);
                    adapter.notifyDataSetChanged();


                } else {
                    Log.i("getNews", e.getMessage() + "," + e.getErrorCode());
                }

            }
        });
    }

}
