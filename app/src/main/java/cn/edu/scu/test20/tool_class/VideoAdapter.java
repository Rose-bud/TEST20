package cn.edu.scu.test20.tool_class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import cn.edu.scu.test20.LittleVideoActivity;
import cn.edu.scu.test20.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>
{
    private ArrayList list;
    private LittleVideoActivity context;
    private String url;

    public VideoAdapter(LittleVideoActivity context, ArrayList list,String url){
        this.list=list;
        this.context=context;
        this.url=url;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoAdapter.ViewHolder holder, int position) {

        holder.jcVideoPlayerStandard.setUp(url,holder.jcVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"视频标题");
        //holder.jcVideoPlayerStandard.thumbImageView.setImageBitmap();//设置封面图

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TextItem;
        JCVideoPlayerStandard jcVideoPlayerStandard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextItem=itemView.findViewById(R.id.video_title);
            jcVideoPlayerStandard=itemView.findViewById(R.id.player_list_video);
        }
    }
}
