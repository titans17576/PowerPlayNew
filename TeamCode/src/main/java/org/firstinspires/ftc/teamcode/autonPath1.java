package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="auton1")
public class autonPath1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);

        TrajectorySequence path1 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .turn(Math.toRadians(90))
            .splineTo(new Vector2d(-60.0, -60.0), Math.toRadians(180))
            .build();

        TrajectorySequence path2 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .turn(Math.toRadians(-90))
            .forward(120.0)
            .build();

        waitForStart();
        R.followTrajectorySequence(path1);
        int count = 0;
        while(count < 4){
            R.followTrajectorySequence(path2);

            count++;
        }
    }
}
