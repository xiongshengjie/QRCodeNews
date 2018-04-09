package cn.xcloude.QRCodeNews.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

public interface HttpService {

	/** 
	 * 执行Get请求 
	 *  
	 * @param url 
	 * @return 请求到的内容 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	String doGet(String url) throws URISyntaxException, ClientProtocolException, IOException;

	/** 
	 * 执行Get请求 
	 *  
	 * @param url 
	 * @param params 
	 *            请求中的参数 
	 * @return 请求到的内容 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	String doGet(String url, Map<String, Object> params)
			throws URISyntaxException, ClientProtocolException, IOException;

	/** 
	 *  
	 * @param url 
	 * @param params 
	 *            请求中的参数 
	 * @return 请求到的内容 
	 * @throws URISyntaxException 
	 * @throws ClientProtocolException 
	 * @throws IOException 
	 */
	String doPost(String url, Map<String, Object> params)
			throws URISyntaxException, ClientProtocolException, IOException;

	/** 
	 *  
	 * @param url 
	 * @param params 
	 *            请求中的参数 
	 * @return 请求到的内容 
	 * @throws URISyntaxException 
	 * @throws ClientProtocolException 
	 * @throws IOException 
	 */
	String doPost(String url) throws URISyntaxException, ClientProtocolException, IOException;

}