package com.yu.crm.settings.service;

import com.yu.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DicService {
    Map<String, List<DicValue>> getAll();
}
