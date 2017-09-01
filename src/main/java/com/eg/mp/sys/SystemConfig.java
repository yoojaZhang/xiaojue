package com.eg.mp.sys;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义系统配置参数
 *
 * @author geliangjian
 */
@ConfigurationProperties(prefix = "renthz")
public class SystemConfig {

    private String rentBaseUrl;

    private String rentAppId;

    private String rentSecretKey;

    private String thingscloudApiUrl;

    private String publicKeyFileName;

    public String getRentBaseUrl() {
        return rentBaseUrl;
    }

    public void setRentBaseUrl(String rentBaseUrl) {
        this.rentBaseUrl = rentBaseUrl;
    }

    public String getRentSecretKey() {
        return rentSecretKey;
    }

    public void setRentSecretKey(String rentSecretKey) {
        this.rentSecretKey = rentSecretKey;
    }

    public String getRentAppId() {
        return rentAppId;
    }

    public void setRentAppId(String rentAppId) {
        this.rentAppId = rentAppId;
    }

    public String getThingscloudApiUrl() {
        return thingscloudApiUrl;
    }

    public void setThingscloudApiUrl(String thingscloudApiUrl) {
        this.thingscloudApiUrl = thingscloudApiUrl;
    }

    public String getPublicKeyFileName() {
        return publicKeyFileName;
    }

    public void setPublicKeyFileName(String publicKeyFileName) {
        this.publicKeyFileName = publicKeyFileName;
    }
}
