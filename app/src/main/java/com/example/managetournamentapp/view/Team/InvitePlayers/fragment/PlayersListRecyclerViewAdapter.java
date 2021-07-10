package com.example.managetournamentapp.view.Team.InvitePlayers.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.view.Team.InvitePlayers.fragment.PlayersListFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayersListRecyclerViewAdapter extends RecyclerView.Adapter<PlayersListRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Player> mValues;
    private final OnListFragmentInteractionListener mListener;


    /**
     * the constructor
     * @param items the list of tournaments
     * @param listener the listener for a tournament selection
     */
    public PlayersListRecyclerViewAdapter(ArrayList<Player> items, OnListFragmentInteractionListener listener) {
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
    public PlayersListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_players_list_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * @param holder the holder
     * @param position the index of the item
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Player currentPlayer = mValues.get(position);
        holder.mItem = currentPlayer;
        holder.txtPlayerName.setText(currentPlayer.getName());
        holder.txtPlayerSurname.setText(currentPlayer.getSurname());
        holder.txtPlayerDOB.setText(String.valueOf(currentPlayer.getBirthDate().getYear()));
        holder.txtPlayerArea.setText(currentPlayer.getLocation());
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
     * get the number of players in the list
     * @return the number of players
     */
    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtPlayerName;
        public final TextView txtPlayerSurname;
        public final TextView txtPlayerDOB;
        public final TextView txtPlayerArea;
        public final ImageButton btnSelect;
        public Player mItem;
        boolean run = true;

        /**
         *  constructor
         * @param view the view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtPlayerName = view.findViewById(R.id.txt_player_name);
            txtPlayerSurname = view.findViewById(R.id.txt_player_surname);
            txtPlayerDOB = view.findViewById(R.id.txt_player_DOB);
            txtPlayerArea = view.findViewById(R.id.txt_player_area);
            btnSelect = view.findViewById(R.id.btn_select_player);


            if(!run){
                btnSelect.setVisibility(View.GONE);
            }
        }
        /**
         * represents the basic info of the view holder as a string
         * @return the string representation of the view holder contents
         */
        @Override
        public String toString() {
            return super.toString() + " '" + txtPlayerName.getText() + "'";
        }
    }


}
