package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.faradaycode.components.Hang;
import org.firstinspires.ftc.teamcode.faradaycode.components.Intake;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;
import org.firstinspires.ftc.teamcode.faradaycode.components.Drone;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.components.BannerBox;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleIsOpping")
public class TeleOp extends OpMode {

    public Hang hang;
    public Intake intake;
    public Slide slide;
    public Drone drone;
    public DriveTrainTeleOp driveTrain;
    public BannerBox bannerBox;

    public double nerf = 1;


    @Override
    public void init() {
        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
        drone = new Drone(hardwareMap);
        driveTrain = new DriveTrainTeleOp(hardwareMap);
        bannerBox = new BannerBox(hardwareMap);
    }

    public void start() {
        drone.init();
        bannerBox.intakePos();
    }


    @Override
    public void loop() {
        telemetry.addData("nerf", nerf);
        telemetry.update();

        //drivetrain
        if (gamepad1.right_trigger <= 0.5) {
            driveTrain.move(false, gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, nerf);
        } else
        {
            driveTrain.move(true, gamepad1.left_stick_y,gamepad1.left_stick_x, gamepad1.right_stick_x, nerf);
        }

        //slide
        if (gamepad1.left_bumper){
            if (gamepad1.right_trigger <= 0.5) {
                slide.down(false);
            } else {
                slide.down(true);
            }
        }
        if (gamepad1.right_bumper) {
            if (gamepad1.right_trigger <= 0.5) {
                slide.up(false);
            } else {
                slide.up(true);
            }
        }
        if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
            slide.antiGrav();
        }

        //intake
        if (gamepad1.b) {
            intake.activate(nerf);
        }
        if (gamepad1.a) {
            intake.reverse(nerf);
        }
        if (!gamepad1.b && !gamepad1.a){
            intake.deactivate();
        }

        //hang
        if (gamepad1.dpad_up) {
            hang.up();
        }
        if (gamepad1.dpad_down) {
            hang.down();
        }
        if (!gamepad1.dpad_up && !gamepad1.dpad_down) {
            hang.deactivate();
        }

        //drone
        if (gamepad1.left_trigger > 0.8 && gamepad1.right_trigger > 0.8){
            drone.launch();
        }

        //bannerbox
        if (gamepad1.x) {
            bannerBox.intakePos();
        }
        if (gamepad1.y) {
            bannerBox.dropPos();
        }

        //nerf
        if (gamepad1.dpad_right) {
            nerf += 0.001;
        }
        if (gamepad1.dpad_left) {
            nerf -= 0.001;
        }

    }
}









