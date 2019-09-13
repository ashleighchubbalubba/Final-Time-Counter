//Ashleigh Chung
import org.junit.Test;

import static org.junit.Assert.*;

public class timeTest {

    @Test
    public void testConstructor1() {
        time time = new time();

        assertEquals(12, time.getHour());
        assertEquals(0, time.getMinute());
        assertEquals(0, time.getSecond());
        assertEquals(false, time.isMilitary());
    }

    @Test
    public void testConstructor2() {
        time t = new time(1, 2, 3, true);
        assertEquals(1, t.getHour());
        assertEquals(2, t.getMinute());
        assertEquals(3, t.getSecond());
        assertEquals(true, t.isMilitary());
        time t1 = new time(23, 59, 59, false);
        assertEquals(23, t1.getHour());
        assertEquals(59, t1.getMinute());
        assertEquals(59, t1.getSecond());
        assertEquals(false, t1.isMilitary());
    }

    @Test
    public void testBadInitialInput() {
        //testing invalid initial input in constructors
        time time1 = new time(45, 0, 0, false);  //hour cannot be over 24
        time time2 = new time(1, -6, 0, false);  //minute cannot be under 0
        time time3 = new time(1, 0, 60, false);  //second cannot be over 59
        time time4 = new time(30, 244, 90, false);  //hour, minute, and seconds are invalid

        assertEquals("01:00:00", time1.toString());
        assertEquals("01:00:00", time2.toString());
        assertEquals("01:00:00", time3.toString());
        assertEquals("01:00:00", time4.toString());
    }

    @Test
    public void testIncrementsGreaterThanOne() {
        time timeA = new time();                              //Standard Time timeA is 12:00:00
        time timeB = new time(24, 0, 0, true);   //Military Time timeB is 24:00:00

        //testing minute increments/decrements greater than one for Standard Time
        timeA.changeMinutes(5);
        assertEquals("12:05:00", timeA.toString());
        timeA.changeMinutes(30);
        assertEquals("12:35:00", timeA.toString());
        timeA.changeMinutes(120);
        assertEquals("02:35:00", timeA.toString());
        timeA.changeMinutes(-7);
        assertEquals("02:28:00", timeA.toString());
        timeA.changeMinutes(-60);
        assertEquals("01:28:00", timeA.toString());
        timeA.changeMinutes(-130);
        assertEquals("11:18:00", timeA.toString());
        timeA.changeMinutes(42);
        assertEquals("12:00:00", timeA.toString());

        //testing minute increments/decrements greater than one for Military Time
        timeB.changeMinutes(5);
        assertEquals("24:05:00", timeB.toString());
        timeB.changeMinutes(30);
        assertEquals("24:35:00", timeB.toString());
        timeB.changeMinutes(120);
        assertEquals("02:35:00", timeB.toString());
        timeB.changeMinutes(-7);
        assertEquals("02:28:00", timeB.toString());
        timeB.changeMinutes(-60);
        assertEquals("01:28:00", timeB.toString());
        timeB.changeMinutes(-130);
        assertEquals("23:18:00", timeB.toString());
        timeB.changeMinutes(42);
        assertEquals("24:00:00", timeB.toString());

        //testing second increments/decrements greater than one for Standard Time
        timeA.changeSeconds(2);
        assertEquals("12:00:02", timeA.toString());
        timeA.changeSeconds(30);
        assertEquals("12:00:32", timeA.toString());
        timeA.changeSeconds(90);
        assertEquals("12:02:02", timeA.toString());
        timeA.changeSeconds(-5);
        assertEquals("12:01:57", timeA.toString());
        timeA.changeSeconds(-20);
        assertEquals("12:01:37", timeA.toString());
        timeA.changeSeconds(-120);
        assertEquals("11:59:37", timeA.toString());

        //testing second increments/decrements greater than one for Military Time
        timeB.changeSeconds(2);
        assertEquals("24:00:02", timeB.toString());
        timeB.changeSeconds(30);
        assertEquals("24:00:32", timeB.toString());
        timeB.changeSeconds(90);
        assertEquals("24:02:02", timeB.toString());
        timeB.changeSeconds(-5);
        assertEquals("24:01:57", timeB.toString());
        timeB.changeSeconds(-20);
        assertEquals("24:01:37", timeB.toString());
        timeB.changeSeconds(-120);
        assertEquals("23:59:37", timeB.toString());

    }

