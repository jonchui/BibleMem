package com.faithcomesbyhearing.dbt;

import com.faithcomesbyhearing.dbt.callback.AssetCallback;
import com.faithcomesbyhearing.dbt.callback.AudioLocationCallback;
import com.faithcomesbyhearing.dbt.callback.AudioPathCallback;
import com.faithcomesbyhearing.dbt.callback.BookCallback;
import com.faithcomesbyhearing.dbt.callback.BookNameCallback;
import com.faithcomesbyhearing.dbt.callback.BookOrderCallback;
import com.faithcomesbyhearing.dbt.callback.ChapterCallback;
import com.faithcomesbyhearing.dbt.callback.LanguageCallback;
import com.faithcomesbyhearing.dbt.callback.LibraryVersionCallback;
import com.faithcomesbyhearing.dbt.callback.OrganizationCallback;
import com.faithcomesbyhearing.dbt.callback.TextSearchCallback;
import com.faithcomesbyhearing.dbt.callback.VerseCallback;
import com.faithcomesbyhearing.dbt.callback.VerseInfoCallback;
import com.faithcomesbyhearing.dbt.callback.VideoLocationCallback;
import com.faithcomesbyhearing.dbt.callback.VideoPathCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeLanguageCallback;
import com.faithcomesbyhearing.dbt.callback.VolumeLanguageFamilyCallback;

import org.jetbrains.annotations.NotNull;

public class Dbt {

    private static Dbt instance = new Dbt();
    private static String apiKey;
    private static LibraryEndpoint libraryEndpoint;
    private static TextEndpoint textEndpoint;
    private static AudioEndpoint audioEndpoint;
    public static VideoEndpoint videoEndpoint;

    protected Dbt() {
        // Only used to prevent instantiation
    }

    /**
     * Set the Digital Bible Toolkit API key
     *
     * @param key Digital Bible Platform API key
     */
    public static void setApiKey(String key) {
        apiKey = key;
        libraryEndpoint = new LibraryEndpoint();
        textEndpoint = new TextEndpoint();
        audioEndpoint = new AudioEndpoint();
        videoEndpoint = new VideoEndpoint();
    }

    protected static String getApiKey() {
        return apiKey;
    }

    /*
     *   Library Endpoints
     */

