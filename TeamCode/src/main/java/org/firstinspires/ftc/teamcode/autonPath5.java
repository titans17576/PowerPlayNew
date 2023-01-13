package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="auton5")
public class autonPath5 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);
        TrajectorySequence path8 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .forward(15.0)
            .waitSeconds(4.0)
            .forward(36.0)
            .strafeRight(35.0)
            .waitSeconds(2.0)
            .strafeLeft(60.0)
            .strafeRight(37.0)
            .waitSeconds(2.0)
            .build();

        R.followTrajectorySequence(path8);
    }
}
