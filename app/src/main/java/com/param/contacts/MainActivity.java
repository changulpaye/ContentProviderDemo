package com.param.contacts;

/*
 * GTU Android Program 12 Understanding content providers and permissions Read phonebook contacts using content providers and display in list 
 * 
 */

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    String namecsv = "";
    String phonecsv = "";

    String namearray[];
    String phonearray[];

    ListView contactListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //====================================================================
        //ListView to Display Contact Names and on click Phone Number in Toast.
        //====================================================================
        contactListView = (ListView) findViewById(R.id.listView1);

        Cursor phones = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {


            //Read Contact Name
            String name = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            //Read Phone Number
            String phoneNumber = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER));

            if (name != null) {
                namecsv += name + ",";
                phonecsv += phoneNumber + ",";
            }

        }
        phones.close();


        //==============================================
        // Convert csv string into array
        //==============================================
        namearray = namecsv.split(",");
        phonearray = phonecsv.split(",");

        //Create Array Adapter and Pass ArrayOfValues to it.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1
                , android.R.id.text1, namearray);

        //BindAdpater with our Actual ListView
        contactListView.setAdapter(adapter);

        //Do something on click on ListView Click on Items
        contactListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {

                //============================================
                // Display number of contact on click.
                //===========================================
                String msg = phonearray[index];
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });


    }

}
