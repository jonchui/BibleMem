package com.faithcomesbyhearing.dbt;

import com.faithcomesbyhearing.dbt.callback.VideoLocationCallback;
import com.faithcomesbyhearing.dbt.callback.VideoPathCallback;
import com.faithcomesbyhearing.dbt.model.VideoLocation;
import com.faithcomesbyhearing.dbt.model.VideoPath;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class VideoEndpoint extends BaseEndpoint {

    public void getLocation(VideoLocationCallback callback) {
        String endpoint = "/video/location";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<VideoLocation> response = new ArrayList<VideoLocation>();
        TypeToken<List<VideoLocation>> typeToken = new TypeToken<List<VideoLocation>>(){};
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }

    public void getPath(@NotNull String damId, @NotNull String encoding, String bookId,
                        String chapterId, String verseId, VideoPathCallback callback) {
        String endpoint = "/video/videopath";
        HashMap<String, String> params = new HashMap<String, String>();
        ArrayList<VideoPath> response = new ArrayList<VideoPath>();
        TypeToken<List<VideoPath>> typeToken = new TypeToken<List<VideoPath>>(){};
        params.put("dam_id", damId);
        params.put("encoding", encoding);
        params.put("book_id", bookId);
        params.put("chapter_id", chapterId);
        params.put("verse_id", verseId);
        new NetworkTask(endpoint, response, typeToken.getType(), params, callback).execute();
    }
}
