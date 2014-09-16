package com.app.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.coolweather.model.City;
import com.app.coolweather.model.Country;
import com.app.coolweather.model.Province;

public class CoolWeatherDB {

	public static final String DB_NAME = "cool_weather";
	public static final int VERSION = 1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase database;

	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelder helder = new CoolWeatherOpenHelder(context, DB_NAME, null, VERSION);
		database = helder.getWritableDatabase();

	}

	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getName());
			values.put("province_code", province.getCode());
			database.insert("province", null, values);
		}
	}

	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = database.query("province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province p = new Province();
				p.setId(cursor.getInt(cursor.getColumnIndex("id")));
				p.setName(cursor.getString(cursor.getColumnIndex("province_name")));
				p.setCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(p);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveCity(City city) {
		if (city != null) {
			ContentValues value = new ContentValues();
			value.put("city_name", city.getName());
			value.put("city_code", city.getCode());
			value.put("province_id", city.getProvinceId());
			database.insert("city", null, value);
		}
	}

	public List<City> loadCities(Province province) {
		List<City> cities = new ArrayList<City>();
		Cursor cursor = database.query("city", null, "province_id = ?",
				new String[] { String.valueOf(province.getId()) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City c = new City();
				c.setId(cursor.getInt(cursor.getColumnIndex("id")));
				c.setCode(cursor.getString(cursor.getColumnIndex("city_code")));
				c.setName(cursor.getString(cursor.getColumnIndex("city_name")));
				c.setProvinceId(province.getId());
				cities.add(c);
			} while (cursor.moveToNext());
		}
		return cities;
	}

	public void saveCountry(Country country) {
		ContentValues v = new ContentValues();
		v.put("country_name", country.getName());
		v.put("country_code", country.getCode());
		v.put("city_id", country.getCityId());
		database.insert("country", null, v);
	}

	public List<Country> loadCountries(City city) {
		List<Country> countries = new ArrayList<Country>();
		Cursor cursor = database.query("country", null, "city_id = ?", new String[] { String.valueOf(city.getId()) },
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Country c = new Country();
				c.setId(cursor.getInt(cursor.getColumnIndex("id")));
				c.setCityId(city.getId());
				c.setName(cursor.getString(cursor.getColumnIndex("country_name")));
				c.setCode(cursor.getString(cursor.getColumnIndex("country_code")));
				countries.add(c);
			} while (cursor.moveToNext());
		}
		return countries;
	}

}
