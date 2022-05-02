package com.yu.crm.settings.dao;

import com.yu.crm.settings.domain.DicValue;

import java.util.List;

/**
 *
 */
public interface DicValueDao {
    List<DicValue> getListByCode(String code);
}
