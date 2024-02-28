package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.LinearOpModePlusConstants;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleIsOpping")
public class TeleIsOpping extends LinearOpModePlusConstants {
    public void runOpMode(){
        super.runOpMode();
        waitForStart();

        drone.init();
        bannerBox.intakePos();

        while (opModeIsActive() && !stopped){

            telemetry.addData("nerf", nerf);
            telemetry.update();

            //drivetrain
            driveTrainTeleOp.move(isSlow, nerf, gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            //slide
            if (gamepad1.left_bumper){
                slide.down(isSlow, nerf);
            } else if (gamepad1.right_bumper) {
                slide.up(isSlow, nerf);
            } else {
                slide.antiGrav();
            }

            //intake
            if (gamepad1.b) {
                intake.activate(isSlow, nerf);
                slide.slowDown();
            } else if (gamepad1.a) {
                intake.reverse(isSlow, nerf);
                slide.slowDown();
            } else{
                intake.deactivate();
            }

            //hang
            if (gamepad1.dpad_up) {
                hang.up(isSlow, nerf);
            } else if (gamepad1.dpad_down) {
                hang.down(isSlow, nerf);
            } else {
                hang.deactivate();
            }

            //drone
            if (gamepad1.left_trigger > 0.6 && gamepad1.right_trigger > 0.6){
                drone.launch();
            }

            //purplepixel
            if (gamepad1.left_trigger > 0.6) {
                purplePixel.init();
            } else {
                purplePixel.release();
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

            //failsafe
            if (gamepad1.left_bumper && gamepad1.left_trigger >= 0.6 && gamepad1.right_bumper && gamepad1.right_trigger >= 0.6) { stopped = true;}

            //slow movement
            isSlow = gamepad1.right_trigger >= 0.6;

        }
    }
}