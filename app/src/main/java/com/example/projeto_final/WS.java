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
import java.util.ArrayList;
import java.util.HashMap;

/**public class WS extends AsyncTask<Void, String, Void> {

    public WSResposta resposta;

    public Alimentos activity;
    public ArrayList<HashMap <String, String>> _listaAlimento;
    public ListView _lv;
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
        Log.d("banana","maca2");
        try {
            //URL _endpoint = new URL("http://localhost/ws/public/api/tarefa");
            URL _endpoint = new URL("http://192.168.7.1/meals/public/api/alimentos");
            Log.d("banana","con");
            conexao = (HttpURLConnection)_endpoint.openConnection();
            conexao.setRequestMethod("GET");



            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.setRequestProperty("Accept", "application/json");
            Log.d("banana","con1");
            conexao.connect();
            Log.d("banana","maca3");
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
                        Log.d("banana","maca4");
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
        Log.d("banana","post");
        // Dismiss the progress dialog
       // if (pDialog.isShowing())
         //   pDialog.dismiss();

        ListAdapter adapter = new SimpleAdapter(activity, _listaAlimento, R.layout.listview_row,
                new String[]{"idAlim", "nome", "valenergetico", "gordura", "acucar", "protaina"},
                new int[]{R.id.tv_idAlimentos, R.id.tv_NomeAli,R.id.tv_ValEnerg, R.id.tv_Gordura, R.id.tv_Acucar, R.id.tv_Prota});
        _lv.setAdapter(adapter);
    }


}*/

public class WS extends AsyncTask<Void, Void, String> {
    private String url;

    public WSResposta resposta;

    public WS(String url) {
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
            URL _endpoint = new URL("http://10.0.2.2/meals/public/api/alimentos/");
            conexao = (HttpURLConnection) _endpoint.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.setRequestProperty("Accept", "application/json");
            Log.d("banana2","antes");
            //É SUPOSTO IR BUSCAR UM ID?//
            conexao.connect();
            Log.d("banana2","conecgtou");
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

