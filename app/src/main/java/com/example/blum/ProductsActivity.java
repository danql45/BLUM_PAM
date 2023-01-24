package com.example.blum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Cache;
//import com.android.volley.Network;
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.BaseHttpStack;
//import com.android.volley.toolbox.BasicNetwork;
//import com.android.volley.toolbox.DiskBasedCache;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.android.volley.toolbox.HurlStack;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
import com.example.blum.model.ProductNew;
import com.example.blum.persistance.FileRepository;
import com.example.blum.persistance.IChangesObserver;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsActivity extends AppCompatActivity implements IChangesObserver {

    RecyclerView recyclerView;

    //public static String JSON_URL = "https://raw.githubusercontent.com/danql45/ProjektPAM/master/products.json";

    List<ProductNew> productsList;

    ProductsNewAdapter productsNewAdapter;
    private FileRepository repository;
    private int selectedCategory;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView = findViewById(R.id.productsItemList);
        productsList = new ArrayList<>();
        repository = new FileRepository(getApplicationContext());
        selectedCategory = getIntent().getIntExtra("clickedPos", 99);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        productsNewAdapter = new ProductsNewAdapter(this, productsList);
        recyclerView.setAdapter(productsNewAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        repository.subscribe(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        repository.unsubscribe(this);
    }

    @Override
    public void notifyObserver() {
        List<ProductNew> products = repository.getProductsByCategory(selectedCategory);
        this.runOnUiThread(()->{
            productsNewAdapter.setProductsList(products);
        });
    }

}