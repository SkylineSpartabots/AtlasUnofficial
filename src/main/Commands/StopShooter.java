import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.ShooterSubsystem.MotorSpeeds;

public class StopShooter extends Command {

    ShooterSubsystem shooter;

    public StopShooter(){
        shooter = ShooterSubsystem.getInstance();
    }

    @Override
    public void initialize() {
        timer.restart();
        shooter.setMotorSpeed(MotorSpeeds.HOLD);
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return (timer.hasElapsed(1));
    }

}