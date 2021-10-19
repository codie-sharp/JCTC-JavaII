/**
   The ProductionWorker class stores data about 
   an employee who is a production worker for the 
   TeamLeader Class programming challenge.
*/

public class TeamLeader extends Employee
{
   // Constants for the day and night shifts.
   public static final int DAY_SHIFT = 1;
   public static final int NIGHT_SHIFT = 2;

   private int shift;         // The employee's shift
   private double payRate, bonus, reqTrain, trainAtt;

   /**
      This constructor initializes an object with a name,
      employee number, hire date, shift, and pay rate
      @param n The employee's name.
      @param num The employee's number.
      @param date The employee's hire date.
      @param sh The employee's shift.
      @param rate The employee's pay rate.
   */
   
   public TeamLeader(String n, String num, String date, int sh, double rate, 
		   double bns, double rqTr, double trAt)
   {
      super(n, num, date);
      shift = sh;
      payRate = rate;
      bonus = bns;
      reqTrain = rqTr;
      trainAtt = trAt;  
   }

   /**
      The no-arg constructor initializes an object with
      null strings for name, employee number, and hire
      date. The day shift is selected, and the pay rate
      is set to 0.0.
   */
   
   public TeamLeader()
   {
      super();
      shift = DAY_SHIFT;
      payRate = 0.0;
      bonus = 0.0;
      reqTrain = 0.0;
      trainAtt = 0.0;
   }
   
   /**
      The setShift method sets the employee's shift.
      @param s The employee's shift.
   */

   public void setShift(int s)
   {
      shift = s;
   }

   /**
      The setPayRate method sets the employee's pay rate.
      @param p The employee's pay rate.
   */
   
   public void setPayRate(double p)
   {
      payRate = p;
   }

   /**
      The getShift method returns the employee's shift.
      @return The employee's shift.
   */
   public void setBonus(double b)
   {
      bonus = b;
   }
   
   public void setReqTrain(double rt)
   {
      reqTrain = rt;
   }
   
   public void setTrainAtt(double ta)
   {
      trainAtt = ta;
   }
   
   public int getShift()
   {
      return shift;
   }

   /**
      The getPayRate method returns the employee's pay rate.
      @return The employee's pay rate.
   */
   
   public double getPayRate()
   {
      return payRate;
   }
   
   public double getBonus()
   {
      return bonus;
   }
   
   public double getReqTrain()
   {
      return reqTrain;
   }
   
   public double getTrainAtt()
   {
      return trainAtt;
   }
   /**
      toString method
      @return A reference to a String representation of
              the object.
   */
   
   @Override
   public String toString()
   {  
      String str = super.toString();
      
      str += "\nShift: ";
      if (shift == DAY_SHIFT)
         str += "Day";
      else if (shift == NIGHT_SHIFT)
         str += "Night";
      else
         str += "INVALID SHIFT NUMBER";
      
      str += String.format("\nHourly Pay Rate: $%,.2f", payRate);
      str += String.format("\nMonthly Bonus: $%,.2f", bonus);    
      str += String.format("\nRequired Training Hours: %,.1f", reqTrain); 
      str += String.format("\nTraining Hours Attended: %,.1f", trainAtt); 
      return str;
   }
}
