package com.techonlinestore.controller;
import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.mapper.TypeMapper;
import com.techonlinestore.service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type")
public class TypeController {
	private final TypeService typeService;
	private final TypeMapper typeMapper;

	@GetMapping("/{typeId}")
	public TypeDto getTypeById(@PathVariable("typeId") int typeId){
		TypeDto typeDto = typeService.getTypeById(typeId);

		if (typeDto != null) {
			return typeDto;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found");
		}
	}

	@GetMapping("/all")
	public List<TypeDto> getAllTypes() {
		return typeService.getAllTypes();
	}

	@PostMapping
	public ResponseEntity<Object> createType(@Valid @RequestBody TypeDto typeDto) {
		var createdType = typeService.createType(typeDto);
		if (createdType == null){
			return ResponseEntity.badRequest().body("Unable to create type. Check your request.");
		}

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{typeId}")
			.buildAndExpand(typeMapper.toLastType(createdType).getTypeId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{typeId}")
	public TypeDto updateType(@PathVariable("typeId") int typeId, @RequestBody TypeDto typeDto) {
		TypeDto updatedType = typeService.updateType(typeId, typeDto);

		if (updatedType != null) {
			return typeDto;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@DeleteMapping("/{typeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBrand(@PathVariable("typeId") int typeId) {
		typeService.deleteType(typeId);
	}
}
