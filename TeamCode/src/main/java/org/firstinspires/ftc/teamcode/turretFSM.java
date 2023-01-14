package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class turretFSM {
    // Enum for state memory
    private enum TurretState {
        FORWARD,
        LEFT,
        RIGHT,
        EXTENDED_LEFT,
        EXTENDED_RIGHT,
    }

    // Position variables
    final int position_tolerance = 25;
    final int forward_position = 0;
    final int left_position = -400;
    final int right_position = 400;
    final int extended_left_position = -500;
    final int extended_right_position = 500;

    // TurretState instance variable
    TurretState turretState = TurretState.FORWARD;

    // OpMode variables
    robot R;
    Telemetry telemetry;
    Gamepad currentGamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public turretFSM(robot Robot, Telemetry t, Gamepad g1, Gamepad pg1) {
        R = Robot;
        telemetry = t;
        currentGamepad1 = g1;
        previousGamepad1 = pg1;
    }

    // Method to move to a targeted position
    private void moveTo(int position) {
        R.turret.setTargetPosition(position);
        R.turret.setTargetPositionTolerance(position_tolerance);
        R.turret.setPower(1);
        R.turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        R.turret.setPower(0);
    }

    // Method to add encoders and status to telemetry
    private void updateTelemetry(String status) {
        // Add encoder position to telemetry
        telemetry.addData("Encoder Ticks", R.turret.getCurrentPosition());
        // Add lift position to telemetry
        telemetry.addData("Status of Turret", status);
    }

    public void teleopUpdate(){
        telemetry.addLine("Turret Data");
                // State machine switch statement
        switch(turretState){
            // Turret set to forward
            case FORWARD:
                // Check position and move if not at forward_position
                if(abs(R.turret.getCurrentPosition() - forward_position) >= position_tolerance){
                    moveTo(forward_position);
                    telemetry.addData("Turret Moved", "TRUE");
                } else {
                    telemetry.addData("Turret Moved", "FALSE");
                }

                // State inputs
                if(currentGamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    turretState = TurretState.LEFT;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else if (currentGamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    turretState = TurretState.RIGHT;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else {
                    telemetry.addData("Turret Move Requested", "FALSE");
                }

                updateTelemetry("FORWARD");

                break;

            // Turret set to left
            case LEFT:
                // Check position and move if not at left_position
                if(abs(R.turret.getCurrentPosition() - left_position) >= position_tolerance){
                    moveTo(left_position);
                    telemetry.addData("Turret Moved", "TRUE");
                } else {
                    telemetry.addData("Turret Moved", "FALSE");
                }

                // State inputs
                if(currentGamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    turretState = TurretState.EXTENDED_LEFT;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else if (currentGamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    turretState = TurretState.FORWARD;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else {
                    telemetry.addData("Turret Move Requested", "FALSE");
                }

                updateTelemetry("LEFT");

                break;

            // Turret set to right
            case RIGHT:
                // Check position and move if not at right_position
                if(abs(R.turret.getCurrentPosition() - right_position) >= position_tolerance){
                    moveTo(right_position);
                    telemetry.addData("Turret Moved", "TRUE");
                } else {
                    telemetry.addData("Turret Moved", "FALSE");
                }

                // State inputs
                if(currentGamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    turretState = TurretState.FORWARD;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else if (currentGamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    turretState = TurretState.EXTENDED_RIGHT;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else {
                    telemetry.addData("Turret Move Requested", "FALSE");
                }

                updateTelemetry("RIGHT");

                break;

            // Turret set to left
            case EXTENDED_LEFT:
                // Check position and move if not at extended_left_position
                if(abs(R.turret.getCurrentPosition() - extended_left_position) >= position_tolerance){
                    moveTo(extended_left_position);
                    telemetry.addData("Turret Moved", "TRUE");
                } else{
                    telemetry.addData("Turret Moved", "FALSE");
                }

                // State inputs
                if (currentGamepad1.dpad_right && !previousGamepad1.dpad_right) {
                    turretState = TurretState.LEFT;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else {
                    telemetry.addData("Turret Move Requested", "FALSE");
                }

                updateTelemetry("EXTENDED_LEFT");

                break;

            // Turret set to right
            case EXTENDED_RIGHT:
                // Check position and move if not at extended_right_position
                if(abs(R.turret.getCurrentPosition() - extended_right_position) >= position_tolerance){
                    moveTo(extended_right_position);
                    telemetry.addData("Turret Moved", "TRUE");
                }
                else
                {
                    telemetry.addData("Turret Moved", "FALSE");
                }

                // State inputs
                if(currentGamepad1.dpad_left && !previousGamepad1.dpad_left) {
                    turretState = TurretState.RIGHT;
                    telemetry.addData("Turret Move Requested", "TRUE");
                } else {
                    telemetry.addData("Turret Move Requested", "FALSE");
                }

                updateTelemetry("EXTENDED_RIGHT");

                break;
        }
    }
}
