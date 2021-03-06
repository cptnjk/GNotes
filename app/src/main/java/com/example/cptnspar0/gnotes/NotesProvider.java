package com.example.cptnspar0.gnotes;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by cptnspar0 on 31/5/17.
 */

public class NotesProvider extends ContentProvider{

    public static final String AUTHORITY = "com.example.cptnspar0.gnotes.notesprovider";
    public static final String BASE_PATH = "notes";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    public static final String CONTENT_ITEM_TYPE ="Note";

    static{
        uriMatcher.addURI(AUTHORITY,BASE_PATH,NOTES);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#",NOTES_ID);

    }

     private SQLiteDatabase database;

    @Override
    public boolean onCreate(){DBOpenHelper helper = new DBOpenHelper(getContext());
         database = helper.getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1){
        /*if(uriMatcher(uri)==NOTES_ID){
            s = DBOpenHelper.NOTE_ID + "=" +uri.getLastPathSegment();
        }*/
        return database.query(DBOpenHelper.TABLE_NOTES, DBOpenHelper.ALL_COLUMNS,s,null,null,null,DBOpenHelper.NOTE_CREATED + " DSEC");
    }

    @Nullable
    @Override
    public String getType(Uri uri){
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues){
        long id = database.insert(DBOpenHelper.TABLE_NOTES,null,contentValues);
        return Uri.parse(BASE_PATH +"/ +id");
    }

    @Override
    public int delete(Uri uri, String s, String[] strings){
        return database.delete(DBOpenHelper.TABLE_NOTES,s,strings);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings){
        return database.update(DBOpenHelper.TABLE_NOTES, contentValues, s, strings);
    }

}
