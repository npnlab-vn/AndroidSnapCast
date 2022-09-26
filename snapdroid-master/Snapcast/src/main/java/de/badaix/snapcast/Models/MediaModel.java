/*
 *     This file is part of snapcast
 *     Copyright (C) 2014-2021  Johannes Pohl
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.badaix.snapcast.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class MediaModel {
    @SerializedName("id")
    private String videoId = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("url")
    private String url = "";

    @SerializedName("video_logo")
    private String videoLogo = "";


    @SerializedName("channel_avatar_url")
    private String channel_avatar_url = "";

    @SerializedName("channel_subscriber")
    private String channel_subscriber = "";

    @SerializedName("duration")
    private String duration = "";

    @SerializedName("post_date")
    private String post_date = "";

    @SerializedName("views")
    private String views = "";

    @SerializedName("channel_name")
    private String channel_name = "";
    //channel_name


    public Bitmap bmp;

    private MediaModel.Type mType;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoLogo() {
        return videoLogo;
    }

    public void setVideoLogo(String videoLogo) {
        this.videoLogo = videoLogo;
    }

    public Type getType() {
        return mType;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getChannel_avatar_url() {
        return channel_avatar_url;
    }

    public void setChannel_avatar_url(String channel_avatar_url) {
        this.channel_avatar_url = channel_avatar_url;
    }

    public String getChannel_subscriber() {
        return channel_subscriber;
    }

    public void setChannel_subscriber(String channel_subscriber) {
        this.channel_subscriber = channel_subscriber;
    }

    public enum Type {
        VIDEO_ITEM

    }

}
