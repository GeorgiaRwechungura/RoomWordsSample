package com.example.roomwordssample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.roomwordssample.REPLY";

    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditText=findViewById(R.id.edit_word);
        final Button button=findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replayIntent=new Intent();
                if(TextUtils.isEmpty(mEditText.getText())){
                    setResult(RESULT_CANCELED,replayIntent);
                }else {
                    String word=mEditText.getText().toString();
                    replayIntent.putExtra(EXTRA_REPLY,word);
                    setResult(RESULT_OK,replayIntent);

                }
                finish();
            }
        });
    }
}
