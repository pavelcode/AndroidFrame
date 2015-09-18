package com.cblue.framework.network;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.cblue.framework.application.SoftApplication;
import com.cblue.framework.network.ServerInterfaceDefinition.RequestMethod;
import com.cblue.framework.util.LogUtil;

/**
 * 异步请求，保存数据到本地，并使用CompleteListener进行传递
 * @author Administrator
 *
 */
public class HttpRequestAsyncTask extends AsyncTask<Request, Void, Object> {
	
	private static final int RESPONSE_TIME_OUT = 60000;
	private static final int REQUEST_TIME_OUT = 60000;

	private String resultString;
	@SuppressWarnings("rawtypes")
	private OnCompleteListener onCompleteListener;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	private HttpClient buildHttpClient() {
		HttpClient httpClient = new DefaultHttpClient();
		// 请求超时
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIME_OUT);
		// 读取超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				RESPONSE_TIME_OUT);

		return httpClient;
	}

	@SuppressLint("SdCardPath")
	@Override
	protected Object doInBackground(Request... params) {

		Object object = null;

		try {
			HttpClient httpClient = buildHttpClient();
			Request request = params[0];

			/**
			 * 获取BaseUrl
			 */
			String urlString = SoftApplication.softApplication.getAppInfo().serverAddress;
			LogUtil.log(urlString);

			HttpResponse httpResponse;
			HttpGet httpGet = null;
			HttpPost httpPost = null;

			/**
			 * GET请求方式
			 */
			if (RequestMethod.GET.equals(request.getServerInterfaceDefinition()
					.getRequestMethod())) {
				StringBuffer stringBuffer = new StringBuffer(urlString + "?");
				for (Map.Entry<String, String> entry : request.getParamsMap()
						.entrySet()) {
					stringBuffer.append(entry.getKey());
					stringBuffer.append('=');
					stringBuffer.append(entry.getValue());
					stringBuffer.append('&');
					LogUtil.log("参数：" + entry.getKey() + "值："
							+ entry.getValue());
				}
				stringBuffer.deleteCharAt(stringBuffer.length() - 1);
				LogUtil.log("GET:" + stringBuffer.toString());
				httpGet = new HttpGet(stringBuffer.toString());
				httpResponse = httpClient.execute(httpGet);
			} else {
				/**
				 * POST请求方式
				 */
				httpPost = new HttpPost(urlString
						+ request.getServerInterfaceDefinition().getOpt());
				ArrayList<BasicNameValuePair> localArrayList = new ArrayList<BasicNameValuePair>();
				for (Map.Entry<String, String> entry : request.getParamsMap()
						.entrySet()) {
					localArrayList.add(new BasicNameValuePair(entry.getKey(),
							entry.getValue()));
					LogUtil.log("参数：" + entry.getKey() + "值："
							+ entry.getValue());
				}
				httpPost.setEntity(new UrlEncodedFormEntity(localArrayList,
						"UTF-8"));
				httpResponse = httpClient.execute(httpPost);
			}

			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				LogUtil.log("返回getStatusCode="
						+ httpResponse.getStatusLine().getStatusCode());
				if (null != httpPost) {
					//终止请求
					httpPost.abort();
				}
				if (null != httpGet) {
					httpGet.abort();
				}
				// 将来可能添加回调
			} else {
				resultString = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				LogUtil.log("返回result=" + resultString);

				try {
					File file = new File("/mnt/sdcard/boyage");
					if (!file.exists()) {
						file.mkdirs();
					}
					File file2 = new File("/mnt/sdcard/boyage", "boyage.txt");
					FileOutputStream outStream = new FileOutputStream(file2);
					outStream.write(resultString.getBytes());
					outStream.flush();
					outStream.close();
				} catch (Exception e) {
				}
				LogUtil.log("返回2result=" + resultString);
				object = request.getJsonParser().parse(resultString);
				LogUtil.log("返回object=" + object);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (null != onCompleteListener) {
			LogUtil.log("onPostExecute.result="+result);
			if (null == result) {
				onCompleteListener.onComplete(null, null);
			} else {
				onCompleteListener.onComplete(result, resultString);
			}
		}
	}
	
	public interface OnCompleteListener<T> {
		public void onComplete(T result, String resultString);
	}

	@SuppressWarnings("rawtypes")
	public OnCompleteListener getOnCompleteListener() {
		return onCompleteListener;
	}

	@SuppressWarnings("rawtypes")
	public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
		this.onCompleteListener = onCompleteListener;
	}
	
	
}