    //these times will be used for the rest of the methods
    time timeStandard = new time(12, 0, 0, false);    //Standard Time is 12:00:00
    time timeMilitary = new time(24, 0, 0, true);     //Military Time is 24:00:00

    @Test
    public void IncrementHourDay() {
        //testing incrementing hour for a half/full day
        String expectedMilitary;
        String expectedStandard;
        for (int i = 1; i < 24; i++) {
            if (i < 12)
                timeStandard.changeHour(1);
            timeMilitary.changeHour(1);
            if (i < 10) {
                expectedStandard = "0" + i + ":00:00";
                expectedMilitary = "0" + i + ":00:00";
            } else {
                expectedStandard = i + ":00:00";
                expectedMilitary = i + ":00:00";
            }
            if (i < 12)
                assertEquals(expectedStandard, timeStandard.toString());
            assertEquals(expectedMilitary, timeMilitary.toString());
        }
        //Standard Time is 11:00:00
        //Military Time is 23:00:00
        timeStandard.changeHour(1);
        timeMilitary.changeHour(1);
        assertEquals("12:00:00", timeStandard.toString());     //Standard Time is 12:00:00
        assertEquals("24:00:00", timeMilitary.toString());     //Military Time is 24:00:00

        //test decrementing hour for a half/full day
        int iStandard = 11;
        for (int i = 23; i > 0; i--) {
            if (iStandard > 0)
                timeStandard.changeHour(-1);
            timeMilitary.changeHour(-1);
            if (iStandard < 10)
                expectedStandard = "0" + iStandard + ":00:00";
            else
                expectedStandard = iStandard + ":00:00";
            if (i < 10)
                expectedMilitary = "0" + i + ":00:00";
            else
                expectedMilitary = i + ":00:00";
            if (iStandard > 0)
                assertEquals(expectedStandard, timeStandard.toString());
            assertEquals(expectedMilitary, timeMilitary.toString());
            iStandard--;
        }
        //Standard Time is 1:00:00
        //Military Time is 1:00:00
        timeStandard.changeHour(-1);
        timeMilitary.changeHour(-1);
        assertEquals("12:00:00", timeStandard.toString());     //Standard Time is 12:00:00
        assertEquals("24:00:00", timeMilitary.toString());     //Military Time is 24:00:00
    }

    @Test
    public void testIncrementDecrementMinuteDay() {
        //test incrementing minute for a half/full day
        String expectedStandard;
        String expectedMilitary;
        for (int x = 0; x < 60; x++) {
            if (x < 10) {
                expectedStandard = "12:0" + x + ":00";
                expectedMilitary = "24:0" + x + ":00";
            } else {
                expectedStandard = "12:" + x + ":00";
                expectedMilitary = "24:" + x + ":00";
            }
            assertEquals(expectedStandard, timeStandard.toString());
            assertEquals(expectedMilitary, timeMilitary.toString());
            timeStandard.changeMinutes(1);
            timeMilitary.changeMinutes(1);
        }
        assertEquals("01:00:00", timeStandard.toString());        //Standard Time is 01:00:00
        assertEquals("01:00:00", timeMilitary.toString());        //Military Time is 01:00:00

        int iStandard = 1;
        for (int i = 1; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                if (j < 10) {
                    if (iStandard < 10)
                        expectedStandard = "0" + iStandard + ":0" + j + ":00";
                    else
                        expectedStandard = iStandard + ":0" + j + ":00";
                    if (i < 10)
                        expectedMilitary = "0" + i + ":0" + j + ":00";
                    else
                        expectedMilitary = i + ":0" + j + ":00";
                } else {
                    if (iStandard < 10)
                        expectedStandard = "0" + iStandard + ":" + j + ":00";
                    else
                        expectedStandard = iStandard + ":" + j + ":00";
                    if (i < 10)
                        expectedMilitary = "0" + i + ":" + j + ":00";
                    else
                        expectedMilitary = i + ":" + j + ":00";
                }
                if (iStandard < 12)
                    assertEquals(expectedStandard, timeStandard.toString());
                assertEquals(expectedMilitary, timeMilitary.toString());
                timeStandard.changeMinutes(1);
                timeMilitary.changeMinutes(1);
            }
            iStandard++;
        }
        assertEquals("12:00:00", timeStandard.toString());            //Standard Time is 12:00:00
        assertEquals("24:00:00", timeMilitary.toString());            //Military Time is 24:00:00

