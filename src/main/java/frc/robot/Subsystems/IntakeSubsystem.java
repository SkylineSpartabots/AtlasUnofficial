package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{

    private static IntakeSubsystem instance;

    private static IntakeSubsystem getInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }

        return instance;
    }

    private enum MotorSpeeds{
        SLOW(0.5),
        FAST(1.0),
        BACKWARDS(-1.0);

        private double speed;

        private MotorSpeeds(double speed){
            this.speed = speed;
        }

        public void setMotorSpeed(double pSpeed){
            this.speed = pSpeed;
        }

        public double getSpeed(){
            return speed;
        }
    }

    private TalonFX m_TalonFX = new TalonFX(1, "rio");

     private void config(TalonFX motor, NeutralModeValue neutralMode, InvertedValue direction){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = direction;
        config.MotorOutput.NeutralMode = neutralMode;

        CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs();
        currentLimitsConfigs.SupplyCurrentLimit = Constants.CurrentLimits.outtakeContinuousCurrentLimit;
        currentLimitsConfigs.SupplyCurrentLimitEnable = true;
        currentLimitsConfigs.StatorCurrentLimit = Constants.CurrentLimits.outtakePeakCurrentLimit;
        currentLimitsConfigs.StatorCurrentLimitEnable = true;

        config.CurrentLimits = currentLimitsConfigs;
        motor.getConfigurator().apply(config);
  }
}
