package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.kV;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.kA;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.kStatic;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.TRACK_WIDTH;



import java.util.List;

public class robot extends MecanumDrive {
    public static double LATERAL_MULTIPLIER=1.246; // yo no se que es un LateralMultiplier
    HardwareMap hwMap = null;

    public robot(HardwareMap ahwMap){
        super(kV, kA, kStatic, TRACK_WIDTH, TRACK_WIDTH, LATERAL_MULTIPLIER);

    }
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap; //init hw map for following devices
    }
    @Override
    protected double getRawExternalHeading() {
        return 0;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return null;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {

    }
}
