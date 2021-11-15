package com.example.isiahlibor.quizvote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sqlitelib.DataBaseHelper;
import com.sqlitelib.SQLite;

import java.util.ArrayList;

public class adminPage extends AppCompatActivity {

    Spinner post;
    EditText name, num;
    Button add, cand, logout;
    Intent list, out;
    String getPost;

    ArrayList<String> president,vicepresident,secretary,treasurer;

    private DataBaseHelper dbhelper = new DataBaseHelper(adminPage.this, "voterDatabase", 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        name = (EditText)findViewById(R.id.name);
        post = (Spinner)findViewById(R.id.post);
        num = (EditText)findViewById(R.id.num);

        add = (Button)findViewById(R.id.btnAdd);
        cand = (Button)findViewById(R.id.btnView);
        logout = (Button)findViewById(R.id.btnLogout);

        president = new ArrayList();
        vicepresident = new ArrayList();
        secretary = new ArrayList();
        treasurer = new ArrayList();

        out = new Intent(adminPage.this, MainActivity.class);
        list = new Intent(adminPage.this, listview.class);

        name();
        position();
        num();

        add();
        cand();
        out();

        refreshall();

    }

    private list getPres()
    {
        list pres = new list();
        pres.setPres(president);
        return pres;
    }

    private list getVice()
    {
        list vice = new list();
        vice.setVice(vicepresident);
        return vice;
    }

    private list getSec()
    {
        list sec = new list();
        sec.setSec(secretary);
        return sec;
    }

    private list getTrea()
    {
        list trea = new list();
        trea.setTrea(treasurer);
        return trea;
    }

    private void sendData()
    {
        list.putExtra("pres",this.getPres());
        list.putExtra("vice",this.getVice());
        list.putExtra("sec",this.getSec());
        list.putExtra("trea",this.getTrea());
        startActivity(list);
    }

    private void setdefault(){
        name.setText("");
        num.setText("");
    }

    private void refreshall(){
        setdefault();
        reload();
        reloadPres();
        reloadVice();
        reloadSec();
        reloadTrea();
    }

    private void reloadTrea(){
        try {
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor list = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            list.moveToNext();
            if (list.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER(90) AUTO-INCREMENT PRIMARY KEY," +
                                "name VARCHAR(90) not null unique, position VARCHAR(90), number VARCHAR(90)");
            }else {
                list = db.rawQuery("SELECT * FROM tblcandidates where position = '"+"Treasurer"+"'", null);
                String value[] = new String[list.getCount()];
                int ctrl = 0;
                treasurer.clear();
                while (list.moveToNext()) {
                    String strFor = "";
                    strFor += System.lineSeparator() + "Name: " + list.getString(list.getColumnIndex("name"));
                    strFor += System.lineSeparator() + "Number: " + list.getString(list.getColumnIndex("number"));
                    value[ctrl] = strFor;
                    treasurer.add(strFor);
                    ctrl++;
                }
            }
        } catch (Exception e) {
            Toast.makeText(adminPage.this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    private void reloadSec(){
        try {
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor list = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            list.moveToNext();
            if (list.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER(90) AUTO-INCREMENT PRIMARY KEY," +
                                "name VARCHAR(90) not null unique, position VARCHAR(90), number VARCHAR(90)");
            }else {
                list = db.rawQuery("SELECT * FROM tblcandidates where position = '"+"Secretary"+"'", null);
                String value[] = new String[list.getCount()];
                int ctrl = 0;
                secretary.clear();
                while (list.moveToNext()) {
                    String strFor = "";
                    strFor += System.lineSeparator() + "Name: " + list.getString(list.getColumnIndex("name"));
                    strFor += System.lineSeparator() + "Number: " + list.getString(list.getColumnIndex("number"));
                    value[ctrl] = strFor;
                    secretary.add(strFor);
                    ctrl++;
                }
            }
        } catch (Exception e) {
            Toast.makeText(adminPage.this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    private void reloadVice(){
        try {
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor list = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            list.moveToNext();
            if (list.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER(90) AUTO-INCREMENT PRIMARY KEY," +
                                "name VARCHAR(90) not null unique, position VARCHAR(90), number VARCHAR(90)");
            }else {
                list = db.rawQuery("SELECT * FROM tblcandidates where position = '"+"Vice President"+"'", null);
                String value[] = new String[list.getCount()];
                int ctrl = 0;
                vicepresident.clear();
                while (list.moveToNext()) {
                    String strFor = "";
                    strFor += System.lineSeparator() + "Name: " + list.getString(list.getColumnIndex("name"));
                    strFor += System.lineSeparator() + "Number: " + list.getString(list.getColumnIndex("number"));
                    value[ctrl] = strFor;
                    vicepresident.add(strFor);
                    ctrl++;
                }
            }
        } catch (Exception e) {
            Toast.makeText(adminPage.this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    private void reloadPres(){
        try {
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor list = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            list.moveToNext();
            if (list.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER(90) AUTO-INCREMENT PRIMARY KEY," +
                                "name VARCHAR(90) not null unique, position VARCHAR(90), number VARCHAR(90)");
            }else {
                list = db.rawQuery("SELECT * FROM tblcandidates where position = '"+"President"+"'", null);
                String value[] = new String[list.getCount()];
                int ctrl = 0;
                president.clear();
                while (list.moveToNext()) {
                    String strFor = "";
                    strFor += System.lineSeparator() + "Name: " + list.getString(list.getColumnIndex("name"));
                    strFor += System.lineSeparator() + "Number: " + list.getString(list.getColumnIndex("number"));
                    value[ctrl] = strFor;
                    president.add(strFor);
                    ctrl++;
                }
            }
        } catch (Exception e) {
            Toast.makeText(adminPage.this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    private void reload(){
        try {

            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor candids = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tblcandidates'", null);
            candids.moveToNext();

            if (candids.getCount() == 0) {
                SQLite.FITCreateTable("voterDatabase", this, "tblcandidates",
                        " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name VARCHAR(90) not null unique, position VARCHAR(90), number INTEGER");
            }

        } catch (Exception e) {
            Toast.makeText(adminPage.this,""+e,Toast.LENGTH_LONG).show();
        }
    }

    private void out(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(out);
            adminPage.super.finish();
            }
        });
    }

    private void cand(){
        cand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    private void add(){
        final SQLiteDatabase db = dbhelper.getWritableDatabase();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sqlStr1 = "select name from tblcandidates where name = '"+name.getText().toString()+"'";
                Cursor cursor = db.rawQuery(sqlStr1, null);
                if (cursor.moveToFirst()) {
                    cursor.moveToFirst();
                    Toast.makeText(adminPage.this,"candidate already exist",Toast.LENGTH_LONG).show();
                }else{
                    try{
                        String sqlStr = "INSERT INTO tblcandidates (name, position, number) VALUES ('"
                                + name.getText().toString() + "', '" + getPost + "', '" + num.getText().toString() + "')";
                        db.execSQL(sqlStr);
                        refreshall();
                        Toast.makeText(adminPage.this,"candidate registered",Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(adminPage.this,"insert-"+e,Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void num(){
        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(num.length() != 0) {
                } else {
                    if(num.getText().length() == 0) {
                        num.setError("This field cannot be blank");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void position(){

        post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                getPost = post.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void name(){
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(name.length() != 0) {
                } else {
                    if(name.getText().length() == 0) {
                        name.setError("This field cannot be blank");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
