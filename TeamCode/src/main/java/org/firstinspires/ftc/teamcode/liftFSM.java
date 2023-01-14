package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class liftFSM {
    // Enum for state memory
    public enum LiftState {
        ZERO,
        LOW,
        MID,
        HIGH
    }

    // Position variables
    final int position_tolerance = 5;
    final int zero_position = 0;
    final int low_position = 723;
    final int mid_position = 1442;
    final int high_position = 1980;

    // LiftState instance variable
    LiftState liftState = LiftState.ZERO;

    // OpMode variables
    robot R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public liftFSM(robot Robot, Telemetry t, Gamepad g1, Gamepad gp1) {
        R = Robot;
        telemetry = t;
        gamepad1 = g1;
        previousGamepad1 = gp1;
    }

    // Method to move to a targeted position
    private void moveTo(int position) {
        R.leftSlide.setTargetPosition(position);
        R.rightSlide.setTargetPosition(position);
        R.leftSlide.setPower(0.8);
        R.rightSlide.setPower(0.8);
        R.leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R.rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R.leftSlide.setPower(0);
    }

    // Method to add encoders and status to telemetry
    private void updateTelemetry(String status) {
        // Add encoder position to telemetry
        telemetry.addData("Left Ticks", R.leftSlide.getCurrentPosition());
        telemetry.addData("Right Ticks", R.rightSlide.getCurrentPosition());
        // Add lift position to telemetry
        telemetry.addData("Status of Lift", status);
    }

    // Update method for teleop implementation
    public void teleopUpdate() {
        telemetry.addLine("Lift Data");

        switch (liftState) {
            // Lift set to 0
            case ZERO:
                // Check position and move if not at 0
                if (abs(R.leftSlide.getCurrentPosition() - zero_position) <= position_tolerance) {
                    moveTo(zero_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("Zero");

                break;

            // Lift set to 1/3
            case LOW:
                // Check position and move if not at low_position
                if (abs(R.leftSlide.getCurrentPosition() - low_position) <= position_tolerance) {
                    moveTo(low_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("LOW");

                break;

            //Lift set to 2/3
            case MID:
                // Check position and move if not at mid_position
                if (abs(R.leftSlide.getCurrentPosition() - mid_position) <= position_tolerance) {
                    moveTo(mid_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("MID");

                break;

            // Lift set to 3/3
            case HIGH:
                // Check position and move if not at high_position
                if (abs(R.leftSlide.getCurrentPosition() - high_position) <= position_tolerance) {
                    moveTo(high_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }


                updateTelemetry("HIGH");

                break;

        }
    }

    // Update method for auton implementation
    public void autonUpdate(LiftState newState) {
        telemetry.addLine("Lift Data");

        switch (liftState) {
            // Lift set to 0
            case ZERO:
                // Check position and move if not at 0
                if (abs(R.leftSlide.getCurrentPosition() - zero_position) <= position_tolerance) {
                    moveTo(zero_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.LOW) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.MID){
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.HIGH) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                        telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("Zero");

                break;

            // Lift set to 1/3
            case LOW:
                // Check position and move if not at low_position
                if (abs(R.leftSlide.getCurrentPosition() - low_position) <= position_tolerance) {
                    moveTo(low_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.ZERO) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.MID){
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.HIGH) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("LOW");

                break;

            //Lift set to 2/3
            case MID:
                // Check position and move if not at mid_position
                if (abs(R.leftSlide.getCurrentPosition() - mid_position) <= position_tolerance) {
                    moveTo(mid_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.ZERO) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.LOW){
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.HIGH) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                updateTelemetry("MID");

                break;

            // Lift set to 3/3
            case HIGH:
                // Check position and move if not at high_position
                if (abs(R.leftSlide.getCurrentPosition() - high_position) <= position_tolerance) {
                    moveTo(high_position);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (newState == LiftState.ZERO) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.LOW){
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (newState == LiftState.MID) {
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }


                updateTelemetry("HIGH");

                break;

        }
    }
}