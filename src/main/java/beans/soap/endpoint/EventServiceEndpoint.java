package beans.soap.endpoint;

import beans.services.EventService;
import beans.soap.event.GetAll;
import beans.soap.event.GetAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EventServiceEndpoint {

    private static final String NAMESPACE_URI = "http://soap.services/event-web-service";

    private final EventService eventService;

    @Autowired
    public EventServiceEndpoint(@Qualifier("eventServiceImpl") EventService eventService) {
        this.eventService = eventService;
    }

/*
    @PayloadRoot(localPart = "getById", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByIdResponse getEventById(@RequestPayload GetById getById) {
        GetByIdResponse getByIdResponse = new GetByIdResponse();
        getByIdResponse.setReturn(eventService.getById(getById.getArg0()));
        return getByIdResponse;
    }

    @PayloadRoot(localPart = "getByName", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByNameResponse getByName(@RequestPayload GetByName getByName) {
        GetByNameResponse getByNameResponse = new GetByNameResponse();
        getByNameResponse.setReturn(eventService.getByName(getByName.getArg0()));
        return getByNameResponse ;
    }*/

    @PayloadRoot(localPart = "getAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllResponse getAllEvents(@RequestPayload GetAll getAll) {
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setReturn(eventService.getAll());
        return getAllResponse ;
    }
}