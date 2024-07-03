package com.ui.coreLayer.FrameworkConfigs;

public class APIConfiguration {

    private String url;
    private String username;
    private String password;


    public APIConfiguration(String url, String username, String password) throws Exception {
        this.url=url;
        this.username = username;
        try {
            this.password = EncryptAndDecrypt.decrypt(password);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public String getUrl(){
        return this.url;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }


}
