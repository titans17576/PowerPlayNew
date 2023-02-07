package org.firstinspires.ftc.teamcode.titans17576.autonpaths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.armFSM;
import org.firstinspires.ftc.teamcode.clawFSM;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.liftFSM;
import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.turretFSM;

import java.util.concurrent.atomic.AtomicReference;

@Config
@Autonomous(group = "drive")
public class autonTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);
        R.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        turretFSM TurretFSM = new turretFSM(R, telemetry, currentGamepad1, previousGamepad1);
        liftFSM LiftFSM = new liftFSM(R, telemetry, currentGamepad1, previousGamepad1);
        armFSM ArmFSM = new armFSM(R, telemetry, currentGamepad1, previousGamepad1);
        clawFSM ClawFSM = new clawFSM(R, telemetry, currentGamepad1, previousGamepad1);

        Trajectory trajectoryForward = R.trajectoryBuilder(new Pose2d())
                .forward(12)
                .build();

        Trajectory trajectoryBackward = R.trajectoryBuilder(trajectoryForward.end())
                .back(12)
                .build();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            R.followTrajectory(trajectoryForward);
            LiftFSM.autonUpdate(liftFSM.LiftState.ZERO);
            ClawFSM.autonUpdate(clawFSM.ClawState.CLOSED);
            ArmFSM.autonUpdate(armFSM.ArmState.RETRACTED);
            TurretFSM.autonUpdate(turretFSM.TurretState.FORWARD);
            
            R.followTrajectory(trajectoryBackward);
            LiftFSM.autonUpdate(liftFSM.LiftState.ZERO);
            ClawFSM.autonUpdate(clawFSM.ClawState.OPEN);
            ArmFSM.autonUpdate(armFSM.ArmState.RETRACTED);
            TurretFSM.autonUpdate(turretFSM.TurretState.FORWARD);
        }
    }
}