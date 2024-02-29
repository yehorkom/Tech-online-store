package com.techonlinestore.controller;
import com.techonlinestore.dto.TypeDto;
import com.techonlinestore.mapper.TypeMapper;
import com.techonlinestore.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<TypeDto> getTypeById(@PathVariable("typeId") int typeId){
		TypeDto typeDto = typeService.getTypeById(typeId);

		if (typeDto != null) {
			return new ResponseEntity<>(typeDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<TypeDto>> getTypes() {
		List<TypeDto> types = typeService.getAllTypes();
		return new ResponseEntity<>(types, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> createType(@RequestBody TypeDto typeDto) {
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
}
