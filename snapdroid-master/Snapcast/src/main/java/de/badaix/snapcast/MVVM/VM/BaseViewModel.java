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

import android.content.Context;

import de.badaix.snapcast.MVVM.MV.IView;

import java.util.HashMap;
import java.util.Map;

import de.badaix.snapcast.Network.ApiResponseListener;
import de.badaix.snapcast.Network.VolleyRemoteApiClient;


public class BaseViewModel <T extends IView> {
    T view;
    protected Context mContext;

    public static final String apiHeaderKey = "User-Agent";
    public static final String apiHeaderValue = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36 NPNLab";

    BaseViewModel() {
    }

    BaseViewModel(Context context) {
        mContext = context;
    }

    public void attach(T view, Context context) {
        this.view = view;
        this.mContext = context;
    }

    public void detach() {
        view = null;
    }

    protected void requestGETWithURL(String url, ApiResponseListener<String> listener) {
        VolleyRemoteApiClient.createInstance(mContext);
        Map<String, String> header = new HashMap<>();
        header.put(apiHeaderKey, apiHeaderValue);
        VolleyRemoteApiClient.getInstance().get(url, header, listener);
    }

    protected void requestPOSTWithURL(String url, String params, ApiResponseListener<String> listener) {
        VolleyRemoteApiClient.createInstance(mContext);
        Map<String, String> header = new HashMap<>();
        header.put(apiHeaderKey, apiHeaderValue);
        header.put("Content-Type", "application/x-www-form-urlencoded");
        VolleyRemoteApiClient.getInstance().post(url, params, header, listener);
    }
}
