package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="auton")
public class autonPath3 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);
        TrajectorySequence path4 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .strafeLeft(23.0)
            .waitSeconds(2.0)
            .forward(50.0)
            .back(10.0)
            .build();
        TrajectorySequence path5 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .waitSeconds(2.0)
            .forward(10.0)
            .waitSeconds(2.0)
            .back(10.0)
            .build();
        TrajectorySequence path6 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .back(10.0)
            .build();

        int n = 0;
        waitForStart();
        R.followTrajectorySequence(path4);
        while (n<3)
        {
            R.followTrajectorySequence(path5);
            n++;
        }
        R.followTrajectorySequence(path6);
    }
}
