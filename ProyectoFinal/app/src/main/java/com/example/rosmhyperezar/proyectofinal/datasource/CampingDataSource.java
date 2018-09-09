package com.example.rosmhyperezar.proyectofinal.datasource;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rosmhyperezar.proyectofinal.modelo.Camping;
import com.example.rosmhyperezar.proyectofinal.modelo.CampingS;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CampingDataSource implements CampingRemoteDataSource {

    private final static String URL_CAMPING = "http://nexo.carm.es/nexo/archivos/recursos/opendata/json/Campings.json";
    private Context context;
    private RequestQueue requestQueue;
    private static CampingRemoteDataSource INSTANCE = null;
    public String body;

    private CampingDataSource(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static CampingRemoteDataSource getInstanece(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CampingDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void consultarCamping(final GetCampingCallback callback) {
        try {
            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    URL_CAMPING, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Gson gson = new Gson();
                        List<Camping> campings = (List<Camping>) gson.fromJson(response.toString(), new TypeToken<List<Camping>>() {
                        }.getType());
                        callback.onCampingLoaded(campings);
                    } catch (Exception e) {
                        callback.onError();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError();
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void guardarCamping(final PostCampingCallback callback, final CampingS camping) {
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, "http://demo4122942.mockable.io",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            callback.guardarCamping();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onError();
                        }
                    }
            ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Gson gson = new GsonBuilder().create();
                    String json = gson.toJson(camping);
                    byte[] body = new byte[0];
                    try {
                        body = json.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        body = new byte[0];
                    }
                    return body;
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


