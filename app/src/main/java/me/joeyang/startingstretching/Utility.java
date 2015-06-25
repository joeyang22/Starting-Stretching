package me.joeyang.startingstretching;

import org.joda.time.LocalDate;

/**
 * Created by Joe Yang on 6/21/2015.
 */
public class Utility {
    static int formatYearDay(int day, int year){


        return Integer.parseInt(String.valueOf(year)+String.valueOf(day));
    }

    static int getGreaterThan(){
        LocalDate date = new LocalDate();
        int localYear = date.getYear();
        int localDateInt = formatYearDay(date.getDayOfYear(), date.getYear());
        int subtractSeven = localDateInt-7;
        int aboveCurrent = 0;
        if ((subtractSeven - localYear*1000)<=0){
            aboveCurrent = localDateInt-localYear*1000;
            if (isLeapYear(localYear-1)){
                return ((localYear-1)*1000+365-(7-aboveCurrent)+1);
            }else{
                return ((localYear-1)*1000+365-(7-aboveCurrent));
            }
        }

        return localDateInt-7;
    }

    static int getSubtracted(int subtract, int startingDate){
        int localYear = startingDate/1000;
        int subtractNums = startingDate-subtract;
        int aboveCurrent = 0;
        if ((subtractNums - localYear*1000)<=0){
            aboveCurrent = startingDate-localYear*1000;
            if (isLeapYear(localYear-1)) {
                return ((localYear-1)*1000+365-(subtract-aboveCurrent)+1);
            }else{
                return ((localYear-1)*1000+365-(subtract-aboveCurrent));
            }
        }

        return startingDate-subtract;
    }

    static int getSubtracted(int subtract){
        LocalDate date = new LocalDate();
        int localYear = date.getYear();
        int localDateInt = formatYearDay(date.getDayOfYear(), date.getYear());
        int subtractNums = localDateInt-subtract;
        int aboveCurrent = 0;
        if ((subtractNums - localYear*1000)<=0){
            aboveCurrent = localDateInt-localYear*1000;
            if (isLeapYear(localYear-1)) {
                return ((localYear-1)*1000+365-(subtract-aboveCurrent)+1);
            }else{
                return ((localYear-1)*1000+365-(subtract-aboveCurrent));
            }
        }

        return localDateInt-subtract;
    }

    static int getDaysBetween(int first, int second){
        int daysBetween = second-first;
        if (daysBetween>=7){
            daysBetween = (second-first)- (1000-365);
        }
        return (isLeapYear(first/1000))?daysBetween+1:daysBetween;

    }
    static boolean isLeapYear(int year){
        if (year%4==0){
            if(year%100==0){
                if(year%400==0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return true;
            }
        }
        return false;
    }


    //subtracts first date by second;


}
