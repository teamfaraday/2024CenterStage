package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "antiGrav")
public class antiGrav extends OpModes {
    public void runOpMode() {
        super.runOpMode();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.left_bumper){
                slide.down(isSlow, nerf);
            } else if (gamepad1.right_bumper) {
                slide.up(isSlow, nerf);
            } else {
                slide.antiGrav();
            }
        }

        //failsafe
        if (gamepad1.left_bumper && gamepad1.left_trigger >= 0.6 && gamepad1.right_bumper && gamepad1.right_trigger >= 0.6) { stopped = true;}

        //slow movement
        isSlow = gamepad1.right_trigger >= 0.6;

    }

}