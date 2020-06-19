package cn.edu.scu.test20.exam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cn.edu.scu.test20.R;


public class ResultGridAdapter extends RecyclerView.Adapter<ResultGridAdapter.ViewHolder1>{
    private Context context;
    private boolean[] selected;
    private boolean isSelected=false;
    private int list=0;;
    OnRecyclerItemClickListener onRecyclerItemClickListener;
    public ResultGridAdapter(Context context, int list, boolean[] selected, OnRecyclerItemClickListener onRecyclerItemClickListener){
        this.context = context;
        this.list = list;
        this.selected = selected;
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(context).inflate(R.layout.recycler_grid_item,parent,false);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(onRecyclerItemClickListener!=null){
                    onRecyclerItemClickListener.onTtemClick(view,(int)view.getTag());

                }
            }
        });
        return  new ViewHolder1(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        ViewHolder1 viewHolder1=holder;
        viewHolder1.itemView.setTag(position);
        viewHolder1.tvResult.setText(String.valueOf(position+1));

        isSelected=selected[position];
        Log.e("Adapter",isSelected+"");
        if(isSelected){
            viewHolder1.tvResult.setBackgroundResource(R.drawable.bg_result_selected);

        }else{
            viewHolder1.tvResult.setBackgroundResource(R.drawable.bg_result_normal);
        }
    }


    @Override
    public int getItemCount() {
        return list;
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder{
        private TextView tvResult;
        public ViewHolder1(View itemView){
            super(itemView);
            tvResult=(TextView)itemView.findViewById(R.id.tv_result_grid_item);
        }
    }
}
