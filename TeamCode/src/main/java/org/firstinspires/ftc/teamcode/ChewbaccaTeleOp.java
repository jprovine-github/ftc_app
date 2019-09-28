package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Chewbacca: Teleop", group="Chewbacca")
public class ChewbaccaTeleOp extends OpMode {

    WarriorsBot robot = new WarriorsBot();

    private ElapsedTime runtime = new ElapsedTime();
    private float spinner_power;
    final double LIMIT_MAX_MOTOR_SPEED = 0.7;
    final double LIMIT_MAX_INTAKE_MOTOR_SPEED = 1.0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");
        updateTelemetry(telemetry);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        activateMecanumMotors();
        activateIntakeMotors();
        activateHooks();
//        activateClaw();
    }
    @Override
    public void stop() {

    }

    void activateMecanumMotors() {

        double lf = gamepad1.left_stick_x + gamepad1.right_stick_x - gamepad1.left_stick_y;
        double rf = -gamepad1.left_stick_x - gamepad1.right_stick_x - gamepad1.left_stick_y;
        double lb = gamepad1.left_stick_x - gamepad1.right_stick_x - gamepad1.left_stick_y;
        double rb = -gamepad1.left_stick_x + gamepad1.right_stick_x - gamepad1.left_stick_y;

        lf = Range.clip(lf, -1, 1);
        rf = Range.clip(rf, -1, 1);
        lb = Range.clip(lb, -1, 1);
        rb = Range.clip(rb, -1, 1);

        lf = scaleInput(lf * LIMIT_MAX_MOTOR_SPEED);
        rf = scaleInput(rf * LIMIT_MAX_MOTOR_SPEED);
        lb = scaleInput(lb * LIMIT_MAX_MOTOR_SPEED);
        rb = scaleInput(rb * LIMIT_MAX_MOTOR_SPEED);


        robot.leftFrontMotor.setPower(lf);
        robot.rightFrontMotor.setPower(rf);
        robot.leftBackMotor.setPower(lb);
        robot.rightBackMotor.setPower(rb);

    }

    void activateIntakeMotors() {

        double intake = gamepad2.left_trigger;

        intake = Range.clip(intake, 0, 1);
        intake = scaleInput(intake*LIMIT_MAX_INTAKE_MOTOR_SPEED);

        robot.intakeLeftMotor.setPower(intake);
        robot.intakeRightMotor.setPower(intake);
    }

    void activateHooks(){

        if (gamepad2.x) {
            robot.leftHookServo.setPosition(0);
            robot.rightHookServo.setPosition(1);
        } else {
            if (gamepad2.y) {
                robot.leftHookServo.setPosition(1);
                robot.rightHookServo.setPosition(0);
            }
        }
    }
