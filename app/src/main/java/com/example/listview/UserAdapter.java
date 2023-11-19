package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter {
//    Context context;
    Activity activity;
    int resource;
    public UserAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
//        this.context=context;
        this.activity=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater =activity.getLayoutInflater();
//        convertView= LayoutInflater.from(context).inflate(resource,parent,false);
       convertView=inflater.inflate(resource,parent,false);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.image);
        TextView nameFood = (TextView) convertView.findViewById(R.id.nameFood);
        TextView totalItemPrix = (TextView) convertView.findViewById(R.id.totalItemPrix);
//        TextView prixTotal = (TextView) convertView.findViewById(R.id.prixTotal);

        TextView prix1 = (TextView) convertView.findViewById(R.id.prix1);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView qua = (TextView) convertView.findViewById(R.id.qua);
        ImageButton add=(ImageButton)convertView.findViewById(R.id.add);
        ImageButton min=(ImageButton)convertView.findViewById(R.id.min);

        User currentUser =(User)getItem(position);
        nameFood.setText(currentUser.getName());
        quantity.setText(String.valueOf(currentUser.getQuantity()));
        qua.setText(String.valueOf(currentUser.getQuantity()));
//       TextView prixTotal=(TextView) convertView.findViewById(R.id.prixTotal);
//       prixTotal.setText("300DZD");


        totalItemPrix.setText(String.valueOf(currentUser.getQuantity()*currentUser.getPrix())+" DZD");
//        prixTotal.setText(String.valueOf(0)+" DZD");


        prix1.setText(String.valueOf(currentUser.getPrix()));


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String getQantity=quantity.getText().toString();



                int value=Integer.parseInt(getQantity);
                    value++;
                        quantity.setText(String.valueOf(value));
                        qua.setText(String.valueOf(value));
                totalItemPrix.setText(String.valueOf(value*currentUser.getPrix())+" DZD");



//                quantity.setText(String.valueOf(currentUser.getQuantity()+1));
            }
        });
        min.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String getQantity=quantity.getText().toString();
                int value=Integer.parseInt(getQantity);
                if(value>0){
                    value--;
                    quantity.setText(String.valueOf(value));
                    qua.setText(String.valueOf(value));
                    totalItemPrix.setText(String.valueOf(value*currentUser.getPrix())+" DZD");

                }



//                quantity.setText(String.valueOf(currentUser.getQuantity()+1));
            }
        });


        String uri ="@drawable/"+currentUser.getUrl();
        int imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName());
        Drawable res =activity.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
        return convertView;
    }
}
