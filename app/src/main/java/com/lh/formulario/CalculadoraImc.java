package com.lh.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

public class CalculadoraImc extends AppCompatActivity implements View.OnClickListener {
    private TextView tvInformation;
    private EditText txtHeight;
    private EditText txtWeight;
    private Button btnCalculator;
    private ImageView imState;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_imc);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String email = intent.getStringExtra("email");
        String menssge = "Hola "+name+" "+surname+" Es un gusto tenerte aca, su correo para el informe es: "+email;
        tvInformation = findViewById(R.id.tvInformation);
        tvResult = findViewById(R.id.tvResult);
        txtWeight = findViewById(R.id.txtWeight);
        txtHeight = findViewById(R.id.txtHeight);
        btnCalculator = findViewById(R.id.btnCalculator);
        imState = findViewById(R.id.imState);
        tvInformation.setText(menssge);
        btnCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCalculator){
            int weight = Integer.parseInt(txtWeight.getText().toString());
            int height = Integer.parseInt(txtHeight.getText().toString());

            if (weight == 0  || weight<0){
                CustomToastView.makeErrorToast(this, "Error al validar el peso", R.layout.custom_toast).show();
                imState.setImageResource(R.drawable.imc_malo);
                return;
            }
            if (height == 0 || height<0  ){imState.setImageResource(R.drawable.imc_malo);
                CustomToastView.makeErrorToast(this, "Error al validar la altura", R.layout.custom_toast).show();

                return;
            }
            int imc = CalcularImc( weight,  height);
            String msgCalImc = CalificacionImc(imc);
            tvResult.setText("Su imc es: "+imc+" y su calificacion es: "+msgCalImc);

        }

    }

    private int CalcularImc (int weight, int height){
       int imc;
       int heightPow = (int) Math.pow(height, 2);
       imc = weight /heightPow;

       return imc;
    }

    private String CalificacionImc(double imc){
        if(imc<18.5){
            imState.setImageResource(R.drawable.insuficiente);
            return "Peso insuficiente";
        }
        if(imc>18.5 && imc<25){
            imState.setImageResource(R.drawable.normopeso);
            return "Normopeso";
        }
        if(imc>=25 && imc<27){
            imState.setImageResource(R.drawable.grado_uno);
            return "Sobrepeso grado I";
        }
        if(imc>=27 && imc<30){
            imState.setImageResource(R.drawable.grado_dos);
            return "Sobrepeso grado II (preobesidad)";
        }
        if(imc>=30 && imc<35){
            imState.setImageResource(R.drawable.tipo_uno);
            return "Obesidad de tipo I";
        }
        if (imc>=35 && imc<40){
            imState.setImageResource(R.drawable.tipo_dos);
            return "Obesidad de tipo II";
        }
        if(imc>=40 && imc<50){
            imState.setImageResource(R.drawable.tipo_tres);
            return "tObesidad de tipo III (mórbida)";
        }
        if(imc>=50 ){
            imState.setImageResource(R.drawable.extrema);
            return "Obesidad de tipo IV (extrema)";
        }
        imState.setImageResource(R.drawable.imc_malo);
        return "error al calcular imc, por favor digite de nuevo los datos";
    }
}