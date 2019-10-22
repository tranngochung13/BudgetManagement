package com.example.budgetmanagement.screen.update;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.Update;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.budgetmanagement.R;
import com.example.budgetmanagement.database.AppDatabase;
import com.example.budgetmanagement.model.Todo;
import com.example.budgetmanagement.screen.add.AddBudgetActivity;
import com.example.budgetmanagement.database.AppDatabase;

public class UpdateActivity extends AppCompatActivity {
    AppDatabase db;
    EditText edtName;
    EditText edtPrice;
    EditText edtDescription;
    Button btnUpdate;
    int todoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        edtName = findViewById(R.id.edit_name);
        edtPrice = findViewById(R.id.edit_price);
        edtDescription = findViewById(R.id.edit_description);
        btnUpdate = findViewById(R.id.button_update);

        int id = getIntent().getIntExtra("id", 0);
        todoId = id;
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String description = getIntent().getStringExtra("description");

        edtName.setText(name);
        edtPrice.setText(price);
        edtPrice.setText(description);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTodoToDatabase();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void updateTodoToDatabase() {
        final String name = edtName.getText().toString();
        final int price = Integer.parseInt(edtPrice.getText().toString());
        final String description = edtDescription.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Todo newTodo = new Todo(name, price, description);
                newTodo.setId(todoId); // thinking about why we need to set id here
                db.todoDao().update(newTodo);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showSuccessDialog();
            }
        }.execute();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Update Success")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
