package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class testFSM {
    // Enum for state memory
    private enum TestState {
        ONE,
        TWO
    };

    // Position variables


    // LiftState instance variable
    TestState testState = TestState.ONE;

    // OpMode variables
    robot R;
    Telemetry telemetry;
    Gamepad gamepad1;
    Gamepad previousGamepad1;

    // Import opmode variables when instance is created
    public testFSM(robot Robot, Telemetry t, Gamepad g1, Gamepad gp1) {
        R = Robot;
        telemetry = t;
        gamepad1 = g1;
        previousGamepad1 = gp1;
    }

    // Update method for teleop implementation
    public void teleopUpdate() {
        telemetry.addLine("Lift Data");

        switch (testState) {

            case ONE:
                break;
        }
    }
}

