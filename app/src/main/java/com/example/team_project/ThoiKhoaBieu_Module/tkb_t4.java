package com.example.team_project.ThoiKhoaBieu_Module;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.team_project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class tkb_t4 extends Fragment {

    RecyclerView RecView_t4;
    String urlGetData ="http://10.0.2.2/Didong/ketnoi.php";
    ArrayList<tkb> arrayTKB;
    TKBAdapter adapter;
    //    static String masv="1911505310115";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String maSV = getArguments().getString("maSV");
        View view =LayoutInflater.from(getContext()).inflate(R.layout.fragment_tkb_t4,container,false);
        // View view =  inflater.inflate(R.layout.fragment_tkb_t4, container, false);
        RecView_t4 = view.findViewById(R.id.listviewTKB_T4);
        arrayTKB = new ArrayList<>();
        adapter = new TKBAdapter(arrayTKB);
        RecView_t4.setAdapter(adapter);
        testGet(urlGetData,maSV);
        return  view;
    }
    private void  testGet(String url,String maSV){
        StringRequest eventoReq = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                try {
                                    JSONObject object = j.getJSONObject(i);
                                    arrayTKB.add(new tkb(
                                            object.getString("ID"),
                                            object.getString("TietBD"),
                                            object.getString("TietKT"),
                                            object.getString("idPhong"),
                                            object.getString("MonHoc")
                                    ));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("maSV", maSV);
                params.put("Thu", "4");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(eventoReq);
    }


}