package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Teleop1")
public class Teleop1 extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            /*R.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_x,
                            -gamepad1.left_stick_y,
                            gamepad1.right_stick_x
                    )
            );
            R.update();*/

            /*float y1 = -gamepad1.left_stick_y;
            float x1 = gamepad1.left_stick_x;
            float yaw = gamepad1.right_stick_x;
            float shift = 1f;


            int reverse;
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
            telemetry.addData("lf", lf);
            telemetry.addData("rf", rf);
            telemetry.addData("lb", lb);
            telemetry.addData("rb", rb);
            telemetry.update();
            R.rightFront.setPower(rf);
            R.leftFront.setPower(lf);
            R.leftRear.setPower(lb);
            R.rightRear.setPower(rb);*/
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double slow = (gamepad1.left_bumper || gamepad2.left_bumper)? 0.3 : 1.0;
            R.leftRear.setPower(calcPower(drive + turn - strafe, slow));
            R.leftFront.setPower(calcPower(drive + turn + strafe, slow));
            R.rightRear.setPower(calcPower(drive - turn + strafe, slow));
            R.rightFront.setPower(calcPower(drive - turn - strafe, slow));
        }

    }
    static double calcPower(double power, double slow){
        return power * power * Math.signum(power) * slow;
    }
}
