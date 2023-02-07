package org.firstinspires.ftc.teamcode.titans17576.vision;

import org.firstinspires.ftc.teamcode.titans17576.vision.vision;
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

public class cmyDetection extends OpenCvPipeline {
    Mat frame = new Mat();

    Mat tempOutput = new Mat();

    vision.location color = vision.location.NONE;

    private final Object sync = new Object();

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
        Core.inRange(frame, new Scalar(70 + 20, 50, 70), new Scalar(110, 255, 255), tempOutput);
        Imgproc.findContours(tempOutput, contours[0], hierarchies[0], Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);

        Core.inRange(frame, new Scalar(140 + 10, 50, 70), new Scalar(160 + 10, 255, 255), tempOutput);
        Imgproc.findContours(tempOutput, contours[1], hierarchies[1], Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);

        Core.inRange(frame, new Scalar(50, 50, 70), new Scalar(70, 255, 255), tempOutput);
        Imgproc.findContours(tempOutput, contours[2], hierarchies[2], Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);

        Scalar color = new Scalar(0, 0, 255);
            if (contours[0].size() + contours[1].size() + contours[2].size() > 0) {
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
                        this.color = vision.location.CYAN;
                        break;
                    case 1:
                        this.color = vision.location.MAGENTA;
                        break;
                    case 2:
                        this.color = vision.location.GREEN;
                        break;
                    default:
                        this.color = vision.location.NONE;
                }
                try {
                    Rect rectangle = Imgproc.boundingRect(contours[maxInd].get(maxValIdx));
                    Imgproc.rectangle(input, rectangle.tl(), rectangle.br(), new Scalar(255, 0, 0), 1);
                    Imgproc.drawContours(input, contours[maxInd], maxValIdx, new Scalar(0, 255, 0), 2, Imgproc.LINE_8,
                            hierarchies[maxInd], 2, new Point());
                } catch (Exception e) {
                    this.color = vision.location.GREEN;
                }
            } else {
                this.color = vision.location.GREEN;
            }
            return (input);
        }

    public String getAnalysis() {
        return color.toString();
    }
    public vision.location getColor() {
        synchronized (sync) {
            return color;
        }
    }

}
