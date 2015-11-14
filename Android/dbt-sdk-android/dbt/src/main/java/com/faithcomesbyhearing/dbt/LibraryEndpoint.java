package com.faithcomesbyhearing.dbt;

import com.faithcomesbyhearing.dbt.callback.AssetCallback;
import com.faithcomesbyhearing.dbt.callback.BookCallback;
import com.faithcomesbyhearing.dbt.callback.BookOrderCallback;
import com.faithcomesbyhearing.dbt.callback.ChapterCallback;
import com.faithcomesbyhearing.dbt.callback.LanguageCallback;
import com.faithcomesbyhearing.dbt.callback.LibraryVersionCallback;
import com.faithcomesbyhearing.dbt.callback.OrganizationCallback;
import com.faithcomesbyhearing.dbt.callback.VerseInfoCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeLanguageCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeLanguageFamilyCallback;
import com.faithcomesbyhearing.dbt.model.Asset;
import com.faithcomesbyhearing.dbt.model.Book;
import com.faithcomesbyhearing.dbt.model.BookName;
import com.faithcomesbyhearing.dbt.model.BookOrder;
import com.faithcomesbyhearing.dbt.model.Chapter;
import com.faithcomesbyhearing.dbt.model.Language;
import com.faithcomesbyhearing.dbt.model.LibraryVersion;
import com.faithcomesbyhearing.dbt.model.Organization;
import com.faithcomesbyhearing.dbt.model.VerseInfo;
import com.faithcomesbyhearing.dbt.model.Volume;
import com.faithcomesbyhearing.dbt.model.VolumeLanguage;
import com.faithcomesbyhearing.dbt.model.VolumeLanguageFamily;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LibraryEndpoint extends BaseEndpoint {

    public void getVolumes(String damId, String media, String language, String languageCode,
                           VolumeCallback callback) {
        String endpoint = "/library/volume";
        ArrayList<Volume> response = new ArrayList<Volume>();
        TypeToken<List<Volume>> typeToken = new TypeToken<List<Volume>>(){};
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("dam_id", damId);
        params.put("media", media);
        params.put("language", language);
        params.put("language_code", languageCode);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getBook(@NotNull String damId, BookCallback callback) {
        String endpoint = "/library/book";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<Book> response = new ArrayList<Book>();
        TypeToken<List<Book>> typeToken = new TypeToken<List<Book>>(){};
        params.put("dam_id", damId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getVolumeLanguage(String media, String languageCode,
                                         VolumeLanguageCallback callback) {
        String endpoint = "/library/volumelanguage";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<VolumeLanguage> response = new ArrayList<VolumeLanguage>();
        TypeToken<List<VolumeLanguage>> typeToken = new TypeToken<List<VolumeLanguage>>(){};
        params.put("media", media);
        params.put("language_code", languageCode);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getVersion(String name, LibraryVersionCallback callback) {
        String endpoint = "/library/version";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<LibraryVersion> response = new ArrayList<LibraryVersion>();
        TypeToken<List<LibraryVersion>> typeToken = new TypeToken<List<LibraryVersion>>(){};
        params.put("name", name);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getBookOrder(@NotNull String damId, BookOrderCallback callback) {
        String endpoint = "/library/bookorder";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<BookOrder> response = new ArrayList<BookOrder>();
        TypeToken<List<BookOrder>> typeToken = new TypeToken<List<BookOrder>>(){};
        params.put("dam_id", damId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getBookName(@NotNull String languageCode, Callback callback) {
        String endpoint = "/library/bookname";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<BookName> response = new ArrayList<BookName>();
        TypeToken<List<BookName>> typeToken = new TypeToken<List<BookName>>(){};
        params.put("language_code", languageCode);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getChapter(@NotNull String damId, String bookId, ChapterCallback callback) {
        String endpoint = "/library/chapter";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<Chapter> response = new ArrayList<Chapter>();
        TypeToken<List<Chapter>> typeToken = new TypeToken<List<Chapter>>(){};
        params.put("dam_id", damId);
        params.put("book_id", bookId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getNumbers(@NotNull String languageCode, @NotNull String start, @NotNull String end,
                           Callback callback) {
        String endpoint = "/library/numbers";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<VolumeLanguage> response = new ArrayList<VolumeLanguage>();
        TypeToken<List<VolumeLanguage>> typeToken = new TypeToken<List<VolumeLanguage>>(){};
        params.put("language_code", languageCode);
        params.put("start", start);
        params.put("end", end);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getOrganization(String name, OrganizationCallback callback) {
        String endpoint = "/library/organization";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<Organization> response = new ArrayList<Organization>();
        TypeToken<List<Organization>> typeToken = new TypeToken<List<Organization>>(){};
        params.put("name", name);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getAsset(String damId, AssetCallback callback) {
        String endpoint = "/library/asset";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<Asset> response = new ArrayList<Asset>();
        TypeToken<List<Asset>> typeToken = new TypeToken<List<Asset>>(){};
        params.put("dam_id", damId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getVolumeLanguageFamily(String languageCode, String media,
                                        VolumeLanguageFamilyCallback callback) {
        String endpoint = "/library/volumelanguagefamily";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<VolumeLanguageFamily> response = new ArrayList<VolumeLanguageFamily>();
        TypeToken<List<VolumeLanguageFamily>> typeToken = new TypeToken<List<VolumeLanguageFamily>>(){};
        params.put("language_code", languageCode);
        params.put("media", media);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getLanguage(String name, LanguageCallback callback) {
        String endpoint = "/library/language";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<Language> response = new ArrayList<Language>();
        TypeToken<List<Language>> typeToken = new TypeToken<List<Language>>(){};
        params.put("name", name);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getVerseInfo(String damId, String bookId, String chapterId,
                             VerseInfoCallback callback) {
        String endpoint = "/library/verseinfo";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<VerseInfo> response = new ArrayList<VerseInfo>();
        TypeToken<List<VerseInfo>> typeToken = new TypeToken<List<VerseInfo>>(){};
        params.put("dam_id", damId);
        params.put("book_id", bookId);
        params.put("chapter_id", chapterId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }
}
