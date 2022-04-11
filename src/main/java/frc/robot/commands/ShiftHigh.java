// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ShiftHigh extends CommandBase {
  /** Creates a new ShiftHigh. */
  public ShiftHigh() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_robotDrive);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("This button is working!");
    // RobotContainer.m_robotDrive.shiftHigh();
    if (RobotContainer.airflo.getRawButtonPressed(6)) {
      RobotContainer.m_robotDrive.shiftHigh();
    } 
    if (RobotContainer.airflo.getRawButtonPressed(5)) {
      RobotContainer.m_robotDrive.shiftLow();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
