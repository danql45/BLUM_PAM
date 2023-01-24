package com.example.blum.persistance;

import android.content.Context;
import android.util.Log;

import com.example.blum.Products;
import com.example.blum.model.ProductInfo;
import com.example.blum.model.ProductNew;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FileRepository implements IRepository{

    private String JSON_URL = "https://raw.githubusercontent.com/danql45/BLUM_PAM/af0c2209e36b82038c80c2d2c42270eaac9e47bd/products.json";
    private String FILE_NAME = "products.txt";
    private int REQUEST_INTERVAL = 60000;
    private OkHttpClient client;
    private List<ProductNew> availableProducts;
    private List<IChangesObserver> observers;
    private ObjectMapper mapper;
    private Context applicationContext;
    private ScheduledExecutorService scheduledExecutorService;
    private Executor executor;

    public FileRepository(Context applicationContext) {
        executor = Executors.newSingleThreadExecutor();
        observers = new ArrayList<>();
        client = new OkHttpClient();
        mapper = new ObjectMapper();
        availableProducts = new ArrayList<>();
        this.applicationContext = applicationContext;
        createObjectsFromFile();
        startScheduledExecutor();
    }

    private void startScheduledExecutor() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                executeRequest();
            }
        }, 0, 60, TimeUnit.SECONDS);
    }

    @Override
    public List<ProductNew> getProducts() {
        return availableProducts;
    }

    @Override
    public List<ProductNew> getProductsByCategory(int categoryId) {
        List<ProductNew> prods = new ArrayList<>();
        for(ProductNew p : availableProducts){
            if(p.getCategory() == categoryId){
                prods.add(p);
            }
        }
        return prods;
    }

    @Override
    public List<Products> getProductsOldByCategory(int categoryId) {
        List<ProductNew> prods = getProductsByCategory(categoryId);
        return createOldProductsFromNew(prods);
    }

    @Override
    public ProductNew getProductById(int id) {
        for(ProductNew p : availableProducts){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public void subscribe(IChangesObserver observer) {
        observers.add(observer);
        observer.notifyObserver();
    }

    @Override
    public void unsubscribe(IChangesObserver observer) {
        observers.remove(observer);
    }


    private void executeRequest(){
        Request request = new Request.Builder()
                .url(JSON_URL)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.code() == 200){
                parseResponse(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(ResponseBody body) {
        try {
            String responseBody = body.string();
            writeToFile(responseBody);
            createObjectsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(applicationContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(FILE_NAME);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("REPOSITORY", e.toString());
        } catch (IOException e) {
            Log.e("REPOSITORY", e.toString());
        }

        return ret;
    }

    private void createObjectsFromFile() {
        executor.execute(()->{
            String json = readFromFile(applicationContext);
            try {
                ProductNew[] prods = mapper.readValue(json,ProductNew[].class);
                if(prods!=null && prods.length>0){
                    availableProducts = Arrays.asList(prods);
                    notifyListeners();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void notifyListeners() {
        for(IChangesObserver observer : observers){
            observer.notifyObserver();
        }
    }


    private List<Products> createOldProductsFromNew(List<ProductNew> prods) {
        List<ProductNew> prodsNewSplitted = new ArrayList<>();
        List<Products> productsList = new ArrayList<>();

        for(ProductNew p2 : prods){
            ProductNew p3 = p2;
            p3.setImageURL(null);
            prodsNewSplitted.add(p2);
            for(ProductInfo pi : p2.getProductInfo()){
                ProductNew temp = p2;
                temp.setProductInfoList(Collections.singletonList(pi));
                prodsNewSplitted.add(temp);
            }
        }
        for(ProductNew p2 : prodsNewSplitted){
            try{
                productsList.add(new Products(p2));
            }catch (Exception e){
                Log.i("TAG", "createOldProductsFromNew: no cos poszlo nie tak");
            }
        }
        return productsList;
    }
}
