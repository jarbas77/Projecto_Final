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

public class Activity_addRefeicoes extends AppCompatActivity {
    private Button btn_Back1;
    private Button btn_adicionar1;
    EditText editTextnome;
    EditText editTextdata;
    EditText editTexttipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_refeicoes);

        btn_Back1 = findViewById(R.id.btn_Back3);

        btn_Back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToactivity_refeicoes();
            }
        });

        btn_adicionar1 = findViewById(R.id.btn_add1);
        btn_adicionar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarRef();
            }
        });

        editTextnome = findViewById(R.id.et_nome);
        editTextdata = findViewById(R.id.et_data);
        editTexttipo = findViewById(R.id.et_tipo);
    }

    void enviarRef(){


        String nomeref = editTextnome.getText().toString();

        String data = editTextdata.getText().toString();

        String tipo = editTexttipo.getText().toString();


        JSONObject json = new JSONObject();

        try {
            json.put("refNome",nomeref);
            json.put("refData",data);
            json.put("idTipo",tipo);

            WSPOSTREF put = new WSPOSTREF(json);
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

    private void moveToactivity_refeicoes(){
        Intent intent = new Intent (Activity_addRefeicoes.this,Refeicoes.class);
        startActivity(intent);


    }
}
