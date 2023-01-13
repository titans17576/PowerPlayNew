package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="auton4")
public class autonPath4 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);
        TrajectorySequence path7 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .forward(5.0)
            .strafeRight(23.0)
            .forward(35.0)
            .waitSeconds(2.0)
            //stuff here
            .forward(11.0)
            .strafeLeft(50.0)
            .strafeRight(62.0)
            .waitSeconds(2.0)
            .build();

        R.followTrajectorySequence(path7);
    }
}
