package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="autonTest")

public class autonTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);

        TrajectorySequence pathd = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
                .forward(5.0)
                .waitSeconds(5.0)
                .strafeRight(35.0)
                .waitSeconds(2.0)
                .strafeLeft(5.0)
                .turn(Math.toRadians(90.0))
                .build();

        waitForStart();

        R.followTrajectorySequence(pathd);
    }
}
