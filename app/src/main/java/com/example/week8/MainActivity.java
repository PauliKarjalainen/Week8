package com.example.week8;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView progressText;
    int progressValue;
    Spinner spinner;
    TextView tw;
    Context context = null;
    BottleDispenser automaatti = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        tw = (TextView) findViewById(R.id.textView);
        automaatti = BottleDispenser.getInstance();
        spinner = (Spinner) findViewById(R.id.spinner);
        seekBar();

        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(context, android.R.layout.simple_spinner_item, automaatti.listBottles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @SuppressLint("DefaultLocale")
    public void seekBar(){
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressText = (TextView) findViewById(R.id.textViewProgress);
        progressText.setText(String.format("Adding: %.2f€", ((float)seekBar.getProgress()/100)));
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        progressText.setText(String.format("Adding: %.2f€", ((float)progress/100)));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar){
                        progressText.setText(String.format("Adding: %.2f€", ((float)progressValue/100)));
                    }
                }
        );
    }

    public void addMoney(View v){
        tw.setText(automaatti.addMoney(progressValue));
        seekBar.setProgress(0);
    }

    public void returnMoney(View v){
        tw.setText(automaatti.returnMoney());
    }

    public void purchase(View v){
        float money = automaatti.getMoney();
        Bottle choice = (Bottle) spinner.getSelectedItem();
        tw.setText(automaatti.buyBottle(choice));
        if(money >= choice.getPrice()) {
            try {
                OutputStreamWriter streamOut = new OutputStreamWriter(context.openFileOutput("receipt.txt", Context.MODE_PRIVATE));
                String s = "Receipt \nProduct: " + choice.getName() + ", " + choice.getEnergy() + "\nPrice: " + choice.getPrice() + "€";
                streamOut.write(s);
                streamOut.close();

            } catch (IOException e) {
                Log.e("IoException", "Error: IOException");
            }
            System.out.println(this.getFilesDir());
        }
    }

}