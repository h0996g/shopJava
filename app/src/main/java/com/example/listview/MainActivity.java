package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ListView list_users;
    FloatingActionButton fab ;
    AlertDialog.Builder builder;
    Button prixTotal;
    TextView quantityTotal;
    ImageButton imageButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textDate);
        prixTotal=(Button) findViewById(R.id.prixTotal);
        quantityTotal=(TextView)findViewById(R.id.quantityTotal);
//


//        date
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        textView.setText(formattedDate);
//prixTotal.setText("30 DZD");
//        -------------------------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Commande de : ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.grey));
        list_users = (ListView) findViewById(R.id.list_item);
        ArrayList<User> values = new ArrayList<>();
        values.add(new User("food 1",  34,"f1",0));
        values.add(new User("food 2",  12,"f2",0));
        values.add(new User("food 3",  5,"f3",0));
        values.add(new User("food 4",  6,"f4",0));

        values.add(new User("food 5",  1,"f1",0));
        values.add(new User("food 6", 4,"f3",0));
        values.add(new User("food 7",  3,"f1",0));
        values.add(new User("food 8",  8,"f3",0));
        values.add(new User("food 9",  10,"f2",0));



//        TextView prixTotal = (TextView) findViewById(R.id.prixTotal);
//        int allQ=0;
//        for (User user : values) {
//            allQ = user.getQuantity()+allQ;
//        }

//        prixTotal.setText(String.valueOf(allQ));
        UserAdapter adapter = new UserAdapter(this, R.layout.item_user, values,prixTotal,quantityTotal);

        list_users.setAdapter(adapter);


        fab=(FloatingActionButton)findViewById(R.id.fab);
        builder=new AlertDialog.Builder(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(quantityTotal.getText().toString())>0){
                    builder.setTitle("Alert!").setMessage("do want to continue").setCancelable(true).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                            Bundle bundle = new Bundle();
//        bundle.putSerializable("obj",user);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }

                    ).show();
                }

            }
        });

        list_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectItem =adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this,selectItem,Toast.LENGTH_SHORT).show();
            }
        });





        imageButton=(ImageButton)findViewById(R.id.dateButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        textView.setText(currentDateString);
    }



}