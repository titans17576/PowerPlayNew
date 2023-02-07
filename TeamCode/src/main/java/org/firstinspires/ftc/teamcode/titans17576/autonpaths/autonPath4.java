package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="autoBeelineRight")
public class autonPath4 extends LinearOpMode {

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

        Trajectory c = R.trajectoryBuilder(new Pose2d())
                .strafeLeft(46)
                .build();

        Trajectory m = R.trajectoryBuilder(new Pose2d())
                .strafeLeft(23)
                .build();


        TrajectorySequence path = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
                .forward(5.0)
                .strafeRight(23.0)
                .forward(46.0)
                .build();


        while (!opModeIsActive()&&!isStopRequested()) {
            loc = V.checkFrame();
            telemetry.addData("Color",loc.toString());
            telemetry.update();
        }
        waitForStart();


        R.followTrajectorySequence(path);

        R.setPoseEstimate(new Pose2d());

        if(loc == vision.location.CYAN){
            R.followTrajectory(c);
        }
        else if(loc == vision.location.MAGENTA){
            R.followTrajectory(m);
        }
    }
}