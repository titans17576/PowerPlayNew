package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class liftFSM {
    // Enum for state memory
    private enum LiftState {
        ZERO,
        LOW,
        MID,
        HIGH
    };

    // Position variables
    final int position_tolerance = 5;
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

    // Update method for teleop implementation
    public void teleopUpdate() {
        telemetry.addLine("Lift Data");

        switch (liftState) {
            // Lift set to 0
            case ZERO:
                // Check position and move if not at 0
                /*if (abs(R.slide.getCurrentPosition()) <= position_tolerance) {
                    R.slide.setTargetPosition(0);
                    R.slide.setPower(0.8);
                    R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    R.slide.setPower(0);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                // Add encoder position to telemetry
                telemetry.addData("Ticks", R.slide.getCurrentPosition());
                // Add lift position to telemetry
                telemetry.addData("Status of Lift", "ZERO");

                // Lift set to 1/3
            case LOW:
                // Check position and move if not at low_position
                if (abs(R.slide.getCurrentPosition() - low_position) <= position_tolerance) {
                    R.slide.setTargetPosition(low_position);
                    R.slide.setPower(0.8);
                    R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    R.slide.setPower(0);
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
                } else if (gamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                // Add encoder position to telemetry
                telemetry.addData("Ticks", R.slide.getCurrentPosition());
                // Add lift position to telemetry
                telemetry.addData("Status of Lift", "LOW");

            //Lift set to 2/3
            case MID:
                // Check position and move if not at mid_position
                if (abs(R.slide.getCurrentPosition() - mid_position) <= position_tolerance) {
                    R.slide.setTargetPosition(mid_position);
                    R.slide.setPower(0.8);
                    R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    R.slide.setPower(0);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    liftState = LiftState.HIGH;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }

                // Add encoder position to telemetry
                telemetry.addData("Ticks", R.slide.getCurrentPosition());
                // Add lift position to telemetry
                telemetry.addData("Status of Lift", "MID");

            // Lift set to 3/3
            case HIGH:
                // Check position and move if not at high_position
                if (abs(R.slide.getCurrentPosition() - high_position) <= position_tolerance) {
                    R.slide.setTargetPosition(high_position);
                    R.slide.setPower(0.8);
                    R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    R.slide.setPower(0);
                    telemetry.addData("Lift Moved", "TRUE");
                } else {
                    telemetry.addData("Lift Moved", "FALSE");
                }

                // State inputs
                if (gamepad1.dpad_down && !previousGamepad1.dpad_down) {
                    liftState = LiftState.ZERO;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    liftState = LiftState.LOW;
                    telemetry.addData("Move Requested", "TRUE");
                } else if (gamepad1.dpad_up && !previousGamepad1.dpad_up) {
                    liftState = LiftState.MID;
                    telemetry.addData("Move Requested", "TRUE");
                } else {
                    telemetry.addData("Move Requested", "FALSE");
                }


                // Add encoder position to telemetry
                telemetry.addData("Ticks", R.slide.getCurrentPosition());
                // Add lift position to telemetry
                telemetry.addData("Status of Lift", "HIGH");*/
        }
    }
}
