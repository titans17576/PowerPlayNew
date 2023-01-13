package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class clawFSM {
    // Enum for state memory
    private enum ClawState {
        CLOSED,
        OPEN
    };

    // Position variables
    final double position_tolerance = 0;
    final double closed_position = 1;
    final double open_position = 0;

    // ClawState instance variable
    ClawState clawState = ClawState.CLOSED;

    // OpMode variables
    robot R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public clawFSM(robot Robot, Telemetry t, Gamepad g1, Gamepad pg1) {
        R = Robot;
        telemetry = t;
        gamepad1 = g1;
        previousGamepad1 = pg1;
    }

    public void teleopUpdate() {
        telemetry.addLine("Claw Data");

        // State machine switch statement
        switch(clawState){
            // Claw open
            case CLOSED:
                // Check position and move if not at closed_position
                if(abs(R.claw.getPosition() - closed_position) >= position_tolerance){
                    R.claw.setPosition(closed_position);
                    telemetry.addData("Claw Moved", "TRUE");
                } else {
                    telemetry.addData("Claw Moved", "FALSE");
                }

                // State inputs
                if(gamepad1.right_bumper && !previousGamepad1.left_bumper){
                    clawState = ClawState.OPEN;
                    telemetry.addData("Claw Move Requested", "TRUE");
                } else {
                    telemetry.addData("Claw Move Requested", "FALSE");
                }

                // Add encoder position to telemetry
                telemetry.addData("Claw Position", R.claw.getPosition());
                // Add lift position to telemetry
                telemetry.addData("Status of Claw", "CLOSED");

                break;
            // Claw closed
            case OPEN:
                // Check position and move if not at open_position
                if(abs(R.claw.getPosition() - open_position) >= position_tolerance){
                    R.claw.setPosition(open_position);
                    telemetry.addData("Claw Moved", "TRUE");
                } else {
                    telemetry.addData("Claw Moved", "FALSE");
                }

                // State inputs
                if(gamepad1.left_bumper && !previousGamepad1.left_bumper){
                    clawState = ClawState.CLOSED;
                    telemetry.addData("Claw Move Requested", "TRUE");
                } else {
                    telemetry.addData("Claw Move Requested", "FALSE");
                }

                // Add encoder position to telemetry
                telemetry.addData("Claw Position", R.claw.getPosition());
                // Add lift position to telemetry
                telemetry.addData("Status of Claw", "OPEN");

                break;
        }
    }
}
