package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
id: the id of the organization
name: the native language name of the organization.
english_name: the English name of the organization.
description: the native language description of the organization.
english_description: the English description of the organization.
web_url: the Web URL for the organization
donation_url: the URL for donations to the organization
enabled: [true|false] whether or not the organization is enabled (active)
 */
public class Organization {

    private String id;
    private String name;
    @SerializedName("english_name")
    private String englishName;
    private String description;
    @SerializedName("english_description")
    private String englishDescription;
    @SerializedName("web_url")
    private String webUrl;
    @SerializedName("donation_url")
    private String donationUrl;
    private String enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDonationUrl() {
        return donationUrl;
    }

    public void setDonationUrl(String donationUrl) {
        this.donationUrl = donationUrl;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
