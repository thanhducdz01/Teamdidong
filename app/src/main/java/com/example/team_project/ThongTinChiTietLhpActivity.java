package com.example.team_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;


public class ThongTinChiTietLhpActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<CTLHP> arrayList;
    CTLHP_Adapter adapter;
    SearchView searchView;
    String url ="http://192.168.0.103/UTEapp/getdata_ttCTLHP.php";

    static String maSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_chi_tiet_lhp);
        ActionBar actionBar = getSupportActionBar();

        if(maSV == null){
            Intent intent = getIntent();
            maSV = intent.getStringExtra("maSV");
        }
        listView = (ListView) findViewById(R.id.listview_ct);
        arrayList = new ArrayList<>();

        adapter = new CTLHP_Adapter(this, R.layout.layout_item_ct, arrayList);
        listView .setAdapter(adapter);

        Intent intent = getIntent();
        String tenLHP = String.valueOf(intent.getStringExtra("tenLHP"));
        String tenCTLHP = String.valueOf(intent.getStringExtra("tenCTLHP"));
        GetData(url, maSV, tenLHP, tenCTLHP);
    }

    private void GetData(String url, String masv, String tenLHP, String tenCTLHP) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(ThongTinChiTietLhpActivity.this,masv+" " + tenLHP + " " + tenCTLHP,Toast.LENGTH_SHORT).show();
                        for (int i = 0;i<response.length();i++)
                        {

                            try {
                                JSONObject object = response.getJSONObject(i);
                                if( masv.trim().equals(object.getString("MaSV")) &&
                                        tenLHP.trim().equals(object.getString("TenLHP")) &&
                                        tenCTLHP.trim().equals(object.getString("TenCTLHP"))
                                ){
                                    arrayList.add(new CTLHP(
                                            object.getString("TenMH"),
                                            object.getInt("STC")
                                    ));}
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThongTinChiTietLhpActivity.this,"Lỗi"+error,Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified())
        {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}