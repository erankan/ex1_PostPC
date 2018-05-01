package erankan.myselfchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.ButterKnife;
import butterknife.OnClick;

import java.text.DateFormat;

public class MyFragment extends Fragment {

    public static final String TAG = "MyFragment";
    public static final String KEY_MESSAGE = "message";

    MyMessage msg;
    MessageDeletedListener clicker;

    public static MyFragment newInstance(MyMessage message){
        Bundle arg = new Bundle();

        arg.putString(KEY_MESSAGE, message.getMessage());
        MyFragment fragment = new MyFragment();
        fragment.setArguments(arg);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);
        updateMsg();
        ButterKnife.bind(this, view);
        TextView senderView = view.findViewById(R.id.fragment_sender_name);
        TextView dateView = view.findViewById(R.id.fragment_date);
        senderView.setText(msg.getSender());
        dateView.setText(DateFormat.getDateInstance().format(msg.getTimestamp()));


        return view;
    }

    private void updateMsg(){
        if (getArguments() != null){
            try {
                String[] parts = getArguments().getString(KEY_MESSAGE).split("-");
                msg = new MyMessage(parts[0], parts[1], parts[2]);

            } catch (NullPointerException e){
                Log.w(TAG, e);
            }

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MessageDeletedListener){
            this.clicker  = (MessageDeletedListener) context;
        }
    }


    @OnClick(R.id.fragment_delete)
    public void deleteMessage(){
        if (this.clicker != null && msg != null) {
            this.clicker.onMessageDeleted(msg);
        }
    }


    public interface MessageDeletedListener {
        void onMessageDeleted(MyMessage msg);
    }
}
