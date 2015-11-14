package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
language_code: DBP language code
language_name: DBP native language language name
english_name: DBP English language language name
language_iso: ISO 639-3 language code
language_iso_2B: ISO 639-2B language code
language_iso_2T: ISO 639-2T language code
language_iso_1: ISO 639-1 language code
language_iso_name: ISO language name
language_family_code: The language code for the language family to which this language belongs
 */
public class Language {

    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("language_name")
    private String languageName;
    @SerializedName("english_name")
    private String englishName;
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

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

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
}
