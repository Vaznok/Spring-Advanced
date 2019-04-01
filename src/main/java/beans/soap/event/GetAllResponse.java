package beans.soap.event;

import beans.models.Event;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllResponse", namespace = "http://event.impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllResponse", namespace = "http://event.impl.service.core.spring.reshetnev.epam.com/")
public class GetAllResponse {

    @XmlElement(name = "return", namespace = "")
    private List<Event> _return;

    /**
     *
     * @return
     *     returns List<Event>
     */
    public List<Event> getReturn() {
        return this._return;
    }

    /**
     *
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<Event> _return) {
        this._return = _return;
    }

}
