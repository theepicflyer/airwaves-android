package io.makerforce.airwaves;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jiacheng on 10/15/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView artistTextView;
        public TextView albumTextView;
        public TextView timeTextView;
        public View view;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: On click listener
                }
            });

            nameTextView = (TextView) itemView.findViewById(R.id.song_name);
            artistTextView = (TextView) itemView.findViewById(R.id.song_artist);
            albumTextView = (TextView) itemView.findViewById(R.id.song_album);
            timeTextView = (TextView) itemView.findViewById(R.id.song_time);
        }
    }

    private static List<Song> mSongs;
    // Store the context for easy access
    private Context mContext;

    public SongAdapter(Context context, List<Song> songList) {
        mContext = context;
        mSongs = songList;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View rowView = inflater.inflate(R.layout.row_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        viewHolder.nameTextView.setText(mSongs.get(position).getName());
        viewHolder.artistTextView.setText(mSongs.get(position).getArtist());
        viewHolder.albumTextView.setText(mSongs.get(position).getAlbum());
        viewHolder.timeTextView.setText(mSongs.get(position).getTime());


        // Set item views based on your views and data model
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }
}
