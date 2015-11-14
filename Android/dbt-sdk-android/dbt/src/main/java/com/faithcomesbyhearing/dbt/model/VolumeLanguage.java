package com.faithcomesbyhearing.dbt.model;
/*
language_name: DBP native language language name
english_name: DBP English language language name
language_code: DBP language code
language_iso: ISO 639-3 language code.
language_iso_2B: ISO 639-2B language code.
language_iso_2T: ISO 639-2T language code.
language_iso_1: ISO 639-1 language code.
language_iso_name: ISO language name
language_family_code: DBP language code of language family to which this language belongs.
language_family_name: native language name of language family to which this language belongs.
language_family_english: English language name of language family to which this language belongs.
language_family_iso: ISO 639-3 language code.
language_family_iso_2B: ISO 639-2B language code.
language_family_iso_2T: ISO 639-2T language code.
language_family_iso_1: ISO 639-1 language code.
media: array of media types
delivery: array of delivery methods
resolution: Array of resolutions available, if applicable.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeLanguage {
    @SerializedName("language_name")
    private String languageName;
    @SerializedName("english_name")
    private String englishName;
    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("language_iso")
    private String languageIso;
    @SerializedName("language_iso_2B")
    private String languageIsoTwoB;
    @SerializedName("language_iso_2T")
    private String languageIsoTwoT;
    @SerializedName("language_iso_1")
    private String languageIsoOne;
    @SerializedName("language_iso_name")
    private String languageIsoName;
    @SerializedName("language_family_code")
    private String languageFamilyCode;
    @SerializedName("language_family_name")
    private String languageFamilyName;
    @SerializedName("language_family_english")
    private String languageFamilyEnglish;
    @SerializedName("language_family_iso")
    private String languageFamilyIso;
    @SerializedName("language_family_iso_2B")
    private String languageFamilyIsoTwoB;
    @SerializedName("language_family_iso_2T")
    private String languageFamilyIsoTwoT;
    @SerializedName("language_family_iso_1")
    private String languageFamilyIsoOne;
    private List<String> media;
    private List<String> delivery;
    private List<String> resolution;

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageIso() {
        return languageIso;
    }

    public void setLanguageIso(String languageIso) {
        this.languageIso = languageIso;
    }

    public String getLanguageIsoTwoB() {
        return languageIsoTwoB;
    }

    public void setLanguageIsoTwoB(String languageIsoTwoB) {
        this.languageIsoTwoB = languageIsoTwoB;
    }

    public String getLanguageIsoTwoT() {
        return languageIsoTwoT;
    }

    public void setLanguageIsoTwoT(String languageIsoTwoT) {
        this.languageIsoTwoT = languageIsoTwoT;
    }

    public String getLanguageIsoOne() {
        return languageIsoOne;
    }

    public void setLanguageIsoOne(String languageIsoOne) {
        this.languageIsoOne = languageIsoOne;
    }

    public String getLanguageIsoName() {
        return languageIsoName;
    }

    public void setLanguageIsoName(String languageIsoName) {
        this.languageIsoName = languageIsoName;
    }

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

    public String getLanguageFamilyIso() {
        return languageFamilyIso;
    }

    public void setLanguageFamilyIso(String languageFamilyIso) {
        this.languageFamilyIso = languageFamilyIso;
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
