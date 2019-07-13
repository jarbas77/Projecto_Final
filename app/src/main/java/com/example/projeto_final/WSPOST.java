package com.example.projeto_final;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WSPOST extends AsyncTask<Void, Void, String> {
    private String url;
    private JSONObject objecto;

    public WSResposta resposta;

    public WSPOST(JSONObject objecto) {

        this.objecto = objecto;
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
            URL _endpoint = new URL("http://10.0.2.2/meals1/public/api/alimentos");
            conexao = (HttpURLConnection) _endpoint.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.setRequestProperty("Content-Type", "application/json;");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.connect();

            // Enviar objecto
            OutputStream outputStream = conexao.getOutputStream();
            outputStream.write(objecto.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.close();


            // Obter código da resposta
            int _httpCode = conexao.getResponseCode();
            if (_httpCode == HttpURLConnection.HTTP_CREATED || _httpCode == HttpURLConnection.HTTP_OK) {
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