    /**
     * Wrapper method for /library/volume call
     *
     * @param damId DAM ID of volume
     *
     * @param media the format of languages the caller
     * is interested in. This specifies if you want languages available in
     * text or languages available in audio or only languages available in
     * both. All are returned by default.
     *
     * @param language Filter the versions returned to a specified
     * language. For example return all the 'English' volumes.
     *
     * @param callback an implemented instance of VolumeCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryVolume(String damId, String media, String language,
                                        String languageCode, VolumeCallback callback) {

        libraryEndpoint.getVolumes(damId, media, language, languageCode, callback);
    }

    /**
     * Wrapper method for /library/book call
     *
     * @param damId The DAM ID, DAM ID LLLVVVC (where LLL is the language code, VVV is the version
     *              code, and C is colletion type  [N|O|S|P|B]), or DAM ID LLLVVV piece for which
     *              to retrieve a list of books. If the six character piece, LLLVVV,
     *              (or 8 or 9 character pieces) is used, the list will be from both OT and NT,
     *              whichever exist.
     *
     * @param callback an implemented instance of BookCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryBook(@NotNull String damId, BookCallback callback) {
        libraryEndpoint.getBook(damId, callback);
    }

    /**
     * Wrapper method for /library/volumelanguage call
     *
     * @param media [text|audio|video] - the format of languages the caller is interested in.
     *              This specifies if you want languages available in text or languages
     *              available in audio.
     *
     * @param languageCode
     *
     * @param callback an implemented instance of VolumeLanguageCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryVolumeLanguage(String media, String languageCode,
                                         VolumeLanguageCallback callback) {
        libraryEndpoint.getVolumeLanguage(media, languageCode, callback);
    }

    /**
     * Wrapper method for /library/version call
     *
     * @param name Get the entry for a part of a version name in either native language or English.
     *
     * @param callback an implemented instance of LibraryVersionCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryVersion(String name, LibraryVersionCallback callback) {
        libraryEndpoint.getVersion(name, callback);
    }

    /**
     * Wrapper method for /library/bookorder call
     *
     * @param damId The DAM ID of the volume. If only six characters of a DAM ID are used,
     *              this call will try to return the matching OT and NT book list, if available.
     *
     * @param callback an implemented instance of BookOrderCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryBookOrder(@NotNull String damId, BookOrderCallback callback) {
        libraryEndpoint.getBookOrder(damId, callback);
    }

    /**
     * Wrapper method for /library/bookname call
     *
     * @param languageCode DBP language code.
     *
     * @param callback an implemented instance of BookNameCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryBookName(@NotNull String languageCode, BookNameCallback callback) {
        libraryEndpoint.getBookName(languageCode, callback);
    }

    /**
     * Wrapper method for /library/chapter call
     *
     * @param damId The 6 - 10 character DAM ID for which to retrieve chapters. A six character
     *              DAM ID will return the chapter info for related OT and NT if such exist.
     *
     * @param bookId The ID for the book for which to retrieve chapters.
     *
     * @param callback an implemented instance of ChapterCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryChapter(@NotNull String damId, String bookId,
                                         ChapterCallback callback) {
        libraryEndpoint.getChapter(damId, bookId, callback);
    }

    /**
     * Wrapper method for /library/numbers call
     *
     * @param languageCode DBP Language code of interest.
     *
     * @param start First number (in Hindu numerals) of range desired.
     *
     * @param end Last number (in Hindu numerals) of range desired.
     *
     * @param callback an implemented instance of ChapterCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryNumbers(String languageCode, String start, String end,
                                         Callback callback) {
        libraryEndpoint.getNumbers(languageCode, start, end, callback);
    }

    /**
     * Wrapper method for /library/organization call
     *
     * @param name the partial or complete name of the organization in either the organization's
     *             native language or English.
     *
     * @param callback an implemented instance of OrganizationCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryOrganization(String name, OrganizationCallback callback) {
        libraryEndpoint.getOrganization(name, callback);
    }

    /**
     * Wrapper method for /library/asset call
     *
     * @param damId To get the location directory and asset file basename for a specific volume.
     *
     * @param callback an implemented instance of AssetCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryAsset(String damId, AssetCallback callback) {
        libraryEndpoint.getAsset(damId, callback);
    }

    /**
     * Wrapper method for /library/volumelanguagefamily call
     *
     * @param languageCode the three letter language code.
     *
     * @param media [text|audio|video] - the format of the volumes of the language families
     *              the caller is interested in. This specifies if you want language families available in text or audio or...
     *
     * @param callback an implemented instance of VolumeLanguageFamilyCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryVolumeLanguageFamily(String languageCode, String media,
                                                      VolumeLanguageFamilyCallback callback) {
        libraryEndpoint.getVolumeLanguageFamily(languageCode, media, callback);
    }

    /**
     * Wrapper method for /library/language call
     *
     * @param name Get the entry for a part of a language name in either native language or English.
     *
     * @param callback an implemented instance of LanguageCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryLanguage(String name, LanguageCallback callback) {
        libraryEndpoint.getLanguage(name, callback);
    }

    /**
     * Wrapper method for /library/verseinfo call
     *
     * @param damId the DAM ID of the verse info.
     *
     * @param bookId If specified returns verse text ONLY for the specified book.
     *
     * @param chapterId If specified returns verse text ONLY for the specified book and chapter.
     *
     * @param callback an implemented instance of VerseInfoCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getLibraryVerseInfo(String damId, String bookId, String chapterId,
                                           VerseInfoCallback callback) {
        libraryEndpoint.getVerseInfo(damId, bookId, chapterId, callback);
    }

    /*
     * Text Endpoints
     */

