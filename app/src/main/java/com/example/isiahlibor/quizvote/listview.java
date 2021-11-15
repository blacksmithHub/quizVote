package com.example.isiahlibor.quizvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listview extends AppCompatActivity {

    ListView listPres,listVice,listSec,listTrea;
    Intent out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        out = new Intent(listview.this, adminPage.class);

        listPres = (ListView)findViewById(R.id.listPres);
        listVice = (ListView)findViewById(R.id.listVice);
        listSec = (ListView)findViewById(R.id.listSec);
        listTrea = (ListView)findViewById(R.id.listTrea);

        receiveData();

    }

    private void receiveData(){
        Intent i=this.getIntent();
        list pres = (list) i.getSerializableExtra("pres");
        list vice = (list) i.getSerializableExtra("vice");
        list sec = (list) i.getSerializableExtra("sec");
        list trea = (list) i.getSerializableExtra("trea");
        listPres.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, pres.getPres()));
        listVice.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, vice.getVice()));
        listSec.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, sec.getSec()));
        listTrea.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, trea.getTrea()));
    }

    @Override
    public void onBackPressed() {
        out.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(out);
        listview.super.finish();
    }
}
