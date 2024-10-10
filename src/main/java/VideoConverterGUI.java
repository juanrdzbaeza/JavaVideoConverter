import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VideoConverterGUI extends JFrame implements ActionListener {

    final JTextField inputField;
    final JTextField outputField;
    final JButton convertButton;
    final JButton inputButton;
    final JButton outputButton;

    public VideoConverterGUI() {
        setTitle("Video Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel inputLabel = new JLabel("Input Video File:");
        inputField = new JTextField();
        inputButton = new JButton("Browse");
        inputButton.addActionListener(this);

        JLabel outputLabel = new JLabel("Output Video File:");
        outputField = new JTextField();
        outputButton = new JButton("Browse");
        outputButton.addActionListener(this);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        convertButton.setEnabled(false);

        add(inputLabel);
        add(inputField);
        add(inputButton);
        add(outputLabel);
        add(outputField);
        add(outputButton);
        add(new JLabel());
        add(convertButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Browse")) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    if (button == inputButton) {
                        inputField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    } else if (button == outputButton) {
                        outputField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                    checkFields();
                }
            }
            else if (button.getText().equals("Convert")) {
                String ffmpegPath = getffmpegPath();
                String inputFile = inputField.getText();
                String outputFile = outputField.getText();

                try {
                    Process process = Runtime.getRuntime().exec(new String[]{ffmpegPath, "-i", inputFile, "-c", "copy", outputFile});
                    int exitValue = process.waitFor();
                    if (exitValue == 0) {
                        JOptionPane.showMessageDialog(this, "Video conversion completed successfully.", "Conversion Result", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Video conversion failed with exit value: " + exitValue, "Conversion Result", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static String getffmpegPath() {
        String ffmpegPath;
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) { // Si estamos en Windows el binario para ffmpeg sera el siguiente
            ffmpegPath = "src/main/resources/ffmpeg-7.1-essentials_build-win/bin/ffmpeg.exe";
        } else if (osName.contains("linux")) { // Si estamos en Linux el binario para ffmpeg sera el siguiente
            ffmpegPath = "src/main/resources/ffmpeg-7.0.2-amd64-static-linux/ffmpeg";
        } else if (osName.contains("mac")) { // Si estamos en macOS el binario para ffmpeg sera el siguiente
            ffmpegPath = "src/main/resources/ffmpeg-.../bin/ffmpeg ?¿?¿ "; // Aquí debes proporcionar la ruta correcta para macOS
        } else {
            throw new RuntimeException("Unsupported operating system: " + osName);
        }

        return ffmpegPath;
    }

    void checkFields() {
        if (!inputField.getText().isEmpty() && !outputField.getText().isEmpty()) {
            convertButton.setEnabled(true);
        } else {
            convertButton.setEnabled(false);
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(VideoConverterGUI::new);
    }
}