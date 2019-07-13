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

public class Refeicoes extends AppCompatActivity {

    private Button btnMove;
    private WS _server;
    private Button btnEditar;
    private ListView listView;
    private ArrayList<HashMap<String, String>> _listarefeicoes = new ArrayList<>();
    private ListView lvConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicoes);

        btnMove = findViewById(R.id.btn_3);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivity_main();
            }
        });

        lvConteudo = findViewById(R.id.lv_refeicoes);

        btnEditar = findViewById(R.id.btn_addRefeicoes);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToactivity_add_refeicoes();
            }
        });

        listView = findViewById(R.id.lv_refeicoes);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("ClientesMenu", String.valueOf(position));
                HashMap<String, String> itemSelecionado = _listarefeicoes.get(position);
                String idRef = itemSelecionado.get("idRef");
                abrirPerfil(idRef);
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        carregarDados();
    }

    private void carregarDados() {

        WSGETREF wsGet = new WSGETREF("http://192.168.7.1/meals/public/api/refeicoes/");
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
        _listarefeicoes.clear();
        try {
            JSONArray prs = new JSONArray(json);

            for (int i = 0; i < prs.length(); i++) {
                JSONObject obj = null;

                obj = prs.getJSONObject(i);

                String id = obj.getString("idRef");
                String nome = obj.getString("refNome");
                String data = obj.getString("refData");
                String tipo = obj.getString("idTipo");

                //lista de propriedades
                HashMap<String, String> alimento = new HashMap();
                alimento.put("idRef", String.valueOf(id));
                alimento.put("refNome", String.valueOf(nome));
                alimento.put("refData", String.valueOf(data));
                alimento.put("idTipo", String.valueOf(tipo));
                _listarefeicoes.add(alimento);
                Log.d("banana","maca4");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(this, _listarefeicoes, R.layout.listview_refeicoes,
                new String[]{"idRef", "refNome", "refData", "idTipo"},
                new int[]{R.id.tv_idRefeicoes, R.id.tv_NomeRef,R.id.tv_refData, R.id.tv_idTipo});
        lvConteudo.setAdapter(adapter);
    }
    private void moveToActivity_main() {
        Intent intent = new Intent (Refeicoes.this,MainActivity.class);
        startActivity(intent);
    }

    private void moveToactivity_add_refeicoes(){
        Intent intent = new Intent (Refeicoes.this, Activity_addRefeicoes.class);
        startActivity(intent);
    }

    private void abrirPerfil(String id){
        Intent editalAlimIntent = new Intent(Refeicoes.this,EditarAlimentos.class);
        editalAlimIntent.putExtra("idRef",id);
        startActivity(editalAlimIntent);
    }
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent(Refeicoes.this,EditarAlimentos.class);
        //intent.setClass(this, ListItemDetail.class);
        // intent.putExtra("position", position);
        // Or / And
        // intent.putExtra("id", id);
        startActivity(intent);
    }
}