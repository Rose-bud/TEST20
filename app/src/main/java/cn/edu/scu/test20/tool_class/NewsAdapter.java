package cn.edu.scu.test20.tool_class;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.scu.test20.R;


public class NewsAdapter extends BaseAdapter {
        private ArrayList list;
        private Context context;

        public NewsAdapter(Context context, ArrayList list){
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;

            view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            holder = new ViewHolder();
            holder.imageNews = view.findViewById(R.id.imageNews);
            holder.textTitle = view.findViewById(R.id.textTitle);
            holder.textAbstract = view.findViewById(R.id.textAbstract);
                //convertView.setTag(holder);

            HashMap map = (HashMap)getItem(position);
            Log.d("getNews", "newslist" + (String)map.get("imageSrc"));
            Picasso.get().load((String)map.get("imageSrc")).into(holder.imageNews);
            holder.textTitle.setText((CharSequence)map.get("title"));
            holder.textAbstract.setText((CharSequence)map.get("abstract"));
            return view;
        }
        class ViewHolder{
            ImageView imageNews;
            TextView textTitle, textAbstract;

        }
}


