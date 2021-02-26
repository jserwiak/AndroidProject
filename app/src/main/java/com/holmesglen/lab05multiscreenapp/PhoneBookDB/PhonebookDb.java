package com.holmesglen.lab05multiscreenapp.PhoneBookDB;

import android.util.Log;

import com.holmesglen.lab05multiscreenapp.Models.Contact;

import java.util.ArrayList;

// refer to https://www.geeksforgeeks.org/singleton-class-java/
// Java program implementing Singleton class
// with getInstance() method
public class PhonebookDb {
    private final String TAG = this.getClass().getSimpleName();

    // static variable single_instance of type Singleton
    private static PhonebookDb db_instance = null;

    // variable of type ArrayList
    private ArrayList<Contact> phonebook;

    public static int getNumberOfContact()
    {
        return PhonebookDb.getInstance().phonebook.size();
    }
    // private constructor restricted to this class itself
    private PhonebookDb()

    {
        phonebook = new ArrayList<Contact>();
        phonebook.add(new Contact("John Smith", "0477568584"));
        phonebook.add(new Contact("Paul Black","0477523654"));
        phonebook.add(new Contact("Bob White", "0411896523"));
        phonebook.add(new Contact("Alan Edwards", "047896524"));
    }

    // static method to create instance of Singleton class
    public static PhonebookDb getInstance()
    {
        if (db_instance == null)
            db_instance = new PhonebookDb();

        return db_instance;
    }

    // add a element to the end of list
    public void addContact(Contact newContact) {
        phonebook.add(newContact);
        return;
    }

    // dump db data to logcat for debug purpose
    public void dumpDB() {
        Log.d(TAG, "Dump phonebook data ");
        if (phonebook == null) {
            Log.d(TAG, "db not initialized");
        } else {
            Log.d(TAG, "number of contact: " + phonebook.size());
            for (int i = 0; i < phonebook.size(); i++){
                Contact c = phonebook.get(i);
                Log.d(TAG, "(" + i + ") Name: " + c.getName() +
                        "; Email: " + c.getPhone());
            }
        }
        return;
    }

    public Contact getContact(int index)
    {
        Contact c = null;
        if(index >= 0 && index <phonebook.size())
        {
            c= phonebook.get(index);
        }
        return c;
    }
}