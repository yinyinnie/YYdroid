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

public class A extends Activity {

    @BindView(R.id.a)
    Button btnToBActivity;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        mContext = this;
        ButterKnife.bind(this);

        btnToBActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A.this, B.class);
                mContext.startActivity(intent);
            }
        });
    }

}
