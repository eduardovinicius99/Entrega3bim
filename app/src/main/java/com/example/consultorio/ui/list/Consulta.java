package com.example.consultorio.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultorio.R;
import com.example.consultorio.data.model.Agenda;

import java.util.ArrayList;
import java.util.Calendar;

public class Consulta extends Activity {

    ArrayList<Agenda> data = new ArrayList<Agenda>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerConsulta =  findViewById(R.id.recycle_consulta);

        getData();
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerConsulta.setLayoutManager(linearLayout);
        recyclerConsulta.setAdapter(new ListAdapter(data));

        Button btnNew = (Button) findViewById(R.id.btn_new_consulta);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itt = new Intent(Consulta.this, Cadastro.class);
                startActivity(itt);
            }
        });
    }

    private void getData(){

        Bundle b = getIntent().getExtras();
        if(b == null){
            return;
        }

        String name = b.getString("name");
        String doctor = b.getString("doctor");
        String email = b.getString("email");
        String phone = b.getString("phone");
        String time = b.getString("time");

        Calendar cd = Calendar.getInstance();
        time = time.replace("/", " ");
        time = time.replace(":", " ");
        String[] partsTime = time.split(" ");

        cd.set(Integer.parseInt(partsTime[2]),
                Integer.parseInt(partsTime[1]) -1,
                Integer.parseInt(partsTime[0]),
                Integer.parseInt(partsTime[3]),
                Integer.parseInt(partsTime[4])
        );

        System.out.println("check");
        Agenda cst = new Agenda(name, cd, doctor, phone, email);

        data.add(cst);
    }
}