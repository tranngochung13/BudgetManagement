package com.example.budgetmanagement.screen.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.budgetmanagement.R;
import com.example.budgetmanagement.database.AppDatabase;
import com.example.budgetmanagement.model.Todo;

public class AddBudgetActivity extends AppCompatActivity {
    AppDatabase db;
    EditText edtName, editPrice, editDescription;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        edtName = findViewById(R.id.edit_name);
        editPrice = findViewById(R.id.edit_price);
        editDescription = findViewById(R.id.edit_description);
        btnAdd = findViewById(R.id.button_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodoToDatabase();
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void addTodoToDatabase() {
        final String name = edtName.getText().toString();
        final String price = editPrice.getText().toString();
        final String description = editDescription.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Title must not null", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Todo newTodo;
                newTodo = new Todo(name, price, description);
                db.todoDao().insert(newTodo);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(AddBudgetActivity.this, "New todo added", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}