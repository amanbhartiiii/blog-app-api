package com.bharti.blog_app_api.controller;

import com.bharti.blog_app_api.payload.ApiResponse;
import com.bharti.blog_app_api.payload.CategoryDto;
import com.bharti.blog_app_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create Category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        System.out.println(categoryDto);
        System.out.println("Controller"+categoryDto.getCategoryTitle());
        System.out.println("Controller"+categoryDto.getCategoryDescription());
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    // Update Category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    // Delete Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        ApiResponse response = new ApiResponse("Category deleted successfully with id "+categoryId, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    // Get All
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }
}
