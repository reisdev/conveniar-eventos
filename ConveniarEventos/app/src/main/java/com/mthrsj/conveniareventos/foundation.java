package com.mthrsj.conveniareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mthrsj.conveniareventos.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class foundation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        Intent it = getIntent();
        String[] aux = it.getStringArrayExtra("foundation_bundle");
        FoundationPicker FndPicker = findViewById(R.id.FoundationList);
        FndPicker.setValues(aux);


        //TODO: A partir da fundacao selecionada mudar o link da api a baixo
        /*ConveniarEndpoints apiService = ConveniarAPI.getClient("https://servicos.conveniar.com.br/servicos/api/").create(ConveniarEndpoints.class);
        Call<List<Category>> allCategory = apiService.getCategories();
        allCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                if(response.isSuccessful()){
                    Log.d("REQ", "Request of categories is successfull");
                    getCategory(response.body());
                } else {
                    Log.e("REQ", "Request of categories failed");
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("REQ", "Could not connect to API");
            }
        });*/
    }

    /*public void getCategory(List<Category> response){
        String[] categories = new String[response.size()];
        for(int i =0; i<response.size(); i++){
            Log.e("RESP", response.get(i).getNomeEventoCategoria());
        }
    }*/
}
