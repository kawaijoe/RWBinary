package me.kawaijoe.rwbinary.controller.helper;

import com.google.common.base.Stopwatch;
import javafx.application.Platform;
import javafx.scene.control.Label;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.concurrent.TimeUnit;

public class StopWatchThread implements Runnable {

    private Stopwatch stopwatch;
    private Label timeField;

    public StopWatchThread(Label timeField) {
        this.timeField = timeField;
    }

    @Override
    public void run() {
        stopwatch = Stopwatch.createStarted();
        while(stopwatch.isRunning()) {
            long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            Platform.runLater(() -> timeField.setText(DurationFormatUtils.formatDuration(millis, "mm : ss")));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop() {
        stopwatch.stop();
    }

    public long getCurrentTime() {
        return stopwatch.elapsed(TimeUnit.MILLISECONDS);
    }

    public boolean isRunning() {
        return stopwatch.isRunning();
    }
}
