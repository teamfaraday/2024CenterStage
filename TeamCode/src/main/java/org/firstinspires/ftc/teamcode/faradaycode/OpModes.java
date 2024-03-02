package org.firstinspires.ftc.teamcode.faradaycode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.faradaycode.components.Hang;
import org.firstinspires.ftc.teamcode.faradaycode.components.Intake;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;
import org.firstinspires.ftc.teamcode.faradaycode.components.Drone;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainAuto;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.components.BannerBox;
import org.firstinspires.ftc.teamcode.faradaycode.components.PurplePixel;
import org.firstinspires.ftc.teamcode.faradaycode.components.tFod;


import com.qualcomm.robotcore.util.ElapsedTime;

public class OpModes extends LinearOpMode{
    /*
    Control Hub
        Motors
            0: backLeft
            1: frontLeft
            2: slide2
            3: hang
        Servos
            0: drone
            1: purplePixel
    Expansion Hub
        Motors
            0: intakeA
            1: slide
            2: frontRight
            3: backRight
        Servos
            0: release
            1: intakeB1
            2: intakeB2
            3: rotate1
            4: rotate2 */

    public Hang hang;
    public Intake intake;
    public Slide slide;
    public Drone drone;
    public DriveTrainAuto driveTrainAuto;
    public DriveTrainTeleOp driveTrainTeleOp;
    public BannerBox bannerBox;
    public PurplePixel purplePixel;

    public tFod tFod = new tFod();
    public ElapsedTime timeSpent = new ElapsedTime();

    public boolean stopped = false;
    public double nerf = 0;
    public boolean isSlow = false;

    public void runOpMode() {
        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
        drone = new Drone(hardwareMap);
        driveTrainAuto = new DriveTrainAuto(hardwareMap);
        driveTrainTeleOp = new DriveTrainTeleOp(hardwareMap);
        bannerBox = new BannerBox(hardwareMap);
        purplePixel = new PurplePixel(hardwareMap);
    }


}
