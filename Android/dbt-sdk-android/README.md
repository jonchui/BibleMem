# dbt-sdk

## Installation

dbt-sdk for now is available as a library project to be included in your projects.  To download the project, run from a terminal:

```
git clone git@bitbucket.org:faithcomesbyhearing/dbt-sdk-android.git
```

## Usage

Below are the steps necessary to get you started with the DBT SDK.

#### 1. Sign up for an API Key

Before you can start using the DBT SDK, you will need to sign up for an API Key at [https://www.digitalbibleplatform.com](https://www.digitalbibleplatform.com).  

#### 2. Use Gradle or import library project into your current projects workspace.

Add the following line to your build.gradle file

```
compile 'com.faithcomesbyhearing:dbt:1.0.1'
```

Or to use as a library project in Android Studio, with your project open click file -> Import Module.  Point the dialog to where you downloaded the SDK to.

In your app level build.gradle within the dependencies tag include the following:

```
compile project(':dbt')
```

#### 3. Set your API key

Create an extended Application class within your project and in the onCreate method, add the following code. Make sure to replace API_KEY with the API key you were assigned for DBT.

```
Dbt.setApiKey("API_KEY");
```

#### 4. Start calling methods

There are quite a few methods available, however there is a common flow that most applications will use in order to fetch the content they need.  Here are the steps:

1. Get the volume listing
2. Get the books for a given volume
3. Get the chapters for a given book
4. Get the verses for a given chapter

Here is a sample flow to get you started:

## Volume Listing

This method lists all of the available volumes for the given parameters.  The media parameter can either be "text", "audio", or "video" depending on what type of content you are interested in retrieving. 

```
Dbt.getLibraryVolume(null, "text", null, "Eng", new VolumeCallback() {
    @Override
    public void success(List<Volume> volumes) {

    }

    @Override
    public void failure(Exception e) {

    }
});
```

This will return an array of DBTVolume objects.  These volume objects have one important field that will be used on all subsequent calls. It's called **damId** which is used to identify which volume you are using.

## Books Listing

Once you have a damId, you can then use it to fetch the array of DBTBook objects.

```
Dbt.getLibraryBook("EN1NIVN1ET", new BookCallback() {
    @Override
    public void success(List<Book> books) {

    }

    @Override
    public void failure(Exception e) {

    }
});
```

## Chapter Listing

The chapters can be parsed out from the Book object's chapter string, however if you want/need to localize them, you will want to do an additional API call.  Here is an example of retrieving the chapter listing for Genesis.

```
Dbt.getLibraryChapter("EN1NIVN1ET", "Gen", new ChapterCallback() {
    @Override
    public void success(List<Chapter> chapters) {

    }

    @Override
    public void failure(Exception e) {

    }
});
```

## Verse Listing

Finally, you will need to get the verses for a given chapter. This method can be used to get a single verse, or any number of verses. Note: passing nil for both verseStart and verseEnd will retrieve every verse.

```
Dbt.getTextVerse("EN1NIVN1ET", "John", "3", null, null, new VerseCallback() {
    @Override
    public void success(List<Verse> verses) {

    }

    @Override
    public void failure(Exception e) {

    }
});
``` 

#### Audio Playback

If you selected a volume that supports audio (by setting media to audio in the getLibraryVolume call), then there are a few things you need to know in order to play back the audio.  Two objects are required in order to build the endpoint for audio playback.  They are a AudioLocation object and a AudioPath object.   Below is a sample set of nested API calls that will generate a full audio path for you.

```
Dbt.getAudioLocation(new AudioLocationCallback() {
    @Override
    public void success(List<AudioLocation> audioLocations) {
        if (audioLocations != null && !audioLocations.isEmpty()) {
            final AudioLocation audioLocation = audioLocations.get(0);
            Dbt.getAudioPath("ENGCEVO2DA", "Gen", "1", new AudioPathCallback() {
                @Override
                public void success(List<AudioPath> audioPaths) {
                    if (audioPaths != null && !audioPaths.isEmpty()) {
                        AudioPath path = audioPaths.get(0);
                        StringBuilder builder = new StringBuilder(audioLocation.getBaseUrl());
                        builder.append("/");
                        builder.append(path.getPath());
                        String audioUrl = builder.toString();
                    } 
                }

                @Override
                public void failure(Exception e) {

                }
            });
        }
    }

    @Override
    public void failure(Exception e) {

    }
});
```				

Once you have the url to the audio file, it can be played using the standard iOS audio playback libraries. 

## Author

Brandon Baker, brandon@brandbaker.org

## License

dbt-sdk is available under the MIT license. See the LICENSE file for more info.