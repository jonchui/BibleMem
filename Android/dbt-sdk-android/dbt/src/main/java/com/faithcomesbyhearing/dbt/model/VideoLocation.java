package com.faithcomesbyhearing.dbt.model;
/*
protocol: HTTP, HTTPS
server: Example video.dbt.io
 */
public class VideoLocation {
    private String protocol;
    private String server;

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
}
