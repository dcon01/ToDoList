package com.example.todolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.HashSet;
import java.util.Set;

public class AddItemActivity extends AppCompatActivity {
    private SharedPreferences dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        dataSource = getSharedPreferences("todo items", Context.MODE_PRIVATE);


    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            EditText userInput = findViewById(R.id.text);
            //Where is user input? Assuming I'm using find view by ID like above
            String item_description = userInput.getText().toString();
            System.out.println("added: " + item_description);

            Set<String> items = dataSource.getStringSet("items",null);
            assert items != null;

            Set<String> newItems = new HashSet<>(items);
            newItems.add(item_description);

            dataSource.edit().clear().putStringSet("items", newItems).apply();


            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}