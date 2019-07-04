package strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import model.Logbook;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaveStrategy implements SavingStrategy {

    public Response create(Logbook logbook) throws IOException {

        File file = new File("logbook.log");
        FileWriter fileWriter = new FileWriter(file, true);
        ObjectMapper mapper = new ObjectMapper();

        SequenceWriter sequenceWriter = mapper.writer().writeValuesAsArray(fileWriter);
        sequenceWriter.write(logbook);
        sequenceWriter.close();
        return Response.ok("Logbook saved to file").build();
    }
}
