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

public class B extends Activity {

    @BindView(R.id.b)
    Button btnToB;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mContext = this;

        ButterKnife.bind(this);

        btnToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(B.this, C.class);
                mContext.startActivity(intent);
            }
        });
    }
}
