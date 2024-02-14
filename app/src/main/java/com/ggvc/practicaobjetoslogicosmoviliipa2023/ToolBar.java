package com.ggvc.practicaobjetoslogicosmoviliipa2023;

import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import android.os.Bundle;
import android.view.MenuInflater;

public class ToolBar extends AppCompatActivity {


    private Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        toolBar=findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;




    }

}