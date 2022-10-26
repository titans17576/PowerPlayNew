package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TeleOp extends LinearOpMode {
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            R.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_x,
                            -gamepad1.left_stick_y,
                            gamepad1.right_stick_x
                    )
            );
            R.update();
            /**
            y1 = -gamepad1.left_stick_y;
            x1 = gamepad1.left_stick_x;
            yaw = gamepad1.right_stick_x;




            if (gamepad1.right_bumper){
                reverse = -1;
            }
            else {
                reverse = 1;
            }

            double lb = Range.clip((((y1 * reverse) + yaw - (x1 * reverse)) * shift), -1.0, 1.0);
            double rb = Range.clip((((y1 * reverse) - yaw + (x1 * reverse)) * shift), -1.0, 1.0);
            double lf = Range.clip((((y1 * reverse) + yaw + (x1 * reverse)) * shift), -1.0, 1.0);
            double rf = Range.clip((((y1 * reverse) - yaw - (x1 * reverse)) * reverse * shift), -1.0, 1.0);

            robot.q1.setPower(rf);
            robot.q2.setPower(lf);
            robot.q3.setPower(lb);
            robot.q4.setPower(rb);
             **/
        }
    }
}
