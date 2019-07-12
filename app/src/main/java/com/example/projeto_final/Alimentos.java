package com.example.projeto_final;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Alimentos extends AppCompatActivity {
/*test*/
    private WS _server;
    private Button btnMove;
    private Button btnEditar;
    private ArrayList<HashMap<String, String>> _listaAlimentos = new ArrayList<>();
    private ListView _lv;
    private ListView lvConteudo;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);

        btnMove = findViewById(R.id.btn_3);

        btnEditar = findViewById(R.id.btn_addAlimento);



        lvConteudo = findViewById(R.id.list_view_test);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity_main();
            }
        });
       btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToactivity_add_alimentos();
            }
        });

        listView =  findViewById(R.id.list_view_test);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("ClientesMenu", String.valueOf(position));
                HashMap<String, String> itemSelecionado = _listaAlimentos.get(position);
                String idAlim = itemSelecionado.get("idAlim");
                abrirPerfil(idAlim);
            }
        });

    /*  // View includeLayout = findViewById(R.id.uuu);
        _server = new WS();
       // HashMap<String, String> alimento = _server._dados;

        //alimento.idAlim
        _server.activity = Alimentos.this;
        _server._listaAlimento = new ArrayList<>();
        _server._lv = findViewById(R.id.list_view_test);
        _server.execute();

        Log.d("banana","maca");*/


    }

    @Override
    public void onResume() {
        super.onResume();
        carregarDados();
    }




    private void carregarDados() {

        WS wsGet = new WS("http://192.168.7.1/meals/public/api/alimentos/");
        wsGet.resposta = new WSResposta() {
            @Override
            public void respostaRecebida(String resposta) {
                if (resposta != null) {
                    mostrarDados(resposta);
                } else {
                    mostarErro();
                }
            }
        };
        wsGet.execute();
    }

    private void mostarErro() {

        Snackbar.make(lvConteudo, "Ocorreu um erro.", Snackbar.LENGTH_LONG).show();
    }

    private void mostrarDados(String json) {
        _listaAlimentos.clear();
        try {
            JSONArray prs = new JSONArray(json);

            for (int i = 0; i < prs.length(); i++) {
                JSONObject obj = null;

                obj = prs.getJSONObject(i);

                String id = obj.getString("idAlim");
                String nome = obj.getString("alNome");
                String valEnergetico = obj.getString("alValEnergetico");
                String godura = obj.getString("alGordura");
                String acucar = obj.getString("alAcucar");
                String protaina = obj.getString("alProteina");


                //lista de propriedades
                HashMap<String, String> alimento = new HashMap();
                alimento.put("idAlim", String.valueOf(id));
                alimento.put("nome", String.valueOf(nome));
                alimento.put("valenergetico", String.valueOf(valEnergetico));
                alimento.put("gordura", String.valueOf(godura));
                alimento.put("acucar", String.valueOf(acucar));
                alimento.put("proteina", String.valueOf(protaina));
                _listaAlimentos.add(alimento);
                Log.d("banana","maca4");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(this, _listaAlimentos, R.layout.listview_row,
                new String[]{"idAlim", "nome", "valenergetico", "gordura", "acucar", "protaina"},
                new int[]{R.id.tv_idAlimentos, R.id.tv_NomeAli,R.id.tv_ValEnerg, R.id.tv_Gordura, R.id.tv_Acucar, R.id.tv_Prota});
        lvConteudo.setAdapter(adapter);


    }

    private void moveToActivity_main() {
        Intent intent = new Intent (Alimentos.this,MainActivity.class);
        startActivity(intent);
    }

    private void moveToactivity_add_alimentos(){
        Intent intent = new Intent (Alimentos.this, Activity_add.class);
        startActivity(intent);
    }

    private void abrirPerfil(String id){
        Intent editalAlimIntent = new Intent(Alimentos.this,EditarAlimentos.class);
        editalAlimIntent.putExtra("idAlim",id);
        startActivity(editalAlimIntent);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent(Alimentos.this,EditarAlimentos.class);
        //intent.setClass(this, ListItemDetail.class);
       // intent.putExtra("position", position);
        // Or / And
       // intent.putExtra("id", id);
        startActivity(intent);
    }
}