
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="liftTest")

public class liftTest extends LinearOpMode{
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();
        R.leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        R.rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while(opModeIsActive()) {
            // Manual control for lift
            if (gamepad1.b) {
                R.leftSlide.setTargetPosition(R.leftSlide.getCurrentPosition()-20);
                R.rightSlide.setTargetPosition(R.rightSlide.getCurrentPosition()-20);

                R.leftSlide.setPower(0.8);
                R.rightSlide.setPower(0.8);
                R.leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.leftSlide.isBusy() || R.rightSlide.isBusy()) {
                    telemetry.addData("Status of Lift", "-20 ticks");
                    telemetry.update();
                }
            }

            // Manual control for lift
            if (gamepad1.y) {
                R.leftSlide.setTargetPosition(R.leftSlide.getCurrentPosition()+20);
                R.rightSlide.setTargetPosition(R.rightSlide.getCurrentPosition()+20);

                R.leftSlide.setPower(0.8);
                R.rightSlide.setPower(0.8);
                R.leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                R.rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.leftSlide.isBusy() || R.rightSlide.isBusy()) {
                    telemetry.addData("Status of Lift", "+20 ticks");
                    telemetry.update();
                }
            }
            telemetry.addData("Ticks L",R.leftSlide.getCurrentPosition());
            telemetry.addData("Ticks R",R.rightSlide.getCurrentPosition());

            telemetry.update();
        }
    }
}
