package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.IDAO;

public interface StationDAO extends IDAO<Station> {
    Station findStationByName(String name);
    /* List<Station> getUnusedStations(long trainId);*/
}
