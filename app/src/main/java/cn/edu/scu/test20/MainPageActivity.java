package cn.edu.scu.test20;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
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
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;





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

    boolean isRunning=false;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // startActivity(new Intent(MainActivity2.this,ImageShowActivity.class));

        setContentView(R.layout.activity_main_page);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toobar1);
        setSupportActionBar(toolbar);

        //imgshow=findViewById(R.id.imgshow);//左滑菜单

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        initNews();
        ListView listView=(ListView)findViewById(R.id.newsListView);

        SimpleAdapter adapter = new SimpleAdapter(this, newsList, R.layout.item_news, new String[]{"title", "imageSrc", "abstract"}, new int[]{R.id.textTitle, R.id.imageNews, R.id.textAbstract});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });


        //img
        initViews();
        initData();
        initAdapter();
//        new Thread(){
//            public void run(){
//               okHttp.sendRequestWithOkHttp("http://118.25.139.202:8080/Android/servlet/GetNewsServlet?messageId=4",1);
//                isRunning=true;
//                while(isRunning){
//                    try{
//                        Thread.sleep(4000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //System.out.println("设置当前位置: " + viewPager.getCurrentItem());
//                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
//                        }
//                    });
//                }
//            }
//        }.start();



    }



    private void   initNews(){
        HashMap map = new HashMap();
        map.put("title", "从中央政治局会议看中国经济走势");
        map.put("imageSrc", R.drawable.t1);
        map.put("abstract", "新华社北京7月30日电 题：乘风破浪 行稳致远——从中央政治局会议看中国经济走势");
        newsList.add(map);
        HashMap map1 = new HashMap();
        map1.put("title", "中共中央政治局召开会议 中共中央总书记习近平主持会议");
        map1.put("imageSrc", R.drawable.t2);
        map1.put("abstract", "中共中央政治局召开会议分析研究当前经济形势和经济工作审议《中国共产党问责条例》和《关于十九届中央第三轮巡视情况的综合报告》");
        newsList.add(map1);
        HashMap map2 = new HashMap();
        map2.put("title", "努力办好人民群众满意的基础教育");
        map2.put("imageSrc", R.drawable.t3);
        map2.put("abstract", "李克强就基础教育改革发展作出重要批示强调着力在提高质量、促进公平上下功夫 努力办好人民群众满意的基础教育");
        newsList.add(map2);
        HashMap map3 = new HashMap();
        map3.put("title", "推动消防执法全方位深层次变革--国新办召开新闻发布会");
        map3.put("imageSrc", R.drawable.t4);
        map3.put("abstract", "【来自国新办新闻发布会的报道】光明日报记者 彭景晖 姚亚奇 光明日报通讯员 徐礼涵 \n");
        newsList.add(map3);


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

//        View pointView;
//        LinearLayout.LayoutParams layoutParams;
        for(int i=0;i<imageResIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResIds[i]);

            imageViewList.add(imageView);

//            pointView=new View(this);
//            pointView.setBackgroundResource(R.drawable.point);
//            layoutParams=new LinearLayout.LayoutParams(5,5);
//            if(i!=0)
//                layoutParams.leftMargin=10;
//            pointView.setEnabled(false);
//            ll_point_container.addView(pointView,layoutParams);
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

}