    /**
     * Wrapper method for /text/verse call
     *
     * @param damId the DAM ID of the verse text
     *
     * @param callback an implemented instance of VerseCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getTextVerse(@NotNull String damId, String bookId, String chapterId,
                                    String verseStart, String verseEnd, VerseCallback callback) {
        textEndpoint.getVerse(damId, bookId, chapterId, verseStart, verseEnd, callback);
    }

    /**
     * Wrapper method for /text/search call
     *
     * @param damId The DAM ID the caller wishes to search in. Using the first eight characters of
     *              a DAM ID will cause a complementary testament search if the seventh
     *              character is O or T.
     *
     * @param query The text that the caller wishes to search for in the specified text.
     *              Multiple words or phrases can be combined with '+' for AND and '|' for OR. They
     *              will be processed simply from left to right. So, "Saul+Paul|Ruth" will evaluate
     *              as (Saul AND Paul) OR Ruth.
     *
     * @param bookId OSIS book id to limit search. For the interim, if sku is used for dam_id,
     *               book_id needs to be the old style numerical code.
     *
     * @param callback an implemented instance of TextSearchCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getTextSearch(@NotNull String damId, @NotNull String query, String bookId,
                              TextSearchCallback callback) {
        textEndpoint.search(damId, query, bookId, callback);
    }

    /**
     * Wrapper method for /audio/location call
     *
     * @param callback an implemented instance of AudioLocationCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getAudioLocation(AudioLocationCallback callback) {
        audioEndpoint.getLocation(callback);
    }

    /**
     * Wrapper method for /audio/path call
     *
     * @param damId The DAM ID for which to retrieve file path info.
     *
     * @param bookId The OSIS id of the book. If book is not specified ALL book/chapter information
     *               for the given language is returned to the caller. Otherwise chapter
     *               information for the specified book is returned.
     *
     * @param chapterId The id for the specified chapter. If chapter is specified only the
     *                  specified chapter audio information is returned to the caller.
     *
     * @param callback an implemented instance of AudioPathCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getAudioPath(@NotNull String damId, String bookId, String chapterId,
                             AudioPathCallback callback) {
        audioEndpoint.getPath(damId, bookId, chapterId, callback);
    }

    /**
     * Wrapper method for /audio/versestart call
     *
     * @param damId the DAM ID of the audio volume
     *
     * @param bookId the book for verse times desired.
     *
     * @param chapterId the chapter for the verse times desired.
     *
     * @param callback an implemented instance of VerseStartCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getAudioVerseStart(@NotNull String damId, @NotNull String bookId,
                                          @NotNull String chapterId, VerseStartCallback callback) {
        audioEndpoint.getVerseStart(damId, bookId, chapterId, callback);
    }

    /**
     * Wrapper method for /video/videolocation call
     *
     * @param callback an implemented instance of VideoLocationCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getVideoLocation(VideoLocationCallback callback) {
        videoEndpoint.getLocation(callback);
    }

    /**
     * Wrapper method for /video/videopath call
     *
     * @param damId DAM ID for the video volume desired.
     *
     * @param encoding  [mp4|m3u8] The video encoding format desired.
     *
     * @param bookId OSIS book code to filter segments by references to book desired.
     *
     * @param chapterId Chapter id to filter segments by references based on book and chapter.
     *
     * @param verseId Verse id to filter segments by references based on book, chapter and verse.
     *
     * @param callback an implemented instance of VideoPathCallback to receive
     *                 whether call succeeded or failed
     */
    public static void getVideoPath(@NotNull String damId, @NotNull String encoding, String bookId,
                                    String chapterId, String verseId, VideoPathCallback callback) {
        videoEndpoint.getPath(damId, encoding, bookId, chapterId, verseId, callback);
    }
}
