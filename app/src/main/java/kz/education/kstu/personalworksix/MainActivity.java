package kz.education.kstu.personalworksix;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kz.education.kstu.personalworksix.api.BooksApiCallback;
import kz.education.kstu.personalworksix.api.GetBooksAsyncTask;
import kz.education.kstu.personalworksix.model.Book;

public class MainActivity extends AppCompatActivity implements BooksApiCallback {
    private EditText mSearch;
    private ListView mList;
    private TextView mEmpty;
    private Button mSearchBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearch = findViewById(R.id.search);
        mList = findViewById(R.id.list);
        mEmpty = findViewById(R.id.empty);
        mSearchBtn = findViewById(R.id.searchBtn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearch.getText() == null || String.valueOf(mSearch.getText()).length() == 0){
                    Toast.makeText(MainActivity.this, R.string.empty_request, Toast.LENGTH_LONG).show();
                } else {
                    if (NetworkChecker.checkNetwork(MainActivity.this)) {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setTitle(R.string.please_wait);
                        progressDialog.show();
                        new GetBooksAsyncTask(MainActivity.this).execute(String.valueOf(mSearch.getText()));
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.no_internet, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onSuccess(List<Book> books) {
        if (books != null && books.size() > 0) {
            BooksAdapter arrayAdapter = new BooksAdapter(books, this);
            mList.setAdapter(arrayAdapter);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mList.setVisibility(View.VISIBLE);
                    mEmpty.setVisibility(View.GONE);
                    synchronized (mList){
                        mList.notify();
                    }
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mList.setVisibility(View.GONE);
                    mEmpty.setVisibility(View.VISIBLE);
                }
            });
        }
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onError() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}