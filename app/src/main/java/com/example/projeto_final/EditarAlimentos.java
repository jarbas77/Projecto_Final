package com.example.projeto_final;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarAlimentos extends AppCompatActivity {
    private Button btnEditar;
    private String id;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alimentos);

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

        id = getIntent().getStringExtra("ID");//temos que verDisto!!!!!
        }

        void enviarAlimento(){

            @SuppressLint("WrongViewCast") EditText editTextAlimento = findViewById(R.id.et_nomeAlimento);
            String nomealimento = editTextAlimento.getText().toString();

            @SuppressLint("WrongViewCast") EditText editTextValorEnergetico = findViewById(R.id.et_valEnerg);
            String valorEnergetico = editTextValorEnergetico.getText().toString();

            @SuppressLint("WrongViewCast") EditText editTextGordura = findViewById(R.id.et_Gordura);
            String gordura = editTextGordura.getText().toString();

            @SuppressLint("WrongViewCast") EditText editTextAcucar = findViewById(R.id.et_Acucar);
            String acucar = editTextAcucar.getText().toString();

            @SuppressLint("WrongViewCast") EditText editTextProta = findViewById(R.id.et_Prota);
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
}
