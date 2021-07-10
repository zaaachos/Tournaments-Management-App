package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class SportTest {
    private Sport sport1;
    private Sport sport2;
    private Sport sport3;
    private Sport sport4;
    private Sport sport5;
    private Sport sport6;
    private Sport sport7;
    private Sport sport8;
    private Sport sport9;
    private Tournament tournament;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        sport1 = new Sport("Basketball3v3");
        sport2 = new Sport("Football5v5");
        sport3 = sport1;
        sport4 = new Sport("Basketball3v3");
        sport5 = new Sport("Football7v7");
        sport6 = new Sport("Volleyball6v6");
        sport7 = new Sport("Basketball2v2");
        //wrong name
        sport8 = new Sport("Footbal5x5");
        tournament = new Tournament();
    }


    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals(){
          Assert.assertEquals(sport3, sport1);
          Assert.assertEquals(sport1,sport4);
    }


    /**
     * test the inequality between to instances
     */
    @Test
    public void testNotEquals(){
          Assert.assertNotEquals(sport1,sport5);
          Assert.assertNotEquals(sport2,sport5);
          Assert.assertNotEquals(sport6,sport7);
          Assert.assertNotEquals(sport1,sport9);
          Assert.assertNotEquals(sport1,tournament);
    }


    /**
     * test the name getter
     */
    @Test
    public void getNameTest(){
          Assert.assertNull(sport8.getName());
          Assert.assertEquals("Basketball3v3",sport3.getName());
          Assert.assertEquals("Volleyball6v6",sport6.getName());
    }


    /**
     * test the min players getter
     */
    @Test
    public void getMinimumPlayersTest(){
        Assert.assertEquals(6,sport1.getMinimumPlayers());
        Assert.assertEquals(10,sport2.getMinimumPlayers());
        Assert.assertEquals(12,sport6.getMinimumPlayers());
    }


    /**
     * test changing the sport type
     */
    @Test
    public void changeSetupTest(){
        sport1.changeSetup("Basketball5v5");
        Assert.assertEquals("Basketball5v5",sport1.getName());
    }

    /**
     * test the to string method
     */
    @Test
    public void toStringTest(){
        Assert.assertEquals(sport1.toString(),sport4.toString());
    }




}