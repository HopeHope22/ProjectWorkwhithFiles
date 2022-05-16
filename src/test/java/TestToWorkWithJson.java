import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestToWorkWithJson {
    @Test
    void jsonTest () {
        String pathFileJson = "src/test/resources/Testjson.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File (pathFileJson);
            MedicineIndex medicineIndex = mapper.readValue(file, MedicineIndex.class);
            assertThat(medicineIndex.antibiotics).isEqualTo(42525);
            assertThat(medicineIndex.painkillers).isEqualTo(456632);
            assertThat(medicineIndex.sprays).isEqualTo(19909);
            assertThat(medicineIndex.throat).isEqualTo(578968);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
