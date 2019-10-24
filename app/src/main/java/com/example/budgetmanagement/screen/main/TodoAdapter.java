package com.example.budgetmanagement.screen.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetmanagement.R;
import com.example.budgetmanagement.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    List<Todo> todos;
    OnItemClickListener listener;

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_budget, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.textName.setText(todos.get(position).getNameBudget());
        holder.textPrice.setText(todos.get(position).getPriceBudget());
        holder.textDescription.setText(todos.get(position).getDescriptionBudget());
    }

    @Override
    public int getItemCount() {
        if (todos == null) {
            return 0;
        }
        return todos.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;
        TextView textDescription;
        Button btnUpdate;
        Button btnDelete;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById(R.id.text_price);
            textDescription = itemView.findViewById(R.id.text_description);
            btnUpdate = itemView.findViewById(R.id.button_update);
            btnDelete = itemView.findViewById(R.id.button_delete);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUpdateClick(getAdapterPosition());
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });
        }
    }

    interface OnItemClickListener {
        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }
}
