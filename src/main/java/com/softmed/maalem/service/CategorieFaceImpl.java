package com.softmed.maalem.service;

import com.softmed.maalem.mapper.CategorieMapper;
import com.softmed.maalem.persistence.repository.CategoryRepository;
import com.softmed.maalem.presentation.dto.CategorieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CategorieFaceImpl implements CategorieFace {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategorieMapper categorieMapper;

    @Override
    public List<CategorieDto> getAllCategories() {
        return categorieMapper.mapToListDto(
                categoryRepository.findAll()
        );
    }
}
