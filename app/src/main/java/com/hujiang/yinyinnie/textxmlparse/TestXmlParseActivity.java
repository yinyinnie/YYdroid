package com.hujiang.yinyinnie.textxmlparse;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by nieyinyin on 09/01/2017.
 */

public class TestXmlParseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            InputStream inputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory().getPath() + "/index.xml"));
            long time1 = SystemClock.elapsedRealtime();
            XMLTestUtils.PullParseXML(inputStream);
//            Map<String, String> xmlMap = XMLTestUtils.DOMParseXML(inputStream);
            long time2 = SystemClock.elapsedRealtime();
            Log.e("time", String.valueOf(time2 - time1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