        //test decrementing minute for a half/full day
        iStandard = 11;
        for (int i = 23; i > 0; i--) {
            for (int j = 59; j >= 0; j--) {
                timeStandard.changeMinutes(-1);
                timeMilitary.changeMinutes(-1);
                if (j < 10) {
                    if (iStandard < 10)
                        expectedStandard = "0" + iStandard + ":0" + j + ":00";
                    else
                        expectedStandard = iStandard + ":0" + j + ":00";
                    if (i < 10)
                        expectedMilitary = "0" + i + ":0" + j + ":00";
                    else
                        expectedMilitary = i + ":0" + j + ":00";
                } else {
                    if (iStandard < 10)
                        expectedStandard = "0" + iStandard + ":" + j + ":00";
                    else
                        expectedStandard = iStandard + ":" + j + ":00";
                    if (i < 10)
                        expectedMilitary = "0" + i + ":" + j + ":00";
                    else
                        expectedMilitary = i + ":" + j + ":00";
                }
                if (iStandard > 1)
                    assertEquals(expectedStandard, timeStandard.toString());
                assertEquals(expectedMilitary, timeMilitary.toString());
            }
            iStandard--;
        }
        assertEquals("01:00:00", timeStandard.toString());        //Standard Time is 01:00:00
        assertEquals("01:00:00", timeMilitary.toString());        //Military Time is 01:00:00
        for (int x = 59; x >= 0; x--) {
            timeStandard.changeMinutes(-1);
            timeMilitary.changeMinutes(-1);
            if (x < 10) {
                expectedStandard = "12:0" + x + ":00";
                expectedMilitary = "24:0" + x + ":00";
            } else {
                expectedStandard = "12:" + x + ":00";
                expectedMilitary = "24:" + x + ":00";
            }
            assertEquals(expectedStandard, timeStandard.toString());
            assertEquals(expectedMilitary, timeMilitary.toString());

        }
        assertEquals("12:00:00", timeStandard.toString());            //Standard Time is 12:00:00
        assertEquals("24:00:00", timeMilitary.toString());            //Military Time is 24:00:00
    }

    @Test
    public void testIncrementDecrementSecondDay() {
        //test incrementing second for a half/full day.
        String expectedMilitary;
        String expectedStandard;
        for (int j = 0; j < 60; j++) {
            for (int o = 0; o < 60; o++) {
                if (o < 10) {
                    if (j < 10) {
                        expectedStandard = "12:0" + j + ":0" + o;
                        expectedMilitary = "24:0" + j + ":0" + o;
                    } else {
                        expectedStandard = "12:" + j + ":0" + o;
                        expectedMilitary = "24:" + j + ":0" + o;
                    }
                } else {
                    if (j < 10) {
                        expectedStandard = "12:0" + j + ":" + o;
                        expectedMilitary = "24:0" + j + ":" + o;
                    } else {
                        expectedStandard = "12:" + j + ":" + o;
                        expectedMilitary = "24:" + j + ":" + o;
                    }
                }
                assertEquals(expectedStandard, timeStandard.toString());
                assertEquals(expectedMilitary, timeMilitary.toString());
                timeStandard.changeSeconds(1);
                timeMilitary.changeSeconds(1);
            }
        }
        assertEquals("01:00:00", timeStandard.toString());  //Standard Time is 01:00:00
        assertEquals("01:00:00", timeMilitary.toString());  //Military Time is 01:00:00

        int iStandard = 1;
        for (int i = 1; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                for (int o = 0; o < 60; o++) {
                    if(o<10){
                        if (j < 10) {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":0" + j + ":0" + o;
                            else
                                expectedStandard = iStandard + ":0" + j + ":0" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":0" + j + ":0" + o;
                            else
                                expectedMilitary = i + ":0" + j + ":0" + o;
                        } else {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":" + j + ":0" + o;
                            else
                                expectedStandard = iStandard + ":" + j + ":0" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":" + j + ":0" + o;
                            else
                                expectedMilitary = i + ":" + j + ":0" + o;
                        }
                    }
                    else{
                        if (j < 10) {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":0" + j + ":" + o;
                            else
                                expectedStandard = iStandard + ":0" + j + ":" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":0" + j + ":" + o;
                            else
                                expectedMilitary = i + ":0" + j + ":" + o;
                        } else {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":" + j + ":" + o;
                            else
                                expectedStandard = iStandard + ":" + j + ":" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":" + j + ":" + o;
                            else
                                expectedMilitary = i + ":" + j + ":" + o;
                        }
                    }
                    if(iStandard<12) {
                        assertEquals(expectedStandard, timeStandard.toString());
                        timeStandard.changeSeconds(1);
                    }
                    assertEquals(expectedMilitary, timeMilitary.toString());
                    timeMilitary.changeSeconds(1);
                }
            }
            iStandard++;
        }
        assertEquals("12:00:00", timeStandard.toString());            //Standard Time is 12:00:00
        assertEquals("24:00:00", timeMilitary.toString());            //Military Time is 24:00:00

        //test decrementing second for a half/full day
        iStandard = 11;
        for (int i = 23; i > 0; i--) {
            for (int j = 59; j>=0; j--) {
                for (int o = 59; o>=0; o--) {
                    if(iStandard>0)
                         timeStandard.changeSeconds(-1);
                    timeMilitary.changeSeconds(-1);
                    if(o<10){
                        if (j < 10) {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":0" + j + ":0" + o;
                            else
                                expectedStandard = iStandard + ":0" + j + ":0" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":0" + j + ":0" + o;
                            else
                                expectedMilitary = i + ":0" + j + ":0" + o;
                        } else {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":" + j + ":0" + o;
                            else
                                expectedStandard = iStandard + ":" + j + ":0" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":" + j + ":0" + o;
                            else
                                expectedMilitary = i + ":" + j + ":0" + o;
                        }
                    }
                    else{
                        if (j < 10) {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":0" + j + ":" + o;
                            else
                                expectedStandard = iStandard + ":0" + j + ":" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":0" + j + ":" + o;
                            else
                                expectedMilitary = i + ":0" + j + ":" + o;
                        } else {
                            if (iStandard < 10)
                                expectedStandard = "0" + iStandard + ":" + j + ":" + o;
                            else
                                expectedStandard = iStandard + ":" + j + ":" + o;
                            if (i<10)
                                expectedMilitary = "0" + i + ":" + j + ":" + o;
                            else
                                expectedMilitary = i + ":" + j + ":" + o;
                        }
                    }
                    if(iStandard>0)
                        assertEquals(expectedStandard, timeStandard.toString());
                    assertEquals(expectedMilitary, timeMilitary.toString());
                }
            }
            iStandard--;
        }
        assertEquals("01:00:00", timeStandard.toString());  //Standard Time is 01:00:00
        assertEquals("01:00:00", timeMilitary.toString());  //Military Time is 01:00:00

        for (int j = 59; j >=0; j--) {
            for (int o = 59; o >=0; o--) {
                timeStandard.changeSeconds(-1);
                timeMilitary.changeSeconds(-1);
                if (o < 10) {
                    if (j < 10) {
                        expectedStandard = "12:0" + j + ":0" + o;
                        expectedMilitary = "24:0" + j + ":0" + o;
                    } else {
                        expectedStandard = "12:" + j + ":0" + o;
                        expectedMilitary = "24:" + j + ":0" + o;
                    }
                } else {
                    if (j < 10) {
                        expectedStandard = "12:0" + j + ":" + o;
                        expectedMilitary = "24:0" + j + ":" + o;
                    } else {
                        expectedStandard = "12:" + j + ":" + o;
                        expectedMilitary = "24:" + j + ":" + o;
                    }
                }
                assertEquals(expectedStandard, timeStandard.toString());
                assertEquals(expectedMilitary, timeMilitary.toString());
            }
        }
        assertEquals("12:00:00", timeStandard.toString());            //Standard Time is 12:00:00
        assertEquals("24:00:00", timeMilitary.toString());            //Military Time is 24:00:00

    }
}