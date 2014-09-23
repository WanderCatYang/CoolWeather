package com.app.coolweather.util;

import android.text.TextUtils;

import com.app.coolweather.db.CoolWeatherDB;
import com.app.coolweather.model.City;
import com.app.coolweather.model.Country;
import com.app.coolweather.model.Province;

public class Utility {

	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			for (String p : allProvinces) {
				String[] array = p.split("\\|");
				Province province = new Province();
				province.setCode(array[0]);
				province.setName(array[1]);
				coolWeatherDB.saveProvince(province);
			}
			return true;
		}
		return false;
	}

	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			for (String c : allCities) {
				String[] array = c.split("\\|");
				City city = new City();
				city.setCode(array[0]);
				city.setName(array[1]);
				city.setProvinceId(provinceId);
				coolWeatherDB.saveCity(city);
			}
			return true;
		}
		return false;
	}
	
	public synchronized static boolean handleCountriesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCountries = response.split(",");
			for(String c : allCountries){
				String[] array = c.split("\\|");
				Country country = new Country();
				country.setCode(array[0]);
				country.setName(array[1]);
				country.setCityId(cityId);
				coolWeatherDB.saveCountry(country);
			}
			return true;
		}
		return false;
		
	}
}
