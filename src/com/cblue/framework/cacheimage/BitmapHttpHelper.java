package com.cblue.framework.cacheimage;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * 图片网络下载
 * @author Administrator
 *
 */
class BitmapHttpHelper {
	
	
	private static BitmapFileCache mBitmapFileCache = BitmapFileCache.getInstance();
	
	/**
	 * 从网上下载一个图片
	 * @param url 图片的链接
	 * @return Bitmap
	 */
	public static Bitmap downloadBitmap(String url) {
		Bitmap bitmap = null;
		try{
			Util.log("下载图片地址：" + url);
			HttpParams params = createHttpParams();
			HttpGet request = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient(params);
			HttpResponse response = httpClient.execute(request);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine != null){
				final int statusCode = statusLine.getStatusCode();
				Util.log("从网上下载图片statusCode="+statusCode);
				if(statusCode != HttpStatus.SC_OK){
					return bitmap;
				}else{
					HttpEntity entity = response.getEntity();
					BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
					if (bufHttpEntity != null && bufHttpEntity.getContentLength() > 0) {
						InputStream is = bufHttpEntity.getContent();
						bitmap = BitmapFactory.decodeStream(is);
						is.close();
						
						//将下载完成之后的bitmap存至SD卡
						mBitmapFileCache.saveInputStreamToLocal(bitmap,url);
					}
				}
			}
			
		}catch(IllegalArgumentException e){
			System.out.println("图片链接地址url不可用");
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	private static HttpParams createHttpParams() {
		final HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, Constants.CONNECT_TIMEOUT * 1000);
		HttpConnectionParams.setSoTimeout(params, Constants.CONNECT_TIMEOUT * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192 * 5);
		return params;
	}
	
	

}
