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

package de.badaix.snapcast.MVVM.VM;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import de.badaix.snapcast.Listeners.SearchViewListener;
import de.badaix.snapcast.MVVM.MV.ISearchView;
import de.badaix.snapcast.Models.MediaModel;
import de.badaix.snapcast.Network.ApiResponseListener;

public class SearchViewModel extends BaseViewModel<ISearchView>{
    public static final String TAG = "SNAPSEARCH";
    public void requestSearchByKeyword(String keywords, SearchViewListener listener) {

        String encodeQuery;
        try {
            encodeQuery = URLEncoder.encode(keywords, "utf-8");
        } catch (UnsupportedEncodingException e) {
            encodeQuery = "";
        }

        String url = "https://www.youtube.com/results?search_query=" + encodeQuery;

        List<MediaModel> mVideosList = new ArrayList<>();

        requestGETWithURL(url, new ApiResponseListener<String>() {
            @Override
            public void onSuccess(String response) {

                String[] splitLines = response.split(Pattern.quote("\n"));
                String relevant_string = "";
                for (int i = 0; i < splitLines.length; i++) {
                    if (splitLines[i].indexOf("ytInitialData") >= 0) {
                        relevant_string = splitLines[i];
                        break;
                    }
                }

                int ytInitDataIndex = relevant_string.indexOf("ytInitialData");
                relevant_string = relevant_string.substring(ytInitDataIndex);

                int index_response = 0;
                if (relevant_string.length() > 0) {
                    for (int i = 0; i < 100; i++) {
                        if (relevant_string.charAt(i) == '{') {
                            index_response = i;
                            break;
                        }
                    }
                }
                String jsonString = "";
                if (index_response > 0) {
                    jsonString = relevant_string.substring(index_response, relevant_string.length() - 1);
                }

                if (jsonString.length() > 100) {
                    try {
                        JSONObject json = new JSONObject(jsonString);

                        JSONObject content = json.getJSONObject("contents")
                                .getJSONObject("twoColumnSearchResultsRenderer")
                                .getJSONObject("primaryContents")
                                .getJSONObject("sectionListRenderer");//

                        JSONArray data = content.getJSONArray("contents");

                        JSONObject objContent = data.getJSONObject(0);
                        JSONObject itemSelectionRender = objContent.getJSONObject("itemSectionRenderer");
                        JSONArray contentsArray = itemSelectionRender.getJSONArray("contents");
                        Log.d(TAG, "Device is decoding..");
                        for (int i = 0; i < contentsArray.length(); i++) {

                            JSONObject aVideo = contentsArray.getJSONObject(i);

                            if (aVideo.has("videoRenderer")) {

                                try {


                                    JSONArray tiltleRun = aVideo.getJSONObject("videoRenderer").getJSONObject("title").getJSONArray("runs");
                                    String title = tiltleRun.getJSONObject(0).getString("text");


                                    String id = aVideo.getJSONObject("videoRenderer").getString("videoId");

                                    MediaModel aMedia = new MediaModel();

                                    aMedia.setName(title);
                                    aMedia.setVideoId(id);
                                    aMedia.setVideoLogo("https://i.ytimg.com/vi/" + id + "/mqdefault.jpg");
                                    aMedia.setUrl("https://www.youtube.com/watch?v=" + id);

//                                aMedia.setChannel_name(getToken(aVideo.toString(), tokenChannelName, "\""));
//                                aMedia.setViews(getToken(aVideo.toString(), tokenViewCount, "\""));
//                                aMedia.setPost_date(getToken(aVideo.toString(), tokenPostedDate, "\""));
//
//                                String durationText = getToken(aVideo.toString(), tokenDuration, "\"DEFAULT\"}}");
//
//                                //Log.d("BSTEST", "durationText: " + durationText);
//
//                                aMedia.setDuration(getToken(durationText, "\"simpleText\":\"", "\""));

                                    mVideosList.add(aMedia);

                                    if (aVideo.getJSONObject("videoRenderer").has("shortBylineText")) {
                                        String channel_name = aVideo.getJSONObject("videoRenderer")
                                                .getJSONObject("shortBylineText")
                                                .getJSONArray("runs").getJSONObject(0)
                                                .getString("text");

                                        aMedia.setChannel_name(channel_name);
                                    }


                                    String mPostedDate = aVideo.getJSONObject("videoRenderer")
                                            .getJSONObject("publishedTimeText").getString("simpleText");

                                    aMedia.setPost_date(mPostedDate);


                                    String mDuration = aVideo.getJSONObject("videoRenderer")
                                            .getJSONArray("thumbnailOverlays").getJSONObject(0)
                                            .getJSONObject("thumbnailOverlayTimeStatusRenderer")
                                            .getJSONObject("text")
                                            .getString("simpleText");

                                    aMedia.setDuration(mDuration);

                                    if (aVideo.getJSONObject("videoRenderer").getJSONObject("shortViewCountText").has("simpleText")) {
                                        String mViewCount = aVideo.getJSONObject("videoRenderer")
                                                .getJSONObject("shortViewCountText").getString("simpleText");
                                        aMedia.setViews(mViewCount);
                                    } else if (aVideo.getJSONObject("videoRenderer").getJSONObject("shortViewCountText").has("runs")) {
                                        String mViewCount = aVideo.getJSONObject("videoRenderer")
                                                .getJSONObject("shortViewCountText")
                                                .getJSONArray("runs").getJSONObject(0)
                                                .getString("text");
                                        aMedia.setViews(mViewCount + " Đang xem");
                                    }

                                }catch (Exception e){}
                            }
                        }

                        if (view != null) {
                            view.onResponseSearchByKeyWord(mVideosList);
                        }
                        if (listener != null) {
                            listener.onResponseSearchByKeyWord(mVideosList);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Exception: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }
}
