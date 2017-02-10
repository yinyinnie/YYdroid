package com.hujiang.yinyinnie.textxmlparse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nieyinyin on 09/02/2017.
 */

public class D extends Activity {

    @BindView(R.id.d)
    Button btnD;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);

        mContext = this;

        ButterKnife.bind(this);

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext = null;
                mContext.startActivity(null);
            }
        });
    }
}
