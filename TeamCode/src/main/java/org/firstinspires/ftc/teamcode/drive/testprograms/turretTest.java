package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="turretTest")

public class turretTest extends LinearOpMode {


    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();
        R.turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while(opModeIsActive()) {
            if(gamepad1.b){
                R.turret.setTargetPosition(200);
                R.turret.setPower(0.8);
                R.turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.turret.setPower(0);
            }
            telemetry.addData("Ticks",R.turret.getCurrentPosition());
            telemetry.update();
        }
    }
}
