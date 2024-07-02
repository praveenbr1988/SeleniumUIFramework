package com.ui.coreLayer.FrameworkConfigs;

public class DatabaseConfiguration {

    private boolean OSAuth;
    private String url;
    private String username;
    private String password;


    public DatabaseConfiguration(String url, String username, String password) throws Exception {
        this.setOSAuth(false);
        this.url=url;
        this.username = username;
        this.username = password;
        //this.password = PasswordUtil.decrypt(password, PasswordUtil.generateKey(),PasswordUtil.generateIv());
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

    private void setOSAuth(boolean oSAuth){
        this.OSAuth = oSAuth;
    }

    public boolean isOSAuth(){
        return this.OSAuth;
    }
}
