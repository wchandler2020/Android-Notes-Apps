package com.example.notesapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Models.Notes;
import com.example.notesapp.NotesClickListener;
import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{
    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_title.setSelected(true);

        holder.tv_notes.setText(list.get(position).getNotes());

        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.iv_pinned.setImageResource(R.drawable.pinned);
        }else{
            holder.iv_pinned.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));
        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });
        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }

    private int getRandomColor(){
        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.color_1);
        colorList.add(R.color.color_2);
        colorList.add(R.color.color_3);
        colorList.add(R.color.color_4);
        colorList.add(R.color.color_5);
        colorList.add(R.color.color_6);

        Random random = new Random();
        int random_color = random.nextInt(colorList.size());
        return colorList.get(random_color);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView tv_title, tv_notes, tv_date;
    ImageView iv_pinned;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container = itemView.findViewById(R.id.notes_container);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_notes = itemView.findViewById(R.id.tv_notes);
        tv_date = itemView.findViewById(R.id.tv_date);
        iv_pinned = itemView.findViewById(R.id.iv_pinned);
    }
}
