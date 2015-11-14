package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
server: Example cloud.faithcomesbyhearing.com
root_path: Example /volumeassets
priority: Example 6
volume_id: the location directory and asset file basename for the volume - the DAM ID for new volumes or the legacy sku for legacy volumes.
 */
public class Asset {

    private String server;
    @SerializedName("root_path")
    private String rootPath;
    private String priority;
    @SerializedName("volume_id")
    private String volumeId;

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }
}
