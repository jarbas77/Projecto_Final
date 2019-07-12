package com.example.projeto_final;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditarAlimentos extends AppCompatActivity {
    private Button btnEditar;
    private String id;
    private Button btnBack;
    EditText editTextAlimento;
    EditText editTextValorEnergetico;
    EditText editTextGordura;
    EditText editTextAcucar;
    EditText editTextProta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);

        editTextAlimento = findViewById(R.id.et_nomeAlimento);
        editTextValorEnergetico = findViewById(R.id.et_valEnerg);
        editTextGordura = findViewById(R.id.et_Gordura);
        editTextAcucar = findViewById(R.id.et_Acucar);
        editTextProta = findViewById(R.id.et_Prota);

        btnEditar = findViewById(R.id.btn_addAlimento);

        btnBack = findViewById(R.id.btn_back);

        btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarAlimento();
                }
            });

        btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveToactivity_alimentos();
                }
            });


        id = getIntent().getStringExtra("idAlim");
        carregarDados(id);

        Log.d("Coentro",id);
        Log.d("Coentro","test");

        }

    private void carregarDados(String id) {
        WS wsGet = new WS("http://192.168.7.1/meals/public/api/alimentos/" + id);
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

        void enviarAlimento(){


            String nomealimento = editTextAlimento.getText().toString();

            String valorEnergetico = editTextValorEnergetico.getText().toString();

            String gordura = editTextGordura.getText().toString();

            String acucar = editTextAcucar.getText().toString();

            String proteina = editTextProta.getText().toString();

            JSONObject json = new JSONObject();

            try {
                json.put("idAlim",nomealimento);
                json.put("alValEnergetico",valorEnergetico);
                json.put("alGordura",gordura);
                json.put("alAcucar",acucar);
                json.put("alProteina",proteina);

                WS put = new WS("http://192.168.7.1/meals/public/api/alimentos");
                put.resposta = new WSResposta() {
                    @Override
                    public void respostaRecebida(String resposta) {
                        finish();
                    }
                };
                put.execute();
            } catch (JSONException e) {
                e.printStackTrace();
                mostarErro();
            }
        }

        private void mostarErro() {
            Snackbar.make(btnEditar, "Ocorreu um erro.", Snackbar.LENGTH_LONG).show();

        }
            private void movetoactivity_alimentos(){
                Intent intent = new Intent (EditarAlimentos.this,Alimentos.class);
                startActivity(intent);
            }

            private void moveToactivity_alimentos(){
                Intent intent = new Intent (EditarAlimentos.this,Alimentos.class);
                startActivity(intent);


            }

    private void mostrarDados(String json) {
        try {
            JSONObject obj = new JSONObject(json);

            String id = obj.getString("idAlim");
            String nome = obj.getString("alNome");
            String valEnergetico = obj.getString("alValEnergetico");
            String godura = obj.getString("alGordura");
            String acucar = obj.getString("alAcucar");
            String protaina = obj.getString("alProteina");

            Log.d("Coentro",id);
            Log.d("Coentro",nome);
            editTextAlimento.setText(nome);
            editTextValorEnergetico.setText(valEnergetico);
            editTextGordura.setText(godura);
            editTextAcucar.setText(acucar);
            editTextProta.setText(protaina);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
