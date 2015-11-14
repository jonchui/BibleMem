package com.faithcomesbyhearing.dbt.model;

import com.google.gson.annotations.SerializedName;

/*
segment_order: Order of the segment in the volume.
title: Segment title. (Not included for DOOR International videos.)
book_id: OSIS book code of book to which segment belongs, which is only applicable to standard Bible video volumes.
path: relative file path for the video file. For DOOR International videos, this path refers to the "story" portion of the video.
chapter_start: Chapter in which segment starts, if standard Bible volume.
verse_start: Verse in which segment starts, if standard Bible volume.
chapter_end: Chapter in which segment ends, if standard Bible volume.
verse_end: Verse in which segment ends, if standard Bible volume.
references: Array of verse references to which the video segment applies if a story volume.
related_videos: Array containing paths to related video segments. Used only for DOOR International videos. Each element contains the following:
video_type: Indicates what type of related video it is. Can be "Intro", "Topic", or "More Info".
path: Relative path for the video file.
thumbnail_image: The file name for the thumbnail image that represents the video. Used only for DOOR International videos. The base URL for thumbnails is currently: http://cloud.faithcomesbyhearing.com/segment-art/700X510/
 */
public class VideoPath {
    @SerializedName("segment_order")
    private String segmentOrder;
    private String title;
    @SerializedName("book_id")
    private String bookId;
    private String path;
    @SerializedName("chapter_start")
    private String chapterStart;
    @SerializedName("verse_start")
    private String verseStart;
    @SerializedName("chapter_end")
    private String chapterEnd;
    @SerializedName("verse_end")
    private String verseEnd;
    private String references;
//    @SerializedName("related_videos")
//    private String relatedVideos;
    @SerializedName("video_type")
    private String videoType;
    @SerializedName("thumbnail_image")
    private String thumbnailImage;

    public String getSegmentOrder() {
        return segmentOrder;
    }

    public void setSegmentOrder(String segmentOrder) {
        this.segmentOrder = segmentOrder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChapterStart() {
        return chapterStart;
    }

    public void setChapterStart(String chapterStart) {
        this.chapterStart = chapterStart;
    }

    public String getVerseStart() {
        return verseStart;
    }

    public void setVerseStart(String verseStart) {
        this.verseStart = verseStart;
    }

    public String getChapterEnd() {
        return chapterEnd;
    }

    public void setChapterEnd(String chapterEnd) {
        this.chapterEnd = chapterEnd;
    }

    public String getVerseEnd() {
        return verseEnd;
    }

    public void setVerseEnd(String verseEnd) {
        this.verseEnd = verseEnd;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

//    public String getRelatedVideos() {
//        return relatedVideos;
//    }
//
//    public void setRelatedVideos(String relatedVideos) {
//        this.relatedVideos = relatedVideos;
//    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }
}
