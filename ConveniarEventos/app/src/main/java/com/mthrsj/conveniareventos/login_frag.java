package com.mthrsj.conveniareventos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.mthrsj.conveniareventos.Utils.API.ConveniarAPI;
import com.mthrsj.conveniareventos.Utils.API.ConveniarEndpoints;
import com.mthrsj.conveniareventos.Utils.API.models.Auth;
import com.mthrsj.conveniareventos.Utils.Session;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_frag extends Fragment {

    Session session;
    EditText email;
    EditText password;
    TextView register;

    public static login_frag newInstance() {
        return new login_frag();
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

        register = getActivity().findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("REGISTER", "REGISTER CLICKED");
                ((MainActivity) getActivity()).updatePager(4);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
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
                    //Log.d("AUTH", response.body().)
                    session.logIn(email.getText().toString(), password.getText().toString(), response.body().getAccessToken());
                    ((MainActivity) getActivity()).updatePager(3);
                } else {
                    Log.e("AUTH", "Error" + response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {

            }
        });
        ConveniarAPI.closeClient();

    }


}
