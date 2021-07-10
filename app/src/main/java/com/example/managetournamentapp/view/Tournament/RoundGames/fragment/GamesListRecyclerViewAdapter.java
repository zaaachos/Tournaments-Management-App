package com.example.managetournamentapp.view.Tournament.RoundGames.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.domain.Game;
import com.example.managetournamentapp.view.Tournament.RoundGames.fragment.GamesListFragment.OnListFragmentInteractionListener;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GamesListRecyclerViewAdapter extends RecyclerView.Adapter<GamesListRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<Game> mValues;
    private final OnListFragmentInteractionListener mListener;


    /**
     * the constructor
     * @param items the list of tournaments
     * @param listener the listener for a tournament selection
     */
    public GamesListRecyclerViewAdapter(ArrayList<Game> items, OnListFragmentInteractionListener listener) {
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
                .inflate(R.layout.fragment_games_list_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     *
     * @param holder the holder
     * @param position the index of the item
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game currentGame = mValues.get(position);
        holder.mItem = currentGame;
        if (currentGame.getTeamA().getName() != null) {
            holder.txtTeamA.setText(currentGame.getTeamA().getName());
            holder.txtTeamB.setText(currentGame.getTeamB().getName());
        } else {
            holder.txtTeamA.setText("To Be Announced");
            holder.txtTeamB.setText("To Be Announced");
        }

        if (currentGame.isFinished()){
            holder.scoreA.setText(String.valueOf(currentGame.getScoreA()));
            holder.scoreB.setText(String.valueOf(currentGame.getScoreB()));
        }else{
            holder.scoreA.setText(" ");
            holder.scoreB.setText(" ");
        }

        holder.date.setText(currentGame.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-","/") );

        holder.btnSelect.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });

    }

    /**
     * get the number of games in the list
     * @return the number of games
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtTeamA;
        public final TextView txtTeamB;
        public final TextView scoreA;
        public final TextView scoreB;
        public final TextView date;
        public final LinearLayout btnSelect;
        public Game mItem;

        /**
         *  constructor
         * @param view the view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtTeamA = view.findViewById(R.id.txt_teamA);
            txtTeamB = view.findViewById(R.id.txt_teamB);
            scoreA = view.findViewById(R.id.scoreA);
            scoreB = view.findViewById(R.id.scoreB);
            date = view.findViewById(R.id.txt_date);
            btnSelect = view.findViewById(R.id.game_selected_btn);

        }

        /**
         * represents the basic info of the view holder as a string
         * @return the string representation of the view holder contents
         */
        @Override
        public String toString() {
            return super.toString() + " '" + txtTeamA.getText() + "'" + txtTeamB.getText();
        }
    }


}
