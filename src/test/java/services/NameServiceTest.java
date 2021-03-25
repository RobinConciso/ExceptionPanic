package services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.NameRepository;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NameServiceImplTest {

    @Mock
    private NameRepository nameRepository;

    @InjectMocks
    private NameServiceImpl cut;

    @Test
    public void shouldUseLocalFile() {
        FileReader testData = new FileReader("path-to-local-file");
        BufferedReader reader = new BufferedReader(testData);
        String testName = reader.readLine();

        cut.addName(testName);

        verify(nameRepository).save(testName);
    }

    public void shouldTestNameReading() {

        String result = cut.getNameFromFile("filename", false);

        assertThat(result).contains("bla");
    }
}