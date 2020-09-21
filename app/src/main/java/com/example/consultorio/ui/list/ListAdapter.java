package com.example.consultorio.ui.list;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultorio.R;
import com.example.consultorio.data.model.Agenda;

import java.util.ArrayList;
import java.util.Calendar;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ListConsultaViewHolder>{
    private ArrayList<Agenda> consultaList;

    public ListAdapter(ArrayList<Agenda> consultaList) {
        this.consultaList = consultaList;
    }

    @NonNull
    @Override
    public ListConsultaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulta, parent, false);
        return new ListConsultaViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ListConsultaViewHolder holder, int position) {
        Agenda item = consultaList.get(position);
        Calendar cd = item.getTime();
        String txtDateTime = cd.get(Calendar.DAY_OF_MONTH) + "/"
                + cd.get(Calendar.MONTH) + "/"
                + cd.get(Calendar.YEAR) + " "
                + cd.get(Calendar.HOUR_OF_DAY) + ":"
                + cd.get(Calendar.MINUTE);



        holder.txtName.setText(txtMaxLength(item.getName(), 30));
        holder.txtTime.setText(txtDateTime);
        holder.txtDoctor.setText(txtMaxLength(item.getDoctor(), 16));
        holder.txtPhone.setText(item.getPhone());
        holder.txtEmail.setText(item.getEmail());
    }

    @Override
    public int getItemCount() {
        System.out.println(consultaList.size());
        return (consultaList != null && consultaList.size() > 0) ? consultaList.size() : 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String txtMaxLength(String txt, int max){
        if(txt.length() <= max){
            return txt;
        }

        String[] parts = txt.split(" ");

        for (int i = parts.length -1; i > 1; i--){
            String elem = parts[i];
            if(elem.length() <= 3){
                continue;
            }
            elem = elem.toUpperCase().substring(0,1) + ".";
            parts[i] = elem;

            if(String.join(" ", parts).length() <= max){
                txt = String.join(" ", parts);
                break;
            }
        }

        while (txt.length() > max){
            String[] parts2 = txt.split(" ");
            parts2[parts2.length -1] = "";
            if(parts2[parts2.length -2].length() <= 3){
                parts2[parts2.length -2] = "";
            }
            txt = String.join(" ", parts2);
        }
        return txt;
    }
    static class  ListConsultaViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        private TextView txtTime;
        private TextView txtDoctor;
        private TextView txtPhone;
        private TextView txtEmail;

        public  ListConsultaViewHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.text_name_consulta);
            txtTime = itemView.findViewById(R.id.text_time_consulta);
            txtDoctor = itemView.findViewById(R.id.text_doctor_consulta);
            txtPhone = itemView.findViewById(R.id.text_phone_consulta);
            txtEmail = itemView.findViewById(R.id.text_email_consulta);

        }
    }
}
