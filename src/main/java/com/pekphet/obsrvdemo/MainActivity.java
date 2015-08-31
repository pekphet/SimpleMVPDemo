package com.pekphet.obsrvdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button btnChange;
    private Presenter presenter;
    private TextView tvShow;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter();
        initView();
        initClick();
        initEvent();
    }

    private void initView() {
        btnChange = (Button) findViewById(R.id.btn);
        tvShow = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.editText);
        presenter.bindShowText(tvShow);
    }

    private void initClick(){
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextDataDao.getInstance().changeTextData("CHANGED~~");
            }
        });
    }

    private void initEvent() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextDataDao.getInstance().changeTextData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        et.addTextChangedListener(watcher);
    }
}
