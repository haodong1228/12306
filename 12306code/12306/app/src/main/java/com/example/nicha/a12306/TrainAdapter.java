package com.example.nicha.a12306;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.nicha.a12306.MainActivity.login;

/**
 * Created by nicha on 2017/11/9.
 */

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {
    private List<Train> mTrainList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView _imageview_start;
        ImageView _imageview_card;
        ImageView _imageview_arrive;
        TextView _checi;
        TextView _start;
        TextView _start_time;
        TextView _arrive;
        TextView _arrive_time;
        TextView _shangwu_ticket;
        TextView _yideng_ticket;
        TextView _erdeng_ticket;
        TextView _wu_ticket;
        Button _take_time;
        View trainView;
        public ViewHolder(View view){
            super(view);
            trainView = view;
            _checi = (TextView)view.findViewById(R.id.checi);
            _imageview_start = (ImageView) view.findViewById(R.id.image_start);
            _start = (TextView)view.findViewById(R.id.start);
            _start_time = (TextView)view.findViewById(R.id.start_time);
            _imageview_card = (ImageView) view.findViewById(R.id.image_card);
            _take_time = (Button) view.findViewById(R.id.take_time);
            _imageview_arrive = (ImageView) view.findViewById(R.id.image_arrive);
            _arrive = (TextView)view.findViewById(R.id.arrive);
            _arrive_time = (TextView)view.findViewById(R.id.arrive_time);
            _shangwu_ticket = (TextView)view.findViewById(R.id.shangwu_ticket);
            _yideng_ticket = (TextView)view.findViewById(R.id.yideng_ticket);
            _erdeng_ticket = (TextView)view.findViewById(R.id.erdeng_ticket);
            _wu_ticket = (TextView)view.findViewById(R.id.wuzuo_ticket);
        }
    }
    public TrainAdapter(List<Train> trainList){
        mTrainList = trainList;
    }
    @Override
    public TrainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item,parent,false);
        final TrainAdapter.ViewHolder holder = new TrainAdapter.ViewHolder(view);
        holder.trainView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Train train = mTrainList.get(position);
                SharedPreferences.Editor editor = view.getContext().getSharedPreferences("position_data", view.getContext().MODE_PRIVATE).edit();
                editor.putInt("position", position);
                editor.apply();
                Intent intent  = new Intent(view.getContext(),LoginActivity.class);
                view.getContext().startActivity(intent);
//                Toast.makeText(view.getContext(), "position: "+ position, Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(TrainAdapter.ViewHolder holder, int position) {
        Train train = mTrainList.get(position);
        holder._checi.setText(train.getCheci());
        holder._imageview_start.setImageResource(train.getImageId_start());
        holder._start.setText(train.getStart());
        holder._start_time.setText(train.getStart_time());
        holder._imageview_card.setImageResource(train.getImageId_card());
        holder._take_time.setText(train.getTake_time());
        holder._imageview_arrive.setImageResource(train.getImageId_arrive());
        holder._arrive.setText(train.getArrive());
        holder._arrive_time.setText(train.getArrive_time());
        holder._shangwu_ticket.setText(train.getShangwu_ticket());
        holder._yideng_ticket.setText(train.getYideng_ticket());
        holder._erdeng_ticket.setText(train.getErdeng_ticket());
        holder._wu_ticket.setText(train.getWuzuo_ticket());
    }
    @Override
    public int getItemCount() {
        return mTrainList.size();
    }
}
