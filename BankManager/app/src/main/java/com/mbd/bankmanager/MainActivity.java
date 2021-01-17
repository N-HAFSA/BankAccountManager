package com.mbd.bankmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int minteger = 0;
    String transactionType;
    ArrayList<ListItem> transaction = new ArrayList<ListItem>();
    int fullSolde =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // transactionList = getData();
      //  Log.d("myTag", "This is my message0+0"+transactionList.toArray());
        final ListView lv = (ListView) findViewById(R.id.user_list);

        EditText amount = (EditText) findViewById(R.id.amount);
        TextView soldTotal = (TextView) findViewById(R.id.soldTotal);
        System.out.println(amount);
        Button submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTextFromET = amount.getText().toString();
                try
                {
                    minteger = new Integer(sTextFromET).intValue();
                }
                catch (NumberFormatException e)
                {
                    // handle the exception
                    System.out.println(e);
                }
                if(minteger != 0){
                    transaction.add(0,getListData(minteger,date(),TransactionType(minteger)));
                    TransactionsListAdapter adapter= new TransactionsListAdapter(MainActivity.this,transaction);
                    lv.setAdapter(adapter);
                    fullSolde += minteger;
                    soldTotal.setText("Your Solde is :"+String.valueOf(fullSolde)+" MAD");
                    amount.setText(String.valueOf(0));

                    adapter.notifyDataSetChanged();
                }else{

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    layout.setBackgroundColor(Color.RED);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Please specify the amount !");
                    text.setTextColor(Color.WHITE);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER|Gravity.BOTTOM, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                }

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem action = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Selected :" + " " + action.getAmount()+", "+ action.getDate(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public ListItem getListData(int amount, String date, String transactionType) {
        //ArrayList<ListItem> results = new ArrayList<>();

        ListItem transaction = new ListItem();
        transaction.setAmount(String.valueOf(amount));
        transaction.setDate(date);
        transaction.setTransaction(transactionType);
       // results.add(transaction);

        return transaction;
    }

    public String date(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        return  formatter.format(date);
    }
    public String TransactionType(int amount){
        if(amount>0){
            transactionType = " Crédit ";
        }else {
            transactionType = " Débit ";
        }
        return transactionType;
    }
    public void increase(View view) {
        minteger = minteger +50;
        display(minteger);

    }public void decrease(View view) {

        minteger = minteger - 50;
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.amount );
        displayInteger.setText("" + number);
    }

}