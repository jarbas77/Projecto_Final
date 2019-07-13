package com.example.projeto_final;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WSGETREF extends AsyncTask<Void, Void, String> {
private String url;

public WSResposta resposta;

public WSGETREF(String url) {
        this.url = url;
        }

@Override
protected void onPreExecute(){
        super.onPreExecute();
        }

@Override
protected String doInBackground(Void... voids) {
        HttpURLConnection conexao;
        InputStream is;
        String jsonStr = null;

        try {
        URL _endpoint = new URL("http://10.0.2.2/meals1/public/api/refeicoes/");
        conexao = (HttpURLConnection) _endpoint.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setReadTimeout(15000);
        conexao.setConnectTimeout(15000);
        conexao.setRequestProperty("Accept", "application/json");
        Log.d("banana2","antes");
        //É SUPOSTO IR BUSCAR UM ID?//
        conexao.connect();
        Log.d("banana2","conectou");
        // Obter código da resposta
        int _httpCode = conexao.getResponseCode();
        if (_httpCode != HttpURLConnection.HTTP_BAD_REQUEST) {
        is = conexao.getInputStream();
        } else {
        is = conexao.getErrorStream();
        }

        // Ler resposta
        jsonStr = inputStreamToString(is);

        is.close();
        conexao.disconnect();
        } catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

        // Devolver resposta
        return jsonStr;
        }

@Override
protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (resposta != null) {
        resposta.respostaRecebida(result);
        }
        }

private static String inputStreamToString(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        try {
        BufferedReader br;
        String linha;

        br = new BufferedReader(new InputStreamReader(is));
        while ((linha = br.readLine()) != null) {
        buffer.append(linha);
        }

        br.close();
        } catch (IOException e) {
        e.printStackTrace();
        }

        return buffer.toString();
        }
}

