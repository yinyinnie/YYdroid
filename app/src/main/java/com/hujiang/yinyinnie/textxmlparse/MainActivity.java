package com.hujiang.yinyinnie.textxmlparse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.btn_delete_contact)
    Button btnDeleteContact;

    @BindView(R.id.toA)
    Button btnToA;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ButterKnife.bind(this);

        btnToA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, A.class);
                mContext.startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn_delete_contact)
    void onDeleteContackClick() {
        Intent intent = new Intent(this, TextContactActivity.class);
        this.startActivity(intent);

    }

}
