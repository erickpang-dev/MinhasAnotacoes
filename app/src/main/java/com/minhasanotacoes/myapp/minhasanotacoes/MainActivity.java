package com.minhasanotacoes.myapp.minhasanotacoes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private EditText insertedText;
    private ImageView saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertedText = findViewById(R.id.insertedTextId);
        saveButton = findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typedText = insertedText.getText().toString();
                saveText(typedText);

            }
        });
        if (loadText() != null){
            insertedText.setText( loadText() );
        }


    }
    private void saveText(String textToSave){
        try{
            OutputStreamWriter outputSaveText = new OutputStreamWriter(openFileOutput("minhasanotacoes.txt", Context.MODE_PRIVATE));
            outputSaveText.write(textToSave);
            outputSaveText.close();
        }catch (IOException e){
            Log.v("MainActivity",e.toString());
        }

    }
    private String loadText(){
        String textToLoad = "";
        try{
            InputStream savedFile = openFileInput("minhasanotacoes.txt");
            if (savedFile != null){
                InputStreamReader inputLoadText = new InputStreamReader(savedFile);
                BufferedReader bufferLoad = new BufferedReader(inputLoadText);
                bufferLoad.readLine();
                String fileLine;
                while((fileLine = bufferLoad.readLine()) != null){
                    textToLoad += fileLine + "\n";

                }
                savedFile.close();
            }

        }catch(IOException e){
            Log.v("MainActivity",e.toString());
        }
        return textToLoad;

    }
}
