package com.faithcomesbyhearing.dbt;

import com.faithcomesbyhearing.dbt.callback.AudioLocationCallback;
import com.faithcomesbyhearing.dbt.callback.AudioPathCallback;
import com.faithcomesbyhearing.dbt.model.AudioLocation;
import com.faithcomesbyhearing.dbt.model.AudioPath;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class AudioEndpoint extends BaseEndpoint {

    public void getLocation(AudioLocationCallback callback) {
        String endpoint = "/audio/location";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<AudioLocation> response = new ArrayList<AudioLocation>();
        TypeToken<List<AudioLocation>> typeToken = new TypeToken<List<AudioLocation>>(){};
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getPath(@NotNull String damId, String bookId, String chapterId, AudioPathCallback callback) {
        String endpoint = "/audio/path";
        HashMap<String, String> params = new HashMap<String, String>();
        List<AudioPath> response = new ArrayList<AudioPath>();
        TypeToken<List<AudioPath>> typeToken = new TypeToken<List<AudioPath>>(){};
        params.put("dam_id", damId);
        params.put("book_id", bookId);
        params.put("chapter_id", chapterId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getVerseStart(@NotNull String damId, @NotNull String bookId,
                              @NotNull String chapterId, Callback callback) {
        String endpoint = "/audio/versestart";
        HashMap<String, String> params = new HashMap<String, String>();
        List<AudioPath> response = new ArrayList<AudioPath>();
        TypeToken<List<AudioPath>> typeToken = new TypeToken<List<AudioPath>>(){};
        params.put("dam_id", damId);
        params.put("book_id", bookId);
        params.put("chapter_id", chapterId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }
}
