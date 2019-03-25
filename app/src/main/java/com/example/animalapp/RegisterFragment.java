package com.example.animalapp;


import android.content.Intent;
import android.os.Bundle;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment  {


    public RegisterFragment() {
        // Required empty public constructor
    }

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        db = new DatabaseHelper(getActivity());
        mTextUsername = (EditText) view.findViewById(R.id.edittext_username);
        mTextPassword = (EditText) view.findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText) view.findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button) view.findViewById(R.id.button_register);
        mTextViewLogin = (TextView) view.findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity) getActivity()).setFragment(new LoginFragment());

                //Intent LoginIntent = new Intent(RegisterFragment.this, LoginFragment.class);
                //startActivity(LoginIntent);
            }
        });

        /*mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd)) {
                    long val = db.addUser(user, pwd);
                    if (val > 0) {
                        Toast.makeText(getActivity(), "You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterFragment.this, LoginFragment.class);
                        startActivity(moveToLogin);
                    } else {
                        if (val > 0) {
                            Toast.makeText(getActivity(), "Registration Error", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Password is not matching", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }
        });*/

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RegisterFragment());
                fr.commit();
            }
        });


        return view;
    }
}