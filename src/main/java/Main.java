import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String ffmpegPath = "src/main/resources/ffmpeg-7.0.2-amd64-static/ffmpeg";
        String inputFile = "src/main/resources/videos/IMG_6107.MOV";
        String outputFile = "src/main/resources/videos/IMG_6107.mp4";

        try {
            Process process = Runtime.getRuntime().exec(new String[]{ffmpegPath, "-i", inputFile, "-c", "copy", outputFile});
            int exitValue = process.waitFor();
            if (exitValue == 0) {
                System.out.println("Video conversion completed successfully.");
            } else {
                System.out.println("Video conversion failed with exit value: " + exitValue);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}