package com.ngobrol.cocomp.cocompchatapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ngobrol.cocomp.cocompchatapps.Adapters.ChatListAdapter;
import com.ngobrol.cocomp.cocompchatapps.Config.Configuration;
import com.ngobrol.cocomp.cocompchatapps.Models.ChatMessageModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ChatRoomActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSubmitMessage;
    private EditText tbMessageBox;
    private ListView lvChatListMessages;
    private Firebase cloudDB;
    private List<ChatMessageModel> ListOfMessages;
    private ChatListAdapter listViewAdapterChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        this.InitializeVariable();
        this.cloudDB.setAndroidContext(this);
        this.cloudDB = new Firebase(Configuration.FIREBASE_URL);

        // noverio debug begin
//        this.cloudDB.child("ConvertationsALL").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    ListOfMessages.add(postSnapshot.getValue(ChatMessageModel.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
        //noverio debug end


        this.cloudDB.child("ConvertationsALL").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ListOfMessages.add(dataSnapshot.getValue(ChatMessageModel.class));
                listViewAdapterChat.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void InitializeVariable(){
        this.btnSubmitMessage = (Button)findViewById(R.id.btnSubmitMessage);
        this.tbMessageBox = (EditText)findViewById(R.id.tbChatMessage);
        this.lvChatListMessages = (ListView) findViewById(R.id.lvChatListMessages);

        this.ListOfMessages = new ArrayList<ChatMessageModel>();
        this.btnSubmitMessage.setOnClickListener(this);
        this.listViewAdapterChat = new ChatListAdapter(this,this.ListOfMessages);
        this.lvChatListMessages.setAdapter(this.listViewAdapterChat);

    }

    @Override
    public void onClick(View v) {
        ChatMessageModel chatMessage = new ChatMessageModel();
        String messageID = UUID.randomUUID().toString();
        chatMessage.setMessage(tbMessageBox.getText().toString());
        chatMessage.setFrom_UserID(Configuration.LogonSessionUserData.getUsername());
        chatMessage.setMessageID(messageID);
        chatMessage.setConverstationID("ConvertationsALL");
        chatMessage.setMessageDate(new Date());

        tbMessageBox.setText("");

        cloudDB.child("ConvertationsALL").child(chatMessage.getMessageID()).setValue(chatMessage);

    }
}
