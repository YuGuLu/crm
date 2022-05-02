package com.yu.crm.workbench.dao;

import com.yu.crm.workbench.domain.Clue;
import com.yu.crm.workbench.service.ClueService;

public interface ClueDao {


    int save(Clue clue);

    Clue detail(String id);

    Clue getById(String clueId);

    int delete(String clueId);
}
