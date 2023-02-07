package org.firstinspires.ftc.teamcode.titans17576.autonpaths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.armFSM;
import org.firstinspires.ftc.teamcode.clawFSM;
import org.firstinspires.ftc.teamcode.liftFSM;
import org.firstinspires.ftc.teamcode.robot;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.turretFSM;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="auton5")
public class autonPath5 extends LinearOpMode {

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

        TrajectorySequence path8 = R.trajectorySequenceBuilder(new Pose2d(0, 0,Math.toRadians(0)))
            .forward(15.0)
            .waitSeconds(4.0)
            .forward(36.0)
            .strafeRight(35.0)
            .waitSeconds(2.0)
            .strafeLeft(60.0)
            .strafeRight(37.0)
            .waitSeconds(2.0)
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

        R.followTrajectorySequence(path8);
    }
}
