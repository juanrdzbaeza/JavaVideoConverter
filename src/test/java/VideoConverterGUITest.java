import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VideoConverterGUITest {

    @Spy
    @InjectMocks
    private VideoConverterGUI videoConverterGUI;

    @BeforeEach
    public void setUp() {
        videoConverterGUI = new VideoConverterGUI();
    }


    @ParameterizedTest
    @CsvSource({
            "true,true",
            "true,false",
            "false,true",
            "false,false",
    })
    public void testConvertButtonIsEnabledWhenInputFieldAndOutputFieldNotEmpty(
            boolean isInputField,
            boolean isOutputField
    ) {

        // ARRANGE
        videoConverterGUI.inputField.setText(isInputField?"input_file.mov":"");
        videoConverterGUI.outputField.setText(isOutputField?"output_file.mov":"");

        // ACT
        videoConverterGUI.checkFields();

        //ASSERT
        if (isInputField && isOutputField) assertTrue(videoConverterGUI.convertButton.isEnabled());
        else assertFalse(videoConverterGUI.convertButton.isEnabled());
    }

}
