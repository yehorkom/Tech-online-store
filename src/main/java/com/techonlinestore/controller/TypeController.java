package com.techonlinestore.controller;
import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.mapper.TypeMapper;
import com.techonlinestore.service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/type")
public class TypeController {
	private final TypeService typeService;
	private final TypeMapper typeMapper;

	@GetMapping("/{typeId}")
	public TypeDto getTypeById(@PathVariable("typeId") int typeId){
		log.info("Request to get type with ID: {}", typeId);
		TypeDto typeDto = typeService.getTypeById(typeId);

		if (typeDto != null) {
			log.info("Type with ID: {} found", typeId);
			return typeDto;
		} else {
			log.error("Type with ID: {} not found", typeId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found");
		}
	}

	@GetMapping("/all")
	public List<TypeDto> getAllTypes() {
		log.info("Request to get all types");
		return typeService.getAllTypes();
	}

	@PostMapping
	public ResponseEntity<Object> createType(@Valid @RequestBody TypeDto typeDto) {
		log.info("Request to create a new type: {}", typeDto);
		TypeDto createdType = typeService.createType(typeDto);

		if (createdType != null) {
			log.info("Type created successfully with ID: {}", typeMapper.toLastType(createdType).getTypeId());
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{typeId}")
				.buildAndExpand(typeMapper.toLastType(createdType).getTypeId()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			log.error("Failed to create type: {}", typeDto);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create type. Check your request.");
		}
	}

	@PutMapping("/{typeId}")
	public TypeDto updateType(@PathVariable("typeId") int typeId, @RequestBody TypeDto typeDto) {
		log.info("Request to update type with ID: {}. New data: {}", typeId, typeDto);
		TypeDto updatedType = typeService.updateType(typeId, typeDto);

		if (updatedType != null) {
			log.info("Type with ID: {} updated successfully", typeId);
			return updatedType;
		} else {
			log.error("Failed to update type with ID: {}", typeId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found");
		}
	}

	@DeleteMapping("/{typeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteType(@PathVariable("typeId") int typeId) {
		log.info("Request to delete type with ID: {}", typeId);
		typeService.deleteType(typeId);
		log.info("Type with ID: {} deleted successfully", typeId);
	}
}
