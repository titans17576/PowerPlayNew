package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="servoTest")

public class servoTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("pos", R.arm.getPosition());
            telemetry.addData("dir", R.arm.getDirection());
            telemetry.update();
        }
    }
}