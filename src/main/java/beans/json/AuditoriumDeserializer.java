package beans.json;

import beans.models.Auditorium;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class AuditoriumDeserializer extends StdDeserializer<Auditorium> {

    private static final long serialVersionUID = 1L;

    protected AuditoriumDeserializer() {
        super(Auditorium.class);
    }

    @Override
    public Auditorium deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode auditoriumNode = jp.getCodec().readTree(jp);
        Auditorium auditorium = new Auditorium();
        if (auditoriumNode.has("id")) {
            auditorium.setId(auditoriumNode.get("id").asInt());
        }
        auditorium.setName(auditoriumNode.get("name").textValue());
        auditorium.setSeatsNumber(auditoriumNode.get("seatsNumber").asInt());
        auditorium.setVipSeats(auditoriumNode.get("vipSeats").textValue());
        return auditorium;
    }
}
