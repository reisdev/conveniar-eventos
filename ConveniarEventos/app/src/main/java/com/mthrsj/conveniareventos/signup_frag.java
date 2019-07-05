package com.mthrsj.conveniareventos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.UserRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup_frag extends Fragment {

    EditText edtNome, edtEmail, edtSenha1, edtSenha2;

    public static signup_frag newInstance() {
        signup_frag fragment = new signup_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_singup_frag, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        Button btn = getActivity().findViewById(R.id.register_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNome = getActivity().findViewById(R.id.nome_field);
                edtEmail = getActivity().findViewById(R.id.email_field);
                edtSenha1 = getActivity().findViewById(R.id.pswd_field);
                edtSenha2 = getActivity().findViewById(R.id.pswd_repeat_field);

                if(edtSenha1.getText().toString().equals(edtSenha2.getText().toString())){
                    UserRegister user = new UserRegister(edtNome.getText().toString(), edtSenha1.getText().toString(), edtEmail.getText().toString());

                    ConveniarEndpoints apiService = ConveniarAPI.getClient().create(ConveniarEndpoints.class);
                    Call<UserRegister> usuario = apiService.registerUser(user);

                    usuario.enqueue(new Callback<UserRegister>() {
                        @Override
                        public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getContext(),"Registrado! Código: " + response.body().getNumRegistro(), Toast.LENGTH_LONG).show();
                                ((MainActivity) getActivity()).setView(3);
                            }
                        }
                        @Override
                        public void onFailure(Call<UserRegister> call, Throwable t) {

                        }
                    });

                } else {

                }
            }
        });
    }
}
