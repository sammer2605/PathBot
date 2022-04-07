// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import java.io.IOException; // Ramsete
// import java.nio.file.Path; // Ramsete
// import java.util.List; // REMOVE FOR EXAMPLE?

// import edu.wpi.first.math.controller.PIDController; // Ramsete
// import edu.wpi.first.math.controller.RamseteController; // Ramsete
// import edu.wpi.first.math.controller.SimpleMotorFeedforward; // Ramsete
// import edu.wpi.first.math.geometry.Pose2d; // REMOVE FOR EXAMPLE?
// import edu.wpi.first.math.geometry.Rotation2d; // REMOVE FOR EXAMPLE?
// import edu.wpi.first.math.geometry.Translation2d; // Ramsete
// import edu.wpi.first.math.trajectory.Trajectory; // Ramsete
// import edu.wpi.first.math.trajectory.TrajectoryConfig; // REMOVE FOR EXAMPLE?
// import edu.wpi.first.math.trajectory.TrajectoryGenerator; // REMOVE FOR EXAMPLE?
// import edu.wpi.first.math.trajectory.TrajectoryUtil; // Ramsete
// import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint; // REMOVE FOR EXAMPLE?
// import edu.wpi.first.wpilibj.DriverStation; // Ramsete
// import edu.wpi.first.wpilibj.Filesystem; // Ramsete
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.RamseteCommand; // Ramsete
import frc.robot.commands.*;
import frc.robot.commands.autocommands.*;
import frc.robot.commands.autoroutines.*;
import frc.robot.subsystems.*;
import frc.robot.utils.Limelight;
import frc.robot.utils.Limelight.LightMode;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static final DriveTrain m_robotDrive = new DriveTrain();
  public static final Shooter m_shooter = new Shooter();
  public static XboxController airflo = new XboxController(0);
  public static XboxController xbox2 = new XboxController(1);

  // Joystick Buttons
  // Primary Driver
  JoystickButton alignButton, shiftHighButton, shiftLowButton, intakeButton, shootButton, climbButton;


  // Autonomous Chooser
  final SendableChooser<Command> m_auto_chooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Primary Driver Joystick Buttons
    alignButton = new JoystickButton(airflo, Constants.ALIGN_ROBOT_BUTTON);
    shiftHighButton = new JoystickButton(airflo, Constants.SHIFT_HIGHGEAR_BUTTON);
    shiftLowButton = new JoystickButton(airflo, Constants.SHIFT_LOWGEAR_BUTTON);
    // Secondary Driver Joystick Buttons
    intakeButton = new JoystickButton(xbox2, Constants.INTAKE_BUTTON);
    shootButton = new JoystickButton(xbox2, Constants.SHOOT_BUTTON);
    climbButton = new JoystickButton(xbox2, Constants.CLIMB_BUTTON);

    // Autonomous Routine Selector
    m_auto_chooser = new SendableChooser<Command>();
    m_auto_chooser.addOption("B2B1", new B2B1());
    m_auto_chooser.addOption("B2B2", new BlueTwoBall_Two());

    Shuffleboard.getTab("SmartDashboard")
     .add("Sendable Title", m_auto_chooser);

    // Configure the button bindings
    configureButtonBindings();

    // Set default commands on subsystems
    m_robotDrive.setDefaultCommand(new arcadeDrive());
    Limelight.setLedMode(LightMode.eOff);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Primary Driver
    alignButton.whileHeld(new AlignRobot());
    shiftHighButton.whenPressed(new ShiftHigh());
    shiftLowButton.whenPressed(new ShiftLow());

    // Secondary Driver
    intakeButton.whenPressed(new doIntake());
    //shootButton.whenPressed(new Shoot());
    //climbButton.whenPressed(new Climb());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_auto_chooser.getSelected();
    //   if (true)
  //     return new AlignRobot();

  // //   //Create a voltage constraint to ensure we don't accelerate too fast
  // //    var autoVoltageConstraint =
  // //        new DifferentialDriveVoltageConstraint(
  // //            new SimpleMotorFeedforward(
  // //                DriveConstants.ksVolts,
  // //                DriveConstants.kvVoltSecondsPerMeter,
  // //                DriveConstants.kaVoltSecondsSquaredPerMeter),
  // //            DriveConstants.kDriveKinematics,
  // //            10);

  // //   //Create config for trajectory
  // //   TrajectoryConfig config =
  // //        new TrajectoryConfig(
  // //                AutoConstants.kMaxSpeedMetersPerSecond,
  // //                AutoConstants.kMaxAccelerationMetersPerSecondSquared)
  // //            // Add kinematics to ensure max speed is actually obeyed
  // //             .setKinematics(DriveConstants.kDriveKinematics)
  // //            // Apply the voltage constraint
  // //             .addConstraint(autoVoltageConstraint);
            
  // //   //An example trajectory to follow.  All units in meters.
  // //   Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
  // //     // Start at the origin facing the +X direction
  // //     new Pose2d(0, 0, new Rotation2d(0)),
  // //     // Pass through these two interior waypoints, making an 's' curve path
  // //     List.of(),
  // //     // End 3 meters straight ahead of where we started, facing forward
  // //     new Pose2d(3, 0, new Rotation2d(0)),
  // //     // Pass config
  // //     config
  // // );

  //   String trajectoryJSON = "output/test.wpilib.json";
  //   Trajectory testTrajectory = new Trajectory();
    
  //   try {
  //     Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
  //     System.out.println("Path: " + trajectoryPath);
  //     testTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
  //     //testTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

  //   } catch (IOException ex) {
  //     DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
  //   }

  //   RamseteCommand ramseteCommand =
  //       new RamseteCommand(
  //           testTrajectory, // To use the example trajectory just replace this with exampleTrajectory.
  //           m_robotDrive::getPose,
  //           new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
  //           new SimpleMotorFeedforward(
  //               DriveConstants.ksVolts,
  //               DriveConstants.kvVoltSecondsPerMeter,
  //               DriveConstants.kaVoltSecondsSquaredPerMeter),
  //           DriveConstants.kDriveKinematics,
  //           m_robotDrive::getWheelSpeeds,
  //           new PIDController(DriveConstants.kPDriveVel, 0.4, 0.05),
  //           new PIDController(DriveConstants.kPDriveVel, 0.4, 0.05),
  //           // RamseteCommand passes volts to the callback
  //           m_robotDrive::tankDriveVolts,
  //           m_robotDrive);

  //   // Reset odometry to the starting pose of the trajectory.
  //   m_robotDrive.resetOdometry(testTrajectory.getInitialPose());

  //   // Run path following command, then stop at the end.
  //   return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0));
  }
}
