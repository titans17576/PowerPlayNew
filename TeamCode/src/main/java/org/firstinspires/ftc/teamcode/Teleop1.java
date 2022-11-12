package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Teleop1")
public class Teleop1 extends LinearOpMode {
    @Override
    public void runOpMode(){
        robot R = new robot(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.dpad_down) {
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
            if (gamepad1.dpad_right) {
                R.slide.setTargetPosition(1990);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status", "0");
                    telemetry.update();
                }

            }
            if (gamepad1.a) {

                R.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            telemetry.addData("Ticks", R.slide.getCurrentPosition());
            telemetry.update();
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double slow = 1 - gamepad1.right_trigger;

            R.leftRear.setPower(calcPower(drive + turn - strafe, slow));
            R.leftFront.setPower(calcPower(drive + turn + strafe, slow));
            R.rightRear.setPower(calcPower(drive - turn + strafe, slow));
            R.rightFront.setPower(calcPower(drive - turn - strafe, slow));

            if (gamepad1.left_bumper){
                R.arm.setPosition(R.arm.getPosition() + 0.1);
            }
            if (gamepad1.right_bumper){
                R.arm.setPosition(R.arm.getPosition() - 0.1);
            }

        }
    }
    static double calcPower(double power, double slow){
        return power * power * Math.signum(power) * slow;
    }
}
