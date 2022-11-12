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
