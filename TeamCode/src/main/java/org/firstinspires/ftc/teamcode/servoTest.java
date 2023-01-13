package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

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
            if(gamepad1.x){
                R.claw.setPosition(0.0);
            }
            if(gamepad1.a) {
                R.claw.setPosition(.29);
            }
            if(gamepad1.b) {
                R.claw.setPosition(1.0);
            }

        }
    }
}
