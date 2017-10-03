package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kyros on 03-10-2017.
 */

public class Meta{
    @SerializedName("server_time")
    @Expose
    private double server_time;

    public double getServerTime() { return this.server_time; }

    public void setServerTime(double server_time) { this.server_time = server_time; }
    @SerializedName("server_timezone")
    @Expose
    private String server_timezone;

    public String getServerTimezone() { return this.server_timezone; }

    public void setServerTimezone(String server_timezone) { this.server_timezone = server_timezone; }
    @SerializedName("api_version")
    @Expose
    private double api_version;

    public double getApiVersion() { return this.api_version; }

    public void setApiVersion(double api_version) { this.api_version = api_version; }
    @SerializedName("execution_time")
    @Expose
    private String execution_time;

    public String getExecutionTime() { return this.execution_time; }

    public void setExecutionTime(String execution_time) { this.execution_time = execution_time; }
}
