package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import java.util.ArrayList;
import java.util.List;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
@TeleOp
public class visionTest extends LinearOpMode
{
    public enum location{
        CYAN,
        MAGENTA,
        GREEN,
        NONE
    }
    OpenCvInternalCamera phoneCam;
    cmyDetection pipeline;

    @Override
    public void runOpMode()
    {
        /**
         * NOTE: Many comments have been omitted from this sample for the
         * sake of conciseness. If you're just starting out with EasyOpenCv,
         * you should take a look at {@link InternalCamera1Example} or its
         * webcam counterpart, {@link WebcamExample} first.
         */

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new cmyDetection();
        phoneCam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                phoneCam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }
    }

    public class cmyDetection extends OpenCvPipeline {
        Mat frame = new Mat();

        Mat tempOutput = new Mat();

        location color = location.NONE;

        Mat outPut = new Mat();

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.GaussianBlur(input, input, new Size(3.0, 3.0), 0.00);
            Imgproc.cvtColor(input, frame, Imgproc.COLOR_RGB2HSV);
            List<MatOfPoint>[] contours = new ArrayList[3];
            //List<MatOfPoint> contours = new ArrayList<>();


            Mat[] hierarchies = new Mat[3];

            for (int i = 0; i < 3; i++) {
                contours[i] = new ArrayList<>();
                hierarchies[i] = new Mat();

            }
            Core.inRange(frame, new Scalar(70+20, 50, 70), new Scalar(110, 255, 255), tempOutput);
            Imgproc.findContours(tempOutput, contours[0], hierarchies[0], Imgproc.RETR_TREE,
                    Imgproc.CHAIN_APPROX_SIMPLE);

            Core.inRange(frame, new Scalar(140+10, 50, 70), new Scalar(160+10, 255, 255), tempOutput);
            Imgproc.findContours(tempOutput, contours[1], hierarchies[1], Imgproc.RETR_TREE,
                    Imgproc.CHAIN_APPROX_SIMPLE);

            Core.inRange(frame, new Scalar(65, 50, 70), new Scalar(85, 255, 255), tempOutput);
            Imgproc.findContours(tempOutput, contours[2], hierarchies[2], Imgproc.RETR_TREE,
                    Imgproc.CHAIN_APPROX_SIMPLE);

            Scalar color = new Scalar(0, 0, 255);

            if (contours[0].size()+contours[1].size()+contours[2].size() > 0) {
                double maxVal = 0;
                int maxValIdx = 0;
                int maxInd = 0;
                for (int ind = 0; ind < 3; ind++) {
                    for (int contourIdx = 0; contourIdx < contours[ind].size(); contourIdx++) {
                        double contourArea = Imgproc.contourArea(contours[ind].get(contourIdx));
                        if (maxVal < contourArea) {
                            maxVal = contourArea;
                            maxValIdx = contourIdx;
                            maxInd = ind;
                        }
                    }
                }
                switch (maxInd) {
                    case 0:
                        this.color = location.CYAN;
                    case 1:
                        this.color = location.MAGENTA;
                    case 2:
                        this.color = location.GREEN;
                    default:
                        this.color = location.NONE;
                }
                try {
                    Rect rectangle = Imgproc.boundingRect(contours[maxInd].get(maxValIdx));
                    Imgproc.rectangle(input, rectangle.tl(), rectangle.br(), new Scalar(255, 0, 0), 1);
                    Imgproc.drawContours(input, contours[maxInd], maxValIdx, new Scalar(0, 255, 0), 2, Imgproc.LINE_8,
                            hierarchies[maxInd], 2, new Point());
                }
                catch (Exception e){
                    this.color = location.GREEN;
                }
            }
            else{
                this.color = location.GREEN;
            }
            return (input);
        }
        public String getAnalysis() {
            return color.toString();
        }
    }
}