package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IndexType generated by hbm2java
 */
@Entity
@Table(name = "index_type", catalog = "admin_nsmjsf")
public class IndexType implements java.io.Serializable {

	private Integer indexTypeId;
	private String indexTypeLabel;
	private Set<MarketIndex> marketIndexes = new HashSet<MarketIndex>(0);

	public IndexType() {
	}

	public IndexType(String indexTypeLabel) {
		this.indexTypeLabel = indexTypeLabel;
	}

	public IndexType(String indexTypeLabel, Set<MarketIndex> marketIndexes) {
		this.indexTypeLabel = indexTypeLabel;
		this.marketIndexes = marketIndexes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "index_type_id", unique = true, nullable = false)
	public Integer getIndexTypeId() {
		return this.indexTypeId;
	}

	public void setIndexTypeId(Integer indexTypeId) {
		this.indexTypeId = indexTypeId;
	}

	@Column(name = "index_type_label", nullable = false)
	public String getIndexTypeLabel() {
		return this.indexTypeLabel;
	}

	public void setIndexTypeLabel(String indexTypeLabel) {
		this.indexTypeLabel = indexTypeLabel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indexType")
	public Set<MarketIndex> getMarketIndexes() {
		return this.marketIndexes;
	}

	public void setMarketIndexes(Set<MarketIndex> marketIndexes) {
		this.marketIndexes = marketIndexes;
	}

}
