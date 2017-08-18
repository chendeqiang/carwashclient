package com.example.carwashclient.ui.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carwashclient.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0,R.anim.slide_bottom_out);
    }
}
