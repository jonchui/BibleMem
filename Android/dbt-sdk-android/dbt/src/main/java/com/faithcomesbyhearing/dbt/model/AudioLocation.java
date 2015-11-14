package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
protocol: HTTP, HTTPS, RTMP
server: Example mp3.faithcomesbyhearing.com
root_path: Example /mp3audiobibles2/
CDN: Boolean specifies if the server is a CDN and geographical distributes requests
 */
public class AudioLocation {
    private String protocol;
    private String server;
    @SerializedName("root_path")
    private String rootPath;
    private String cdn;
    private String baseUrl;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getCdn() {
        return cdn;
    }

    public void setCdn(String cdn) {
        this.cdn = cdn;
    }

    public String getBaseUrl() {
        return server + rootPath;
    }
}
