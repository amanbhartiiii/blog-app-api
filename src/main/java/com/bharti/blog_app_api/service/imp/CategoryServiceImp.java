package com.bharti.blog_app_api.service.imp;

import com.bharti.blog_app_api.entity.Category;
import com.bharti.blog_app_api.exception.ResourceNotFoundException;
import com.bharti.blog_app_api.payload.CategoryDto;
import com.bharti.blog_app_api.repository.CategoryRepo;
import com.bharti.blog_app_api.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryRepo categoryRepo;


    // Create Category

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        Category savedCategory = categoryRepo.save(category);
        return categoryToDto(savedCategory);
    }


    // Update Category

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        categoryDto.setCategoryId(categoryId);
        Category updatedCategory = categoryRepo.save(dtoToCategory(categoryDto));
        return categoryToDto(updatedCategory);
    }


    // Delete Category

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        categoryRepo.delete(category);
    }


    // Get Category

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        return categoryToDto(category);
    }


    // Get All Category

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(category -> {
            CategoryDto categoryDto = categoryToDto(category);
            categoryDtoList.add(categoryDto);
        });
        return categoryDtoList;
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    private CategoryDto categoryToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
