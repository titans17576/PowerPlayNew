package org.firstinspires.ftc.teamcode.titans17576.autonpaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.titans17576.vision.vision;

public class autonUtil {
    public enum side{
        LEFT,
        RIGHT
    }
    robot R;
    public autonUtil(robot R){
        this.R = R;
    }
    public void park(Pose2d cur, vision.location color,side s){
        Pose2d p = new Pose2d();
        int x, y = -14;
        switch(s){
            case LEFT:
                x=-58;
                break;
            case RIGHT:
                x=15;
                break;
        }
        switch(color){
            case MAGENTA:
                y+=23;
                break;
            case GREEN:
                y+=46;
                break;
        }
        Trajectory t = R.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(cur.getX(),-14))
                .build();
    }

}
