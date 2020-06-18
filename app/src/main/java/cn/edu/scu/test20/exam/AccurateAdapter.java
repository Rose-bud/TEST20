package cn.edu.scu.test20.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import cn.edu.scu.test20.R;

import java.util.List;


/**
 * Created by 86130 on 2020/3/27.
 */

public class AccurateAdapter extends RecyclerView.Adapter<AccurateAdapter.ViewHolder>{
    private Context mContext;
    private List<Accurate> accurateList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView accurate;
        CardView cardView;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            studentName=(TextView)view.findViewById(R.id.student_name);
            accurate=(TextView)view.findViewById(R.id.accurate);
        }
    }
    public AccurateAdapter(List<Accurate> accurateList){
        this.accurateList=accurateList;
    }

    @Override
    public AccurateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.accurate_item,parent,false);
        final AccurateAdapter.ViewHolder holder1=new AccurateAdapter.ViewHolder(view);
        holder1.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position1=holder1.getAdapterPosition();
                Accurate accurate=accurateList.get(position1);

            }
        });
        return holder1;
    }

    @Override
    public void onBindViewHolder(AccurateAdapter.ViewHolder holder, int position) {
        Accurate accurate=accurateList.get(position);
        holder.studentName.setText("学生姓名："+accurate.getStudentName()+"     ");
        holder.accurate.setText("正确率："+accurate.getAccurate()+"");

    }

    @Override
    public int getItemCount() {
        return accurateList.size();
    }
}
