package com.lh.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDone;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDone = findViewById(R.id.btndone);
        txtName = findViewById(R.id.txtname);
        txtSurname = findViewById(R.id.txtsurname);
        txtEmail = findViewById(R.id.txtemail);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btndone){
            String name = txtName.getText().toString();
            String surname = txtSurname.getText().toString();
            String email = txtEmail.getText().toString();

            if (name.isEmpty()){
                CustomToastView.makeErrorToast(this, "Error al validar el nombre", R.layout.custom_toast).show();
                return;
            }
            if (surname.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al validar el apellido", R.layout.custom_toast).show();
                return;
            }
            if (!isValidEmail(email)){
                CustomToastView.makeSuccessToast(this, "Error al validar el email", R.layout.custom_toast).show();
                return;
            }
            Intent Myintent = new Intent(this, CalculadoraImc.class);
            Myintent.putExtra("name", name);
            Myintent.putExtra("surname", surname);
            Myintent.putExtra("email", email);
            startActivity(Myintent);

        }
    }

    private Boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}