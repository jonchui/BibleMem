package com.faithcomesbyhearing.dbt;

import com.faithcomesbyhearing.dbt.callback.TextSearchCallback;
import com.faithcomesbyhearing.dbt.callback.VerseCallback;
import com.faithcomesbyhearing.dbt.model.TextSearch;
import com.faithcomesbyhearing.dbt.model.Verse;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TextEndpoint extends BaseEndpoint {

    public void getVerse(@NotNull String damId, String bookId, String chapterId,
                         String verseStart, String verseEnd, VerseCallback callback) {
        String endpoint = "/library/verse";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<Verse> response = new ArrayList<Verse>();
        TypeToken<List<Verse>> typeToken = new TypeToken<List<Verse>>(){};
        params.put("dam_id", damId);
        params.put("book_id", bookId);
        params.put("chapter_id", chapterId);
        params.put("verse_start", verseStart);
        params.put("verse_end", verseEnd);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void search(@NotNull String damId, @NotNull String query, String bookId,
                       TextSearchCallback callback) {
        String endpoint = "/text/search";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<TextSearch> response = new ArrayList<TextSearch>();
        TypeToken<List<TextSearch>> typeToken = new TypeToken<List<TextSearch>>(){};
        params.put("dam_id", damId);
        params.put("query", query);
        params.put("book_id", bookId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

}
