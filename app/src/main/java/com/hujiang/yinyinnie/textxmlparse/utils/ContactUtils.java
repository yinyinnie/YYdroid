package com.hujiang.yinyinnie.textxmlparse.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by nieyinyin on 09/01/2017.
 */

public class ContactUtils {


    public static void deleteContact(ContentResolver contentResolver, String contactName){
        Log.d("ContactUtils", "delete contact begin");
        String name = contactName;
        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver =  contentResolver;
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Contacts.Data._ID},"display_name=?", new String[]{name}, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            //根据id删除data中的相应数据
            resolver.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id+""});

            Log.d("ContactUtils", "delete contact:" + uri);
        }
    }

}
