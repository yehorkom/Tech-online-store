package com.techonlinestore.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "types")
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int typeId;

	@Column(name = "type_name", nullable=false)
	private String typeName;
}
