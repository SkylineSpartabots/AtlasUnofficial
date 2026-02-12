import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.ShooterSubsystem.MotorSpeeds;

public class StartShooter extends Command {

    ShooterSubsystem shooter;

    public StartShooter(){
        shooter = ShooterSubsystem.getInstance();
    }

    @Override
    public void initialize() {
        timer.restart();
        shooter.setMotorSpeed(MotorSpeeds.OUTTAKE);
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return (timer.hasElapsed(1));
    }

}
