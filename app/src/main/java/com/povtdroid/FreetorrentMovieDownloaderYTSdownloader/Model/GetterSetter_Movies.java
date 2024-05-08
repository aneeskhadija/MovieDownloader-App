package com.povtdroid.FreetorrentMovieDownloaderYTSdownloader.Model;

public class GetterSetter_Movies {

    public String  status, status_message, url, imdb_code, title, title_english, title_long, slug, summary, Action, Adventure, Animation, Crime, Family, Mystery, description_full, synopsis, yt_trailer_code, language, mpa_rating, background_image, background_image_original, small_cover_image, medium_cover_image, large_cover_image, state, hash, quality, type, size, date_uploaded, server_timezone, execution_time;
    public int id, year, rating, runtime, movie_count, limit, page_number, seeds, peers, size_bytes, date_uploaded_unix, server_time, api_version;

    /*public GetterSetter_Movies(String status, String status_message, String url, String imdb_code, String title, String title_english, String title_long, String slug, String summary, String action, String adventure, String animation, String crime, String family, String mystery, String description_full, String synopsis, String yt_trailer_code, String language, String mpa_rating, String background_image, String background_image_original, String small_cover_image, String medium_cover_image, String large_cover_image, String state, String hash, String quality, String type, String size, String date_uploaded, String server_timezone, String execution_time, int id, int year, int rating, int runtime, int movie_count, int limit, int page_number, int seeds, int peers, int size_bytes, int date_uploaded_unix, int server_time, int api_version) {
        this.status = status;
        this.status_message = status_message;
        this.url = url;
        this.imdb_code = imdb_code;
        this.title = title;
        this.title_english = title_english;
        this.title_long = title_long;
        this.slug = slug;
        this.summary = summary;
        Action = action;
        Adventure = adventure;
        Animation = animation;
        Crime = crime;
        Family = family;
        Mystery = mystery;
        this.description_full = description_full;
        this.synopsis = synopsis;
        this.yt_trailer_code = yt_trailer_code;
        this.language = language;
        this.mpa_rating = mpa_rating;
        this.background_image = background_image;
        this.background_image_original = background_image_original;
        this.small_cover_image = small_cover_image;
        this.medium_cover_image = medium_cover_image;
        this.large_cover_image = large_cover_image;
        this.state = state;
        this.hash = hash;
        this.quality = quality;
        this.type = type;
        this.size = size;
        this.date_uploaded = date_uploaded;
        this.server_timezone = server_timezone;
        this.execution_time = execution_time;
        this.id = id;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
        this.movie_count = movie_count;
        this.limit = limit;
        this.page_number = page_number;
        this.seeds = seeds;
        this.peers = peers;
        this.size_bytes = size_bytes;
        this.date_uploaded_unix = date_uploaded_unix;
        this.server_time = server_time;
        this.api_version = api_version;
    }
*/
    public GetterSetter_Movies(int int_id, String str_urll, String str_imdb_code, String str_title, String str_title_english, String str_medium_cover_image, int int_year, int int_movie_count, int int_limit, String str_synopsis, String str_data_andTime, int int_rating, String str_background_image,
    String str_urlT, String str_hashT, String str_qualityT, String str_typeT, int int_seedsT, int int_peersT, String str_sizeT, int int_size_bytesT, String str_date_uploadedT,
                               int int_date_uploaded_unixT) {
        this.id = int_id;
        this.url = str_urll;
        this.url = str_urlT;
        this.imdb_code = str_imdb_code;
        this.title = str_title;
        this.title_english = str_title_english;
        this.medium_cover_image = str_medium_cover_image;
        this.year = int_year;
        this.movie_count = int_movie_count;
        this.limit = int_limit;
        this.synopsis = str_synopsis;
        this.date_uploaded = str_data_andTime;
        this.rating = int_rating;
        this.background_image = str_background_image;
        this.hash = str_hashT;
        this.quality = str_qualityT;
        this.type = str_typeT;
        this.size = str_sizeT;
        this.seeds = int_seedsT;
        this.peers = int_peersT;
        this.size_bytes = int_size_bytesT;
        this.date_uploaded = str_date_uploadedT;
        this.date_uploaded_unix = int_date_uploaded_unixT;
    }

