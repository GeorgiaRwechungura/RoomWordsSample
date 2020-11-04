package com.example.roomwordssample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private WordViewModel mWordViewModel;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.floatingActionButton);

  floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
          startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

      }
  });
        mWordViewModel= ViewModelProviders.of(this).get(WordViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      if(requestCode== NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode== RESULT_OK ){
          Word word=new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
          mWordViewModel.insert(word);

      }else {
          Toast.makeText(getApplicationContext(),R.string.empty_not_saved,Toast.LENGTH_LONG).show();
      }
    }
}