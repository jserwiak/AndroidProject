package com.holmesglen.lab05multiscreenapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.holmesglen.lab05multiscreenapp.Models.Contact;
import com.holmesglen.lab05multiscreenapp.PhoneBookDB.PhonebookDb;
import com.holmesglen.lab05multiscreenapp.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //set title on action bar
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Contact Details");
        }

        TextView txtName = findViewById(R.id.txt_detail_name_value);
        TextView txtPhone = findViewById(R.id.txt_detail_phone_value);

        int index = -1;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("index_of_contacts"))
        {
            index = bundle.getInt("index_of_contacts");
            if(index >= 0 && index < PhonebookDb.getInstance().getNumberOfContact())
            {
                //index is valid get data from db and display in ui
                Contact c = PhonebookDb.getInstance().getContact(index);
                if(c != null)
                {
                    txtName.setText(c.getName());
                    txtPhone.setText(c.getPhone());
                }
            }
        }
    }
    @Override
    public void onBackPressed()
    {
        //when click on back button, return a message to parent activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("message", "detail screen finished");
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();

    }
}
