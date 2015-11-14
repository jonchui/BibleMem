package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
language_family_code: DBP language code of language family to which this language belongs.
language_family_name: native language name of language family to which this language belongs.
language_family_english: English language name of language family to which this language belongs.
language_family_iso: ISO 639-3 code for the language family.
language_family_iso_2B: ISO 639-2B language code.
language_family_iso_2T: ISO 639-2T language code.
language_family_iso_1: ISO 639-1 language code.
language: array of language codes of languages in family.
media: array of media types
delivery: array of delivery methods
resolution: Array of resolutions available, if applicable.
 */
public class VolumeLanguageFamily {

    @SerializedName("language_family_code")
    private String languageFamilyCode;
    @SerializedName("language_family_name")
    private String languageFamilyName;
    @SerializedName("language_family_english")
    private String languageFamilyEnglish;
    @SerializedName("language_family_iso")
    private String languageIsoFamilyIso;
    @SerializedName("language_family_iso_2B")
    private String languageFamilyIsoTwoB;
    @SerializedName("language_family_iso_2T")
    private String languageFamilyIsoTwoT;
    @SerializedName("language_family_iso_1")
    private String languageFamilyIsoOne;
    private String language;
    private List<String> media;
    private List<String> delivery;
    private List<String> resolution;

    public String getLanguageFamilyCode() {
        return languageFamilyCode;
    }

    public void setLanguageFamilyCode(String languageFamilyCode) {
        this.languageFamilyCode = languageFamilyCode;
    }

    public String getLanguageFamilyName() {
        return languageFamilyName;
    }

    public void setLanguageFamilyName(String languageFamilyName) {
        this.languageFamilyName = languageFamilyName;
    }

    public String getLanguageFamilyEnglish() {
        return languageFamilyEnglish;
    }

    public void setLanguageFamilyEnglish(String languageFamilyEnglish) {
        this.languageFamilyEnglish = languageFamilyEnglish;
    }

    public String getLanguageIsoFamilyIso() {
        return languageIsoFamilyIso;
    }

    public void setLanguageIsoFamilyIso(String languageIsoFamilyIso) {
        this.languageIsoFamilyIso = languageIsoFamilyIso;
    }

    public String getLanguageFamilyIsoTwoB() {
        return languageFamilyIsoTwoB;
    }

    public void setLanguageFamilyIsoTwoB(String languageFamilyIsoTwoB) {
        this.languageFamilyIsoTwoB = languageFamilyIsoTwoB;
    }

    public String getLanguageFamilyIsoTwoT() {
        return languageFamilyIsoTwoT;
    }

    public void setLanguageFamilyIsoTwoT(String languageFamilyIsoTwoT) {
        this.languageFamilyIsoTwoT = languageFamilyIsoTwoT;
    }

    public String getLanguageFamilyIsoOne() {
        return languageFamilyIsoOne;
    }

    public void setLanguageFamilyIsoOne(String languageFamilyIsoOne) {
        this.languageFamilyIsoOne = languageFamilyIsoOne;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public List<String> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<String> delivery) {
        this.delivery = delivery;
    }

    public List<String> getResolution() {
        return resolution;
    }

    public void setResolution(List<String> resolution) {
        this.resolution = resolution;
    }
}
