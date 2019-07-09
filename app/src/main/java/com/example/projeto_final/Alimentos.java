package com.example.projeto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Alimentos extends AppCompatActivity {
/*test*/
    private WS _server;
    private Button btnMove;
    private ArrayList<HashMap<String, String>> _listaAlimento;
    private ListView _lv;
    private ListView lvConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);

        btnMove = findViewById(R.id.btn_3);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity_main();
            }
        });

       // View includeLayout = findViewById(R.id.uuu);
        _server = new WS();
       // HashMap<String, String> alimento = _server._dados;

        //alimento.idAlim
        _server.activity = Alimentos.this;
        _server._listaAlimento = new ArrayList<>();
        _server._lv = findViewById(R.id.list_view_test);
        _server.execute();

        Log.d("banana","maca");


    }

    private void moveToActivity_main() {
        Intent intent = new Intent (Alimentos.this,MainActivity.class);
        startActivity(intent);

    }
}