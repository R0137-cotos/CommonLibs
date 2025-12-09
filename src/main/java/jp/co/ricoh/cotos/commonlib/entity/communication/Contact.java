package jp.co.ricoh.cotos.commonlib.entity.communication;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 問い合わせを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contact")
public class Contact extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
	@SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq", allocationSize = 1)
	@Schema(description = "問い合わせID (作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long estimationId;

	/**
	 * 親問い合わせ
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@Schema(description = "親問い合わせ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Contact parent;

	/**
	 * 子問い合わせリスト
	 */
	@Valid
	@OneToMany(mappedBy = "parent")
	@Schema(description = "子問い合わせリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<Contact> children;

	/**
	 * 送信者MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "送信者MoM社員ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contactFromEmpId;

	/**
	 * サービスカテゴリ
	 */
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * タイトル
	 */
	@Size(max = 255)
	@Schema(description = "タイトル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String title;

	/**
	 * 内容
	 */
	@Schema(description = "内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String content;

	/**
	 * 送信日時
	 */
	@Column(nullable = false)
	@Schema(description = "送信日時 (作成時不要)", readOnly = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendAt;

	/**
	 * 送信者氏名
	 */
	@Size(max = 255)
	@Schema(description = "送信者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactFromEmpName;

	/**
	 * 宛先
	 */
	@Valid
	@OneToMany(mappedBy = "contact")
	@NotNull
	@Schema(description = "宛先", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ContactTo> contactToList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.sendAt = super.getCreatedAt();
	}
}
