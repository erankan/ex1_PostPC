package erankan.myselfchat;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    private List<MyMessage> myContextList;
    private final MyItemClick listener;

    public MyAdapter(MyItemClick clicker, List<MyMessage> myContextList) {
        this.listener = clicker;
        this.myContextList = myContextList;
    }


    public void addMessage(MyMessage message){
        myContextList.add(message);
        notifyItemInserted(myContextList.size() - 1);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.my_chat, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(myView);
        myViewHolder.setOnClickListener(listener);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyMessage msg = this.myContextList.get(position);

        // Show sent message in screen.
        holder.dataText.setText(msg.getContent());
        holder.senderText.setText(msg.getSender());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(msg.getTimestamp().getTime());
        holder.timeStampText.setText(time);
    }


    @Override
    public int getItemCount() {
        if (myContextList == null){
            myContextList = new ArrayList<>();
        }
        return myContextList.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView senderText, dataText, timeStampText;
        MyItemClick itemClick;

        MyViewHolder(final View itemView){
            super(itemView);
            dataText = itemView.findViewById(R.id.dataText);
            senderText = itemView.findViewById(R.id.senderText);
            timeStampText = itemView.findViewById(R.id.timeStampText);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(itemClick != null){
                        itemClick.onItemClick(myContextList.get(getAdapterPosition()));
                        return true;
                    }
                    return false;
                }
            });
        }

        void setOnClickListener(MyItemClick onClickListener) {
            this.itemClick = onClickListener;
        }
    }


    public void removeItem(MyMessage message){
        for(int i = 0, size = myContextList.size(); i < size; i++) {
            if (message.equals(myContextList.get(i))) {
                myContextList.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
        throw new IllegalArgumentException("item is not in dataset");
    }


    interface MyItemClick{
        void onItemClick(MyMessage message);
    }
}
