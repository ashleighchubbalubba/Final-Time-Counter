//Ashleigh Chung
public class time{

    private int hour;
    private int minute;
    private int second;
    private boolean military;

    //default Constuctor
    public time(){
        hour = 12;
        minute = 0;
        second = 0;
        military = false;
    }

    public time(int h, int m, int s, boolean mil){
        hour = h;
        if(hour<1 || hour>24) {
            hour = 1;               //if user enters invalid hour, hour will automatically equal 1
        }
        minute = m;
        if(minute<0 || minute>59){
            minute = 0;             //if user enters invalid minute, minute will automatically equal 0
        }
        second = s;
        if(second<0 || second>59){
            second = 0;             //if user enters invalid second, second will automatically equal 0
        }
        military = mil;
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }
    public int getSecond(){
        return second;
    }
    public boolean isMilitary(){
        return military;
    }

    //user enters a negative or positive amount to change seconds
    public void changeSeconds(int s){
        if(s>0){
            second+=s;
            while(second>=60){
                minute++;
                if(minute==60){
                    hour++;
                    if(military==true && hour == 25){
                        hour = 1;
                        minute = 0;
                    }
                    else if(military==false && hour==13){
                        hour = 1;
                        minute = 0;
                    }
                    else
                        minute-=60;
                }
                second-=60;
            }
        }
        else{
            second += s;
            while(second<0){
                minute--;
                if(minute==-1){
                    hour--;
                    if(military==true && hour==0){
                        hour = 24;
                        minute = 59;
                    }
                    else if(military==false && hour==0){
                        hour = 12;
                        minute = 59;
                    }
                    else
                        minute+=60;
                }
                second+=60;
            }
        }
    }

    //user enters a negative or positive amount to change minutes
    public void changeMinutes(int m){
        if(m>0){
            minute+=m;
            while(minute>=60){
                hour++;
                if(military == true && hour == 25)
                    hour = 1;
                if(military == false && hour == 13)
                    hour = 1;
                minute-=60;
            }
        }
        else{
            minute+=m;
            while(minute<0){
                hour--;
                if(military == true && hour == 0)
                    hour = 24;
                if(military == false && hour == 0)
                    hour = 12;
                minute+=60;
            }
        }
    }

    //user enters a negative or positive amount to change hour
    public void changeHour(int h){
        if(h==1){
            hour++;
            if(military==true && hour == 25)
                hour = 1;
            if(military ==false && hour==13)
                hour=1;
        }
        else if(h==-1){
            hour--;
            if(military==true && hour==0)
                hour = 24;
            if(military==false && hour==0)
                hour = 12;
        }
        else
            System.out.println("Only can increment one hour at a time.");
    }
    public String toString(){
        String newSecond;
        String newMinute;
        String newHour;
        if(hour<10)
            newHour = "0" + hour;
        else
            newHour = hour + "";
        if(minute<10)
            newMinute = "0" + minute;
        else
            newMinute = minute + "";
        if(second<10)
            newSecond = "0" + second;
        else
            newSecond = second + "";
        return newHour +":"+ newMinute +":"+ newSecond;
    }
}
