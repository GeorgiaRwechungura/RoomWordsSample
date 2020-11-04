package com.example.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mwords;

    WordListAdapter(Context context) {

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.recyclerview_item,parent,false);

        return new  WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mwords!=null){
            Word current=mwords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }

    }
    void setWords(List<Word> words){
        mwords= words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mwords!=null){
            return  mwords.size();
        }else{
            return 0;
        }

    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;
        public WordViewHolder( View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);




        }
    }
}
