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

package de.badaix.snapcast.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import de.badaix.snapcast.Listeners.ItemListListenner;
import de.badaix.snapcast.Models.MediaModel;
import de.badaix.snapcast.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private Context context;
    private List<MediaModel> elements;
    private ItemListListenner mListener;
    private boolean isForcused = false;
    private int contextType = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textChannel;
        TextView textInfo;
        TextView textDuration;

        ImageView imgThumbnail;

        public ViewHolder(View view) {
            super(view);

            textName = view.findViewById(R.id.txt_media_title);
            textChannel = view.findViewById(R.id.txt_media_channel);
            textInfo = view.findViewById(R.id.txt_media_info);
            textDuration = view.findViewById(R.id.txt_media_duration);
            
            imgThumbnail = view.findViewById(R.id.img_media_thumbnail);
        }

    }
}
