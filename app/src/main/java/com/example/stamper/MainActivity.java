package com.example.stamper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private StamperView stamperView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stamperView = (StamperView) findViewById(R.id.stamperView);

        Spinner modeSpinner = (Spinner) findViewById(R.id.modeSpinner);
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        modeAdapter.add("点を描く");
        modeAdapter.add("点を連続");
        modeSpinner.setAdapter(modeAdapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stamperView.setMode((String) parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        colorAdapter.add("黒");
        colorAdapter.add("白");
        colorAdapter.add("赤");
        colorAdapter.add("緑");
        colorAdapter.add("青");
        colorAdapter.add("黄色");
        colorAdapter.add("水色");
        colorAdapter.add("紫");
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeColor((String) parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stamperView.clear();
            }
        });
    }

    private void changeColor(String color) {
        switch (color) {
            case "黒":
                stamperView.setColor(Color.BLACK);
                break;
            case "白":
                stamperView.setColor(Color.WHITE);
                break;
            case "赤":
                stamperView.setColor(Color.RED);
                break;
            case "緑":
                stamperView.setColor(Color.GREEN);
                break;
            case "青":
                stamperView.setColor(Color.BLUE);
                break;
            case "黄色":
                stamperView.setColor(Color.YELLOW);
                break;
            case "水色":
                stamperView.setColor(Color.CYAN);
                break;
            case "紫":
                stamperView.setColor(Color.MAGENTA);
                break;
        }
    }
}
