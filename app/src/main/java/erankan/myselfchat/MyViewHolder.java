package erankan.myselfchat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    private LinearLayout myMessageLayout;
    TextView senderText, dataText, timeStampText;

    public MyViewHolder(View itemView){
        super(itemView);
        myMessageLayout = itemView.findViewById(R.id.my_chat_layout);
        dataText = itemView.findViewById(R.id.dataText);
        senderText = itemView.findViewById(R.id.senderText);
        timeStampText = itemView.findViewById(R.id.timeStampText);
    }
}