/*
    void activateClaw() {
        if (gamepad2.left_bumper) {
            robot.leftClawServo.setPosition(0);
            robot.rightClawServo.setPosition(0);
        } else {
            if (gamepad2.right_bumper) {
                robot.leftClawServo.setPosition(1);
                robot.rightClawServo.setPosition(1);
            }
        }
    }
*/
    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.5, 0.6, 0.72, 0.85, 1.0, 1.0};
        int index = (int) (dVal * 16.0);

        if (index < 0) {
            index = -index;
        }

        if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;


        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;

    }

    double gscaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.025, 0.05, 0.07, 0.09, 0.095, 0.10, 0.11, 0.12, 0.135,
                0.15, 0.165, 0.18, 0.21, 0.24, 0.27, 0.30, 0.33, 0.36, 0.395, 0.43, 0.465, 0.5, 0.55,
                0.6, 0.66, 0.72, 0.785, 0.85, 0.925, 1.0, 1.0};
        int index = (int) (dVal * 32.0);

        if (index < 0) {
            index = -index;
        }

        if (index > 31) {
            index = 31;
        }

        double dScale = 0.0;


        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;

    }
    double sgscaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.005, 0.01, 0.015, 0.02, 0.025, 0.03, 0.035,
                0.04, 0.045, 0.05, 0.055, 0.06, 0.07, 0.08, 0.09,
                0.1, 0.11, 0.12, 0.13, 0.14, 0.15, 0.16, 0.17,
                0.18, 0.19, 0.2, 0.21, 0.22, 0.23, 0.24, 0.25,
                0.26, 0.27, 0.28, 0.29, 0.3, 0.325, 0.35, 0.375,
                0.4, 0.425, 0.45, 0.475, 0.5, 0.525, 0.55, 0.575,
                0.6, 0.625, 0.65, 0.675, 0.7, 0.725, 0.75, 0.775,
                0.8, 0.825, 0.85, 0.875, 0.9, 0.95, 1.0, 1.0 };
        int index = (int) (dVal * 64.0);

        if (index < 0) {
            index = -index;
        }

        if (index > 63) {
            index = 63;
        }

        double dScale = 0.0;


        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;

    }
    double ssgscaleInput(double dVal) {
        double[] scaleArray = {0.0000000000000000, 0.0000000000000036, 0.0000000000002274, 0.0000000000025899,
                0.0000000000145519, 0.0000000000555112, 0.0000000001657554, 0.0000000004179732,
                0.0000000009313226, 0.0000000018880577, 0.0000000035527137, 0.0000000062938490,
                0.0000000106083462, 0.0000000171482704, 0.0000000267502855, 0.0000000404676292,
                0.0000000596046448, 0.0000000857538716, 0.0000001208356935, 0.0000001671405450,
                0.0000002273736754, 0.0000003047024713, 0.0000004028063358, 0.0000005259291278,
                0.0000006789341569, 0.0000008673617380, 0.0000010974893030, 0.0000013763940707,
                0.0000017120182747, 0.0000021132369490, 0.0000025899282718, 0.0000031530464675,
                0.0000038146972656, 0.0000045882159192, 0.0000054882477798, 0.0000065308314312,
                0.0000077334843809, 0.0000091152913093, 0.0000106969948774, 0.0000125010890919,
                0.0000145519152284, 0.0000168757603127, 0.0000195009581603, 0.0000224579929728,
                0.0000257796054939, 0.0000295009017215, 0.0000336594641794, 0.0000382954657461,
                0.0000434517860413, 0.0000491741303712, 0.0000555111512313, 0.0000625145723667,
                0.0000702393153915, 0.0000787436289649, 0.0000880892205259, 0.0000983413905864,
                0.0001095691695809, 0.0001218454572758, 0.0001352471647351, 0.0001498553588455,
                0.0001657554093981, 0.0001830371387292, 0.0002017949739184, 0.0002221281015444,
                0.0002441406250000, 0.0002679417243634, 0.0002936458188287, 0.0003213727316940,
                0.0003512478579069, 0.0003834023341689, 0.0004179732115972, 0.0004551036309444,
                0.0004949430003762, 0.0005376471758076, 0.0005833786437961, 0.0006323067069935,
                0.0006846076721558, 0.0007404650407103, 0.0008000697018815, 0.0008636201283743,
                0.0009313225746155, 0.0010033912775533, 0.0010800486600147, 0.0011615255366202,
                0.0012480613222579, 0.0013399042431139, 0.0014373115502622, 0.0015405497358110,
                0.0016498947516084, 0.0017656322305051, 0.0018880577101754, 0.0020174768594963,
                0.0021542057074839, 0.0022985708747889, 0.0024509098077488, 0.0026115710149988,
                0.0027809143066406, 0.0029593110359691, 0.0031471443437567, 0.0033448094050961,
                0.0035527136788005, 0.0037712771593625, 0.0040009326314703, 0.0042421259270817,
                0.0044953161850572, 0.0047609761133494, 0.0050395922537518, 0.0053316652492050,
                0.0056377101136604, 0.0059582565045027, 0.0062938489975295, 0.0066450473644899,
                0.0070124268531799, 0.0073965784700967, 0.0077981092656501, 0.0082176426219327,
                0.0086558185430476, 0.0091132939479941, 0.0095907429661111, 0.0100888572350790,
                0.0106083462014794, 0.0111499374239123, 0.0117143768786718, 0.0123024292679794,
                0.0129148783307755, 0.0135525271560688, 0.0142161984988434, 0.0149067350985241,
                0.0156250000000000, 0.0163718768772050, 0.0171482703592574, 0.0179551063591568,
                0.0187933324050391, 0.0196639179739897, 0.0205678548284141, 0.0215061573549669,
                0.0224798629060388, 0.0234900321438012, 0.0245377493868091, 0.0256241229591616,
                0.0267502855422208, 0.0279173945288882, 0.0291266323804393, 0.0303792069859163,
                0.0316763520240784, 0.0330193273279100, 0.0344094192516877, 0.0358479410406041,
                0.0373362332029501, 0.0388756638848555, 0.0404676292475870, 0.0421135538474040,
                0.0438148910179734, 0.0455731232553411, 0.0473897626054622, 0.0492663510542890,
                0.0512044609204168, 0.0532056952502877, 0.0552716882159530, 0.0574041055153920,
                0.0596046447753906, 0.0618750359569766, 0.0642170417634134, 0.0666324580507514,
                0.0691231142409379, 0.0716908737374844, 0.0743376343436921, 0.0770653286834353,
                0.0798759246245027, 0.0827714257044967, 0.0857538715592909, 0.0888253383540452,
                0.0919879392167786, 0.0952438246745011, 0.0985951830919021, 0.1020442411125977,
                0.1055932641029358, 0.1092445565983589, 0.1130004627523249, 0.1168633667877863,
                0.1208356934512267, 0.1249199084692556, 0.1291185190077613, 0.1334340741336213,
                0.1378691652789712, 0.1424264267080311, 0.1471085359864901, 0.1519182144534490,
                0.1568582276959205, 0.1619313860258877, 0.1671405449599206, 0.1724886057013499,
                0.1779785156250000, 0.1836132687644785, 0.1893959063020247, 0.1953295170609159,
                0.2014172380004311, 0.2076622547133731, 0.2140678019261486, 0.2206371640014062,
                0.2273736754432321, 0.2342807214049039, 0.2413617381992026, 0.2486202138112823,
                0.2560596884140978, 0.2636837548863902, 0.2714960593332307, 0.2795003016091222,
                0.2877002358436584, 0.2960996709697419, 0.3047024712543589, 0.3135125568319133,
                0.3225339042401174, 0.3317705469584418, 0.3412265759491220, 0.3509061402007241,
                0.3608134472742677, 0.3709527638519070, 0.3813284162881700, 0.3919447911637555,
                0.4028063358418876, 0.4139175590272295, 0.4252830313273535, 0.4369073858167702,
                0.4487953186035156, 0.4609515893982952, 0.4733810220861869, 0.4860885053009021,
                0.4990789930016035, 0.5123575050522824, 0.5259291278036926, 0.5397990146778433,
                0.5539723867550492, 0.5684545333635391, 0.5832508126716220, 0.5983666522824116,
                0.6138075498311082, 0.6295790735848392, 0.6456868630450572, 0.6621366295524957,
                0.6789341568946838, 0.6960853019160176, 0.7135959951303903, 0.7314722413363803,
                0.7497201202349970, 0.7683457870499844, 0.7873554731506829, 0.8067554866774493,
                0.8265522131696343, 0.8467521161961180, 0.8673617379884035, 0.8883877000762688,
                0.9098367039259756, 0.9317155315810375, 0.9540310463055448, 1.0
        };
        int index = (int) (dVal * 256.0);

        if (index < 0) {
            index = -index;
        }

        if (index > 255) {
            index = 255;
        }

        double dScale = 0.0;


        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;

    }
}
