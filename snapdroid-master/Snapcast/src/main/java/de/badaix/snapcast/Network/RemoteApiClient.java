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

package de.badaix.snapcast.Network;

import java.io.File;
import java.util.Map;

public interface RemoteApiClient {
    void get(String requestUrl, ApiResponseListener<String> apiResponseListener);

    void get(String requestUrl, Map<String, String> header, ApiResponseListener<String> apiResponseListener);

    void post(String requestUrl, String params, ApiResponseListener<String> apiResponseListener);

    void multipartRequest(String requestUrl, File file, ApiResponseListener<String> apiResponseListener);

    void put(String requestUrl, ApiResponseListener<String> apiResponseListener);

    void put(String requestUrl, String params, ApiResponseListener<String> apiResponseListener);

    void post(String requestUrl, ApiResponseListener<String> apiResponseListener);

    void delete(String requestUrl, ApiResponseListener<String> apiResponseListener);

    void post(String requestUrl, String params, Map<String, String> header, ApiResponseListener<String> apiResponseListener);

}
