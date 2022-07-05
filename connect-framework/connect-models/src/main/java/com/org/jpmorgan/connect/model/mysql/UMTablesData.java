/**
 * Model class for user maintenance.
 */
package com.org.jpmorgan.connect.model.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "UM_TABLES_DATA")
@Data
@NonNull
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UMTablesData {

	@Id
	@Column(name = "ROW_ID")
	@JsonProperty("rowId")
	String rowId;

	@Column(name = "RECORD")
	@JsonProperty("record")
	String record;

	@Column(name = "RECORD_TYP")
	@JsonProperty("recordTyp")
	String recordTyp;

	@Column(name = "TBL_NM")
	@JsonProperty("tblNm")
	String tblNm;

}