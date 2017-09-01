package com.eg.mp.common;

/**
 * @author liangjiange
 * @Description: 常量类
 * @date 2016年7月28日 上午9:23:16
 */
public class Constants {

    /**
     * 请求URL
     */
    public static final String THINGSCLOUD_API_URL = "https://thingscloud.enginhz.com/thingscloud/openapi/v1/";
    //public static final String THINGSCLOUD_API_URL = "https://localhost:8443/thingscloud/openapi/v1/";
  //  public static final String THINGSCLOUD_API_URL = "http://localhost:8081/thingscloud/openapi/v1/";
//	public static final String THINGSCLOUD_API_URL = "https://192.168.0.201:8081/thingscloud/openapi/v1/";

    public static final String ACCESS_TOKEN = "0349a7eebde5e4d3c9e63cfea2c94581";


    /**
     * 采用md5签名算法
     */
    public static final Integer SIGN_METHOD_MD5 = 0;

    /**
     * 采用hmac签名算法
     */
    public static final Integer SIGN_METHOD_HMAC = 1;

    public static final String CHARSET_UTF8 = "UTF-8";

    public static final String SECRET_KEY = "700cca3de7f851b530f74223d7b5df21";

    /**
     * Thingscloud的公钥
     */
    public static final String Public_Key_File_Name = "213995005080281.pem";


}
