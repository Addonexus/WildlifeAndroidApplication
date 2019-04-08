package com.example.animalapp;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        db = new DatabaseHelper(getActivity());
        mTextUsername = (EditText) view.findViewById(R.id.edittext_username);
        mTextPassword = (EditText) view.findViewById(R.id.edittext_password);
        mButtonLogin = (Button) view.findViewById(R.id.button_login);
        mTextViewRegister = (TextView) view.findViewById(R.id.textview_register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                //LOGIN FRAGMENT
                fragmentTransaction.add(R.id.fragment_container, new RegisterFragment());
                fragmentTransaction.commit();
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();

                boolean res = db.checkUser(user, pwd);
                if (res) {
                    Toast.makeText(getActivity(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container, new HomeFragment());
                    fragmentTransaction.commit();
                    //mButtonLogin.setVisibility(View.INVISIBLE);

                } else {
                    Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();
                    //mButtonLogin.setVisibility(View.VISIBLE);
                }
            }
        });



        return view;
    }

}