package com.example.consultorio.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consultorio.R;
import com.example.consultorio.data.model.Agenda;
import com.example.consultorio.utils.Mask;

import java.util.Calendar;

public class Cadastro extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        EditText phone = findViewById(R.id.txt_cad_phone);
        EditText time =  findViewById(R.id.txt_cad_time);
        EditText doctor =  findViewById(R.id.txt_cad_doctor);
        EditText name =  findViewById(R.id.txt_cad_name);
        EditText email =  findViewById(R.id.txt_cad_email);
        Button btnCad = (Button) findViewById(R.id.btn_cad);

        phone.addTextChangedListener(Mask.setMask(phone, Mask.FORMAT_FONE));
        time.addTextChangedListener(Mask.setMask(time, Mask.FORMAT_DATETIME));

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Cadastro.this.finish();
            }
        });
    }

    private void saveData() {
        EditText phone = findViewById(R.id.txt_cad_phone);
        EditText time =  findViewById(R.id.txt_cad_time);
        EditText doctor =  findViewById(R.id.txt_cad_doctor);
        EditText name =  findViewById(R.id.txt_cad_name);
        EditText email =  findViewById(R.id.txt_cad_email);

        boolean valid = true;
        Calendar cd = Calendar.getInstance();
        if(name.getText().toString() == "" || name.getText().toString() == null){
            Toast toast = Toast.makeText(getApplicationContext(), "Nome obrigatorio", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
            return;
        }

        if(doctor.getText().toString() == "" || doctor.getText().toString() == null){
            Toast toast = Toast.makeText(getApplicationContext(), "Medico obrigatorio", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
            return;
        }

        if(phone.getText().toString() == "" || phone.getText().toString() == null){
            Toast toast = Toast.makeText(getApplicationContext(), "Telefone obrigatorio", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
            return;
        }

        if(time.getText().toString() == "" || time.getText().toString() == null){
            Toast toast = Toast.makeText(getApplicationContext(), "Data/Hora obrigatorio", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
            return;
        }

        if(email.getText().toString() == "" || email.getText().toString() == null){
            Toast toast = Toast.makeText(getApplicationContext(), "email obrigatorio", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
            return;
        }
        try{
            String txtTime = time.getText().toString();
            txtTime = txtTime.replace("/", " ");
            txtTime = txtTime.replace(":", " ");
            String[] partsTime = txtTime.split(" ");

            cd.set(Integer.parseInt(partsTime[2]),
                    Integer.parseInt(partsTime[1]) -1,
                    Integer.parseInt(partsTime[0]),
                    Integer.parseInt(partsTime[3]),
                    Integer.parseInt(partsTime[4])
            );
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Data/Hora invalida", Toast.LENGTH_SHORT);
            toast.show();
            valid = false;
            return;
        }
        if(valid) {
            Agenda ag = new Agenda(
                    name.getText().toString(),
                    cd,
                    doctor.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString()
            );

            Intent itt = new Intent(Cadastro.this, Consulta.class);
            Bundle b = new Bundle();
            b.putString("name", name.getText().toString());
            b.putString("doctor", doctor.getText().toString());
            b.putString("phone", phone.getText().toString());
            b.putString("email", email.getText().toString());
            b.putString("time", time.getText().toString());

            itt.putExtras(b);
            startActivity(itt);
            finish();
        }
    }
}