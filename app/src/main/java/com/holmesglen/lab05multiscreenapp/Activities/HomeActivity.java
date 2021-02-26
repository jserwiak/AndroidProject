package com.holmesglen.lab05multiscreenapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.holmesglen.lab05multiscreenapp.PhoneBookDB.PhonebookDb;
import com.holmesglen.lab05multiscreenapp.R;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    public enum RequestCode
    {
        VIEW_DETAIL_REQUEST_CODE,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get UI objects
        final EditText editContactId = findViewById(R.id.edit_home_id);
        Button btnShowDetail = findViewById(R.id.btn_show_detail);

        //define button click action
        btnShowDetail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //get the index value
                int id = Integer.parseInt(editContactId.getText().toString());
                if(id >= 0 && id < PhonebookDb.getInstance().getNumberOfContact() )
                {
                    //index value is valid, lets build an intent and start the new activity

                    Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                    intent.putExtra("index_of_contacts", id);

                    startActivityForResult(intent, RequestCode.VIEW_DETAIL_REQUEST_CODE.ordinal());
                }
                else
                {
                    Log.d(TAG, "Invalid index value " + id);
                }
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RequestCode.VIEW_DETAIL_REQUEST_CODE.ordinal())
        {
            if(data != null && data.hasExtra("message"))
            {
                //get the return data from DetailActivity
                String message = data.getStringExtra("message");
                //display message with TOAST
                Toast.makeText(
                        getApplicationContext(),
                        "Message: " + message,
                        Toast.LENGTH_LONG
                ).show();
            }
            else
            {
                Log.d(TAG,"no message is returned from DetailActivity");
            }
        }
        else
        {
            Log.d(TAG, "RequestCode(" + requestCode + ") not recognizable");
        }
    }
}
