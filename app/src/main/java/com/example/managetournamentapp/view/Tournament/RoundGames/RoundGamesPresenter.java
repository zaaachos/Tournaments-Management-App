package com.example.managetournamentapp.view.Tournament.RoundGames;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Game;
import com.example.managetournamentapp.domain.Group;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Round;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.User;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RoundGamesPresenter {
    public RoundGamesView view;
    private TournamentDAO tournamentDAO;
    private Tournament tournament;
    private LoggedInUser loggedInUser;
    private ArrayList<Game> results = new ArrayList<>();
    private boolean hasAccess;
    private Round currentRound;
    private int currentRoundIndex;


    public RoundGamesPresenter() {
    }

    /**
     * find the games in this round
     *
     * @param tournamentTitle  the title of the tournament
     * @param roundTeamsNumber the number of teams in this tournament
     * @param specificGroup    is the index of the specific group  if we
     *                         don't want to search in every group of the round
     *                         or else it takes the value -1
     */
    public void findGames(String tournamentTitle, int roundTeamsNumber, int specificGroup) {
        if (tournamentTitle == null)
            return;
        tournament = tournamentDAO.find(tournamentTitle);
        if (tournament == null)
            return;

        findAccess();
        results.clear();

        currentRoundIndex = 0;
        for (Round round : tournament.getRounds()) {
            if (round.getTeamsNumber() == roundTeamsNumber) {
                currentRound = round;
                break;
            }
            currentRoundIndex++;
        }

        ArrayList<Group> groupsWanted = new ArrayList<>();
        if (specificGroup == -1)
            groupsWanted.addAll(currentRound.getGroups());
        else
            groupsWanted.add(currentRound.getGroups().get(specificGroup));

        for (Group group : groupsWanted) {
            results.addAll(group.getGames());
        }
    }


    /**
     * what happens when the user presses on a game
     *
     * @param game the game that was pressed on
     */
    public void onPressed(Game game) {
        if (hasAccess)
            if (game.isFinished())
                view.showToast("THE SCORE HAS ALREADY BEEN SET");
            else if (game.getTeamA().getName() == null || game.getTeamA().getName() == null)
                view.showToast("THE TEAMS HAVE NOT BEEN SET");
            else
                view.showPopup(game);
    }

    /**
     * what happens when the organizer presses "save"
     * on the popup
     *
     * @param game   the game that was set
     * @param scoreA the score of the first team
     * @param scoreB the score of the second teams
     */
    public void onSave(Game game, String scoreA, String scoreB) {
        if (scoreA == null || scoreA.isEmpty() || scoreB == null || scoreB.isEmpty())
            return;

        game.setScoreA(Integer.parseInt(scoreA));
        game.setScoreB(Integer.parseInt(scoreB));
        game.setFinished(true);
        checkIfFinished();
        view.recreateView();
    }

    /**
     * if the current round just finished then
     * promote the winners of this round
     * to the next round
     */
    public void checkIfFinished() {
        if (currentRound.allGamesFinished() && currentRoundIndex < tournament.getRounds().size() - 1) {
            tournament.getRounds().get(currentRoundIndex + 1).setup(currentRound.getRoundWinners());
        }
    }

    /**
     * find out if the logged in user
     * is the organizer of this tournament
     */
    public void findAccess() {
        hasAccess = false;
        if (loggedInUser.getUser() != null)
            if (loggedInUser.getUser() instanceof Organizer)
                if (((Organizer) loggedInUser.getUser()).getTournaments().contains(tournament))
                    hasAccess = true;
    }

    /**
     * get the games of this round
     *
     * @return the ArrayList of games in this round
     */
    public ArrayList<Game> getResults() {
        return results;
    }

    /**
     * set the loggedInUser
     *
     * @param loggedInUser the new LoggedInUser
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * set the tournamentDAO
     *
     * @param tournamentDAO the new TournamentDAO
     */
    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }

    /**
     * set a new view
     *
     * @param view the new view
     */
    public void setView(RoundGamesView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage(){
        User user = loggedInUser.getUser();
        if (user == null){
            view.backToHomePage(true, false,"");
        }
        else if (user instanceof Player){
            view.backToHomePage(false,true,user.getCredentials().getUsername());
        }
        else{
            view.backToHomePage(false,false,((Organizer)user).getTitle());
        }
    }
}
