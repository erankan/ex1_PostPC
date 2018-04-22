package erankan.myselfchat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<MyMessage> myContextList;

    public MyAdapter(List<MyMessage> listReceived){ myContextList = listReceived;}


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.my_chat, parent, false);
        return new MyViewHolder((myView));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyMessage msg = this.myContextList.get(position);

        // Show sent message in screen.
        holder.dataText.setText(msg.getContent());
        holder.senderText.setText(msg.getSender());
        holder.timeStampText.setText(msg.getTimestamp());
    }

    @Override
    public int getItemCount() {
        if (myContextList == null){
            myContextList = new ArrayList<>();
        }
        return myContextList.size();
    }
}
