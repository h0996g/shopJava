package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends ArrayAdapter {
    Button prixTotal;
    TextView quantityTotal;
//    Context context;
    Activity activity;
    int totalPrixCounter=0;
    int quantityCounter=0;
    int resource;
    public UserAdapter(@NonNull Activity context, int resource, @NonNull List objects,Button prixTotal,TextView quantityTotal) {

        super(context, resource, objects);
        this.prixTotal=prixTotal;
        this.quantityTotal=quantityTotal;
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
        ImageView imageView2=(ImageView)convertView.findViewById(R.id.image2);


        TextView nameFood = (TextView) convertView.findViewById(R.id.nameFood);
        TextView nameFood2 = (TextView) convertView.findViewById(R.id.nameFood2);

        TextView totalItemPrix = (TextView) convertView.findViewById(R.id.totalItemPrix);
//        TextView prixTotal = (TextView) convertView.findViewById(R.id.prixTotal);

        TextView prix1 = (TextView) convertView.findViewById(R.id.prix1);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView qua = (TextView) convertView.findViewById(R.id.qua);
        ImageButton add=(ImageButton) convertView.findViewById(R.id.add);
        ImageButton min=(ImageButton) convertView.findViewById(R.id.min);
        ImageButton iconShop=(ImageButton)convertView.findViewById(R.id.iconShop);

        User currentUser =(User)getItem(position);
        nameFood.setText(currentUser.getName());
        nameFood2.setText(currentUser.getName());

        quantity.setText(String.valueOf(currentUser.getQuantity()));
        qua.setText(String.valueOf(currentUser.getQuantity()));
        RelativeLayout itemClicked = convertView.findViewById(R.id.itemClicked);
//       TextView prixTotal=(TextView) convertView.findViewById(R.id.prixTotal);
//       prixTotal.setText("300DZD");


        totalItemPrix.setText(String.valueOf(currentUser.getQuantity()*currentUser.getPrix())+" DZD");
//        prixTotal.setText(String.valueOf(0)+" DZD");


        prix1.setText(String.valueOf(currentUser.getPrix()));
        LinearLayout discLayout = convertView.findViewById(R.id.discLayout);
        LinearLayout motherLayout = convertView.findViewById(R.id.motherLayout);


        itemClicked.setOnClickListener(v -> {

            if (discLayout.getVisibility() == View.GONE){
                TransitionManager.beginDelayedTransition(motherLayout, new AutoTransition());
                discLayout.setVisibility(View.VISIBLE);


            } else {
                TransitionManager.beginDelayedTransition(motherLayout, new AutoTransition());
                discLayout.setVisibility(View.GONE);


            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getQantity = quantity.getText().toString();
                int value = Integer.parseInt(getQantity);
                if (value == 0) {
                    String quantityTotall = quantityTotal.getText().toString();
                    int value2 = Integer.parseInt(quantityTotall);
                    value2++;
                    quantityTotal.setText(String.valueOf(value2));

                }
                value++;

                quantity.setText(String.valueOf(value));

                qua.setText(String.valueOf(value));
                totalItemPrix.setText(String.valueOf(value * currentUser.getPrix()) + " DZD");
                totalPrixCounter = totalPrixCounter + currentUser.getPrix();
                prixTotal.setText(String.valueOf(totalPrixCounter) + " DZD");
                quantityCounter++;

                iconShop.setImageDrawable(activity.getResources().getDrawable(R.drawable.baseline_remove_shopping_cart_24));
            }
        });
        iconShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getQantity=quantity.getText().toString();
                if(Integer.parseInt( getQantity)>0){
//
                    iconShop.setImageDrawable(activity.getResources().getDrawable(R.drawable.baseline_add_shopping_cart_24));
                    quantity.setText("0");
                    String quantityTotall=quantityTotal.getText().toString();
                    int value2=Integer.parseInt(quantityTotall);
                    value2--;
                    quantityTotal.setText(String.valueOf(value2));
//                    String totalItemPrixValue = totalItemPrix.getText().toString();

//                    int totalItemPrixValue1=Integer.parseInt(totalItemPrixValue);
                    totalPrixCounter = totalPrixCounter -( currentUser.getPrix() *Integer.parseInt( getQantity));
                    prixTotal.setText(String.valueOf(totalPrixCounter)+" DZD");
                    qua.setText(String.valueOf(0));
                    totalItemPrix.setText(String.valueOf(0)+" DZD");


                }else {

                    iconShop.setImageDrawable(activity.getResources().getDrawable(R.drawable.baseline_remove_shopping_cart_24));
                    quantity.setText(String.valueOf(1));
                    String quantityTotall = quantityTotal.getText().toString();
                    int value2 = Integer.parseInt(quantityTotall);
                    value2++;
                    quantityTotal.setText(String.valueOf(value2));
                    totalItemPrix.setText(String.valueOf(currentUser.getPrix())+" DZD");
                    totalPrixCounter = totalPrixCounter + currentUser.getPrix();
                    prixTotal.setText(String.valueOf(totalPrixCounter) + " DZD");
                    qua.setText(String.valueOf(1));


                }

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
                if(value==0){
                    String quantityTotall=quantityTotal.getText().toString();
                    int value2=Integer.parseInt(quantityTotall);
                    value2--;
                    quantityTotal.setText(String.valueOf(value2));

                }
                    qua.setText(String.valueOf(value));
                    totalItemPrix.setText(String.valueOf(value*currentUser.getPrix())+" DZD");
                    totalPrixCounter=totalPrixCounter-currentUser.getPrix();
                    prixTotal.setText(String.valueOf(totalPrixCounter)+" DZD");
                    quantityCounter--;
                    if(value==0){
                        iconShop.setImageDrawable(activity.getResources().getDrawable(R.drawable.baseline_add_shopping_cart_24));

                    }
//                    quantityTotal.setText(String.valueOf(quantityCounter));

}




//                quantity.setText(String.valueOf(currentUser.getQuantity()+1));
            }
        });


        String uri ="@drawable/"+currentUser.getUrl();
        int imageResource = activity.getResources().getIdentifier(uri, null, activity.getPackageName());
        Drawable res =activity.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
        imageView2.setImageDrawable(res);

        return convertView;
    }

}
