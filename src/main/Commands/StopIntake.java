package frc.robot.Commands;

import frc.robot.Subsystems.IntakeSubsystem;


public class StopIntake extends Command {

    IntakeSubsystem s_IntakeSubsystem;
    MotorSpeeds state;

    public StopIntake(MotorSpeeds state) {
        s_IntakeSubsystem = IntakeSubsystem.getInstance();
        this.state = state;
        //He did the thing!
        addRequirements(s_IntakeSubsystem);
    }


    @Override
    public void initialize(){
        s_IntakeSubsystem.setMotorSpeed(MotorSpeeds.OFF());
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
