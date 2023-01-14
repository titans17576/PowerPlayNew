package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="auton2")
public class autonPath2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot R = new robot(hardwareMap);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        turretFSM TurretFSM = new turretFSM(R, telemetry, currentGamepad1, previousGamepad1);
        liftFSM LiftFSM = new liftFSM(R, telemetry, currentGamepad1, previousGamepad1);
        armFSM ArmFSM = new armFSM(R, telemetry, currentGamepad1, previousGamepad1);
        clawFSM ClawFSM = new clawFSM(R, telemetry, currentGamepad1, previousGamepad1);

        AtomicReference<liftFSM.LiftState> liftState = new AtomicReference<>(liftFSM.LiftState.ZERO);
        AtomicReference<clawFSM.ClawState> clawState = new AtomicReference<>(clawFSM.ClawState.CLOSED);
        AtomicReference<armFSM.ArmState> armState = new AtomicReference<>(armFSM.ArmState.RETRACTED);
        AtomicReference<turretFSM.TurretState> turretState = new AtomicReference<>(turretFSM.TurretState.FORWARD);

        TrajectorySequence path3 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .forward(15.0)
            .waitSeconds(2.0)
            .back(10.0)
            .strafeRight(20.0)
            .forward(45.0)
            .strafeLeft(45.0)
            .waitSeconds(2.0)
            .strafeRight(60.0)
            .waitSeconds(2.0)
            .strafeLeft(60.0)
            .waitSeconds(2.0)
            .strafeRight(60.0)
            .waitSeconds(2.0)
            .strafeLeft(15.0)
            .back(20.0)
            .strafeLeft(40.0)
            .strafeRight(40.0)
            .addTemporalMarker(0, () -> {
                while (true) {
                    LiftFSM.autonUpdate(liftState.get());
                    ClawFSM.autonUpdate(clawState.get());
                    ArmFSM.autonUpdate(armState.get());
                    TurretFSM.autonUpdate(turretState.get());
                }
            })
            .build();

        waitForStart();

        R.followTrajectorySequence(path3);
    }
}
