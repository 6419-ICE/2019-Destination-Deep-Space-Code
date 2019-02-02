package frc.robot;

public enum DirectionEnum
{
LEFT{

    @Override
  public double getPower() {
      return -.2;
    }
    
},
 RIGHT{

    @Override
  public  double getPower() {
      return .2;
    }

 },
  CENTER{

    @Override
  public  double getPower() {
      return 0;
    }

  },
  UNKNOWN{

    @Override
   public double getPower() {
      return 0;
    }

  };
  public abstract double getPower();
}