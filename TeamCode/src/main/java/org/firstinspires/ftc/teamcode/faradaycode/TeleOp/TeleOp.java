package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.others.components.Hang;
import org.firstinspires.ftc.teamcode.others.components.Intake;
import org.firstinspires.ftc.teamcode.others.components.Slide;
import org.firstinspires.ftc.teamcode.others.components.Drone;
import org.firstinspires.ftc.teamcode.others.components.DriveTrain.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.others.components.Bannerbox;

import com.qualcomm.robotcore.util.ElapsedTime;

   /*
      Drivetrain: Sticks
      intake: a and b
      hanging: dpad up and down
      drone: both triggers
      outtake: x and y
       */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends OpMode {

    public Hang hang;
    public Intake intake;
    public Slide slide;
    public Drone drone;
    public DriveTrainTeleOp driveTrain;
    public Bannerbox bannerbox;

    //todo, move time system to bannerbox
    //todo, test bnerf & fnerf, possibly make its own class to test
    public ElapsedTime timeSpent = new ElapsedTime();

    public double t;
    public double fnerf = 1;
    public double bnerf = 1;


    @Override
    public void init() {
        //todo, try to couple both import statements tg
        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
        drone = new Drone(hardwareMap);
        driveTrain = new DriveTrainTeleOp(hardwareMap);
        bannerbox = new Bannerbox(hardwareMap);

    }

    public void start() {
        timeSpent.reset();
        drone.init();
    }


    @Override
    public void loop() {
        telemetry.update();
        telemetry.addData("bnerf", bnerf);
        telemetry.addData("fnerf", fnerf);
        telemetry.update();
        telemetry.update();
        telemetry.addData("fnerf", fnerf);
        telemetry.addData("bnerf", bnerf);
        telemetry.update();

        //drivetrain
        double y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;
        driveTrain.move(y, x, rx,bnerf,fnerf);

        //slides
        if (gamepad1.left_bumper){
            slide.down();
        } else if (!gamepad2.left_bumper) {
            if (!gamepad2.a) {
                slide.antiGrav();
            }
            else {
                slide.deactivate();
            }
        }
        if (gamepad1.right_bumper) {
            slide.up();
        }

        //intake
        if (gamepad1.b) {
            intake.activate();
        } else if (!gamepad1.b){
            intake.deactivate();
        }
        if (gamepad1.a) {
            intake.reverse();
        }

        //Hanging
        if (gamepad1.dpad_up) {
            hang.up();
        } else if (!gamepad1.dpad_up) {
            hang.deactivate();
        }
        if (gamepad1.dpad_down) {
            hang.down();
        }

        //drone
        if (gamepad1.left_trigger > 0.8 && gamepad1.right_trigger > 0.8){
            drone.launch();
        }

        //outtake
        if (gamepad1.x){
            bannerbox.drop();
            t = getRuntime();
            if (getRuntime() - t > 1000) {
                bannerbox.open();
            }
        }
        if (gamepad1.y){
            bannerbox.close();
            bannerbox.intake();
        }

        if (gamepad2.dpad_up) {
            bnerf += 0.005;
            telemetry.update();
            telemetry.addData("bnerf", bnerf);
            telemetry.update();
        }
        if (gamepad2.dpad_down) {
            bnerf -= 0.005;
            telemetry.update();
            telemetry.addData("bnerf", bnerf);
            telemetry.update();
        }
        if (gamepad2.dpad_right) {
            fnerf += 0.005;
            telemetry.update();
            telemetry.addData("fnerf", fnerf);
            telemetry.update();
        }
        if (gamepad2.dpad_right) {
            fnerf -= 0.005;
            telemetry.update();
            telemetry.addData("fnerf", fnerf);
            telemetry.update();
        }

        telemetry.update();
        telemetry.addData("bnerf", bnerf);
        telemetry.addData("fnerf", fnerf);
        telemetry.update();
        telemetry.update();
        telemetry.addData("fnerf", fnerf);
        telemetry.addData("bnerf", bnerf);
        telemetry.update();

    }
}









