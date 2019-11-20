package com.azkj.houseproperty.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class HttpUtil {

	private static final String ENCODING = "UTF-8";
	

	public static String post(String url) {
	    CloseableHttpClient client = HttpClients.createDefault();
	    String responseText = "";
	    CloseableHttpResponse response = null;
	    try {
	        HttpPost method = new HttpPost(url);
	        response = client.execute(method);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            responseText = EntityUtils.toString(entity);
	        }
	    } catch (Exception e) {
	    	log.error("http request failed",e);
	    } finally {
	        try {
	            response.close();
	        } catch (Exception e) {
	        	log.error("",e);
	        }
	    }
	        return responseText;
	    }
	
	public static String get(String url) {
	    CloseableHttpClient client = HttpClients.createDefault();
	    String responseText = "";
	    CloseableHttpResponse response = null;
	    try {
	        HttpGet method = new HttpGet(url);
	        response = client.execute(method);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            responseText = EntityUtils.toString(entity);
	        }
	    } catch (Exception e) {
	    	log.error("http request failed",e);
	    } finally {
	        try {
	            response.close();
	        } catch (Exception e) {
	        	log.error("",e);
	        }
	    }
	        return responseText;
	    }



	public static String post(String url, String body) {
		System.out.println("body:" + System.lineSeparator() + body);

		String result = "";
		try {
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			boolean firstLine = true; // 读第一行不加换行符
			while ((line = in.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
				} else {
					result += System.lineSeparator();
				}
				result += line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
