package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp(name="servoTest")

public class servoTest extends LinearOpMode {
    Servo.Direction down = Servo.Direction.REVERSE;
    Servo.Direction up = Servo.Direction.FORWARD;

    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            /*telemetry.addData("pos", R.arm.getPosition());
            telemetry.addData("dir", R.arm.getDirection());
            telemetry.update();*/
            R.arm.setPosition(0.0);
            R.arm.setPosition(1.0);
        }
    }
}
