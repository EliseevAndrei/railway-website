package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface StationDAO extends IDAO<Station> {
    List<Station> getUnusedStations(long trainId);
}
