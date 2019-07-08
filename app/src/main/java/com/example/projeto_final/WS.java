package com.example.projeto_final;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.HashMap;

public class WS extends AsyncTask<Void, Void, Void> {
    public ListActivity activity;
    private ProgressDialog pDialog;
    public ArrayList<HashMap<String, String>> _dados;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpURLConnection conexao;
        InputStream is;
        String jsonStr;

        try {
            //URL _endpoint = new URL("http://localhost/ws/public/api/tarefa");
            URL _endpoint = new URL("http://10.0.2.2/meals/public/api/alimentos");
            conexao = (HttpURLConnection) _endpoint.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            //aqui deve estar o código de envio dos dados (quando ocorre)

            int _httpCode = conexao.getResponseCode();
            if (_httpCode != HttpURLConnection.HTTP_BAD_REQUEST) {
                is = conexao.getInputStream();
            } else {
                is = conexao.getErrorStream();
            }
            jsonStr = inputStreamToString(is);

            //aqui deve estar o código que converte o stream para HashMap

            int count;
            if (jsonStr != null) {
                try {
                    JSONArray prs = new JSONArray(jsonStr);

                    for (int i = 0; i < prs.length(); i++)
                    {
                        JSONObject obj = prs.getJSONObject(i);
                        String id = obj.getString("idAlim");
                        String nome = obj.getString("alNome");
                        String valEnergetico = obj.getString("alValEnergetico");
                        String godura = obj.getString("alGodura");
                        String acucar = obj.getString("alAcucar");
                        String protaina = obj.getString("alProtaina");

                        //lista de propriedades
                        HashMap<String, String> alimento = new HashMap();
                        alimento.put("idAlim", String.valueOf(id));
                        alimento.put("nome", String.valueOf(nome));
                        alimento.put("valenergetico", String.valueOf(valEnergetico));
                        alimento.put("gordura", String.valueOf(godura));
                        alimento.put("acucar", String.valueOf(acucar));
                        alimento.put("protaina", String.valueOf(protaina));

                        _dados.add(alimento);
                    }
                } catch (final JSONException e) {
                    Log.d("Erro", "doInBackground: " + e.getMessage());
                }
            }
            is.close();
            conexao.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String inputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
