package beans.soap.adapter;

import beans.models.Rate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class RateAdapter extends XmlAdapter<String, Rate> {
    @Override
    public Rate unmarshal(String v) throws Exception {
        return Rate.valueOf(v);
    }

    @Override
    public String marshal(Rate v) throws Exception {
        return v.name();
    }
}
