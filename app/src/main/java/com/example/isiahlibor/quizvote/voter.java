package com.example.isiahlibor.quizvote;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.sqlitelib.DataBaseHelper;
import com.sqlitelib.SQLite;

import java.util.ArrayList;
import java.util.List;

public class voter extends AppCompatActivity {

    Button submit;
    Spinner pres, vice, sec, treas;
    String name, position, number, spinpresident, spinvicepresident, spinsecretary, spintreasurer;
    String contact, candidate, post, msg, code;
    Intent back;

    private DataBaseHelper dbhelper = new DataBaseHelper(voter.this, "voterDatabase", 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter);

        submit = (Button)findViewById(R.id.submit);

        pres = (Spinner)findViewById(R.id.pres);
        vice = (Spinner)findViewById(R.id.vice);
        sec = (Spinner)findViewById(R.id.sec);
        treas = (Spinner)findViewById(R.id.treas);

        back = new Intent(voter.this, MainActivity.class);

        code = "#@!*&%";

        submit();
        populate();
        pres();
        vice();
        sec();
        treas();
        reload();
    }
    private void pres(){
        pres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinpresident = pres.getItemAtPosition(i).toString();
                Toast.makeText(voter.this, ""+spinpresident, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void vice(){
        vice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinvicepresident = vice.getItemAtPosition(i).toString();
                Toast.makeText(voter.this, ""+spinvicepresident, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void sec(){
        sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinsecretary = sec.getItemAtPosition(i).toString();
                Toast.makeText(voter.this, ""+spinsecretary, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void treas(){
        treas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spintreasurer = treas.getItemAtPosition(i).toString();
                Toast.makeText(voter.this, ""+spintreasurer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void reload(){
        try {

            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor candids = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            candids.moveToNext();

            if (candids.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name VARCHAR(90), position VARCHAR(90), number INTEGER");
            }

        } catch (Exception e) {
            Toast.makeText(voter.this,""+e,Toast.LENGTH_LONG).show();
        }
    }
    private void populate(){

        final SQLiteDatabase db = dbhelper.getWritableDatabase();

            String query = "Select * FROM tblcandidates";
            Cursor cursor = db.rawQuery(query, null);

        List<String> listpres = new ArrayList<String>();
        List<String> listvice = new ArrayList<String>();
        List<String> listsec = new ArrayList<String>();
        List<String> listtreas = new ArrayList<String>();
        ArrayAdapter<String> treasurer = new ArrayAdapter<String>(this,
                R.layout.spinner_item, listtreas);
        ArrayAdapter<String> secretary = new ArrayAdapter<String>(this,
                R.layout.spinner_item, listsec);
        ArrayAdapter<String> vicepresident = new ArrayAdapter<String>(this,
                R.layout.spinner_item, listvice);
        ArrayAdapter<String> president = new ArrayAdapter<String>(this,
                R.layout.spinner_item, listpres);

            while (cursor.moveToNext()) {
                name = cursor.getString(1);
                position = cursor.getString(2);
                number = cursor.getString(3);

                if(position.equals("President")){
                    listpres.add(cursor.getString(1));
                    president.setDropDownViewResource(R.layout.spinner_item_dropdown);
                    pres.setAdapter(president);
                }
                if(position.equals("Vice President")){
                    listvice.add(cursor.getString(1));
                    vicepresident.setDropDownViewResource(R.layout.spinner_item_dropdown);
                    vice.setAdapter(vicepresident);
                }
                if(position.equals("Secretary")){
                    listsec.add(cursor.getString(1));
                    secretary.setDropDownViewResource(R.layout.spinner_item_dropdown);
                    sec.setAdapter(secretary);
                }
                if(position.equals("Treasurer")){
                    listtreas.add(cursor.getString(1));
                    treasurer.setDropDownViewResource(R.layout.spinner_item_dropdown);
                    treas.setAdapter(treasurer);
                }

            }

    }
    private void submit(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    final SQLiteDatabase db = dbhelper.getWritableDatabase();

                    String query = "Select * FROM tblcandidates where name = '"+spinpresident+"'";
                    Cursor cursor = db.rawQuery(query, null);

                    if (cursor.moveToFirst()) {
                        cursor.moveToFirst();

                        candidate = cursor.getString(1);
                        post = cursor.getString(2);
                        contact = cursor.getString(3);

                        msg = candidate+"."+post+"."+code;

                        try {
                            SmsManager smsMngr = SmsManager.getDefault();
                            smsMngr.sendTextMessage(0+contact, null, msg, null, null);
                            Toast.makeText(voter.this,"success",Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(voter.this,""+e,Toast.LENGTH_LONG).show();
                        }



                    }

                }catch (Exception e){

                    Toast.makeText(voter.this,""+e,Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
