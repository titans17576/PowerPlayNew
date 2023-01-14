package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class armFSM {
    // Enum for state memory
    public enum ArmState {
        RETRACTED,
        EXTENDED
    }

    // Position variables
    final double position_tolerance = 0;
    final double retracted_position = 1;
    final double extended_position = 0;

    // ClawState instance variable
    ArmState armState = ArmState.RETRACTED;

    // OpMode variables
    robot R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public armFSM(robot Robot, Telemetry t, Gamepad g1, Gamepad pg1) {
        R = Robot;
        telemetry = t;
        gamepad1 = g1;
        previousGamepad1 = pg1;
    }

    private void updateTelemetry(String status) {
        // Add encoder position to telemetry
        telemetry.addData("Arm Position", R.arm.getPosition());
        // Add lift position to telemetry
        telemetry.addData("Status of Claw", status);
    }

    // Update method for teleop implementation
    public void teleopUpdate() {
        telemetry.addLine("Arm Data");

        // State machine switch statement
        switch(armState){
            // Claw open
            case RETRACTED:
                // Check position and move if not at closed_position
                if(abs(R.arm.getPosition() - retracted_position) >= position_tolerance){
                    R.arm.setPosition(retracted_position);
                    telemetry.addData("Arm Moved", "TRUE");
                } else {
                    telemetry.addData("Arm Moved", "FALSE");
                }

                // State inputs
                if(gamepad1.x && !previousGamepad1.x){
                    armState = ArmState.EXTENDED;
                    telemetry.addData("Arm Move Requested", "TRUE");
                } else {
                    telemetry.addData("Arm Move Requested", "FALSE");
                }

                updateTelemetry("CLOSED");

                break;

            // Claw closed
            case EXTENDED:
                // Check position and move if not at open_position
                if(abs(R.arm.getPosition() - extended_position) >= position_tolerance){
                    R.arm.setPosition(extended_position);
                    telemetry.addData("Arm Moved", "TRUE");
                } else {
                    telemetry.addData("Arm Moved", "FALSE");
                }

                // State inputs
                if(gamepad1.y && !previousGamepad1.y){
                    armState = ArmState.RETRACTED;
                    telemetry.addData("Arm Move Requested", "TRUE");
                } else {
                    telemetry.addData("Arm Move Requested", "FALSE");
                }

                updateTelemetry("OPEN");

                break;
        }
    }

    // Update method for auton implementation
    public void autonUpdate(ArmState newState) {
        telemetry.addLine("Arm Data");

        // State machine switch statement
        switch(armState){
            // Claw open
            case RETRACTED:
                // Check position and move if not at closed_position
                if(abs(R.arm.getPosition() - retracted_position) >= position_tolerance){
                    R.arm.setPosition(retracted_position);
                    telemetry.addData("Arm Moved", "TRUE");
                } else {
                    telemetry.addData("Arm Moved", "FALSE");
                }

                // State inputs
                if(newState == ArmState.EXTENDED){
                    armState = ArmState.EXTENDED;
                    telemetry.addData("Arm Move Requested", "TRUE");
                } else {
                    telemetry.addData("Arm Move Requested", "FALSE");
                }

                updateTelemetry("CLOSED");

                break;

            // Claw closed
            case EXTENDED:
                // Check position and move if not at open_position
                if(abs(R.arm.getPosition() - extended_position) >= position_tolerance){
                    R.arm.setPosition(extended_position);
                    telemetry.addData("Arm Moved", "TRUE");
                } else {
                    telemetry.addData("Arm Moved", "FALSE");
                }

                // State inputs
                if(newState == ArmState.RETRACTED){
                    armState = ArmState.RETRACTED;
                    telemetry.addData("Arm Move Requested", "TRUE");
                } else {
                    telemetry.addData("Arm Move Requested", "FALSE");
                }

                updateTelemetry("OPEN");

                break;
        }
    }
}
