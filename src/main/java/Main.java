import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        String ffmpegPath = "src/main/resources/ffmpeg-7.0.2-amd64-static/ffmpeg";
        String inputFile = "src/main/resources/videos/IMG_6107.MOV";
        String outputFile = "src/main/resources/videos/IMG_6107.mp4";

        try {
            Process process = Runtime.getRuntime().exec(
                    new String[]{ffmpegPath, "-i", inputFile, "input.mov", "-c", "copy", outputFile}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
