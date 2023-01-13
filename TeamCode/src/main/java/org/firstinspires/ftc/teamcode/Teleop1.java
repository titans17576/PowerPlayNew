package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import java.util.HashMap;

@TeleOp(name="Teleop1")
public class Teleop1 extends LinearOpMode {


    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        Gamepad previousGamepad1 = new Gamepad();
        liftFSM LiftFSM = new liftFSM(R, telemetry, gamepad1,previousGamepad1);
        clawFSM ClawFSM = new clawFSM(R, telemetry, gamepad1, previousGamepad1);
        turretFSM TurretFSM = new turretFSM(R, telemetry, gamepad1, previousGamepad1);
        waitForStart();

        while(opModeIsActive()){
            // Turret finite state machine update
            TurretFSM.teleopUpdate();

            // Lift finite state machine update
            LiftFSM.teleopUpdate();

            // Claw finite state machine update
            ClawFSM.teleopUpdate();

            // Update telemetry data
            telemetry.update();

            // Update previousGamepad with current states
            previousGamepad1 = gamepad1;
        }
    }
}
;