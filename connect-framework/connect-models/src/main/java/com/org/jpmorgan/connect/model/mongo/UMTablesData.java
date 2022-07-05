/**
 * Model class for user maintenance.
 */
package com.org.jpmorgan.connect.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Document(collection = "um_tables_data")
@Data
@NonNull
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UMTablesData {

	@Id
	@JsonProperty("rowId")
	String rowId;

	@JsonProperty("record")
	String record;

	@JsonProperty("recordTyp")
	String recordTyp;

	@JsonProperty("tblNm")
	String tblNm;

}