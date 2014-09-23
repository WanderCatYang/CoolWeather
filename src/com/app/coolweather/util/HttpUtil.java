package com.app.coolweather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

	public static void sendRequest(final String address, final HttpCallbackListener listener) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setReadTimeout(8000);
					connection.setRequestMethod("GET");
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					if (listener != null) {
						listener.onFinish(builder.toString());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					listener.onError(e);
				} finally {
					if(connection != null){
						connection.disconnect();
					}
				}

			}

		});
	}
}
