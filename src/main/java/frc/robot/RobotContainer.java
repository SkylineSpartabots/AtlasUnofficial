// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.ShooterSubsystem.MotorSpeeds;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class RobotContainer {

  ShooterSubsystem s_Shooter = ShooterSubsystem.getInstance();

  CommandXboxController pjyController = new CommandXboxController(0);
  
  private static RobotContainer container;  
  
  private void configureBindings() {
    //Review lambdas
     pjyController.rightTrigger().onTrue(new InstantCommand(() -> s_Shooter.setMotorSpeed(MotorSpeeds.OUTTAKE)));
     pjyController.rightTrigger().onFalse(new InstantCommand(() -> s_Shooter.setMotorSpeed(MotorSpeeds.HOLD)));
  }
   
   public RobotContainer() {
    configureBindings();
  }
   
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