    public GetterSetter_Movies(int int_id, String strUrlT, String strHashT, String strQualityT, String strTypeT, int int_seedsT, int int_peersT, String strSizeT, int int_size_bytesT, String strDateUploadedT, int int_date_uploaded_unixT) {

        this.id = int_id;
        this.url = strUrlT;
        this.hash = strHashT;
        this.quality = strQualityT;
        this.type = strTypeT;
        this.seeds = int_seedsT;
        this.peers = int_peersT;
        this.size = strSizeT;
        this.size_bytes = int_size_bytesT;
        this.date_uploaded = strDateUploadedT;
        this.date_uploaded_unix = int_date_uploaded_unixT;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImdb_code() {
        return imdb_code;
    }

    public void setImdb_code(String imdb_code) {
        this.imdb_code = imdb_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_english() {
        return title_english;
    }

    public void setTitle_english(String title_english) {
        this.title_english = title_english;
    }

    public String getTitle_long() {
        return title_long;
    }

    public void setTitle_long(String title_long) {
        this.title_long = title_long;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getAdventure() {
        return Adventure;
    }

    public void setAdventure(String adventure) {
        Adventure = adventure;
    }

    public String getAnimation() {
        return Animation;
    }

    public void setAnimation(String animation) {
        Animation = animation;
    }

    public String getCrime() {
        return Crime;
    }

    public void setCrime(String crime) {
        Crime = crime;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getMystery() {
        return Mystery;
    }

    public void setMystery(String mystery) {
        Mystery = mystery;
    }

    public String getDescription_full() {
        return description_full;
    }

    public void setDescription_full(String description_full) {
        this.description_full = description_full;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getYt_trailer_code() {
        return yt_trailer_code;
    }

    public void setYt_trailer_code(String yt_trailer_code) {
        this.yt_trailer_code = yt_trailer_code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public void setMpa_rating(String mpa_rating) {
        this.mpa_rating = mpa_rating;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getBackground_image_original() {
        return background_image_original;
    }

    public void setBackground_image_original(String background_image_original) {
        this.background_image_original = background_image_original;
    }

    public String getSmall_cover_image() {
        return small_cover_image;
    }

    public void setSmall_cover_image(String small_cover_image) {
        this.small_cover_image = small_cover_image;
    }

    public String getMedium_cover_image() {
        return medium_cover_image;
    }

    public void setMedium_cover_image(String medium_cover_image) {
        this.medium_cover_image = medium_cover_image;
    }

    public String getLarge_cover_image() {
        return large_cover_image;
    }

    public void setLarge_cover_image(String large_cover_image) {
        this.large_cover_image = large_cover_image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate_uploaded() {
        return date_uploaded;
    }

    public void setDate_uploaded(String date_uploaded) {
        this.date_uploaded = date_uploaded;
    }

    public String getServer_timezone() {
        return server_timezone;
    }

    public void setServer_timezone(String server_timezone) {
        this.server_timezone = server_timezone;
    }

    public String getExecution_time() {
        return execution_time;
    }

    public void setExecution_time(String execution_time) {
        this.execution_time = execution_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getMovie_count() {
        return movie_count;
    }

    public void setMovie_count(int movie_count) {
        this.movie_count = movie_count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    public int getSize_bytes() {
        return size_bytes;
    }

    public void setSize_bytes(int size_bytes) {
        this.size_bytes = size_bytes;
    }

    public int getDate_uploaded_unix() {
        return date_uploaded_unix;
    }

    public void setDate_uploaded_unix(int date_uploaded_unix) {
        this.date_uploaded_unix = date_uploaded_unix;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public int getApi_version() {
        return api_version;
    }

    public void setApi_version(int api_version) {
        this.api_version = api_version;
    }
}
