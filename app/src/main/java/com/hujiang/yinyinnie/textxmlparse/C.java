package com.hujiang.yinyinnie.textxmlparse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nieyinyin on 09/02/2017.
 */

public class C extends Activity {

    @BindView(R.id.c)
    Button btnToD;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        mContext = this;

        ButterKnife.bind(this);

        btnToD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(C.this, D.class);
                mContext.startActivity(intent);
            }
        });
    }
}
