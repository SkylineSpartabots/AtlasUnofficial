
package frc.robot.Commands;

import frc.robot.Subsystems.IntakeSubsystem;


public class StartIntake extends Command {
    IntakeSubsystem s_IntakeSubsystem;
    MotorSpeeds state;

    public StartIntake(MotorSpeeds state) {
        s_IntakeSubsystem = IntakeSubsystem.getInstance();
        this.state = state;

        addRequirements(s_IntakeSubsystem);
    }


    @Override
    public void initialize(){
        s_IntakeSubsystem.setMotorSpeed(MotorSpeeds.SLOW());
    }

    @Override
    public void execute(){

    }
    @Override
    public boolean isFinished() {
        return true;
    }


    @Override
    public void end() {

    }
}