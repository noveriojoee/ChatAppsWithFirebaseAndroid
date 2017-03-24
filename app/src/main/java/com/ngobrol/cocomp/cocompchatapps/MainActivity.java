package com.ngobrol.cocomp.cocompchatapps;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ngobrol.cocomp.cocompchatapps.Config.Configuration;
import com.ngobrol.cocomp.cocompchatapps.Models.UserModel;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Firebase cloudDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cloudDB.setAndroidContext(this);

        Configuration.AndroidID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        this.cloudDB = new Firebase(Configuration.FIREBASE_URL);

        //Check if this Phone Already being a member or not.
        this.cloudDB.child("Members").child("Member_"+Configuration.AndroidID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    //user already exists
                    Configuration.LogonSessionUserData = dataSnapshot.getValue(UserModel.class);
                    Intent chatRoomActivity = new Intent(getApplicationContext(),ChatRoomActivity.class);
                    startActivity(chatRoomActivity);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void btnJoinOnClick(View v){
        String strUserName = ((EditText) findViewById(R.id.tbUserName)).getText().toString();
        String strPin = ((EditText) findViewById(R.id.tbPIN)).getText().toString();
        String uniqueID = UUID.randomUUID().toString();

        UserModel dataUser = new UserModel();
        dataUser.setUsername(strUserName);
        dataUser.setPin(strPin);
        dataUser.setUserID(uniqueID);
        dataUser.setUserDeviceID(Configuration.AndroidID);

        this.cloudDB.child("Members").child("Member_"+dataUser.getUserDeviceID()).setValue(dataUser);

    }
}
