package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;


import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


// name of subsystem + Subsystem
public class ShooterSubsystem extends SubsystemBase {
   
  private static ShooterSubsystem instance;

  private TalonFX motor;

  //singleton, maybe research synchronized singletons (EXTRA)
  public static ShooterSubsystem getInstance(){
    if(instance == null) instance = new ShooterSubsystem();
    return instance;
  }

  public ShooterSubsystem() {
     motor = new TalonFX(0, "shooter");
     //config motor here :D
  }
 
  public enum MotorSpeeds {
    HOLD(0.0),
    OUTTAKE(1.0),
    INTAKE(-1.0);

    private double speed;

    private MotorSpeeds(double speed){
      this.speed = speed;
    }
    private double getSpeed() {
      return speed;
    }
  }
 
  //pretend config method here

  private void config() {}
 

  //Overloaded
  public void setMotorSpeed(double speed){
    motor.set(speed);
  }

  public void setMotorSpeed(MotorSpeeds state){
    motor.set(state.getSpeed());
  }

  @Override
  public void periodic() {
    //put periodic code here, typically some sort of dashboard
  }

}
