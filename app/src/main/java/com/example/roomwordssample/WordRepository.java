package com.example.roomwordssample;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        WordRoomDatabase db=WordRoomDatabase.getDatabase(application);
        mWordDao=db.wordDao();
        mAllWords=mWordDao.getAllAllWord();

    }

    public LiveData<List<Word>> getmAllWords() {

        return mAllWords;
    }

    public void  insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask  extends AsyncTask<Word,Void,Void> {
        private WordDao mAsyTanskDao;

        insertAsyncTask(WordDao dao){
            mAsyTanskDao=dao;

        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyTanskDao.insert(params[0]);
            return null;
        }
    }
}
