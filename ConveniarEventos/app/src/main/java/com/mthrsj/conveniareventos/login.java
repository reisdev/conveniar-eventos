package com.mthrsj.conveniareventos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.Auth;
import com.mthrsj.conveniareventos.Utils.Session;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends Fragment {

    Session session;
    EditText email;
    EditText password;

    public static login newInstance() {
        return new login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        MaterialButton authButton = getActivity().findViewById(R.id.authenticate);
        session = new Session(this.getContext());
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate(v);
            }
        });
    }

    public void authenticate(View v) {
        email = getActivity().findViewById(R.id.email_field);
        password = getActivity().findViewById(R.id.pswd_field);

        String credentials = Credentials.basic(email.getText().toString(), password.getText().toString());
        ConveniarEndpoints apiService = ConveniarAPI.getClient("https://apiauth.conveniar.com.br/conveniar/api/").create(ConveniarEndpoints.class);

        Call<Auth> auth = apiService.authenticate(credentials);

        auth.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
                    Log.d("AUTH", response.body().toString());
                    session.logIn(email.getText().toString(), password.getText().toString(), response.body().getAccessToken());

                    perfil_frag search = new perfil_frag();
                    FragmentManager fm = getFragmentManager();

                    fm.beginTransaction().replace(R.id.frame_layout, perfil_frag.newInstance()).commit();
                } else {
                    Log.e("AUTH","Error"+ response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

            }
        });
        ConveniarAPI.closeClient();

    }


}
