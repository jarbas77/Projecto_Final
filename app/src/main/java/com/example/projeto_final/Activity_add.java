package com.example.projeto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_add extends AppCompatActivity {

    private Button btnBack;
    private Button btn_adicionar;
    EditText editTextAlimento;
    EditText editTextValorEnergetico;
    EditText editTextGordura;
    EditText editTextAcucar;
    EditText editTextProta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextAlimento = findViewById(R.id.et_nomeAlimento2);
        editTextValorEnergetico = findViewById(R.id.et_valEnerg2);
        editTextGordura = findViewById(R.id.et_Gordura2);
        editTextAcucar = findViewById(R.id.et_Acucar2);
        editTextProta = findViewById(R.id.et_Prota2);

        btn_adicionar = findViewById(R.id.btn_addAlimento2);

        btnBack = findViewById(R.id.btn_back2);

        btn_adicionar.setOnClickListener(new View.OnClickListener() {
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

            WSPOST put = new WSPOST(json);
            put.resposta = new WSResposta() {
                @Override
                public void respostaRecebida(String resposta) {
                    finish();
                    Log.d("foi","try");
                }
            };
            put.execute();
        } catch (JSONException e) {
            e.printStackTrace();
            mostarErro();
        }
    }

    private void mostarErro() {
        //Toast.makeText(this, "Ocorreu um erro.", LENGTH_SHORT).show();
        //Snackbar.make(btnEditar, "Ocorreu um erro.", Snackbar.LENGTH_LONG).show();

    }

    private void moveToactivity_alimentos(){
        Intent intent = new Intent (Activity_add.this,Alimentos.class);
        startActivity(intent);


    }
}
