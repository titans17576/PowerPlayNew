package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="testEncoder")
public class testEncoder extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();
        //R.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while(opModeIsActive()){
            /*if (gamepad1.a) {

                R.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            if(gamepad1.y){
                R.slide.setTargetPosition(0);
                R.slide.setPower(0.2);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status", "highpitch stuff");
                    telemetry.update();
                }
                R.slide.setPower(0);
            }
            if (gamepad1.b) {
                R.slide.setTargetPosition(1990);//420 1990
                R.slide.setPower(1);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
            }
            if (gamepad1.dpad_right) {
               R.slide.setTargetPosition(0);
               R.slide.setPower(-0.8);
               R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               while(R.slide.isBusy()){
                   telemetry.addData("Status", "0");
                   telemetry.update();
               }
               R.slide.setPower(0);
            }
            if (gamepad1.dpad_left) {
                R.slide.setTargetPosition(663);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status", "0");
                    telemetry.update();
                }

            }
            if (gamepad1.dpad_up) {
                R.slide.setTargetPosition(1362);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status", "0");
                    telemetry.update();
                }

            }
            if (gamepad1.dpad_down) {
                R.slide.setTargetPosition(1990);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status", "0");
                    telemetry.update();
                }

            }
            telemetry.addData("Ticks", R.slide.getCurrentPosition());
            telemetry.update();*/
        }
    }
}