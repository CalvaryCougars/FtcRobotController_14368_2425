package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
                                                                                                                                                                                                                                                                                                                 
@TeleOp (name = "TestingAndroid", group = "Tele Op")
public class AndroidStudioTestMain extends LinearOpMode {

    Hardware hw = new Hardware(); 
    
    @Override public void runOpMode () { 
        hw.init(hardwareMap);
        
        waitForStart(); 
        hw.clock.reset(); 
        
        boolean lastClawInput = false;
        double lastMonitorTime = -hw.CLAW_SUCCESSIVE_WINDOW;
      
        double leftpwr;
        double rightpwr; 
        while (opModeIsActive()) { 
            telemetry.addData("current Extension (cm): ", hw.liftExtension); 
            telemetry.update(); 
            
            leftpwr = -gamepad1.left_stick_y; 
            rightpwr = -gamepad1.right_stick_y; 
            
            boolean extended = false; 
            
            if (gamepad1.left_bumper){ 
                hw.drive( 1, -1, -1,1); 
            } else if (gamepad1.right_bumper) { 
                hw.drive(-1, 1, 1, -1);
            } else {
                hw.drive(rightpwr, leftpwr, rightpwr, leftpwr);
            }
           // telemetry.addData("Motor Power",hw.drive())
            
            //precise movement
             /* if (gamepad1.right_trigger> 0) {
              // Reduce power by 80% when moving forward
              hw.drive(0.30, 0.3 , 0.30, 0.30);
                            }
            if (gamepad1.left_trigger > 0) {
                 // Reduce power by 80% when moving backward
                hw.drive(-0.30, -0.30 , -0.30, -0.30);
            }
            */
            while(gamepad1.dpad_up)
            {
                hw.drive(0.40, 0.40 , 0.40, 0.40);
            }
            while(gamepad1.dpad_down)
            {
                hw.drive(-0.40, -0.40 , -0.40, -0.40);
            }
            while(gamepad1.dpad_left)
            {
                hw.drive(0.40, -0.40 , -0.40, 0.40);
            }
            while(gamepad1.dpad_right)
            {
                hw.drive(-0.40,0.40, 0.40,-0.40);
            }
            /*
            if((opModeIsActive()) && gamepad1.left_trigger<0.45)
            {
                //hw.diagonal(160, -0.95, 0.25) front of bot facing human player going left to HPzone
                //(dist,powerForFrandBl,powerForFlAndBr)
                //(double fr, double fl, double br, double bl)
                hw.drive(-0.95, 0.25, 0.25, -0.95);
            }
            if((opModeIsActive()) && gamepad1.right_trigger<0.45)
            {
                //hw.diagonal(160, -0.95, 0.25) front of bot facing human player going left to HPzone
                //(dist,powerForFrandBl,powerForFlAndBr)
                //(double fr, double fl, double br, double bl)
                hw.drive(0.25,-0.95,-.95,0.25);
            }
            */
            /*
            if (gamepad1.dpad_up) {
              hw.drive(0.40, 0.40 , 0.40, 0.40);
                            
            } else if (gamepad1.dpad_down) { 
                hw.drive(-0.40, -0.40 , -0.40, -0.40);
            } 
            else if (gamepad1.dpad_left) { 
                hw.drive(0.40, -0.40 , -0.40, 0.40);
              
            } 
            else if (gamepad1.dpad_right) { 
                hw.drive(-0.40,0.40, 0.40,-0.40);
                        }
                        */
                      /*  else {
                            hw.drive(0,0,0,0);
                        }
   */          

            
            if (gamepad2.dpad_up) {
                hw.pwrLift(1);
            } else if (gamepad2.dpad_down) { 
                hw.pwrLift(-1);
            } else {                                                                                                                                                                       
                hw.pwrLift(0);
            }

            if (gamepad2.left_trigger > 0){
                hw.setClaw(.40); // .60 -> .40 28 jan 25
            } else if (gamepad2.right_trigger > 0){
                hw.setClaw(0); 
            }
            
            if (gamepad2.left_bumper) { 
                hw.backClaw.setPosition(1);
            } else if (gamepad2.right_bumper) { 
                hw.backClaw.setPosition(0);
            }
            
            if (hw.clock.seconds() - lastMonitorTime < hw.CLAW_SUCCESSIVE_WINDOW) {
                if (gamepad2.x && !lastClawInput) {
                    lastMonitorTime = 0;
                    hw.backClaw.setPosition(0);
                    hw.extend(.75);
                    hw.wrist.setPosition(1);
                }
            } else {
                if (gamepad2.x && !lastClawInput) {
                    lastMonitorTime = hw.clock.seconds();
                } else if (gamepad2.x) {
                    hw.extend(.75);
                    hw.wrist.setPosition(1);
                } else if (lastMonitorTime > 0) {
                    hw.extend(.75);
                    lastMonitorTime = 0;
                }
            }
            
            if (gamepad2.y) { 
                hw.extend(0); 
            } 
            
            if (-gamepad2.right_stick_y > 0) { 
                hw.wrist.setPosition(1); 
            } else if (-gamepad2.right_stick_y < 0) { 
                hw.wrist.setPosition(0);    
            }
            
            lastClawInput = gamepad2.x;
        }
    }
}
