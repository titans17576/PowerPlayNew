package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

public class driveControls {
    double slow = 1; //
    double regSpeed;
    double slowSpeed;
    public driveControls(){
        this(1,0.15);
    }
    public driveControls(double regularSpeed, double slowedSpeed){
        regSpeed = regularSpeed;
        slowSpeed = slowedSpeed;
    }
    public void initialize(robot R){
        R.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        R.rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void drive(robot R, Gamepad gamepad1) {
        if(gamepad1.left_trigger >= 0.5) {
            slow = slowSpeed;
        }
        else{
            slow = regSpeed;
        }
        // Drive inputs
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;

        // Drive calculations
        R.leftRear.setPower(calcPower(drive + turn - strafe, slow));
        R.leftFront.setPower(calcPower(drive + turn + strafe, slow));
        R.rightRear.setPower(calcPower(drive - turn + strafe, slow));
        R.rightFront.setPower(calcPower(drive - turn - strafe, slow));

    }
    static double calcPower(double power, double slow) {
        return Range.clip(power * power * Math.signum(power) * slow,-slow,slow);
    }
}