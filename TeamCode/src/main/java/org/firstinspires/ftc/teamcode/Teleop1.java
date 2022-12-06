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
        double test = 0;
        double slow = 1;
        while(opModeIsActive()){
            // Lift set to 0
            if (gamepad1.dpad_down) {
                R.slide.setTargetPosition(0);
                R.slide.setPower(-0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status of Lift", "0");
                    telemetry.update();
                }
                R.slide.setPower(0);
            }

            // Lift set to 1/3
            if (gamepad1.dpad_left) {
                R.slide.setTargetPosition(663);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status of Lift", "1/3");
                    telemetry.addData("Ticks", R.slide.getTargetPosition());
                    telemetry.update();
                }
            }

            // Lift set to 2/3
            if (gamepad1.dpad_up) {
                R.slide.setTargetPosition(1362);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status of Lift", "2/3");
                    telemetry.addData("Ticks", R.slide.getTargetPosition());
                    telemetry.update();
                }
            }


            // Lift set to full
            if (gamepad1.dpad_right) {
                R.slide.setTargetPosition(1980);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()){
                    telemetry.addData("Status of Lift", "3/3");
                    telemetry.addData("Ticks", R.slide.getTargetPosition());
                    telemetry.update();
                }
            }

            // Emergency stop for lift
            if (gamepad1.a){
                R.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            // Manual control for lift
            if (gamepad1.x) {
                R.slide.setTargetPosition(R.slide.getCurrentPosition()+100);
                R.slide.setPower(0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()) {
                    telemetry.addData("Status of Lift", "+100 ticks");
                    telemetry.update();
                }
            }

            // Manual control for lift
            if (gamepad1.b) {
                R.slide.setTargetPosition(R.slide.getCurrentPosition()-100);
                R.slide.setPower(-0.8);
                R.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(R.slide.isBusy()) {
                    telemetry.addData("Status of Lift", "-100 ticks");
                    telemetry.update();
                }
            }

            // Telemetry tick update
            telemetry.addData("Ticks", R.slide.getCurrentPosition());

            // Drive inputs
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;

            // Drive calculations
            R.leftRear.setPower(Range.clip(calcPower(drive + turn - strafe, slow),-slow,slow));
            R.leftFront.setPower(Range.clip(calcPower(drive + turn + strafe, slow),-slow,slow));
            R.rightRear.setPower(Range.clip(calcPower(drive - turn + strafe, slow),-slow,slow));
            R.rightFront.setPower(Range.clip(calcPower(drive - turn - strafe, slow),-slow,slow));

            // Servo controls
            if(gamepad1.left_bumper){
                //R.arm.setPosition(1.0);
                slow = 1;
            }
            if(gamepad1.right_bumper) {
                //R.arm.setPosition(.29);
                slow = 0.5;
            }


            telemetry.addData("Front", R.leftFront.getPower()+" "+R.rightFront.getPower());
            telemetry.addData("Back", R.leftRear.getPower()+" "+R.rightRear.getPower());
            telemetry.addData("Servo", R.arm.getPosition());
            test += R.arm.getPosition();
            telemetry.addData("Test", test);
            telemetry.update();
        }
    }

    // Power calculation method
    static double calcPower(double power, double slow){
        return power * power * Math.signum(power) * slow;
    }
}
