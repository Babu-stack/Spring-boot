package com.mobile.app.photoapp.album.upload.utility;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="imageapi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageApiEntity implements Serializable {
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false,length =50 )
	private String fileName;
	@Column(nullable =false,length=120)
	private String path;
	@Column(unique = true)
	private String url;
	@Column(nullable = false)
	private Blob image;
	
}
