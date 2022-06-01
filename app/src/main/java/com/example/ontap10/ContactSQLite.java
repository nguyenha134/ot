package com.example.ontap10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactSQLite extends SQLiteOpenHelper {
    public static final String TableName = "ContactTable";
    public static final String Id = "Id";
    public static final String Name = "Name";
    public static final String Phone = "PhoneNumber";

    public ContactSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQLCreate = "Create table if not exists " + TableName + "("
                + Id + " Integer primary key,"
                + Name + " Text,"
                + Phone + " Text)";
        sqLiteDatabase.execSQL(SQLCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TableName);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, contact.getId());
        value.put(Name, contact.getName());
        value.put(Phone, contact.getPhone());
        db.insert(TableName, null, value);
        db.close();
    }

    public void updateContact(int id, Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, contact.getId());
        value.put(Name, contact.getName());
        value.put(Phone, contact.getPhone());
        db.update(TableName, value, Id + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " where Id=" + id;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null)
            while (cursor.moveToNext()) {
                Contact contact = new Contact(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                list.add(contact);
            }
        return list;
    }
}
