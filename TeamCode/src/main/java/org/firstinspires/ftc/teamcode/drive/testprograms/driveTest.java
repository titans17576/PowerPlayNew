package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="driveTest")
public class driveTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        R.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        double test = 0;
        double slow = 1;
        while(opModeIsActive()){
            // Drive calculations
            // Servo controls
            if(gamepad1.x){
                R.leftFront.setPower(0.5);
            }
            if(gamepad1.y){
                R.leftRear.setPower(0.5);
            }
            if(gamepad1.a){
                R.rightFront.setPower(0.5);
            }
            if(gamepad1.b){
                R.rightRear.setPower(0.5);
            }
            if(gamepad1.left_bumper){
                R.rightRear.setPower(0);
                R.rightFront.setPower(0);
                R.leftRear.setPower(0);
                R.leftFront.setPower(0);
            }


            telemetry.addData("Front", R.leftFront.getPower()+" "+R.rightFront.getPower());
            telemetry.addData("Back", R.leftRear.getPower()+" "+R.rightRear.getPower());

            telemetry.update();
        }
    }

    // Power calculation method
    static double calcPower(double power, double slow){
        return power * power * Math.signum(power) * slow;
    }
}
