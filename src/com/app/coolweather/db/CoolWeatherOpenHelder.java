package com.app.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelder extends SQLiteOpenHelper{

	public static final String CREATE_PROVINCR = "create table province ("
			+ "id integer primary key autoincrement,"
			+ "province_name text,"
			+ "province_code text)";
	public static final String CREATE_CITY = "create table city("
			+ "id integer primary key autoincrement,"
			+ "city_name text,"
			+ "city_code text,"
			+ "province_id text)";
	public static final String CREATE_COUNTRY = "create table country("
			+ "id integer primary key autoincrement,"
			+ "country_name text,"
			+ "country_code text,"
			+ "city_id text)";
	
	public CoolWeatherOpenHelder(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVINCR);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTRY);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
