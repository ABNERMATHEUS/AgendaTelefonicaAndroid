package com.abnerdev.agenda.ArrayAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.R;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.ContactServices;
import com.abnerdev.agenda.Services.UserServices;

import java.io.File;

public class ContactArrayAdapter extends RecyclerView.Adapter<ContactArrayAdapter.ViewHolder> {

    private static ClickListener clickListener;
    private  Context context;
    public void setClickListener(ClickListener clickListener) {
        ContactArrayAdapter.clickListener = clickListener;
    }

    public ContactArrayAdapter(Context context) {
        this.context = context;
    }

    public interface ClickListener {

        void onItemClick(int position, View view);
        boolean onItemLongClick(int position,View view);
    }


    @NonNull
    @Override
    public ContactArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { //QUANDO ELE CRIAR
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.recycleview_item,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactArrayAdapter.ViewHolder viewHolder, int i) { //QUANDO ELE APARECER PARA USUÁRIO, IRÁ CHAMAR ESSA FUNÇÃO
        //Quando mostrar o item para gente
        Contact contact = UserServices.getInstance(context).FindById().getPhoneBook().getContato().get(i);
        viewHolder.nameContact.setText(contact.getName());
        viewHolder.numberPhone.setText(contact.getPhone());
        if(contact.getImage()!= null){
            File file = new File(contact.getImage());
            viewHolder.imageView.setImageURI(Uri.fromFile(file));

            //Aplicando escala cinza
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            viewHolder.imageView.setColorFilter(filter);
        }

    }

    @Override
    public int getItemCount() { //NÚMERO DE ITEM QUE TEMOS EM TELA
        return ContactServices.getInstance(this.context).CountContact();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{ //CONTEUDO QUE IRÁ TER EM CADA ITEM
        TextView nameContact;
        TextView numberPhone;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameContact = itemView.findViewById(R.id.nameContact);
            numberPhone = itemView.findViewById(R.id.phoneContact);
            imageView = itemView.findViewById(R.id.imageContatoList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener==null)
                        return;
                    else
                        clickListener.onItemClick(getAdapterPosition(),view);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(clickListener == null)
                        return  false;
                    return clickListener.onItemLongClick(getAdapterPosition(),v);
                }
            });

        }

    }




}
