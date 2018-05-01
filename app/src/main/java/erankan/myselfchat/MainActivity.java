package erankan.myselfchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyItemClick, MyFragment.MessageDeletedListener{

    private static final String[] senders = {"Eran", "Lior", "Adi"};
    private int num;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = 0;

        // Set RecyclerView layout manager
        final RecyclerView myRec = findViewById(R.id.my_recycle);
        LinearLayoutManager myManger = new LinearLayoutManager(this);
        myRec.setLayoutManager(myManger);

        final List<MyMessage> myListMessage = new ArrayList<>();
        adapter = new MyAdapter(this, myListMessage);

        // set data adapter to RecyclerView
        myRec.setAdapter(adapter);

        // Connect the objects.
        final EditText msgInput = findViewById(R.id.chat_text);
        Button myButton = findViewById(R.id.my_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sentMessage = msgInput.getText().toString();
                if (!TextUtils.isEmpty(sentMessage)){

                    // add a new message to the list
                    MyMessage msg = new MyMessage(senders[num], sentMessage);
                    adapter.addMessage(msg);

                    myRec.scrollToPosition(myListMessage.size()-1);

                    // empty the input edit text box
                    msgInput.setText("");

                    num = (num + 1) % senders.length;

                }
            }
        });

    }


    @Override
    public void onItemClick(MyMessage message) {
        MyFragment fragment = MyFragment.newInstance(message);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onMessageDeleted(MyMessage msg) {
        adapter.removeItem(msg);
        getSupportFragmentManager().popBackStack();
    }
}
