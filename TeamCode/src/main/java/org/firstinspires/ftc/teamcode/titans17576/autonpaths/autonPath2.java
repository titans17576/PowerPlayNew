package org.firstinspires.ftc.teamcode.titans17576.autonpaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.armFSM;
import org.firstinspires.ftc.teamcode.clawFSM;
import org.firstinspires.ftc.teamcode.liftFSM;
import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.titans17576.vision.vision;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.turretFSM;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="auton2")
public class autonPath2 extends LinearOpMode {

    vision.location loc;
    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);

        vision V = new vision();
        V.initVision(hardwareMap);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        R.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        turretFSM TurretFSM = new turretFSM(R, telemetry, currentGamepad1, previousGamepad1);
        liftFSM LiftFSM = new liftFSM(R, telemetry, currentGamepad1, previousGamepad1);
        armFSM ArmFSM = new armFSM(R, telemetry, currentGamepad1, previousGamepad1);
        clawFSM ClawFSM = new clawFSM(R, telemetry, currentGamepad1, previousGamepad1);

        AtomicReference<liftFSM.LiftState> liftState = new AtomicReference<>(liftFSM.LiftState.ZERO);
        AtomicReference<clawFSM.ClawState> clawState = new AtomicReference<>(clawFSM.ClawState.CLOSED);
        AtomicReference<armFSM.ArmState> armState = new AtomicReference<>(armFSM.ArmState.RETRACTED);
        AtomicReference<turretFSM.TurretState> turretState = new AtomicReference<>(turretFSM.TurretState.FORWARD);

        Trajectory m = R.trajectoryBuilder(new Pose2d())
                .strafeRight(23)
                .build();

        Trajectory g = R.trajectoryBuilder(new Pose2d())
                .strafeRight(46)
                .build();


        TrajectorySequence path2 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
                .forward(5.0)
                .strafeRight(23.0)
                .forward(35.0)
                .waitSeconds(2.0)
                //stuff here
                .forward(11.0)
                .strafeLeft(46.0)
                //.strafeRight(58.0)
                .waitSeconds(2.0)
                .build();


        while (!opModeIsActive()&&!isStopRequested()) {
            loc = V.checkFrame();
            telemetry.addData("Color",loc.toString());
            telemetry.update();
        }
        waitForStart();


        R.followTrajectorySequence(path2);

        R.setPoseEstimate(new Pose2d());

        if(loc == vision.location.MAGENTA){
            R.followTrajectory(m);
        }
        else if(loc == vision.location.GREEN){
            R.followTrajectory(g);
        }
    }
}
