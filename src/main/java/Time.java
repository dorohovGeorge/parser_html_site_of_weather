public class Time {
     static TimeZone timeZone;
    protected static int howManyTimesOfDay(String string) {
       timeZone = conversionStringToTime(string);
        switch (timeZone){
            case MORNING:
                return 4;
            case DAY:
                return 3;
            case EVENING:
                return 2;
            case NIGHT:
                return 1;
        }
        return 0;
    }
    private static TimeZone conversionStringToTime(String time)
    {
      if(time.contains("Ночь")){
          return TimeZone.MORNING;
      }
      if(time.contains("Утро")){
          return TimeZone.DAY;
      }
      if(time.contains("День")){
          return TimeZone.EVENING;
      }
      if(time.contains("Вечер")){
          return TimeZone.NIGHT;
      }
        return null;
    }
}
enum TimeZone {
    MORNING,
    DAY,
    EVENING,
    NIGHT
}
