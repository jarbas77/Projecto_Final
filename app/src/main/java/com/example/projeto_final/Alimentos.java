package com.example.projeto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    }

    private void moveToActivity_main() {
        Intent intent = new Intent (Alimentos.this,MainActivity.class);
        startActivity(intent);

        _server = new WS();
        HashMap<String, String> alimento = _server._dados;

        alimento.idAlim



        //_server.activity = ListActivity.this;
        _server._listaAliemento = new ArrayList<>();
        _server._lv = findViewById(R.id.list_view_conteudo);
        _server.execute();

        ListAdapter adapter = new SimpleAdapter(this, _listaAlimento, R.layout.listview_row,
                new String[]{"idAlim", "nome", "valenergetico", "gordura", "acucar", "protaina"},
                new int[]{R.id.tv_idAlimentos, R.id.tv_NomeAli,R.id.tv_ValEnerg, R.id.tv_Gordura, R.id.tv_Acucar, R.id.tv_Prota});
        _lv.setAdapter(adapter);


    }


}