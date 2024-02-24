package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.faradaycode.components.Hang;
import org.firstinspires.ftc.teamcode.faradaycode.components.Intake;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;
import org.firstinspires.ftc.teamcode.faradaycode.components.Drone;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.components.Bannerbox;

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

    public ElapsedTime timeSpent = new ElapsedTime();

    public double t;
    public double nerf = 1;


    @Override
    public void init() {
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
        bannerbox.intakePos();
        bannerbox.close();
    }


    @Override
    public void loop() {
        telemetry.addData("nerf", nerf);
        telemetry.update();

        //drivetrain
        double forwardSpeed = gamepad1.left_stick_y;
        double strafeSpeed = gamepad1.left_stick_x;
        double turnSpeed = gamepad1.right_stick_x;
        driveTrain.move(forwardSpeed, strafeSpeed, turnSpeed, nerf);

        //slides
        if (gamepad1.left_bumper){
            slide.down();
        }
        if (gamepad1.right_bumper) {
            slide.up();
        }
        if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
            slide.antiGrav();
        }

        //intake
        if (gamepad1.b) {
            intake.activate();
        }
        if (gamepad1.a) {
            intake.reverse();
        }
        else if (!gamepad1.b && !gamepad1.a){
            intake.deactivate();
        }

        //Hanging
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

        //outtake
        if (gamepad1.x){
            bannerbox.dropPos();
            t = getRuntime();
            if (getRuntime() - t > 1) {
                bannerbox.open();
            }
        }
        if (gamepad1.y){
            bannerbox.intakePos();
            bannerbox.close();
        }

        if (gamepad1.dpad_right) {
            nerf += 0.001;
            telemetry.addData("nerf", nerf);
            telemetry.update();
        }
        if (gamepad1.dpad_left) {
            nerf -= 0.001;
            telemetry.addData("nerf", nerf);
            telemetry.update();
        }

        telemetry.addData("nerf", nerf);
        telemetry.update();

    }
}









