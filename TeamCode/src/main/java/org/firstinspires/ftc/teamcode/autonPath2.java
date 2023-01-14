package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="auton2")
public class autonPath2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);

        TrajectorySequence path3 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
                .forward(15.0)
                .waitSeconds(2.0)
                .back(10.0)
                .strafeRight(20.0)
                .forward(45.0)
                .strafeLeft(45.0)
                .waitSeconds(2.0)
                .strafeRight(60.0)
                .waitSeconds(2.0)
                .strafeLeft(60.0)
                .waitSeconds(2.0)
                .strafeRight(60.0)
                .waitSeconds(2.0)
                .strafeLeft(15.0)
                .back(20.0)
                .strafeLeft(40.0)
                .strafeRight(40.0)
                .build();

        waitForStart();

        R.followTrajectorySequence(path3);
    }
}
