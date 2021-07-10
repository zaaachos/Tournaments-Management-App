package com.example.managetournamentapp.view.Organizer.OrganizerTournaments.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.view.Organizer.OrganizerTournaments.fragment.TournamentListFragment.OnListFragmentInteractionListener;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentListRecyclerViewAdapter extends RecyclerView.Adapter<TournamentListRecyclerViewAdapter.ViewHolder>  {

    private final ArrayList<Tournament> mValues;
    private final OnListFragmentInteractionListener mListener;

    /**
     * the constructor
     * @param items the list of tournaments
     * @param listener the listener for a tournament selection
     */
    public TournamentListRecyclerViewAdapter(ArrayList<Tournament> items, OnListFragmentInteractionListener listener){
        mValues = items;
        mListener = listener;
    }

    /**
     *
     * @param parent the view parent
     * @param viewType the view type
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tournaments_list_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     *
     * @param holder the holder
     * @param position the index of the item
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tournament currentTournament = mValues.get(position);
        holder.mItem = currentTournament;
        holder.txtTournamentTitle.setText(currentTournament.getTitle());

        holder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * get the number of tournaments in the list
     * @return the number of tournaments
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtTournamentTitle;
        public final ImageButton btnSelect;
        public Tournament mItem;

        /**
         *  constructor
         * @param view the view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtTournamentTitle = view.findViewById(R.id.txt_tournament_title);
            btnSelect = view.findViewById(R.id.btn_select_tournament);
        }

        /**
         * represents the basic info of the view holder as a string
         * @return the string representation of the view holder contents
         */
        @Override
        public String toString() {
            return super.toString() + " '" + txtTournamentTitle.getText() + "'";
        }
    }

}
