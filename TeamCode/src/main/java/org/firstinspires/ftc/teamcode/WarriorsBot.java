package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WarriorsBot {

    /* Public OpMode members. */

    public DcMotor leftFrontMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor rightFrontMotor  = null;
    public DcMotor rightBackMotor = null;
    public DcMotor intakeLeftMotor = null;
    public DcMotor intakeRightMotor = null;
    public Servo leftHookServo = null;
    public Servo rightHookServo = null;
/*    public Servo leftClawServo = null;
    public Servo rightClawServo = null;
*/
    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public WarriorsBot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontMotor   = hwMap.dcMotor.get("lf");
        leftBackMotor = hwMap.dcMotor.get("lb");
        rightFrontMotor  = hwMap.dcMotor.get("rf");
        rightBackMotor = hwMap.dcMotor.get("rb");
        intakeLeftMotor = hwMap.dcMotor.get("il");
        intakeRightMotor = hwMap.dcMotor.get("ir");
        leftHookServo = hwMap.servo.get("lh");
        rightHookServo = hwMap.servo.get("rh");
/*        leftClawServo = hwMap.servo.get("lc");
        rightClawServo = hwMap.servo.get("rc");
*/
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeRightMotor.setDirection(DcMotor.Direction.FORWARD);


        // Set all motors to zero power
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        intakeLeftMotor.setPower(0);
        intakeRightMotor.setPower(0);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    public void stopAllMotors() {
        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightBackMotor.setPower(0);
        intakeLeftMotor.setPower(0);
        intakeRightMotor.setPower(0);
    }
}